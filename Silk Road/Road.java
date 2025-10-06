
/**
 * Write a description of class Road here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Road
{
    private Rectangle sizeStreet;
    private Rectangle sizePlatform;
    private int xPosition;
    private int yPosition;
    private boolean isVisible;

    /**
     * Constructor for objects of class Road
     */
    public Road()
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        isVisible = false;
        sizeStreet = new Rectangle(70,35);
        sizePlatform = new Rectangle(70, 15);
        sizeStreet.changeSize(30, 40);
        sizeStreet.changeColor("black");
        sizePlatform.changeSize(20, 40);
        sizePlatform.changeColor("gray");
    }


    /**
     * Draw the road with the specified parameters
     */
    private void draw() {
        if(isVisible) {
            sizeStreet.makeVisible();
            sizePlatform.makeVisible();
        }
    }

    /**
     * Erase the road from the screen.
     */
    private void erase() {
        if(isVisible) {
            sizeStreet.makeInvisible();
            sizePlatform.makeInvisible();
        }
    }

    /**
     * Make this road visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }

    /**
     * Make this road invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /**
     * Returns the rectangle representing the platform size.
     * @return the platform size
     */
    public Rectangle getSizeStreet() {
        return sizeStreet;
    }

    /**
     * Returns the rectangle representing the street size.
     * @return the street size
     */    
    public Rectangle getSizePlatform() {
        return sizePlatform;
    }

    /**
     * Move the road vertically by a specified distance.
     * @param distance the distance to move vertically
     */
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        sizeStreet.moveVertical(distance);
        sizePlatform.moveVertical(distance);
        draw();
    }

    /**
     * Move the road horizontally by a specified distance.
     * @param distance the distance to move horizontally
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        sizeStreet.moveHorizontal(distance);
        sizePlatform.moveHorizontal(distance);
        draw();
    }
}
