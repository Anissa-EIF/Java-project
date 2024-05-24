package simu;

import java.util.ArrayList;
import model.*;
import compo.*;
import control.Controller;
import fr.tp.inf112.projects.canvas.controller.CanvasViewerController;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasPersistenceManager;
import fr.tp.inf112.projects.canvas.view.CanvasViewer;
import fr.tp.inf112.projects.canvas.view.FileCanvasChooser;


public class Simu {

	public static void main(String[] args) {
		
// Initialisation du modèle avec 1 usine
		Model myModel = new Model("M01", 600, 600);
		Factory myFactory = new Factory("My Factory", 500,500);
		myModel.addComponent(myFactory);
		 
   	   
// Ajout de 4 salles à l'usine
	    Room packaging = new Room("Packaging", 0, 0, 200, 300, myFactory); 
	    Room sorting = new Room("Sorting", 200, 0, 300, 300, myFactory);
	    Room stock = new Room("Stock room", 0, 300, 300, 200, myFactory); 
	    Room delivery = new Room("Delivery", 300, 300, 200, 200, myFactory); 
	  
	    
// Ajouts des portes à l'usine séparants leur salles respectives
	    ArrayList<Room> d1rooms = new ArrayList<Room>();
	    ArrayList<Room> d2rooms = new ArrayList<Room>();
	    ArrayList<Room> d3rooms = new ArrayList<Room>();
	    ArrayList<Room> d4rooms = new ArrayList<Room>();
	    
	    d1rooms.add(stock);					//d1 mène à la salle stock
	    d1rooms.add(packaging);				//d1 mène à la salle packaging
	    
	    d2rooms.add(packaging);				//d2 mène à la salle packaging
	    d2rooms.add(sorting);				//d2 mène à la salle sorting
	    
	    d3rooms.add(stock);					//d3 mène à la salle stock
	    d3rooms.add(delivery);				//d3 mène à la salle delivery
	    
	    d4rooms.add(sorting);				//d4 mène à la salle sorting
	    d4rooms.add(delivery);				//d4 mène à la salle delivery

	    // Instantiation des portes dans l'usine
	    Door d1 = new Door("D1", 25,300,75,1, d1rooms, myFactory);
	    Door d2 = new Door("D2", 200,125,1,75, d2rooms, myFactory);
	    Door d3 = new Door("D3", 300,400,1,75, d3rooms, myFactory);
	    Door d4 = new Door("D4", 325,300,75,1, d4rooms, myFactory);
	    
	    
// Initialisation des aires de productions et station de recharges dans leur salle respective
	    ProductionArea ap = new ProductionArea("Packaging Area", 10,125,100,100,packaging);
	    ProductionArea sd = new ProductionArea("Stock Delivery Area", 420,305,75,75,delivery);
	    ProductionArea as = new ProductionArea("Sorting Area", 225,25,100,100,sorting);
	    ProductionArea sg = new ProductionArea("Stock Gestion", 5,345,150,150, stock);
	    
	    PowerSupplyStation cp1 = new PowerSupplyStation("Charging Point 1", 175,75,'v', packaging);
	    PowerSupplyStation cp2 = new PowerSupplyStation("Charging Point 2", 250,300,'h', stock);
	    

	    
// Initialisation des machines de production dans leur aire de production respective
	    ProductionMachine pu = new ProductionMachine("Production Unit", 50,25,10,100, ap);
	    ProductionMachine bc = new ProductionMachine("Band Convoyer", 450, 0,45,350, as);
	    ProductionMachine st = new ProductionMachine("Stock Machine", 5,345,150,150, sg); 
	    
	    
// Initialisation des robots et de leurs destinations,
	    ArrayList<Component> r1Dest = new ArrayList<Component>();
	    r1Dest.add(ap);
	    r1Dest.add(st);
	    ArrayList<Component> r2Dest = new ArrayList<Component>();
	    r2Dest.add(bc);
	    r2Dest.add(as);
	    ArrayList<Component> r3Dest = new ArrayList<Component>();
	    r3Dest.add(st);
	    r3Dest.add(bc);
	    r3Dest.add(sd);

		 
	    Robot r1 = new Robot("R1", 100,250,10,5, myFactory, r1Dest, packaging);
	    Robot r2 = new Robot("R2", 375,150,10,5, myFactory, r2Dest, sorting);
	    Robot r3 = new Robot("R3", 275,400,10,5, myFactory, r3Dest, stock);
	    
	    
	    // Affichage de l'itinéraire du robot 3  :  test method CalculateItinerary(r3Dest)
	    // Reflexion alternative au TP 8
	    for (Component c:r3.getDestinations()) {
		    System.out.println(c.getName());
	    }
	       

	    
	    
// Affichage du modèle dans la console
	    //System.out.println(myModel.toString());
	    
	    
// Initialisation du MVC
	    FileCanvasChooser canvasChooser = new FileCanvasChooser("bin","binary" );
	    CanvasManager manager = new CanvasManager(canvasChooser);
	    Controller controller = new Controller((Canvas)myModel, (CanvasPersistenceManager)manager);
	    CanvasViewer view = new CanvasViewer(controller);
	    canvasChooser.setViewer(view);
	    controller.addObserver(view);
	    
	    
	}

}
