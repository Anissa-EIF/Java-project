package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasChooser;
import fr.tp.inf112.projects.canvas.model.impl.AbstractCanvasPersistenceManager;
import fr.tp.inf112.projects.canvas.view.FileCanvasChooser;

/**
 * CanvasManager serves as a manager for reading, saving, and deleting canvas models.
 * 
 */
public class CanvasManager extends AbstractCanvasPersistenceManager {
    private FileCanvasChooser canvasChooser;

    /**
     * Constructs a CanvasManager with the specified canvas chooser.
     * 
     * @param canvasChooser The canvas chooser.
     */
    public CanvasManager(FileCanvasChooser canvasChooser) {
        super(canvasChooser);
        this.canvasChooser = canvasChooser;
    }

    /**
     * Reads a canvas model from the file system.
     * 
     * @param canvasId The ID of the canvas.
     * @return The canvas model.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public Canvas read(String canvasId) throws IOException {
        try (FileInputStream fileInpStr = new FileInputStream(canvasId);
                InputStream bufInpStr = new BufferedInputStream(fileInpStr);
                ObjectInputStream objInpStream = new ObjectInputStream(bufInpStr)) {
            return (Canvas) objInpStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Persists a canvas model to the file system.
     * 
     * @param canvasModel The canvas model to persist.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void persist(Canvas canvasModel) throws IOException {
        String fileName = canvasModel.getId() + ".bin";
        try (OutputStream fileOutStream = new FileOutputStream(fileName);
                OutputStream bufOutStream = new BufferedOutputStream(fileOutStream);
                ObjectOutputStream objOutStream = new ObjectOutputStream(bufOutStream)) {
            objOutStream.writeObject(canvasModel);
        } 
    }

    /**
     * Deletes a canvas model from the file system.
     * 
     * @param canvasModel The canvas model to delete.
     * @return True if the canvas model is successfully deleted, otherwise false.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public boolean delete(Canvas canvasModel) throws IOException {
        String fileName = canvasModel.getId() + ".bin";
        File file = new File(fileName);
        return file.delete();
    }

    /**
     * Retrieves the canvas chooser associated with this manager.
     * 
     * @return The canvas chooser.
     */
    @Override
    public CanvasChooser getCanvasChooser() {
        return canvasChooser;
    }
}
