package compo;

import java.util.ArrayList;

import drawable.ColorObject;
import drawable.Rectangle;
import drawable.StrokeObject;
import drawable.StyleObject;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;

/**
 * The Room class represents a room within a factory, containing production areas, power supply stations,
 * and doors for access.
 * 
 * Author: Your Name
 */
public class Room extends Component {
    
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The list of production areas in the room.
     */
    private ArrayList<ProductionArea> paList;

    /**
     * The list of power supply stations in the room.
     */
    private ArrayList<PowerSupplyStation> pssList;

    /**
     * The list of doors in the room.
     */
    private ArrayList<Door> doorList;

    /**
     * The default color of the room.
     */
    private final ColorObject color = new ColorObject(192, 192, 192);

    /**
     * Constructs a room with the specified properties.
     * 
     * @param name The name of the room.
     * @param xCoord The x-coordinate of the room.
     * @param yCoord The y-coordinate of the room.
     * @param xSize The width of the room.
     * @param ySize The height of the room.
     * @param factory The factory to which the room belongs.
     */
    public Room(String name, int xCoord, int yCoord, int xSize, int ySize, Factory factory) {
        super(name, xCoord, yCoord, xSize, ySize);
        this.paList = new ArrayList<>();
        this.pssList = new ArrayList<>();
        this.doorList = new ArrayList<>();
        this.factory = factory;
        this.factory.addCompo(this);
        this.room = this;
    }

    /**
     * Constructs a room with the specified properties and existing lists of production areas, power supply
     * stations, and doors.
     * 
     * @param name The name of the room.
     * @param xCoord The x-coordinate of the room.
     * @param yCoord The y-coordinate of the room.
     * @param xSize The width of the room.
     * @param ySize The height of the room.
     * @param paList The list of production areas.
     * @param pssList The list of power supply stations.
     * @param doorList The list of doors.
     */
    public Room(String name, int xCoord, int yCoord, int xSize, int ySize, ArrayList<ProductionArea> paList, ArrayList<PowerSupplyStation> pssList, ArrayList<Door> doorList) {
        super(name, xCoord, yCoord, xSize, ySize);
        this.paList = paList;
        this.pssList = pssList;
        this.doorList = doorList;
    }

    /**
     * Gets the list of doors in the room.
     * 
     * @return The list of doors.
     */
    public ArrayList<Door> getDoorList() {
        return doorList;
    }

    /**
     * Sets the list of doors in the room.
     * 
     * @param doorList The new list of doors.
     */
    public void setDoorList(ArrayList<Door> doorList) {
        this.doorList = doorList;
        for (Door door : doorList) {
            model.addComponent(door);
        }
    }

    /**
     * Adds a door to the list of doors in the room.
     * 
     * @param door The door to add.
     */
    public void addDoor(Door door) {
        this.doorList.add(door);
        model.addComponent(door);
    }
    
    /**
     * Gets the list of production areas in the room.
     * 
     * @return The list of production areas.
     */
    public ArrayList<ProductionArea> getPaList() {
        return paList;
    }

    /**
     * Sets the list of production areas in the room.
     * 
     * @param paList The new list of production areas.
     */
    public void setPaList(ArrayList<ProductionArea> paList) {
        this.paList = paList;
        for (ProductionArea pa : paList) {
            model.addComponent(pa);
        }
    }

    /**
     * Adds a production area to the list of production areas in the room.
     * 
     * @param pa The production area to add.
     */
    public void addPa(ProductionArea pa) {
        this.paList.add(pa);
        model.addComponent(pa);
    }

    /**
     * Gets the list of power supply stations in the room.
     * 
     * @return The list of power supply stations.
     */
    public ArrayList<PowerSupplyStation> getPssList() {
        return pssList;
    }

    /**
     * Sets the list of power supply stations in the room.
     * 
     * @param pssList The new list of power supply stations.
     */
    public void setPssList(ArrayList<PowerSupplyStation> pssList) {
        this.pssList = pssList;
        for (PowerSupplyStation pss : pssList) {
            model.addComponent(pss);
        }
    }

    /**
     * Adds a power supply station to the list of power supply stations in the room.
     * 
     * @param pss The power supply station to add.
     */
    public void addPss(PowerSupplyStation pss) {
        this.pssList.add(pss);
        model.addComponent(pss);
    }

    /**
     * Returns a string representation of the room's properties, including its production areas,
     * power supply stations, and doors.
     * 
     * @return A string describing the room.
     */
    @Override
    public String toString() {
        String res = "\t SALLE " + name + " en x = " + xCoord + " y = " + yCoord + " et de taille " + xSize + " x " + ySize;

        if(paList.size() > 0) {
			res += "\n \t \t \t Aires de Production : \t" ;
			for(Component c : paList) {
				res += "\n \t \t \t" ;
				res += c.toString();
			}
		}
		if(pssList.size() > 0) {
			res += "\n \t \t \t Stations de Recharge : \t" ;
			for(Component c2 : pssList) {
				res += "\n \t \t \t" ;
				res += c2.toString();
			}
		}
		if(doorList.size() > 0) {
			res += "\n \t \t \t Portes : \t" ;
			for(Component c3 : doorList) {
				res += "\n \t \t \t" ;
				res += c3.toString();
			}
		}
		res += "\n";
		return res;
	}
	
    /**
     * 
     * @return A Door that leads to the destination wanted.
     */
	public Door findDoor(Component destination) {
		Room destinationRoom = destination.getRoom();
		for(Door door : destinationRoom.getDoorList()) {
			for(Room room : door.getRooms()) {
				if(room.equals(this)) {
					return door;
				}
			}
		}
		return null;
	}
	
    /**
     * Gets the shape of the robot.
     * 
     * @return The shape as a {@link Shape} object.
     */
	@Override
	public Shape getShape() {
		return new Rectangle(getWidth(), getHeight());
	}
	
	/**
     * Gets the style of the robot.
     * 
     * @return The style as a {@link Style} object.
     */
	@Override
	public Style getStyle() {
		return new StyleObject(color, new StrokeObject(1));
	}
}