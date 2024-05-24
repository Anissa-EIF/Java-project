package compo;

import drawable.ColorObject;
import drawable.Rectangle;
import drawable.StyleObject;
import fr.tp.inf112.projects.canvas.model.RectangleShape;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

/**
 * The PowerSupplyStation class represents a power supply station in a canvas model.
 * It is a specific type of Component with a rectangular shape and a specific orientation.
 * The power supply station is associated with a specific room and factory.
 * 
 */
public class PowerSupplyStation extends Component implements RectangleShape {
    
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default color of the power supply station.
     */
    private final ColorObject color = new ColorObject(100, 200, 0);

    /**
     * Constructs a PowerSupplyStation with the specified properties.
     * 
     * @param name The name of the power supply station.
     * @param xCoord The x-coordinate of the power supply station.
     * @param yCoord The y-coordinate of the power supply station.
     * @param orientation The orientation of the power supply station ('v' for vertical, 'h' for horizontal).
     * @param room The room to which the power supply station belongs.
     */
    public PowerSupplyStation(String name, int xCoord, int yCoord, char orientation, Room room) {
        super(name, xCoord, yCoord, 0, 0);

        if (orientation == 'v') {
            super.xSize = 25;
            super.ySize = 50;
        } else if (orientation == 'h') {
            super.xSize = 50;
            super.ySize = 25;
        }

        this.room = room;
        this.room.addPss(this);
        this.factory = room.getFactory();
    }

    /**
     * 
     * @return A string describing the power supply station's coordinates.
     */
    @Override
    public String toString() {
        return "\t x = " + xCoord + " y = " + yCoord;
    }

    /**
     * Gets the shape of the power supply station.
     * 
     * @return The shape as a {@link Shape} object.
     */
    @Override
    public Shape getShape() {
        return new Rectangle(getWidth(), getHeight());
    }

    /**
     * Gets the style of the power supply station.
     * 
     * @return The style as a {@link Style} object.
     */
    @Override
    public Style getStyle() {
        return new StyleObject(color);
    }
}
