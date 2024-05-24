package drawable;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.RectangleShape;

/**
 * Rectangle represents a rectangle shape with a specified width and height.
 * 
 */
public class Rectangle implements RectangleShape, Serializable {
    private static final long serialVersionUID = 1L;

    private int width;
    private int height;

    /**
     * Constructs a Rectangle with the specified width and height.
     * 
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Retrieves the width of the rectangle.
     * 
     * @return The width of the rectangle.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Retrieves the height of the rectangle.
     * 
     * @return The height of the rectangle.
     */
    @Override
    public int getHeight() {
        return height;
    }
}
