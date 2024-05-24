package compo;

import java.util.ArrayList;

import drawable.ColorObject;
import drawable.Oval;
import drawable.StyleObject;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

/**
 * The Robot class represents a robot within a canvas model.
 * The robot can move between various destinations within a factory and has specific behaviors.
 * 
 */
public class Robot extends Component {
    
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The speed of the robot.
     */
    private int speed;

    /**
     * The default color of the robot.
     */
    private final ColorObject color = new ColorObject(0, 0, 0);

    /**
     * The factory to which the robot belongs.
     */
    private Factory factory;

    /**
     * The list of destinations the robot must visit.
     */
    private ArrayList<Component> destinations;

    /**
     * The current destination the robot is heading towards.
     */
    private Component currentDestination = null;

    /**
     * The number of destinations the robot must visit.
     */
    private int numberOfDestinations;

    /**
     * The index of the current destination in the destinations list.
     */
    private int indexOfDestination;

    /**
     * Constructs a Robot with the specified properties.
     * 
     * @param name The name of the robot.
     * @param xCoord The x-coordinate of the robot.
     * @param yCoord The y-coordinate of the robot.
     * @param xSize The size of the robot (width and height will be the same).
     * @param speed The speed of the robot.
     * @param factory The factory to which the robot belongs.
     * @param destinations The list of destinations for the robot.
     * @param currentRoom The current room where the robot is located.
     */
    public Robot(String name, int xCoord, int yCoord, int xSize, int speed, Factory factory, ArrayList<Component> destinations, Room currentRoom) {
        super(name, xCoord, yCoord, xSize, xSize);
        this.speed = speed;
        this.factory = factory;
        this.factory.addCompo(this);
        this.room = currentRoom;
        this.destinations = calculateItinerary(destinations);
        this.numberOfDestinations = this.destinations.size();
        this.indexOfDestination = 0;
        this.currentDestination = this.destinations.get(0);
    }

    /**
     * Defines the behavior of the robot.
     * 
     * @param play If true, the robot performs its behavior; otherwise, it remains idle.
     */
    @Override
    public void behave(boolean play) {
        if (play) {
            float xMiddle = currentDestination.getxCoordinate() + (currentDestination.getWidth()) / 2;
            float yMiddle = currentDestination.getyCoordinate() + (currentDestination.getHeight()) / 2;
            if (Math.abs(xMiddle - this.getxCoordinate()) < speed && Math.abs(yMiddle - this.getyCoordinate()) < speed) {
                indexOfDestination = (indexOfDestination + 1) % numberOfDestinations;
                currentDestination = destinations.get(indexOfDestination);
            }
            move(currentDestination);
        }
    }

    /**
     * Moves the robot towards a specified destination.
     * 
     * @param desti The destination to move towards.
     */
    public void move(Component desti) {
        float xMiddle = desti.getxCoordinate() + (desti.getWidth()) / 2;
        float yMiddle = desti.getyCoordinate() + (desti.getHeight()) / 2;
        if (xMiddle > this.getxCoordinate()) {
            setxCoord(this.getxCoordinate() + speed);
        }
        if (xMiddle < this.getxCoordinate()) {
            setxCoord(this.getxCoordinate() - speed);
        }
        if (yMiddle > this.getyCoordinate()) {
            setyCoord(this.getyCoordinate() + speed);
        }
        if (yMiddle < this.getyCoordinate()) {
            setyCoord(this.getyCoordinate() - speed);
        }
        this.room = getGoodRoom();
    }

    /**
     * Adds a destination to the robot's list of destinations.
     * 
     * @param destination The destination to add.
     * @return True if the destination was added; false if it was already in the list.
     */
    public boolean addDestination(Component destination) {
        for (Component desti : destinations) {
            if (destination.equals(desti)) {
                return false;
            }
        }
        this.destinations.add(destination);
        this.numberOfDestinations += 1;
        return true;
    }

    /**
     * Gets the list of destinations.
     * 
     * @return The list of destinations.
     */
    public ArrayList<Component> getDestinations() {
        return destinations;
    }

    /**
     * Sets the list of destinations.
     * 
     * @param destinations The new list of destinations.
     */
    public void setDestinations(ArrayList<Component> destinations) {
        this.destinations = destinations;
        this.numberOfDestinations = destinations.size();
    }

    /**
     * Calculates the itinerary for the robot based on the provided destinations.
     * 
     * @param destinations The list of destinations.
     * @return The calculated itinerary.
     */
    public ArrayList<Component> calculateItinerary(ArrayList<Component> destinations) {
        ArrayList<Component> destinationsTmp = new ArrayList<>();
        Room roomTmp = this.room;
        Component destination0 = destinations.get(0);
        Component destinationTmp = destinations.get(0);

        int i = 0;

        while (i <= destinations.size()) {
            if (roomTmp == destinationTmp.getRoom()) {
                destinationsTmp.add(destinationTmp);
                i += 1;
                if (i < destinations.size()) {
                    destinationTmp = destinations.get(i);
                } else {
                    destinationTmp = destination0;
                }
            } else {
                if (roomTmp.findDoor(destinationTmp) != null) {
                    destinationsTmp.add(roomTmp.findDoor(destinationTmp));
                    destinationsTmp.add(destinationTmp);
                    roomTmp = destinationTmp.getRoom();
                    i += 1;
                    if (i < destinations.size()) {
                        destinationTmp = destinations.get(i);
                    } else {
                        destinationTmp = destination0;
                    }
                } else {
                    Room intermediateRoom = findIntermediateRoom(destinationTmp);
                    if (intermediateRoom != null) {
                        destinationsTmp.add(roomTmp.findDoor(intermediateRoom));
                        destinationsTmp.add(intermediateRoom);
                        roomTmp = intermediateRoom;
                    }
                }
            }
        }
        return destinationsTmp;
    }

    /**
     * Finds an intermediate room between the robot's current room and the destination's room.
     * 
     * @param destination The destination component.
     * @return The intermediate room, or null if none is found.
     */
    public Room findIntermediateRoom(Component destination) {
        if (isInRoom(destination.getRoom())) {
            return this.room;
        }
        for (Door doorCurrentRoom : this.room.getDoorList()) {
            for (Door doorDestinationRoom : destination.getRoom().getDoorList()) {
                if (doorCurrentRoom.equals(doorDestinationRoom)) {
                    return destination.getRoom();
                }
            }
        }
        for (Room intermediateRoom : this.factory.getRooms()) {
            if (intermediateRoom != this.room && intermediateRoom != destination.getRoom() && this.room.findDoor(intermediateRoom) != null) {
                return intermediateRoom;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of the robot's properties.
     * 
     * @return A string describing the robot.
     */
    @Override
    public String toString() {
        return "\t ROBOT " + this.getName() + " en x = " + xCoord + " y = " + yCoord + " _ " + xSize + " x " + xSize + " _ speed " + speed;
    }

    /**
     * Gets the shape of the robot.
     * 
     * @return The shape as a {@link Shape} object.
     */
    @Override
    public Shape getShape() {
        return new Oval(getWidth(), getHeight());
    }

    /**
     * Gets the style of the robot.
     * 
     * @return The style as a {@link Style} object.
     */
    @Override
    public Style getStyle() {
        return new StyleObject(color);
    }
}
