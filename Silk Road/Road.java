/**
 * Class Road - represents a road segment in the Silk Road simulation.
 * @author Juan Daniel Bogota Fuentes
 * @author Nicolas Felipe Bernal Gallo
 * @version 1.0
 */
public class Road{


    private Rectangle street;
    private int xPosition;
    private int yPosition;
    private boolean isVisible;

    /**
     * Constructor for objects of class Road with corner option
     */
    public Road(){
        street = new Rectangle(40, 15);
        street.changeSize(40, 40);
        street.changeColor("gray");
        int streetHeight = street.getHeight();
        int streetWidth = street.getWidth();
        int streetXPosition = street.getXPosition();
        int streetYPosition = street.getYPosition();
    }
        
    /**
     * Draw the road with the specified parameters
     */
    private void draw() {
        if(isVisible) {
            street.makeVisible();
        }
    }

    /**
     * Erase the road from the screen.
     */
    private void erase() {
        if(isVisible) {
            street.makeInvisible();
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
    public Rectangle getStreet() {
        return street;
    }

    /**
     * Move the road vertically by a specified distance.
     * @param distance the distance to move vertically
     */
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        street.moveVertical(distance);
        draw();
    }

    /**
     * Move the road horizontally by a specified distance.
     * @param distance the distance to move horizontally
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        street.moveHorizontal(distance);
        draw();
    }


    /**
     * Rotate the road to change its orientation.
     */
    public void rotateRoad(){
        erase();
        int streetHeight = street.getHeight();
        int streetWidth = street.getWidth();
        int streetXPosition = street.getXPosition();
        int streetYPosition = street.getYPosition();
        draw();
    }
}
