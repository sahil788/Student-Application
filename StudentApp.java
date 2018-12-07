package jac444.wk5;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * @author sahil
 * This is the main class that will load the GUI on the screen
 */
public class StudentApp extends Application {

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Student Application");
        primaryStage.setScene(new Scene(root, 600, 500));
/**
 * creates column inside the table view on load and lets the user edit the values by double clicking the values in the tableview row
 */
        TableView<Student> table = (TableView) primaryStage.getScene().lookup("#tblStudents");
        table.setEditable(true);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("name")
        );
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Student, String> t) {
                        try {
							((Student) t.getTableView().getItems().get(
							        t.getTablePosition().getRow())
							).setName(t.getNewValue());
						} catch (Exception e) {
							Controller.showError(e);
							t.getTableView().getColumns().get(0).setVisible(false);
							t.getTableView().getColumns().get(0).setVisible(true);
							return;

						}
                    }
                }
        );

       
		TableColumn courseCol = new TableColumn("Course");
        courseCol.setMinWidth(200);
        courseCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("course")
        );
        courseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Student, String> t) {
                        try {
							((Student) t.getTableView().getItems().get(
							        t.getTablePosition().getRow())
							).setCourse(t.getNewValue());
							
							
						} catch (Exception e) {
							Controller.showError(e);
							t.getTableView().getColumns().get(0).setVisible(false);
							t.getTableView().getColumns().get(0).setVisible(true);
							return;
							
						}
                    }
                }
        );

        TableColumn gradeCol = new TableColumn("Grade");
        gradeCol.setMinWidth(200);
        gradeCol.setCellValueFactory(
                new PropertyValueFactory<Student, Integer>("grade")
        );
        gradeCol.setCellFactory(TextFieldTableCell.<Student, Integer>forTableColumn(new IntegerStringConverter()));
        gradeCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Student, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Student, Integer> t) {
                        try {
                        
                            ((Student) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setGrade(t.getNewValue());
                        } catch (Exception e) {
                        	Controller.showError(e);
                        	t.getTableView().getColumns().get(0).setVisible(false);
							t.getTableView().getColumns().get(0).setVisible(true);
							return;

                        }
                    }
                }
        );

        table.getColumns().addAll(idCol, nameCol, courseCol, gradeCol);

        primaryStage.show();
    }

    /**
     * Main method to launch the application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
