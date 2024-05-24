package drawable;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;

/**
 * StrokeObject represents a stroke with a specified color, thickness, and dash pattern.
 * 
 */
public class StrokeObject implements Stroke, Serializable {
    private static final long serialVersionUID = 1L;

    private ColorObject color;
    private float thickness;
    private float[] dashPattern;

    /**
     * Constructs a StrokeObject with the specified color, thickness, and dash pattern.
     * 
     * @param color       The color of the stroke.
     * @param thickness   The thickness of the stroke.
     * @param dashPattern The dash pattern of the stroke.
     */
    public StrokeObject(ColorObject color, float thickness, float[] dashPattern) {
        this.color = color;
        this.thickness = thickness;
        this.dashPattern = dashPattern;
    }

    /**
     * Constructs a StrokeObject with the default color, thickness, and dash pattern.
     */
    public StrokeObject() {
        this.color = new ColorObject(0, 0, 0);
        this.thickness = 2;
        this.dashPattern = new float[] { 5.0f, 2.0f };
    }
    
    public StrokeObject(float thickness) {
		super();
		this.color = new ColorObject(0,0,0);
		this.thickness = 2;
		this.dashPattern = new float[] {5.0f, 2.0f};
	}

    /**
     * Retrieves the color of the stroke.
     * 
     * @return The color of the stroke.
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Retrieves the thickness of the stroke.
     * 
     * @return The thickness of the stroke.
     */
    @Override
    public float getThickness() {
        return thickness;
    }

    /**
     * Retrieves the dash pattern of the stroke.
     * 
     * @return The dash pattern of the stroke.
     */
    @Override
    public float[] getDashPattern() {
        return dashPattern;
    }
}
