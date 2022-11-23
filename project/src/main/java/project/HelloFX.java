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
	private Label welcome2 = new Label("Welcome to Shoppers Drugmart valued Employee");
	
	private Label emailLabel = new Label("Email:");
	private Label passwordLabel = new Label("Password:");
	private TextField emailTextField = new TextField();
	private TextField passwordTextField = new TextField();
	
	private Button customers = new Button ("Customers");
	private Button employees = new Button ("Employees");
	
	private Button customerLoginButton = new Button ("Login");
	private Button employeeLoginButton = new Button ("Login");
	private Button cusLogOut = new Button ("Logout");
	private Button empLogOut = new Button ("Logout");
	private Button back = new Button("Back");
	private Button checkout = new Button("Checkout");
	
	
	private Button drop = new Button("Drop Tables");
	private Button create = new Button("Create Tables");
	private Button populate = new Button("Populate Tables");
	private Button queryTab = new Button("Query Tables");
	
	private Button accessCustomer = new Button("Customer Table");
	private Button accessCusLogin = new Button("Cus Login Table");
	private Button accessEmployee = new Button("Employee Table");
	private Button accessEmpLogin = new Button("Emp Login Table");
	private Button accessItem = new Button("Item Table");
	private Button accessPurchase = new Button("Purchase Table");
	private Button accessListOfItems = new Button("ListOfItems Table");
	
	//private Button loginButton = new Button ("Login");
	private Button buy = new Button("Buy");
	
	private HBox hb, hb2, hb3;
	
	private Connection conn1 = null;
	
	@SuppressWarnings("rawtypes")
	private ObservableList<Item> itemTableData = FXCollections.observableArrayList();
	private ObservableList<Purchase> purchaseTableData = FXCollections.observableArrayList();
	private ObservableList<ListOfItems> listOfItemsTableData = FXCollections.observableArrayList();
	
	private double totalCost = 0;
	private double totalCostTax = 0;
	private double taxRate = 1.13;
	private String cusID;
	private String cusName;
	private String purchaseID;
	private String orderID;
	
	private String empID;
	private String empName;
	
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
			
			query = "SELECT * FROM purchase";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String invoiceID2 = rs.getString("InvoiceID");
				String OrderID2 = rs.getString("OrderID");
				int cusID2 = rs.getInt("CusID");
				double totalPrice = rs.getDouble("TotalPrice");
				double taxRate = rs.getDouble("TaxRate");
				String couponCodes = rs.getString("CouponCodes");
				
				purchaseTableData.add(new Purchase(invoiceID2, OrderID2, cusID2, totalPrice, taxRate, couponCodes));
			}
			
			query = "SELECT * FROM ListOfItems";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int listIndex = rs.getInt("ListIndex");
				String orderID2 = rs.getString("OrderID");
				String itemIDs = rs.getString("ItemIDs");
				
				listOfItemsTableData.add(new ListOfItems(listIndex, orderID2, itemIDs));
			}

		} catch (SQLException ex) {
			//System.out.println("SQL ERROR:" + ex.getErrorCode());
			//ex.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Database has not yet been initalized. Login as admin to do so.");
            alert.show();  
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
                    	cusID = "0";
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
                				//System.out.println("SQL ERROR:" + ex.getErrorCode());
                				//ex.printStackTrace();
                				Alert alert = new Alert(AlertType.ERROR);
                	            alert.setContentText("Database has not yet been initalized. Login as admin employee to do so.");
                	            alert.show(); 
                			}
                    	
                    	if(passwordTextField.getText().equals(password)) {
                    		passwordTextField.clear(); 
                    		emailTextField.clear(); 
                    		password = "0";
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
                        	boolean soldout = false;
                        	
                            for (int i = 0; i < itemTableData.size(); i++){ 
                                if (itemTableData.get(i).getSelect().get()){
                                    totalCost += itemTableData.get(i).getPrice();
                                    if (itemTableData.get(i).getInventory()==0) {
                                    	soldout = true;
                                    }
                                }
                            }
                            if (totalCost == 0){ 
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Select the items you wish to buy.");
                                alert.show();  
                            }
                            else if(soldout == true){
                            	Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("An item you have selected is out of stock");
                                alert.show();  
                            }
                            else if (totalCost > 0 && soldout == false){ 
                            	for (int i = 0; i < itemTableData.size(); i++){ 
                                    if (itemTableData.get(i).getSelect().get()){
                                    	boughtItems.add(itemTableData.get(i));
                                    }
                                }
                            	primaryStage.setScene(new Scene(buyScreen(), 400, 300));
                                // System.out.println("Total price = " + totalCost);
                            }
                        }
                    } 
            );
        
        checkout.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @SuppressWarnings("resource")
					@Override
                    public void handle(ActionEvent e) {
                    	purchaseID = RandomStringUtils.random(20, charSet);
                    	orderID = RandomStringUtils.random(20, charSet);
                    	
                    	for (int i = 0; i < purchaseTableData.size(); i++){ 
	                    	for (int j = 0; j < purchaseTableData.size(); j++){ 
	                            if (purchaseTableData.get(j).getInvoiceID().equals(purchaseID)) {
	                            	purchaseID = RandomStringUtils.random(20, charSet);
	                            }
	                        }
                    	}
                    	
                    	for (int i = 0; i < purchaseTableData.size(); i++){ 
	                    	for (int j = 0; j < purchaseTableData.size(); j++){ 
	                            if (purchaseTableData.get(j).getOrderID().equals(orderID)) {
	                            	orderID = RandomStringUtils.random(20, charSet);
	                            }
	                        }
                    	}
                    	
                    	for (int i = 0; i < boughtItems.size(); i++) {
                    		boughtItems.get(i).setInventory(boughtItems.get(i).getInventory()-1);
                    	}
                    	
                    	
                    	/*try (Statement stmt = conn1.createStatement()) {
                    		String query;
                    		int result;
                    		
                    		for (int i = 0; i < boughtItems.size(); i++) {
                    			query = "UPDATE item SET PriceOfItem = "  + boughtItems.get(i).getPrice() + " WHERE ItemID = '" + boughtItems.get(i).getItemID() + "'";
                    			result = stmt.executeUpdate(query);

                    		}
                    		
                			
                			} catch (SQLException ex) {
                				System.out.println("SQL ERROR:" + ex.getErrorCode());
                				ex.printStackTrace();
                			}*/
                    	
                    	purchaseTableData.add(new Purchase(purchaseID, orderID, Integer.parseInt(cusID), totalCostTax, taxRate, null));
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                    		String query;
                    		
                    		query = "INSERT INTO purchase(InvoiceID, OrderID, CusID, TaxRate, TotalPrice) VALUES ('" + purchaseID + "', '" + orderID + "', " 
            						+ cusID + ", " + taxRate + ", " + totalCostTax +")";     			
                    		int result = stmt.executeUpdate(query);
                    		
                    		
                    		int temp = listOfItemsTableData.size();
                    		
                    		for (int i = 0; i < boughtItems.size(); i++) {
                    			query = "INSERT INTO listofitems(ListIndex, OrderID, ItemIDs) VALUES (" + (temp + i + 1) + ", '" + orderID + "', '" + boughtItems.get(i).getItemID() +"')";
                    			result = stmt.executeUpdate(query);

                    		}
                    		
                			
                			} catch (SQLException ex) {
                				System.out.println("SQL ERROR:" + ex.getErrorCode());
                				ex.printStackTrace();
                			}
                    	
                    	int temp = listOfItemsTableData.size();
                    	
                    	for (int i = 0; i < boughtItems.size(); i++) {
                    		listOfItemsTableData.add(new ListOfItems(temp + i + 1, purchaseID, boughtItems.get(i).getItemID()));
                    	}
                    	
                    	boughtItems.clear();
                    	primaryStage.setScene(new Scene(checkoutScreen(), 400, 200));
                    }
        		}
            );
        
        employees.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(employeeMenu(), 450, 200));
                    }
                } 
            );
        
        employeeLoginButton.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	
                    	empID = "0";
                    	String password = "0";
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                    		String query = "SELECT UserID FROM emplogin WHERE Email = '" + emailTextField.getText() + "'";
                			ResultSet rs = stmt.executeQuery(query);
                			
                			while (rs.next()) {
                				empID = rs.getString("UserID");
                			}
                			
                			
                			query = "SELECT password FROM emplogin WHERE UserID = " + empID;
                			rs = stmt.executeQuery(query);
                			
                			while (rs.next()) {
                				password = rs.getString("password");
                			}
                			
                			query = "SELECT name FROM employee WHERE EmpID = " + empID;
                			rs = stmt.executeQuery(query);
                			
                			while (rs.next()) {
                				empName = rs.getString("name");
                			}
                			
                			} catch (SQLException ex) {
                				//System.out.println("SQL ERROR:" + ex.getErrorCode());
                				//ex.printStackTrace();
                				System.out.println("No Employee Users must use Admin");
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Database not yet initalized");
                                alert.show();  
                			}
                    	if(emailTextField.getText().equals("Admin") && passwordTextField.getText().equals("Admin")) {
                    		empID = "SuperAdmin";
                    		empName = "Admin";
                    		passwordTextField.clear(); 
                    		emailTextField.clear(); 
                    		primaryStage.setScene(new Scene(employees(), 500, 500));
                    	}
                    	else if(passwordTextField.getText().equals(password)) {
                    		passwordTextField.clear(); 
                    		emailTextField.clear(); 
                    		password = "0";
                    		primaryStage.setScene(new Scene(employees(), 500, 500));
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
        
        create.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	try (Statement stmt = conn1.createStatement()) {
                    		String query = "CREATE TABLE customer "
                    				+ " (CusID     INTEGER PRIMARY KEY,"
                    				+ " Name      VARCHAR2(50) NOT NULL,"
                    				+ " Email     VARCHAR2(50) NOT NULL UNIQUE,"
                    				+ " PhoneNum  VARCHAR2(12) NOT NULL,"
                    				+ " Address   VARCHAR2(25) NOT NULL,"
                    				+ " PCCard    VARCHAR2(16) NULL,"
                    				+ " SavedCard VARCHAR2(16) NULL)";
                    		
                    		int check1 = stmt.executeUpdate(query);
                    		
                    		query = "CREATE TABLE cuslogin"
                    				+ "    (UserID      INTEGER REFERENCES customer(CusID),"
                    				+ "    AccountType CHAR DEFAULT 'G', "
                    				+ "    Password    VARCHAR2(50) NOT NULL)"
                    				+ "";
                    		
                    		int check2 = stmt.executeUpdate(query);
                    		
                    		query = "CREATE TABLE employee"
                    				+ "    (EmpID       INTEGER PRIMARY KEY,"
                    				+ "    Name     VARCHAR2(50) NOT NULL,"
                    				+ "    Info     VARCHAR2(100) NOT NULL,"
                    				+ "    Wage        NUMBER NOT NULL,"
                    				+ "    HoursWorked NUMBER NOT NULL)";
                    		
                    		int check3 = stmt.executeUpdate(query);
                    		
                    		query = "CREATE TABLE emplogin"
                    				+ "    (UserID      INTEGER REFERENCES employee(EmpID),"
                    				+ "    Password    VARCHAR2(50) NOT NULL,"
                    				+ "    Email       VARCHAR2(50) NOT NULL UNIQUE)";
                    		
                    		int check4 = stmt.executeUpdate(query);
                    				
                    		query = "CREATE TABLE item"
                    				+ "    (ItemID        VARCHAR2(20) PRIMARY KEY,"
                    				+ "    ItemName      VARCHAR2(100) NOT NULL UNIQUE,"
                    				+ "    PriceOfItem   NUMBER NOT NULL,"
                    				+ "    Rating        INTEGER DEFAULT '0',"
                    				+ "    ItemCatagory  VARCHAR2(20) DEFAULT 'Other',"
                    				+ "    Inventory     INT NOT NULL)";
                    		
                    		int check5 = stmt.executeUpdate(query);
                    		
                    		query = "CREATE TABLE purchase"
                    				+ "    (InvoiceID     VARCHAR2(20) UNIQUE NOT NULL,"
                    				+ "    CusID         INTEGER REFERENCES customer(CusID),"
                    				+ "    OrderID       VARCHAR2(20) UNIQUE NOT NULL,"
                    				+ "    TotalPrice    NUMBER NOT NULL,"
                    				+ "    TaxRate       NUMBER DEFAULT 0.13,"
                    				+ "    CouponCodes   VARCHAR2(10) NULL,"
                    				+ "    Primary Key (InvoiceID, OrderID))";
                    		
                    		int check6 = stmt.executeUpdate(query);			
                    				
                    		query = "CREATE TABLE listofitems"
                    				+ "    (ListIndex INT PRIMARY KEY,"
                    				+ "    OrderID VARCHAR2(20) REFERENCES purchase(OrderID),"
                    				+ "    ItemIDs VARCHAR2(2000) NOT NULL)";
                    		
                    		int check7 = stmt.executeUpdate(query);
                    		
                			//int check = stmt.executeUpdate(query);
                			
                			if (check1 == 0 && check2 == 0 && check3 == 0 && check4 == 0 && check5 == 0 && check6 == 0 && check7 == 0) {
                				System.out.println("Table successfully created");
                			}
                			else {
                				System.out.println("Error");
                			}
                			
                			} catch (SQLException ex) {
                				System.out.println("SQL ERROR:" + ex.getErrorCode());
                				ex.printStackTrace();
                			}
                    }
                } 
            );
        
        populate.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	try (Statement stmt = conn1.createStatement()) {
                    		String query = "INSERT INTO customer(CusID, Name, Email, PhoneNum, Address, PCCard, SavedCard)"
                    				+ " VALUES (1,'Arshad','a1usman@ryerson.ca','9052670667','54 Bannister Crescent','1234567890123456','0987654321098765')";
                    		int check = stmt.executeUpdate(query);		
                    				
                    		query = " INSERT INTO customer(CusID, Name, Email, PhoneNum, Address)"                    				
                    				+ " VALUES (2,'Romit Sagu','romit.sagu@gmail.com','4162701760','123 Main Street')";
                    		
                    		stmt.executeUpdate(query);		
                    				
                    		query = " INSERT INTO customer(CusID, Name, Email, PhoneNum, Address)"                    				
                    				+ " VALUES (3,'Rodrigo','temp@hotmail.com','4235465176','6623 Main Street')";
                    		
                    		stmt.executeUpdate(query);	
                    		
                    		query = " INSERT INTO employee(EmpID, Name, Info, Wage, HoursWorked)"
                    				+ " VALUES (27,'Abrar','manager',25,25)";
                    		
                    		stmt.executeUpdate(query);	
                    		
                    		query =  " INSERT INTO employee(EmpID, Name, Info, Wage, HoursWorked)"
                    				+ " VALUES (605,'Tuba','worker',15,60)";
                    		
                    		stmt.executeUpdate(query);
                    				
                    		query = " INSERT INTO employee(EmpID, Name, Info, Wage, HoursWorked)"
                    				+ " VALUES (5098,'Alpha','AI',45,200)";
                    		
                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO emplogin(UserID, Password, Email)"
                    				+ " VALUES (27,'Ahmed','arshadahmed0210@gmail.com')";
                    		
                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO emplogin(UserID, Password, Email)"
                    				+ " VALUES (605,'ertuafdasdqwe','tuba.adil@ryerson.ca')";
                    		
                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO emplogin(UserID, Password, Email)"
                    				+ " VALUES (5098,'123','romit.sagu@gmail.com')";
                    		
                    		stmt.executeUpdate(query);

                    		query = " INSERT INTO cuslogin(UserID, Password)"
                    				+ " VALUES (1,'1234')";
                    		
                    		stmt.executeUpdate(query);	
                    		
                    		query = " INSERT INTO cuslogin(UserID, Password)"
                    				+ " VALUES (2,'romit123')";
                    		
                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO cuslogin(UserID, Password, AccountType)"
                    				+ " VALUES (3,'rtdfkre234', 'R')";

                    		stmt.executeUpdate(query);	
                    		
                    		query = " INSERT INTO item(ItemID, ItemName, PriceOfItem, Rating, ItemCatagory, Inventory)"
                    				+ " VALUES ('1','Soap',1.99,3,'Other',4)";
                    		
                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO item(ItemID, ItemName, PriceOfItem, Rating, ItemCatagory, Inventory)"
                    				+ " VALUES ('2','Cetaphil Hydrating Cream',15.49,0,'Cleansers',130)";
                    		
                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO item(ItemID, ItemName, PriceOfItem, Rating, ItemCatagory, Inventory)"
                    				+ " VALUES ('3','PS5 Console Bundle/Horizon Forbidden West with extra Dualsense White Controller',649.99,5,'Video Games',0)";
                    		
                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO item(ItemID, ItemName, PriceOfItem, Rating, ItemCatagory, Inventory)"
                    				+ " VALUES ('4','Arthritis Pain Relief Acetaminophen 650mg',29.99,3,'Drugs',5353)";
                    		
                    		stmt.executeUpdate(query);
                    				
                    		query = " INSERT INTO item(ItemID, ItemName, PriceOfItem, Rating, ItemCatagory, Inventory)"
                    				+ " VALUES ('78','Acti-Fresh Body Shape Panty Liners Regular To Go Pack of 54 Liners',69,4,'Women',420420)";
                    		
                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO item(ItemID, ItemName, PriceOfItem, Rating, ItemCatagory, Inventory)"
                    				+ " VALUES ('5r4r','Thrive Lozenges 1mg Regular Strength Nicotine Replacement Mint 108 count',5.69,3,'zooted',420)";

                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO purchase(InvoiceID, CusID, OrderID, TaxRate, TotalPrice)"
                    				+ " VALUES ('69',1,'ADA104',0.1,1.99)";
                    		
                    		stmt.executeUpdate(query);		
                    				
                    		query = " INSERT INTO purchase(InvoiceID, CusID, OrderID, TaxRate, TotalPrice)"
                    				+ " VALUES ('1201',1,'TU100',0.1,1.99)";
                    		
                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO listofitems(ListIndex, OrderID, ItemIDs)"
                    				+ " VALUES (1, 'ADA104','1,2,4')";

                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO listofitems(ListIndex, OrderID, ItemIDs)"
                    				+ " VALUES (2, 'ADA104','2')";

                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO listofitems(ListIndex, OrderID, ItemIDs)"
                    				+ " VALUES (3, 'TU100','1,4,5r4r')";
                    		
                    		int check19 = stmt.executeUpdate(query);
                			
                			if (check > 0 && check19 > 0) {
                				System.out.println("Data successfully entered");
                			}
                			else {
                				System.out.println("Error");
                			}
                			
                			} catch (SQLException ex) {
                				System.out.println("SQL ERROR:" + ex.getErrorCode());
                				ex.printStackTrace();
                			}
                    }
                } 
            );
        
        drop.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	try (Statement stmt = conn1.createStatement()) {
                    		
                    		
                    		
                    		}catch (SQLException ex) {
                				System.out.println("SQL ERROR:" + ex.getErrorCode());
                				ex.printStackTrace();
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
        Label totalCostLabel = new Label("Total Cost: " + decFor.format(totalCost));  
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
        
        Label welcome2 = new Label("Thank you for your purchase " + cusName + ".");
        Label purchase = new Label("Your purchase ID is " + purchaseID);
        
        
        temp.add(welcome2, 0, 0);
        temp.add(purchase, 0, 1);
        temp.add(cusLogOut, 0, 2);
        
        return temp;
    }
	
	public GridPane employeeMenu(){ 
		GridPane temp = new GridPane();
        temp.setAlignment(Pos.CENTER);
        
		temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
		
        temp.add(welcome2, 0, 0);
        temp.add(emailLabel, 0,1);
        temp.add(emailTextField, 1, 1);
        
        temp.add(passwordLabel, 0, 2);
        temp.add(passwordTextField, 1, 2);
        
        temp.add(employeeLoginButton, 1, 3);
        temp.add(back, 0, 3);
        
        return temp;
    }
	
	public GridPane employees(){
		GridPane temp = new GridPane();
        //temp.setAlignment(Pos.CENTER);

		 hb = new HBox();
		 hb2 = new HBox();
		 hb3 = new HBox();
		
		temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
		
        Label welcome2 = new Label("Welcome to the DBMS " + empName + ".");
        Label welcome3 = new Label("Employee ID " + empID + ".");
        Label label1 = new Label("For general Database functionality: ");
        Label label2 = new Label("For advanced Database functionality: ");
        
        hb.getChildren().addAll(create, populate, queryTab, drop);
        hb2.getChildren().addAll(accessCustomer, accessCusLogin, accessEmployee, accessEmpLogin);
        hb3.getChildren().addAll(accessItem, accessPurchase, accessListOfItems);
        
        temp.add(welcome2, 0, 0);
        temp.add(welcome3, 0, 1);
        temp.add(label1, 0, 2);
        temp.add(hb, 0, 3);
        temp.add(label2, 0, 4);
        temp.add(hb2, 0, 5);
        temp.add(hb3, 0, 6);
        
        return temp;
    }
	
	
	public static void main(String[] args) {
        launch(args);
    }
	
}
