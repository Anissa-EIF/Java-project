package control;

import java.io.Serializable;
import java.util.ArrayList;

import model.Model; // Model class
import fr.tp.inf112.projects.canvas.controller.CanvasViewerController; // Interface implemented
import fr.tp.inf112.projects.canvas.controller.Observer; // Interface implemented
import fr.tp.inf112.projects.canvas.model.Canvas; // The canvas model
import fr.tp.inf112.projects.canvas.model.CanvasPersistenceManager; // Persistence manager

/**
 * The Controller class manages the interaction between the Canvas model and the user interface.
 * It implements the CanvasViewerController interface and handles animation control and observer management.
 * 
 */
public class Controller implements CanvasViewerController, Serializable {
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The canvas model managed by the controller.
     */
    private Canvas model;

    /**
     * List of observers registered with the controller.
     */
    private transient ArrayList<Observer> observers;

    /**
     * The persistence manager for saving and loading canvas data.
     */
    private CanvasPersistenceManager manager;

    /**
     * Constructs a Controller with the specified canvas model and persistence manager.
     * 
     * @param model The canvas model.
     * @param manager The persistence manager.
     */
    public Controller(Canvas model, CanvasPersistenceManager manager) {
        this.model = model;
        this.observers = new ArrayList<>();
        this.manager = manager;
    }

    // Methods implementing the CanvasViewerController interface

    @Override
    public boolean addObserver(Observer observer) {
        observers.add((Observer) observer);
        ((Model) model).addObserver((Observer) observer); 
        return true;
    }

    @Override
    public boolean removeObserver(Observer observer) {
        observers.remove(observer);
        ((Model) model).removeObserver((Observer) observer); 
        return true;
    }

    @Override
    public Canvas getCanvas() {
        return model;
    }

    @Override
    public void setCanvas(Canvas canvasModel) {
        this.model = canvasModel;
    }

    @Override
    public CanvasPersistenceManager getPersistenceManager() {
        return manager;
    }

    @Override
    public void startAnimation() {
        ((Model) model).setPlay(true); 
        while (((Model) model).isPlay()) { 
            ((Model) model).behave(true); 
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void stopAnimation() {
        ((Model) model).setPlay(false); 
    }

    @Override
    public boolean isAnimationRunning() {
        return ((Model) model).isPlay(); 
    }
}
