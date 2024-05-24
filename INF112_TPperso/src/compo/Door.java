package compo;

import java.util.ArrayList;

import drawable.ColorObject;
import drawable.Rectangle;
import drawable.StyleObject;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

/**
 * The Door class represents a door in a canvas model.
 * It is a specific type of Component that connects multiple rooms.
 * The door has a specific color, shape, and style.
 */
public final class Door extends Component {
    
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default color of the door (red).
     */
    private static final ColorObject color = new ColorObject(240, 16, 16);

    /**
     * List of rooms connected by this door.
     */
    private ArrayList<Room> rooms;

    /**
     * Constructs a Door with specified properties.
     * 
     * @param name The name of the door.
     * @param xCoord The x-coordinate of the door.
     * @param yCoord The y-coordinate of the door.
     * @param xSize The width of the door.
     * @param ySize The height of the door.
     * @param rooms The list of rooms connected by this door.
     * @param factory The factory that contains this door.
     */
    public Door(String name, int xCoord, int yCoord, int xSize, int ySize, ArrayList<Room> rooms, Factory factory) {
        super(name, xCoord, yCoord, xSize, ySize);
        this.rooms = rooms;
        this.factory = factory;
        for (Room room : rooms) {
            room.addDoor(this);
        }
    }

    /**
     * Gets the list of rooms connected by this door.
     * 
     * @return The list of rooms.
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /** 
     * @return A string describing the door's coordinates and size.
     */
    @Override
    public String toString() {
        return "\t x = " + xCoord + 
               " y = " + yCoord + " _ " + 
               xSize + " x " + ySize;
    }

    /**
     * Gets the shape of the door.
     * 
     * @return The shape as a {@link Shape} object.
     */
    @Override
    public Shape getShape() {
        return new Rectangle(getWidth(), getHeight());
    }

    /**
     * Gets the style of the door.
     * 
     * @return The style as a {@link Style} object.
     */
    @Override
    public Style getStyle() {
        return new StyleObject(color);
    }
}
