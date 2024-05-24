package compo;

import drawable.ColorObject;
import drawable.Rectangle;
import drawable.StyleObject;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

/**
 * The ProductionMachine class represents a machine used in the production area within a canvas model.
 * It is a specific type of Component that is associated with a ProductionArea, Room, and Factory.
 * 
 */
public class ProductionMachine extends Component {
    
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default color of the production machine.
     */
    private final ColorObject color = new ColorObject(153, 0, 153);

    /**
     * The production area to which this machine belongs.
     */
    private ProductionArea pa;

    /**
     * Constructs a ProductionMachine with the specified properties.
     * 
     * @param name The name of the production machine.
     * @param xCoord The x-coordinate of the production machine.
     * @param yCoord The y-coordinate of the production machine.
     * @param xSize The width of the production machine.
     * @param ySize The height of the production machine.
     * @param pa The production area to which the machine belongs.
     */
    public ProductionMachine(String name, int xCoord, int yCoord, 
                             int xSize, int ySize, ProductionArea pa) {
        super(name, xCoord, yCoord, xSize, ySize);
        this.pa = pa;
        this.pa.addPm(this);
        this.room = this.pa.getRoom();
        this.factory = pa.getFactory();
    }

    /**
     * 
     * @return A string describing the production machine.
     */
    @Override
    public String toString() {
        return "\t \t x = " + xCoord + " y = " + yCoord;
    }

    /**
     * Gets the shape of the production machine.
     * 
     * @return The shape as a {@link Shape} object.
     */
    @Override
    public Shape getShape() {
        return new Rectangle(getWidth(), getHeight());
    }

    /**
     * Gets the style of the production machine.
     * 
     * @return The style as a {@link Style} object.
     */
    @Override
    public Style getStyle() {
        return new StyleObject(color);
    }
}
