package project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import javafx.stage.WindowEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.util.Scanner; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

public class HelloFX extends Application{
	
	private Label welcome = new Label("Welcome to Shoppers Drugmart");
	
	private Label emailLabel = new Label("Email:");
	private Label passwordLabel = new Label("Password:");
	private TextField emailTextField = new TextField();
	private TextField passwordTextField = new TextField();
	
	private Button customers = new Button ("Customers");
	private Button employees = new Button ("Employees");
	
	private Button customerLoginButton = new Button ("Login");
	
	private Button loginButton = new Button ("Login");
	
	private HBox hb, hb2;
	
	private Connection conn1 = null;
	
	@SuppressWarnings("rawtypes")
	private ObservableList<ObservableList> itemsTableData = FXCollections.observableArrayList();
	
	@SuppressWarnings("rawtypes")
	private TableView tableview;
	
	@Override
	public void start(final Stage primaryStage) throws Exception {
		
		//Connection conn1 = null;
		
        try {
            // registers Oracle JDBC driver - though this is no longer required
            // since JDBC 4.0, but added here for backward compatibility
            Class.forName("oracle.jdbc.OracleDriver");
            String dbURL1 = "jdbc:oracle:thin:system/RomitSagu@localhost:1521:xe";
            conn1 = DriverManager.getConnection(dbURL1);
            if (conn1 != null) {
                System.out.println("Connected with connection #1");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        
        
        primaryStage.setTitle("Shoppers Drugmart POS"); // Sets up the main stage title, size and shows the main stage.
        primaryStage.setScene(new Scene(mainMenu(), 200, 200));
        primaryStage.show();
        
        customers.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        primaryStage.setScene(new Scene(customerMenu(), 400, 200));
                    }
                } 
            );
        
        customerLoginButton.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	String cusID = "0";
                    	String password = "0";
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                    		String query = "SELECT CusID FROM customer WHERE email = '" + emailTextField.getText() + "'";
                			ResultSet rs = stmt.executeQuery(query);
                			
                			while (rs.next()) {
                				cusID = rs.getString("CusID");
                				//System.out.println("CusID: " + cusID);
                			}
                			
                			
                			query = "SELECT password FROM cuslogin WHERE UserID = " + cusID;
                			rs = stmt.executeQuery(query);
                			
                			while (rs.next()) {
                				password = rs.getString("password");
                				//System.out.println("Password: " + password);
                			}
                			
                			} catch (SQLException ex) {
                				System.out.println("SQL ERROR:" + ex.getErrorCode());
                				ex.printStackTrace();
                			}
                    	
                    	if(passwordTextField.getText().equals(password)) {                    	
                    		primaryStage.setScene(new Scene(customers(), 400, 200));
                    	}
                    	else if (emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()){ // Shows the user an error if they did not enter a email or password.
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setContentText("Enter a email and/or a password.");
                            alert.show();   
                        }
                    	else {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setContentText("INCORRECT Username and/or password.");
                            alert.show(); 
                    	}
                    	
                    	
                    }
                } 
            );
        
        primaryStage.setOnCloseRequest(
                new EventHandler<WindowEvent>() {
                     @Override
                     public void handle(WindowEvent e) {
                    	 try {
                             if (conn1 != null && !conn1.isClosed()) {
                                 conn1.close();
                             }

                         } catch (SQLException ex) {
                             ex.printStackTrace();
                         }
                    }  
                }
            );
	}
	
	
	public GridPane mainMenu(){ // returns the mainMenu elements
        GridPane temp = new GridPane();
        temp.setAlignment(Pos.CENTER);
        
        temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
        
        employees.setStyle("-fx-font-size: 2em; ");
        customers.setStyle("-fx-font-size: 2em; ");
        
        temp.add(customers, 0, 1);
        temp.add(employees, 0, 2);
        
        return temp;
    }
	
	public GridPane customerMenu(){
		GridPane temp = new GridPane();
        temp.setAlignment(Pos.CENTER);
        
        temp.add(welcome, 0, 0);
        temp.add(emailLabel, 0,1);
        temp.add(emailTextField, 1, 1);
        
        temp.add(passwordLabel, 0, 2);
        temp.add(passwordTextField, 1, 2);
        
        temp.add(customerLoginButton, 1, 3);
        
        return temp;
	}
	

	public GridPane customers(){
		GridPane temp = new GridPane();
        Label hello = new Label("Welcome to Shoppers Drugmart");
        
        hb = new HBox();
        hb2 = new HBox();
        temp.setAlignment(Pos.CENTER);
        
        try (Statement stmt = conn1.createStatement()) {
    		String query = "SELECT * FROM item";
			ResultSet rs = stmt.executeQuery(query);
			
			// For the table, make a class for each table and store the data in an observable arraylist of that class type and then use the normal column to make the table
			 

			} catch (SQLException ex) {
				System.out.println("SQL ERROR:" + ex.getErrorCode());
				ex.printStackTrace();
			}
        
        temp.add(hello, 0, 0);
        
        return temp;
	}
	
	
	public static void main(String[] args) {
        launch(args);
    }
	
}
