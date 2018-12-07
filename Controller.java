package jac444.wk5;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This is the Controller class that will handle all the events that will happen on the GUI
 *
 * @author sahil
 */
public class Controller {
    @FXML
    private TextField fileName;
    @FXML
    private TextField name;
    @FXML
    private TextField course;
    @FXML
    private TextField grade;

    @FXML
    private TableView<Student> tblStudents;


    /**
     * Clears the fields of the GUI and makes them editable
     *
     * @param event
     */
    @FXML
    private void handleNewStudent(MouseEvent event) {
        clearFields();
        makeEditableFields();
    }
/**
 * Adds the student object to the tableview
 * @param event
 */
    @FXML
    private void handleAddStudent(MouseEvent event) {
    	
      if (grade.getText().trim().equals("")) {
         showError(new Exception("Please enter grade."));
          return;
          }
      /**
       * checks for the value if it is numeric or not
       */
      if (!isNumeric(grade.getText()))
    		  {
    	        showError(new Exception("Please enter valid grade."));
    	        return;
    		  }
        Student student=null;
		try {
			student = new Student(generateUniqueId(), name.getText().trim(), course.getText().trim(), Integer.parseInt(grade.getText()));
		}  catch (Exception e) {
			showError(e);
			return;
		}
        tblStudents.getItems().add(student);
        clearFields();
    }
/**
 * generates a stream of unique ID
 * @return ID
 */
    private Integer generateUniqueId() {
        if (tblStudents.getItems().size() == 0)
            return 1;
        return tblStudents.getItems().stream().max(Comparator.comparing(Student::getId)).get().getId() + 1;
    }

    /**
     * Saves the Object into the file and shows error if an invalid input is received
     *
     * @param event
     */
    @FXML
    private void handleSaveStudent(ActionEvent event) {
    	if (fileName.getText().trim().equals("")) {
            showError(new Exception("Please enter file name."));
            return;
        }
        try {
            try (FileOutputStream fout = new FileOutputStream(fileName.getText());
                 ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                oos.writeObject(new ArrayList<Student>(tblStudents.getItems()));
            }
        } catch (Exception ex) {
            showError(ex);
            return;
        }

        showMessage("Voila, students has been saved successfully.");

    }

    /**
     * Opens the file according to the filename specified and loads the student objects inside the file shows error if a exception arises
     *
     *
     * @param event
     */
    @SuppressWarnings("unchecked")
	@FXML
    private void handleLoadStudent(ActionEvent event) {
        if (fileName.getText().trim().equals("")) {
            showError(new Exception("Please enter file name."));
            return;
        }

        ArrayList<Student> students = null;

        try {

            try (FileInputStream streamIn = new FileInputStream(fileName.getText());
                 ObjectInputStream objectinputstream = new ObjectInputStream(streamIn)) {
                students = (ArrayList<Student>) objectinputstream.readObject();


            }
        } catch (Exception ex) {
            showError(ex);
            return;
        }

        tblStudents.getItems().clear();
        tblStudents.setItems(FXCollections.observableArrayList(students));

    }

    /**
     * Makes all the fields Editable
     *
     * @param event
     */
    @FXML
    private void handleEditStudent(MouseEvent event) {
        makeEditableFields();
    }

    /**
     * Deletes the selected student objects from the file specified
     *
     * @param event
     */
    @FXML
    private void handleDeleteStudent(ActionEvent event) {
        tblStudents.getItems().removeAll(
                tblStudents.getSelectionModel().getSelectedItems()
        );
    }
/**
 * lets the user choose a file to load from the file system
 * @param event
 */
    @FXML
    private void handleBrowse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog((Stage) name.getScene().getWindow());
        fileName.setText(selectedFile.getAbsolutePath());

    }

    /**
     * Shows an error dialog box
     *
     * @param message
     */
    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * sets the message content in the error dialog box
     *
     * @param e
     * @return 
     */
   public static void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Its an Error !!!");
        alert.setContentText(e.getMessage());

        alert.showAndWait();
    }

    /**
     * makes all the fields editable
     */
    private void makeEditableFields() {
        name.setEditable(true);
        course.setEditable(true);
        grade.setEditable(true);
    }

    /**
     * Makes all the fields uneditable
     */
    @SuppressWarnings("unused")
	private void makeUnEditableFields() {
        name.setEditable(false);
        course.setEditable(false);
        grade.setEditable(false);
    }

    /**
     * Clear all the fields
     */
    private void clearFields() {
        name.setText("");
        course.setText("");
        grade.setText("");
    }
 /**
  * Method to check if a value is numeric   
  * @param st
  * @return
  */
    static boolean isNumeric(String st)
    {
    	try
    	{
    		Integer g = Integer.parseInt(st);

    	}catch(NumberFormatException e) {
    		
    		return false;
    	}
    	return true;
    }
}
