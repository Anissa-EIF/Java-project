package drawable;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.OvalShape;

/**
 * Oval represents an oval shape with a specified width and height.
 * 
 */
public class Oval implements OvalShape, Serializable {
    private static final long serialVersionUID = 1L;

    private int width;
    private int height;

    /**
     * Constructs an Oval with the specified width and height.
     * 
     * @param width  The width of the oval.
     * @param height The height of the oval.
     */
    public Oval(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Retrieves the width of the oval.
     * 
     * @return The width of the oval.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Retrieves the height of the oval.
     * 
     * @return The height of the oval.
     */
    @Override
    public int getHeight() {
        return height;
    }
}
