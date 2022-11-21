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
import javafx.beans.value.ObservableValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

public class HelloFX extends Application{
	
	private Label welcome = new Label("Welcome to Shoppers Drugmart");
	
	private Label usernameLabel = new Label("Username:");
	private Label passwordLabel = new Label("Password:");
	private TextField usernameTextField = new TextField();
	private TextField passwordTextField = new TextField();
	
	private Button loginButton = new Button ("Login");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Connection conn1 = null;

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
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        primaryStage.setTitle("Shoppers Drugmart POS"); // Sets up the main stage title, size and shows the main stage.
        primaryStage.setScene(new Scene(mainMenu(), 400, 200));
        primaryStage.show();
	}
	
	
	public GridPane mainMenu(){ // returns the mainMenu elements
        GridPane temp = new GridPane();
        temp.setAlignment(Pos.CENTER);
        
        temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
        temp.add(welcome, 0, 0);
        temp.add(usernameLabel, 0,1);
        temp.add(usernameTextField, 1, 1);
        
        temp.add(passwordLabel, 0, 2);
        temp.add(passwordTextField, 1, 2);
        
        temp.add(loginButton, 1, 3);
        
        return temp;
    }
	
	
	public static void main(String[] args) {
        launch(args);
    }
	
}
