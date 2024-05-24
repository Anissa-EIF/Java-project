package drawable;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;
import fr.tp.inf112.projects.canvas.model.Style;

/**
 * StyleObject represents the style of a drawable object, including its color and stroke.
 * 
 * Author: Your Name
 */
public class StyleObject implements Style, Serializable {
    private static final long serialVersionUID = 1L;

    private ColorObject color;
    private StrokeObject stroke;

    /**
     * Constructs a StyleObject with the specified color and stroke.
     * 
     * @param color  The color of the drawable object.
     * @param stroke The stroke of the drawable object.
     */
    public StyleObject(ColorObject color, StrokeObject stroke) {
        this.color = color;
        this.stroke = stroke;
    }

    /**
     * Constructs a StyleObject with the specified color and default stroke.
     * 
     * @param color The color of the drawable object.
     */
    public StyleObject(ColorObject color) {
        this.color = color;
        this.stroke = new StrokeObject();
    }

    /**
     * Constructs a StyleObject with the default color and stroke.
     */
    public StyleObject() {
        this.color = new ColorObject(0, 0, 0);
        this.stroke = new StrokeObject();
    }

    /**
     * Retrieves the background color of the drawable object.
     * 
     * @return The background color.
     */
    @Override
    public Color getBackgroundColor() {
        return color;
    }

    /**
     * Retrieves the stroke of the drawable object.
     * 
     * @return The stroke.
     */
    @Override
    public Stroke getStroke() {
        return stroke;
    }
}
