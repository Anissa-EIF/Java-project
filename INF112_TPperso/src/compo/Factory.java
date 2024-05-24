package compo;

import java.util.ArrayList;

import drawable.ColorObject;
import drawable.Rectangle;
import drawable.StyleObject;
import fr.tp.inf112.projects.canvas.model.RectangleShape;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

/**
 * The Factory class represents a factory in a canvas model.
 * It is a specific type of Component that contains and manages other components, including rooms.
 * The factory has a specific color, shape, and style, and can contain multiple components and rooms.
 * 
 */
public class Factory extends Component implements RectangleShape {
    
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default color of the factory.
     */
    private static final ColorObject color = new ColorObject(215, 50, 175);

    /**
     * List of components contained within this factory.
     */
    private ArrayList<Component> components;

    /**
     * List of rooms contained within this factory.
     */
    private ArrayList<Room> rooms = new ArrayList<Room>();

    /**
     * Constructs a Factory with the specified name and size.
     * 
     * @param name The name of the factory.
     * @param xSize The width of the factory.
     * @param ySize The height of the factory.
     */
    public Factory(String name, int xSize, int ySize) {
        super(name, 0, 0, xSize, ySize);
        this.components = new ArrayList<Component>();
        this.factory = this;
    }

    /**
     * Constructs a Factory with the specified name, size, and list of components.
     * 
     * @param name The name of the factory.
     * @param xSize The width of the factory.
     * @param ySize The height of the factory.
     * @param components The list of components to be contained in the factory.
     */
    public Factory(String name, int xSize, int ySize, ArrayList<Component> components) {
        super(name, 0, 0, xSize, ySize);
        this.components = components;
        this.factory = this;
    }

    /**
     * Adds a component to the factory. If the component is a room, it is also added to the list of rooms.
     * 
     * @param compo The component to add.
     * @return True if the component was successfully added, false if it already exists in the factory.
     */
    public boolean addCompo(Component compo) {
        for (Component c : components) {
            if (c.equals(compo)) {
                System.out.println("L'objet " + compo.getName() + " appartient déjà à l'usine");
                return false;
            }
        }
        components.add(compo);
        model.addComponent(compo);
        if (compo instanceof Room) {
            rooms.add((Room) compo);
        }
        return true;
    }

    /**
     * 
     * @return A string describing the factory.
     */
    @Override
    public String toString() {
        String res = "Usine " + name + " : " + xSize + " x " + ySize;
        for (Component c : components) {
            res += "\n \t";
            res += c.toString();
        }
        return res;
    }

    /**
     * Gets the list of components contained in the factory.
     * 
     * @return The list of components.
     */
    public ArrayList<Component> getComponents() {
        return components;
    }

    /**
     * Sets the list of components contained in the factory. If a component is a room, it is also added to the list of rooms.
     * 
     * @param components The list of components to set.
     */
    public void setComponents(ArrayList<Component> components) {
        this.components = components;
        for (Component compo : components) {
            if (compo instanceof Room) {
                rooms.add((Room) compo);
            }
        }
    }

    /**
     * Gets the list of rooms contained in the factory.
     * 
     * @return The list of rooms.
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Gets the shape of the factory.
     * 
     * @return The shape as a {@link Shape} object.
     */
    @Override
    public Shape getShape() {
        return new Rectangle(getWidth(), getHeight());
    }

    /**
     * Gets the style of the factory.
     * 
     * @return The style as a {@link Style} object.
     */
    @Override
    public Style getStyle() {
        return new StyleObject(color);
    }

    /**
     * Defines the behavior of the factory and its components. The behavior can be defined by subclasses.
     * 
     * @param play A boolean flag to determine the behavior state.
     */
    @Override 
    public void behave(boolean play) {
        for (Component c : components) {
            c.behave(play);
        }
    }
}
