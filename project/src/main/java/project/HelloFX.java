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
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import java.util.ArrayList;

import java.text.DecimalFormat;
import org.apache.commons.lang3.RandomStringUtils;

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
	private Button cusLogOut = new Button ("Logout");
	private Button back = new Button("Back");
	private Button checkout = new Button("Checkout");
	
	//private Button loginButton = new Button ("Login");
	private Button buy = new Button("Buy");
	
	private HBox hb, hb2;
	
	private Connection conn1 = null;
	
	@SuppressWarnings("rawtypes")
	private ObservableList<Item> itemTableData = FXCollections.observableArrayList();
	
	private double totalCost = 0;
	private double totalCostTax = 0;
	private double taxRate = 1.13;
	private String cusName;
	private String purchaseID;
	private String orderID;
	private final String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private ObservableList<Item> boughtItems = FXCollections.observableArrayList();
	
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
        
        try (Statement stmt = conn1.createStatement()) {
    		String query = "SELECT * FROM item";
			ResultSet rs = stmt.executeQuery(query);
			
			// For the table, make a class for each table and store the data in an observable arraylist of that class type and then use the normal column to make the table
			while(rs.next()) {
				String itemID = rs.getString("ItemID");
				String itemName = rs.getString("ItemName");
				double price = rs.getDouble("PriceOfItem");
				int rating = rs.getInt("Rating");
				String itemCategory = rs.getString("ItemCatagory");
				int inventory = rs.getInt("Inventory");
				
				itemTableData.add(new Item(itemID, itemName, price, rating, itemCategory, inventory));
			}

		} catch (SQLException ex) {
			System.out.println("SQL ERROR:" + ex.getErrorCode());
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
                			}
                			
                			
                			query = "SELECT password FROM cuslogin WHERE UserID = " + cusID;
                			rs = stmt.executeQuery(query);
                			
                			while (rs.next()) {
                				password = rs.getString("password");
                			}
                			
                			query = "SELECT name FROM customer WHERE CusID = " + cusID;
                			rs = stmt.executeQuery(query);
                			
                			while (rs.next()) {
                				cusName = rs.getString("name");
                			}
                			
                			} catch (SQLException ex) {
                				System.out.println("SQL ERROR:" + ex.getErrorCode());
                				ex.printStackTrace();
                			}
                    	
                    	if(passwordTextField.getText().equals(password)) {
                    		passwordTextField.clear(); 
                    		emailTextField.clear(); 
                    		primaryStage.setScene(new Scene(customers(), 700, 500));
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
        
        back.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(mainMenu(), 200, 200));
                    }
                } 
            );
        
        cusLogOut.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(customerMenu(), 400, 200));
                    }
                } 
            );
        
        buy.setOnAction( 
    	        new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                        	totalCost = 0;
                            for (int i = 0; i < itemTableData.size(); i++){ 
                                if (itemTableData.get(i).getSelect().get()){
                                    totalCost += itemTableData.get(i).getPrice();
                                }
                            }
                            if (totalCost == 0){ 
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Select the items you wish to buy.");
                                alert.show();  
                            }
                            else if (totalCost > 0){ 
                            	for (int i = 0; i < itemTableData.size(); i++){ 
                                    if (itemTableData.get(i).getSelect().get()){
                                    	boughtItems.add(itemTableData.get(i));
                                    }
                                }
                            	primaryStage.setScene(new Scene(buyScreen(), 400, 300));
                                System.out.println("Total price = " + totalCost);
                            }
                        }
                    } 
            );
        
        checkout.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	try (Statement stmt = conn1.createStatement()) {
                    		String query = "SELECT * FROM item";
                			ResultSet rs = stmt.executeQuery(query);
                			
                			// For the table, make a class for each table and store the data in an observable arraylist of that class type and then use the normal column to make the table
                			while(rs.next()) {
                				String result = rs.getString("");
                			}
                			
                			/*while ()
                            	purchaseID = RandomStringUtils.random(20, charSet);
                            	orderID = RandomStringUtils.random(20, charSet);*/

                		} catch (SQLException ex) {
                			System.out.println("SQL ERROR:" + ex.getErrorCode());
                			ex.printStackTrace();
                		}
                    	primaryStage.setScene(new Scene(checkoutScreen(), 400, 200));
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
        temp.add(back, 0, 3);
        
        return temp;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GridPane customers(){
		GridPane temp = new GridPane();
        Label hello = new Label("Welcome to Shoppers Drugmart");
        TableView<Item> itemBuyTable = new TableView<Item>();
        
        hb = new HBox();
        hb2 = new HBox();
        temp.setAlignment(Pos.CENTER);
        
        itemBuyTable.setEditable(true);
        
        TableColumn<Item, Boolean> select = new TableColumn<>("Select");
        select.setMinWidth(25);
        select.setCellFactory(CheckBoxTableCell.forTableColumn(select));
        select.setCellValueFactory(
                new Callback<CellDataFeatures<Item,Boolean>,ObservableValue<Boolean>>(){
                    @Override
                    public ObservableValue<Boolean> call(CellDataFeatures<Item, Boolean> p){   
                        return p.getValue().getSelect();
                    }   
                });
        
        TableColumn itemName = new TableColumn("Item Name");
        itemName.setMinWidth(250);
        itemName.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemName"));                             
        itemName.setCellFactory(TextFieldTableCell.forTableColumn());
        itemName.setEditable(false);
        
        TableColumn itemPrice = new TableColumn("Item Price");
        itemPrice.setMinWidth(50);
        itemPrice.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("price"));                             
        itemPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        itemPrice.setEditable(false);
        
        TableColumn rating = new TableColumn("Rating");
        rating.setMinWidth(25);
        rating.setCellValueFactory(
                new PropertyValueFactory<Item, Integer>("rating"));                             
        rating.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        rating.setEditable(false);
        
        TableColumn category = new TableColumn("Category");
        category.setMinWidth(50);
        category.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemCategory"));                             
        category.setCellFactory(TextFieldTableCell.forTableColumn());
        category.setEditable(false);
        
        TableColumn inventory = new TableColumn("Inventory");
        inventory.setMinWidth(25);
        inventory.setCellValueFactory(
                new PropertyValueFactory<Item, Integer>("inventory"));                             
        inventory.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        inventory.setEditable(false);
        
        itemBuyTable.setItems(itemTableData);
        itemBuyTable.getColumns().addAll(select, itemName, itemPrice, rating, category, inventory);
        
        
        
        hb.getChildren().addAll(buy, cusLogOut);
        
        temp.add(hello, 0, 0);
        temp.add(itemBuyTable, 0, 1);
        temp.add(hb, 0, 2);
        
        return temp;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GridPane buyScreen(){
		GridPane temp = new GridPane();
		DecimalFormat decFor = new DecimalFormat("###.##");
		temp.setPadding(new Insets(11, 12, 13, 14));
        //temp.setAlignment(Pos.CENTER);
		TableView<Item> items = new TableView<Item>();
		totalCostTax = totalCost * taxRate;
        Label welcome2 = new Label("Hello " + cusName);
        Label totalCostLabel = new Label("Total Cost: " + totalCost);  
        Label totalCostTaxLabel = new Label("Total Cost After Tax: " + decFor.format(totalCostTax));
        Label cart = new Label ("Cart:");
        
        items.setEditable(false);
        
        TableColumn itemName = new TableColumn("Item Name");
        itemName.setMinWidth(250);
        itemName.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemName"));                             
        itemName.setCellFactory(TextFieldTableCell.forTableColumn());
        itemName.setEditable(false);
        
        TableColumn itemPrice = new TableColumn("Item Price");
        itemPrice.setMinWidth(50);
        itemPrice.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("price"));                             
        itemPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        itemPrice.setEditable(false);
        
        items.setItems(boughtItems);
        items.getColumns().addAll(itemName, itemPrice);
        
        temp.add(welcome2, 0, 0);
        temp.add(cart, 0, 1);
        temp.add(items, 0, 2);
        temp.add(totalCostLabel, 0, 3);
        temp.add(totalCostTaxLabel, 0, 4);
        temp.add(checkout, 0, 5);
        return temp;
	}
	
	public GridPane checkoutScreen(){ // returns the mainMenu elements
        GridPane temp = new GridPane();
        //temp.setAlignment(Pos.CENTER);
        temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
        
        Label welcome2 = new Label("Thank you for your purchese " + cusName + ".");
        Label purchase = new Label("Your purchase ID is " + purchaseID);
        
        
        temp.add(welcome2, 0, 0);
        temp.add(purchase, 0, 1);
        temp.add(cusLogOut, 0, 2);
        
        return temp;
    }
	
	
	public static void main(String[] args) {
        launch(args);
    }
	
}
