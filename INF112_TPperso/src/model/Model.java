package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import compo.*;
import drawable.StyleObject;
import fr.tp.inf112.projects.canvas.controller.Observable;
import fr.tp.inf112.projects.canvas.controller.Observer;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.Figure;
import fr.tp.inf112.projects.canvas.model.Style;

// Represents the model of a canvas
public class Model implements Canvas, Observable, Serializable {
    private static final long serialVersionUID = 1L;
    
    // List of components on the canvas
    private ArrayList<Component> compoList;
    // Width and height of the canvas
    private int xSize;
    private int ySize;
    // Name of the model
    private String name;
    // List of observers monitoring changes in the model
    private transient ArrayList<Observer> observers;
    // List of factories on the canvas
    private ArrayList<Factory> factories;
    // Indicates whether animation is playing
    private boolean play;
    
    // Constructor
    public Model(String id, int xSize, int ySize) {
        super();
        this.name = id;
        this.xSize = xSize;
        this.ySize = ySize;
        this.compoList = new ArrayList<Component>();
        this.factories = new ArrayList<Factory>();
        this.observers = new ArrayList<Observer>();
    }
    
    // Getters and setters
    public ArrayList<Component> getCompoList() {
        return compoList;
    }
    
    public void setCompoList(ArrayList<Component> compoList) {
        this.compoList = compoList;
    }
    
    // Add a component to the canvas
    public boolean addComponent(Component compo) {
        // Check if the component fits within the canvas boundaries
        if (compo.getxCoordinate() + compo.getWidth() > this.xSize) {
            return false;
        }
        if (compo.getyCoordinate() + compo.getHeight() > this.ySize) {
            return false;
        }
        // If it's a factory, add it to the factory list; otherwise, add it to the component list
        if (compo instanceof Factory) {
            factories.add((Factory) compo);
        } else {
            compoList.add(compo);
        }
        compo.setModel(this);
        return true;
    }

    // String representation of the model
    @Override
    public String toString() {
        String res = "Ce modèle est composé de " + this.factories.size() + " usines : ";
        for (Factory f : factories) {
            res += "\n \t";
            res += f.toString();
        }
        return res + "\n ___________________________________________________________";
    }
    
    public ArrayList<Factory> getFactories() {
        return factories;
    }
    
    // Getters and setters for canvas properties
    @Override
    public String getId() {
        return name;
    }
    
    @Override
    public void setId(String id) {
        this.name = id;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    // Methods required by the Canvas interface
    @Override
    public int getWidth() {
        return xSize;
    }
    
    @Override
    public int getHeight() {
        return ySize;
    }
    
    @Override
    public Collection<Figure> getFigures() {
        return (Collection) compoList;
    }
    
    @Override
    public Style getStyle() {
        return new StyleObject();
    }
    
    // Methods related to observers
    @Override
    public boolean addObserver(Observer observer) {
        observers.add(observer);
        return true;
    }
    
    @Override
    public boolean removeObserver(Observer observer) {
        observers.remove(observer);
        return true;
    }
    
    // Notify all observers of model changes
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.modelChanged();
        }
    }
    
    // Methods related to animation behavior
    public void behave(boolean play) {
        for (Factory f : factories) {
            f.behave(play);
        }
    }
    
    public boolean isPlay() {
        return play;
    }
    
    public void setPlay(boolean play) {
        if (this.play != play) {
            this.play = play;
            for (Factory f : factories) {
                f.behave(play);
            }
            notifyObservers();
        }
    }
}
