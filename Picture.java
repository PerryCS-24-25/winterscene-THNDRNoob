
import java.io.Console;

import javax.swing.Timer;

/**
 * Draw a pretty picture composed of shape objects on a canvas
 * 
 * @author: (Your name)
 * @version: (Date)
 * 
 */
public class Picture
{
    // Private member (instance) variables
    private Canvas pic;
    private Rect ground;
    private Circle[] snow;
    private Rect HouseMain;
    private Triangle HouseRoof;
    private Rect HouseChimney;
    private Rect Window1;
    private Rect Window2;
    private Circle PersonHead;
    private Triangle PersonBody;


    private Circle ball; // The sample ball to animate
    private int dx = 5; // Speed in x direction for the sample ball
    private int dy = -5; // Speed in y direction for the sample ball
    
    public Picture()
    {
        // Get a reference to the canvas for this drawing and set its title.
        pic = Canvas.getCanvas();
        pic.setTitle("Bouncing Ball");
        pic.setBackgroundColor("blue");
        
        // Turn off automatic redrawing
        pic.pause(true);

        snow = new Circle[400];
        HouseMain = new Rect();
        HouseChimney = new Rect();
        HouseRoof = new Triangle();
        Window1 = new Rect();
        Window2 = new Rect();
        PersonHead = new Circle();
        PersonBody = new Triangle();

        HouseMain.changeSize(125, 150);
        HouseMain.changeColor("#202020");
        HouseMain.setPosition(100, 450);
        HouseMain.makeVisible();

        Window1.changeSize(40, 25);
        Window1.changeColor("#904000");
        Window1.setPosition(120, 490);
        Window1.makeVisible();

        Window2.changeSize(40, 25);
        Window2.changeColor("#904000");
        Window2.setPosition(205, 490);
        Window2.makeVisible();

        HouseRoof.changeSize(50, 175);
        HouseRoof.changeColor("#202020");
        HouseRoof.setPosition(173, 400);
        HouseRoof.makeVisible();

        HouseChimney.changeSize(50, 35);
        HouseChimney.changeColor("#202020");
        HouseChimney.setPosition(200, 400);
        HouseChimney.makeVisible();

        PersonHead.changeSize(20);;
        PersonHead.changeColor("#202020");
        PersonHead.setPosition(208, 500);
        PersonHead.makeVisible();

        PersonBody.changeSize(30, 20);
        PersonBody.changeColor("#202020");
        PersonBody.setPosition(218, 500);
        PersonBody.makeVisible();

        for (int i = 0; i < snow.length; i++) {
            snow[i] = new Circle(((int) (Math.random() * 850)), ((int) (Math.random() * 600) - 50), 2, "white", true);
        }

        ground = new Rect();
        ground.changeSize(25, 850);
        ground.setPosition(0, 575);
        ground.makeVisible();
        ground.changeColor("white");
        ball = new Circle();
        ball.changeColor("red");
        // ball.makeVisible();
        
        // Show the initial picture
        pic.redraw();
    }
    
    /**
     * Update the screen to create a new frame for the animation
     */
    public void update() 
    {
        int x = ball.getX();
        int y = ball.getY();
        
        // Calculate a new position for the ball
        x = x + dx;
        y = y + dy;        
        
        // Bounce off the edges
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        else if (x + ball.getDiameter() > pic.getWidth()) {
            dx = -dx;
            x = pic.getWidth() - ball.getDiameter();
        }
        
        if (y < 0) {
            y = 0;
            dy = -dy;
        }        
        else if (y + ball.getDiameter() > pic.getHeight()) {
            y = pic.getHeight() - ball.getDiameter();
            dy = -dy;
        }
        
        // Move the ball
        ball.setPosition(x, y);

        for (int i = 0; i < snow.length; i++) {
            Circle currentsnow = snow[i];
            if (currentsnow.getY() > 575 || currentsnow.getX() > 850){
                //System.out.println("reset!!!");
                //currentsnow.setY((int) (Math.random() * 850));
                currentsnow.setY((int) (Math.random() * 10) - 20);
                currentsnow.setX(((int) (Math.random() * 850) * 2 - 850));
            }
            else{
                currentsnow.setX(currentsnow.getX() + 5);
                currentsnow.setY(currentsnow.getY() + 5);
            }
        }
        
        // Update the screen
        pic.redraw();
    }
    
    /**
     * This main method prepares and regularly updates a picture.
     */
    public static void main(String[] args)
    {
        Picture pic = new Picture();
        
        // update the screen every 33ms (30 times / second)
        new Timer(33, e->pic.update()).start();        
    }
   
}