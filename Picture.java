
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

        snow = new Circle[100];

        for (int i = 0; i < snow.length; i++) {
            snow[i] = new Circle(((int) (Math.random() * 850)), ((int) (Math.random() * 100)), 2, "white", true);
        }

        ground = new Rect();
        ground.changeSize(25, 850);
        ground.setPosition(0, 575);
        ground.makeVisible();
        ground.changeColor("white");
        ball = new Circle();
        ball.changeColor("red");
        ball.makeVisible();
        
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
            if (currentsnow.getY() > 575){
                System.out.println("reset!!!");
                currentsnow.setY(0);
            }
            else{
                currentsnow.setY(currentsnow.getY() + 3);
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