package compo;

import java.io.Serializable;

import drawable.ColorObject;
import drawable.StyleObject;
import fr.tp.inf112.projects.canvas.model.Figure;
import fr.tp.inf112.projects.canvas.model.Style;
import model.Model;

/**
 * The abstract Component class represents a graphical component in a canvas model.
 * It implements the Figure interface and Serializable interface to allow the component
 * to be rendered and serialized.
 * The class encapsulates properties like coordinates, size, color, and associations with a model, room, and factory.
 */
public abstract class Component implements Figure, Serializable {
    
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;
    
    // Properties of the component
    String name;
    int xCoord;
    int yCoord;
    int xSize;
    int ySize;
    ColorObject color;
    Model model;
    Room room;
    Factory factory;

    /**
     * Constructs a new Component with specified properties.
     * 
     * @param name The name of the component.
     * @param xCoord The x-coordinate of the component, offset by 50.
     * @param yCoord The y-coordinate of the component, offset by 50.
     * @param xSize The width of the component.
     * @param ySize The height of the component.
     */
    public Component(String name, int xCoord, int yCoord, int xSize, int ySize) {
        super();
        this.name = name;
        this.xCoord = xCoord + 50;
        this.yCoord = yCoord + 50;
        this.xSize = xSize;
        this.ySize = ySize;
        this.model = null;
        this.room = null;
    }

    /**
     * Gets the x-coordinate of the component.
     * 
     * @return The x-coordinate.
     */
    public int getxCoordinate() {
        return xCoord;
    }

    /**
     * Sets the x-coordinate of the component and notifies the model observers.
     * 
     * @param xCoord The new x-coordinate.
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
        model.notifyObservers();
    }

    /**
     * Gets the y-coordinate of the component.
     * 
     * @return The y-coordinate.
     */
    public int getyCoordinate() {
        return yCoord;
    }

    /**
     * Sets the y-coordinate of the component and notifies the model observers.
     * 
     * @param yCoord The new y-coordinate.
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
        model.notifyObservers();
    }

    /**
     * Gets the width of the component.
     * 
     * @return The width.
     */
    public int getWidth() {
        return xSize;
    }

    /**
     * Gets the height of the component.
     * 
     * @return The height.
     */
    public int getHeight() {
        return ySize;
    }

    /**
     * Gets the name of the component.
     * 
     * @return The name.
     */
    public String getName() {
        return name;
    }    
    
    /**
     * Gets the factory associated with the component.
     * 
     * @return The factory.
     */
    public Factory getFactory() {
        return factory;
    }

    /**
     * Sets the factory associated with the component.
     * 
     * @param factory The factory to associate.
     */
    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    /**
     * Gets the room associated with the component.
     * 
     * @return The room.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the room associated with the component.
     * 
     * @param room The room to associate.
     */
    public void setRoom(Room room) {
        this.room = room;
    }
    
    /**
     * Defines the behavior of the component. The behavior can be defined by subclasses.
     * 
     * @param play A boolean flag to determine the behavior state.
     */
    public void behave(boolean play) {
        // To be implemented by subclasses
    }
    
    /**
     * Determines the appropriate room for the component.
     * If the component is not in a room, it searches for a suitable room in the model.
     * 
     * @return The appropriate room for the component, or null if no suitable room is found.
     */
    public Room getGoodRoom() {
        if (room == null) {
            for (Factory factory : model.getFactories()) {
                for (Room r : factory.getRooms()) {
                    if (isInRoom(r)) {
                        return r;
                    } else {
                        return null;
                    }
                }
            }
        }
        if (!isInRoom(this.room)) {
            for (Factory factory : model.getFactories()) {
                for (Room r : factory.getRooms()) {
                    if (isInRoom(r)) {
                        return r;
                    } else {
                        return null;
                    }
                }
            }
        }
        return this.room;
    }
    
    /**
     * Checks if the component is inside the given room.
     * 
     * @param room The room to check.
     * @return True if the component is inside the room, false otherwise.
     */
    public boolean isInRoom(Room room) {
        return this.getxCoordinate() >= room.getxCoordinate() 
            && this.getxCoordinate() <= room.getxCoordinate() + room.getWidth()
            && this.getyCoordinate() >= room.getyCoordinate() 
            && this.getyCoordinate() <= room.getyCoordinate() + room.getHeight();
    }

    /**
     * Gets the style of the component.
     * 
     * @return The style as a {@link Style} object.
     */
    public Style getStyle() {
        return new StyleObject(color);
    }
    
    /**
     * Sets the model associated with the component.
     * 
     * @param model The model to associate.
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * 
     * @return A string describing the component's coordinates and size.
     */
    @Override
    public String toString() {
        return "Composant situé en x = " + xCoord + 
               " y = " + yCoord + " et de taille " + 
               xSize + " x " + ySize;
    }
}
