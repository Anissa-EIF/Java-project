package compo;

import java.util.ArrayList;

import drawable.ColorObject;
import drawable.Rectangle;
import drawable.StyleObject;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

/**
 * The ProductionArea class represents an area designated for production within a canvas model.
 * It is a specific type of Component that can contain multiple ProductionMachine objects.
 * The production area is associated with a specific room and factory.
 * 
 */
public class ProductionArea extends Component {
    
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * List of production machines in the production area.
     */
    private ArrayList<ProductionMachine> prodMachines;

    /**
     * Default color of the production area.
     */
    private final ColorObject color = new ColorObject(50, 255, 255);

    /**
     * Constructs a ProductionArea with the specified properties.
     * 
     * @param name The name of the production area.
     * @param xCoord The x-coordinate of the production area.
     * @param yCoord The y-coordinate of the production area.
     * @param xSize The width of the production area.
     * @param ySize The height of the production area.
     * @param room The room to which the production area belongs.
     * @param prodMachines The list of production machines in the production area.
     */
    public ProductionArea(String name, int xCoord, int yCoord, 
                          int xSize, int ySize, Room room,
                          ArrayList<ProductionMachine> prodMachines) {
        
        super(name, xCoord, yCoord, xSize, ySize);
        this.prodMachines = prodMachines;
        this.room = room;
        this.room.addPa(this);
        this.factory = room.getFactory();
    }

    /**
     * Constructs a ProductionArea with the specified properties.
     * 
     * @param name The name of the production area.
     * @param xCoord The x-coordinate of the production area.
     * @param yCoord The y-coordinate of the production area.
     * @param xSize The width of the production area.
     * @param ySize The height of the production area.
     * @param room The room to which the production area belongs.
     */
    public ProductionArea(String name, int xCoord, int yCoord, 
                          int xSize, int ySize, Room room) {

        super(name, xCoord, yCoord, xSize, ySize);
        this.prodMachines = new ArrayList<ProductionMachine>();
        this.room = room;
        this.room.addPa(this);
    }

    /**
     * Gets the list of production machines in the production area.
     * 
     * @return The list of production machines.
     */
    public ArrayList<ProductionMachine> getPm() {
        return prodMachines;
    }

    /**
     * Sets the list of production machines in the production area.
     * 
     * @param prodMachines The list of production machines.
     */
    public void setPm(ArrayList<ProductionMachine> prodMachines) {
        this.prodMachines = prodMachines;
        for (ProductionMachine pm : prodMachines) {
            model.addComponent(pm);
        }
    }

    /**
     * Adds a production machine to the production area.
     * 
     * @param pm The production machine to add.
     */
    public void addPm(ProductionMachine pm) {
        this.prodMachines.add(pm);
        model.addComponent(pm);
    }

    /**
     * 
     * @return A string describing the production area and its machines.
     */
    @Override
    public String toString() {
        String res = "\t x = " + xCoord + 
                     " y = " + yCoord + 
                     "(" + this.getWidth() + ":" + this.getHeight() + ")";
        res += "\n \t \t \t \t \t";
        if (prodMachines.size() > 0) {
            res += "Machines de Production";
            for (ProductionMachine pm : prodMachines) {
                res += "\n \t \t \t \t";
                res += pm.toString();
            }
        }
        return res;
    }

    /**
     * Gets the shape of the production area.
     * 
     * @return The shape as a {@link Shape} object.
     */
    @Override
    public Shape getShape() {
        return new Rectangle(getWidth(), getHeight());
    }

    /**
     * Gets the style of the production area.
     * 
     * @return The style as a {@link Style} object.
     */
    @Override
    public Style getStyle() {
        return new StyleObject(color);
    }
}
