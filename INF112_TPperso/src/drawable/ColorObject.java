package drawable;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Color;

/**
 * ColorObject represents a color with red, green, and blue components.
 * 
 */
public class ColorObject implements Color, Serializable {
    private static final long serialVersionUID = 1L;

    private int red;
    private int green;
    private int blue;

    /**
     * Constructs a ColorObject with the specified red, green, and blue components.
     * 
     * @param red   The red component of the color.
     * @param green The green component of the color.
     * @param blue  The blue component of the color.
     */
    public ColorObject(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Retrieves the red component of the color.
     * 
     * @return The red component.
     */
    @Override
    public int getRedComponent() {
        return red;
    }

    /**
     * Retrieves the green component of the color.
     * 
     * @return The green component.
     */
    @Override
    public int getGreenComponent() {
        return green;
    }

    /**
     * Retrieves the blue component of the color.
     * 
     * @return The blue component.
     */
    @Override
    public int getBlueComponent() {
        return blue;
    }
}
