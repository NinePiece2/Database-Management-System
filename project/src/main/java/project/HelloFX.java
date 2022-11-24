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
import javafx.util.converter.CharacterStringConverter;
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
import java.sql.SQLIntegrityConstraintViolationException;

public class HelloFX extends Application{
	
	private Label welcome = new Label("Welcome to Shoppers Drugmart");
	private Label welcome2 = new Label("Welcome to Shoppers Drugmart valued Employee");
	
	private Label emailLabel = new Label("Email:");
	private Label passwordLabel = new Label("Password:");
	private Label blank = new Label("  ");
	private TextField emailTextField = new TextField();
	private TextField passwordTextField = new TextField();
	private TextField searchItemName = new TextField();
	private TextField searchCustomerName = new TextField();
	private TextField searchCusLogID = new TextField();
	private TextField searchEmployeeName = new TextField();
	private TextField searchEmpLogID = new TextField();
	private TextField searchInvoiceID = new TextField();
	private TextField searchOrderID = new TextField();
	
	private Button customers = new Button ("Customers");
	private Button employees = new Button ("Employees");
	
	private Button customerLoginButton = new Button ("Login");
	private Button employeeLoginButton = new Button ("Login");
	private Button cusLogOut = new Button ("Logout");
	private Button empLogOut = new Button ("Logout");
	private Button back = new Button("Back");
	private Button checkout = new Button("Checkout");
	private Button backEmp = new Button("Back");
		
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
	
	private Button searchItem = new Button("Search");
	private Button searchCustomer = new Button("Search");
	private Button searchCusLogin = new Button("Search");
	private Button searchEmployee = new Button("Search");
	private Button searchEmpLogin = new Button("Search");
	private Button searchPurchase = new Button("Search");
	private Button searchListOfItems = new Button("Search");
	private Button delItem = new Button("Delete");
	private Button del = new Button("Delete");
	private Button add = new Button("Add");
	
	
	private TextField itemIDField = new TextField();
	private TextField itemNameField = new TextField();
	private TextField itemPriceField = new TextField();
	private TextField itemRatingField = new TextField();
	private TextField itemCategoryField = new TextField();
	private TextField itemInventoryField = new TextField();
	
	private TextField cusIDField = new TextField();
	private TextField cusNameField = new TextField();
	private TextField cusEmailField = new TextField();
	private TextField cusPhoneNumField = new TextField();
	private TextField cusAddressField = new TextField();
	private TextField cusPCCardField = new TextField();
	private TextField cusSavedCardField = new TextField();
	
	private TextField cusUserIDField = new TextField();
	private TextField cusAccountTypeField = new TextField();
	private TextField cusPasswordField = new TextField();
	
	private TextField empIDField = new TextField();
	private TextField empNameField = new TextField();
	private TextField empInfoField = new TextField();
	private TextField empWageField = new TextField();
	private TextField empHoursWorkedField = new TextField();
	
	private TextField invoiceIDField = new TextField();
	private TextField orderIDField = new TextField();
	private TextField totalPriceField = new TextField();
	private TextField taxRateField = new TextField();
	private TextField couponCodesField = new TextField();
	
	private TextField empPasswordField = new TextField();
	private TextField empEmailField = new TextField();
	
	private TextField indexField = new TextField();
	private TextField itemIDsField = new TextField();

	
	//private Button loginButton = new Button ("Login");
	private Button buy = new Button("Buy");
	
	private HBox hb, hb2, hb3;
	
	private Connection conn1 = null;
	
	@SuppressWarnings("rawtypes")
	private ObservableList<Item> itemTableData = FXCollections.observableArrayList();
	private ObservableList<Purchase> purchaseTableData = FXCollections.observableArrayList();
	private ObservableList<ListOfItems> listOfItemsTableData = FXCollections.observableArrayList();
	
	private ObservableList<Customer> searchedCustomerTableData = FXCollections.observableArrayList();
	private ObservableList<CusLogin> searchedCusLoginTableData = FXCollections.observableArrayList();
	private ObservableList<Employee> searchedEmployeeTableData = FXCollections.observableArrayList();
	private ObservableList<EmpLogin> searchedEmpLoginTableData = FXCollections.observableArrayList();
	private ObservableList<Item> searchedItemTableData = FXCollections.observableArrayList();
	private ObservableList<Purchase> searchedPurchaseTableData = FXCollections.observableArrayList();
	private ObservableList<ListOfItems> searchedListOfItemsTableData = FXCollections.observableArrayList();
	
	private ObservableList<Customer> customerTableData = FXCollections.observableArrayList();
	private ObservableList<CusLogin> cusLoginTableData = FXCollections.observableArrayList();
	private ObservableList<Employee> employeeTableData = FXCollections.observableArrayList();
	private ObservableList<EmpLogin> empLoginTableData = FXCollections.observableArrayList();
	
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
                    	
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                    		String query;
                    		int result;
                    		
                    		for (int i = 0; i < boughtItems.size(); i++) {
                    			query = "\n" +
                                        "   UPDATE item\n" +
                                        "   SET Inventory = " + boughtItems.get(i).getInventory() + "\n" +
                                        " WHERE ItemID = '" + boughtItems.get(i).getItemID() + "'\n" +
                                        "   \n" +
                                        "";
                    			result = stmt.executeUpdate(query);

                    		} //Maybe use batch processing
                    		
                			
                			} catch (SQLException ex) {
                				System.out.println("SQL ERROR:" + ex.getErrorCode());
                				ex.printStackTrace();
                			}
                    	
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
                				//System.out.println("No Employee Users must use Admin");
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Database not yet initalized");
                                alert.show();  
                			}
                    	if(emailTextField.getText().equals("Admin") && passwordTextField.getText().equals("Admin")) {
                    		empID = "SuperAdmin";
                    		empName = "Admin";
                    		passwordTextField.clear(); 
                    		emailTextField.clear(); 
                    		primaryStage.setScene(new Scene(employees(), 500, 250));
                    	}
                    	else if(passwordTextField.getText().equals(password)) {
                    		passwordTextField.clear(); 
                    		emailTextField.clear(); 
                    		password = "0";
                    		primaryStage.setScene(new Scene(employees(), 500, 250));
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
                    				+ "    HoursWorked INTEGER NOT NULL)";
                    		
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
                    				+ "    TaxRate       NUMBER DEFAULT 1.13,"
                    				+ "    CouponCodes   VARCHAR2(10) NULL,"
                    				+ "    Primary Key (InvoiceID, OrderID))";
                    		
                    		int check6 = stmt.executeUpdate(query);			
                    				
                    		query = "CREATE TABLE listofitems"
                    				+ "    (ListIndex INT PRIMARY KEY,"
                    				+ "    OrderID VARCHAR2(20) REFERENCES purchase(OrderID) ON DELETE CASCADE,"
                    				+ "    ItemIDs VARCHAR2(2000) NOT NULL)";
                    		
                    		int check7 = stmt.executeUpdate(query);
                    		
                			//int check = stmt.executeUpdate(query);
                			
                			if (check1 == 0 && check2 == 0 && check3 == 0 && check4 == 0 && check5 == 0 && check6 == 0 && check7 == 0) {
                				//System.out.println("Tables successfully created");
                				Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setContentText("Tables successfully created");
                                alert.show(); 
                			}
                			else {
                				//System.out.println("Error");
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Tables NOT created");
                                alert.show();
                			}
                			
                			} catch (SQLException ex) {
                				//System.out.println("SQL ERROR:" + ex.getErrorCode());
                				//ex.printStackTrace();
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Tables already created");
                                alert.show();
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
                    				+ " VALUES (1, 'ADA104','1')";

                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO listofitems(ListIndex, OrderID, ItemIDs)"
                    				+ " VALUES (2, 'ADA104','2')";

                    		stmt.executeUpdate(query);
                    		
                    		query = " INSERT INTO listofitems(ListIndex, OrderID, ItemIDs)"
                    				+ " VALUES (3, 'TU100','5r4r')";
                    		
                    		int check19 = stmt.executeUpdate(query);
                			
                			if (check > 0 && check19 > 0) {
                				//System.out.println("Data successfully entered");
                				Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setContentText("Data successfully entered");
                                alert.show();
                			}
                			else {
                				//System.out.println("Error");
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Data has NOT been entered");
                                alert.show();
                			}
                			
                			} catch (SQLException ex) {
                				//System.out.println("SQL ERROR:" + ex.getErrorCode());
                				//ex.printStackTrace();
                				
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Data has already been entered");
                                alert.show();
                			}
                    }
                } 
            );
        
        drop.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	try (Statement stmt = conn1.createStatement()) {
                    		
                    		String query = "DROP TABLE listofitems";
                    		int check1 = stmt.executeUpdate(query);
                    		
                    		query = "DROP TABLE purchase";
                    		int check2 = stmt.executeUpdate(query);
                    		
                    		query = "DROP TABLE cuslogin";
                    		int check3 = stmt.executeUpdate(query);
                    		
                    		query = "DROP TABLE customer";
                    		int check4 = stmt.executeUpdate(query);
                    		
                    		query = "DROP TABLE emplogin";
                    		int check5 = stmt.executeUpdate(query);
                    		
                    		query = "DROP TABLE employee";
                    		int check6 = stmt.executeUpdate(query);
                    		
                    		query = "DROP TABLE item";
                    		int check7 = stmt.executeUpdate(query);
                    		
                    		itemTableData.clear();
                    		purchaseTableData.clear();
                    		listOfItemsTableData.clear();
                    		
                    		if (check1 == 0 && check2 == 0 && check3 == 0 && check4 == 0 && check5 == 0 && check6 == 0 && check7 == 0) {
                				//System.out.println("Data successfully entered");
                				Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setContentText("Tables successfully dropped");
                                alert.show();
                			}
                			else {
                				//System.out.println("Error");
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Tables have NOT been dropped");
                                alert.show();
                			}
                    		                    		
                    		}catch (SQLException ex) {
                				//System.out.println("SQL ERROR:" + ex.getErrorCode());
                				//ex.printStackTrace();
                    			Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("No tables to drop");
                                alert.show();
                			}
                    	}
                    } 
                );
        
        queryTab.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setContentText("To query each table use the advanced button for each table.");
                        alert.show();
                    }
                } 
            );
        
        accessItem.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(itemScreen(), 700, 500));
                    }
                } 
            );
        
        empLogOut.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(employeeMenu(), 450, 200));
                    }
                } 
            );
        
        backEmp.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(employees(), 500, 250));
                    }
                } 
            );
        
        searchItem.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                			String query;
                			
                			if(searchItemName.getText().isEmpty()) {
                				query = "SELECT * FROM item";
                			}
                			else {
                				query = "SELECT * FROM item WHERE ItemName LIKE '%" + searchItemName.getText() + "%'";
                			}
                			
                			
                			ResultSet rs = stmt.executeQuery(query);
                			
                			searchedItemTableData.clear();
                			
                			while(rs.next()) {
                				String itemID = rs.getString("ItemID");
                				String itemName = rs.getString("ItemName");
                				double price = rs.getDouble("PriceOfItem");
                				int rating = rs.getInt("Rating");
                				String itemCategory = rs.getString("ItemCatagory");
                				int inventory = rs.getInt("Inventory");
                				          				
                				searchedItemTableData.add(new Item(itemID, itemName, price, rating, itemCategory, inventory));
                				
                			}
                				                			
                		} catch (SQLException ex) {
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Database not yet initalized");
                                alert.show();
                		}		
                    }
                } 
            );
        
        accessCustomer.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(customerScreen(), 800, 500));
                    }
                } 
            );
        
        accessCusLogin.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(cusLoginScreen(), 375, 500));
                    }
                } 
            );
        
        accessEmployee.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(employeeScreen(), 575, 500));
                    }
                } 
            );
        
        accessEmpLogin.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(empLoginScreen(), 375, 500));
                    }
                } 
            );
        
        accessPurchase.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(purchaseScreen(), 675, 500));
                    }
                } 
            );
        
        accessListOfItems.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	primaryStage.setScene(new Scene(listOfItemsScreen(), 375, 500));
                    }
                } 
            );
        
        searchCustomer.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                			String query;
                			
                			if(searchCustomerName.getText().isEmpty()) {
                				query = "SELECT * FROM customer";
                			}
                			else {
                				query = "SELECT * FROM customer WHERE Name LIKE '%" + searchCustomerName.getText() + "%'";
                			}
                			
                			
                			ResultSet rs = stmt.executeQuery(query);
                			
                			searchedCustomerTableData.clear();
                			
                			while(rs.next()) {
                				int cusID = rs.getInt("CusID");
                				String cusName = rs.getString("Name");
                				String email = rs.getString("Email");
                				String phoneNum = rs.getString("PhoneNum");
                				String address = rs.getString("Address");
                				String pcCard = rs.getString("PCCard");
                				String savedCard = rs.getString("SavedCard");
                				
                				if (pcCard == null) {
                					pcCard = "0";
                				}
                				
                				if (savedCard == null) {
                					savedCard = "0";
                				}
                								
                				searchedCustomerTableData.add(new Customer(cusID, cusName, email, phoneNum, address, pcCard, savedCard));
                			}
                				                			
                		} catch (SQLException ex) {
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Database not yet initalized");
                                alert.show();
                		}		
                    }
                } 
            );
        
        searchCusLogin.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                			String query;
                			
                			if(searchCusLogID.getText().isEmpty()) {
                				query = "SELECT * FROM cuslogin";
                			}
                			else {
                				query = "SELECT * FROM cuslogin WHERE UserID LIKE '%" + searchCusLogID.getText() + "%'";
                			}
                			
                			
                			ResultSet rs = stmt.executeQuery(query);
                			
                			searchedCusLoginTableData.clear();
                			
                			while(rs.next()) {
                				int userID = rs.getInt("UserID");
                				char accountType = rs.getString("AccountType").charAt(0);
                				String password = rs.getString("Password");				
                				
                				searchedCusLoginTableData.add(new CusLogin(userID, accountType, password));
                			}
                				                			
                		} catch (SQLException ex) {
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Database not yet initalized");
                                alert.show();
                		}		
                    }
                } 
            );
        
        searchEmployee.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                			String query;
                			
                			if(searchEmployeeName.getText().isEmpty()) {
                				query = "SELECT * FROM employee";
                			}
                			else {
                				query = "SELECT * FROM employee WHERE Name LIKE '%" + searchEmployeeName.getText() + "%'";
                			}
                			
                			
                			ResultSet rs = stmt.executeQuery(query);
                			
                			searchedEmployeeTableData.clear();
                			
                			while(rs.next()) {
                				int empID = rs.getInt("EmpID");
                				String empName = rs.getString("Name");
                				String info = rs.getString("Info");
                				double empWage = rs.getDouble("Wage");
                				int hoursWorked = rs.getInt("HoursWorked");
                								
                				searchedEmployeeTableData.add(new Employee(empID, empName, info, empWage, hoursWorked));
                			}
                				                			
                		} catch (SQLException ex) {
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Database not yet initalized");
                                alert.show();
                		}		
                    }
                } 
            );
        
        searchPurchase.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                			String query;
                			
                			if(searchInvoiceID.getText().isEmpty()) {
                				query = "SELECT * FROM purchase";
                			}
                			else {
                				query = "SELECT * FROM purchase WHERE InvoiceID LIKE '%" + searchInvoiceID.getText() + "%'";
                			}
                			
                			
                			ResultSet rs = stmt.executeQuery(query);
                			
                			searchedPurchaseTableData.clear();
                			
                			while(rs.next()) {
                				String invoiceID2 = rs.getString("InvoiceID");
                				String OrderID2 = rs.getString("OrderID");
                				int cusID2 = rs.getInt("CusID");
                				double totalPrice = rs.getDouble("TotalPrice");
                				double taxRate = rs.getDouble("TaxRate");
                				String couponCodes = rs.getString("CouponCodes");
                				
                				searchedPurchaseTableData.add(new Purchase(invoiceID2, OrderID2, cusID2, totalPrice, taxRate, couponCodes));
                			}
                				                			
                		} catch (SQLException ex) {
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Database not yet initalized");
                                alert.show();
                		}		
                    }
                } 
            );
        
        searchEmpLogin.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                			String query;
                			
                			if(searchEmpLogID.getText().isEmpty()) {
                				query = "SELECT * FROM emplogin";
                			}
                			else {
                				query = "SELECT * FROM emplogin WHERE UserID LIKE '%" + searchEmpLogID.getText() + "%'";
                			}
                			
                			
                			ResultSet rs = stmt.executeQuery(query);
                			
                			searchedEmpLoginTableData.clear();
                			
                			while(rs.next()) {
                				int userID = rs.getInt("UserID");
                				String password = rs.getString("Password");
                				String email = rs.getString("Email");
                				
                				searchedEmpLoginTableData.add(new EmpLogin(userID, password, email));
                			}
                				                			
                		} catch (SQLException ex) {
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Database not yet initalized");
                                alert.show();
                		}		
                    }
                } 
            );
        
        searchListOfItems.setOnAction( 
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	
                    	try (Statement stmt = conn1.createStatement()) {
                			String query;
                			
                			if(searchOrderID.getText().isEmpty()) {
                				query = "SELECT * FROM listofitems";
                			}
                			else {
                				query = "SELECT * FROM listofitems WHERE OrderID LIKE '%" + searchOrderID.getText() + "%'";
                			}
                			
                			
                			ResultSet rs = stmt.executeQuery(query);
                			
                			searchedListOfItemsTableData.clear();
                			
                			while(rs.next()) {
                				int listIndex = rs.getInt("ListIndex");
                				String orderID2 = rs.getString("OrderID");
                				String itemIDs = rs.getString("ItemIDs");
                				
                				searchedListOfItemsTableData.add(new ListOfItems(listIndex, orderID2, itemIDs));
                			}
                				                			
                		} catch (SQLException ex) {
                				Alert alert = new Alert(AlertType.ERROR);
                                alert.setContentText("Database not yet initalized");
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
        
        try (Statement stmt = conn1.createStatement()) {
    		String query = "SELECT * FROM item";
			ResultSet rs = stmt.executeQuery(query);
			
			itemTableData.clear();
			purchaseTableData.clear();
			listOfItemsTableData.clear();
			
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
            alert.setContentText("Database has not yet been initalized. Login as admin to do so and restart to access customer functionality.");
            alert.show();  
		}
        
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
        temp.add(blank, 0, 7);
        temp.add(empLogOut, 0, 8);
        
        return temp;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GridPane itemScreen(){
		GridPane temp = new GridPane();
		TableView<Item> items = new TableView<Item>();
		boolean same = false;
		
		temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
		
        hb = new HBox();
		hb2 = new HBox();
		hb3 = new HBox();
		
		searchedItemTableData.clear();
		itemTableData.clear();
		
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
					
				searchedItemTableData.add(new Item(itemID, itemName, price, rating, itemCategory, inventory));
				itemTableData.add(new Item(itemID, itemName, price, rating, itemCategory, inventory));
			}
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Database has not yet been initalized.");
            alert.show();
			
		}
		
		TableColumn itemid = new TableColumn("Item ID");
		itemid.setMinWidth(25);
		itemid.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemID"));                             
		itemid.setCellFactory(TextFieldTableCell.forTableColumn());
		itemid.setEditable(false);
		
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
        
        items.setItems(searchedItemTableData);
        items.getColumns().addAll(itemid, itemName, itemPrice, rating, category, inventory);
		
        searchItemName.setPromptText("Item Name");
        
        itemIDField.setPromptText("Item ID");
        itemIDField.setPrefWidth(100);
        itemNameField.setPromptText("Item Name");
        itemNameField.setPrefWidth(100);
        itemPriceField.setPromptText("Price");
        itemPriceField.setPrefWidth(100);
        itemRatingField.setPromptText("Rating");
        itemRatingField.setPrefWidth(100);
        itemCategoryField.setPromptText("Category");
        itemCategoryField.setPrefWidth(100);
        itemInventoryField.setPromptText("Inventory");
        itemInventoryField.setPrefWidth(100);
        
        hb.getChildren().addAll(searchItemName, searchItem, delItem);
        hb2.getChildren().addAll(itemIDField, itemNameField, itemPriceField, itemRatingField, itemCategoryField, itemInventoryField, add);
        hb3.getChildren().addAll(backEmp);
        
        delItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try{
                	Item temp = items.getSelectionModel().getSelectedItem();
                	if (temp == null) {
                        throw new RuntimeException();
                	}
                	
                	Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Are you sure you want to delete the selected entry");
                    
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK && temp != null) {
                        	searchedItemTableData.remove(temp);
                        	try (Statement stmt = conn1.createStatement()) {
                        		String query = "\n" +
                                        "   DELETE FROM item\n" +
                                        "    WHERE ItemID = '" + temp.getItemID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                    			stmt.executeUpdate(query);
                    			
                        	} catch (SQLException ex) {   
                				ex.printStackTrace();                   				
                    		}                    			
                        }
                    });
                    
                }catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Select a entry to Delete.");
                    alert.show();
            	}
            }
        });
        
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	boolean uniqueID = true;
            	
            	for (int i = 0; i < itemTableData.size(); i++) {
            		if (itemTableData.get(i).getItemID().equals(itemIDField.getText())) {
            			uniqueID = false;
            		}
            	}
            	if (uniqueID && !itemIDField.getText().isEmpty() && !itemNameField.getText().isEmpty() && !itemPriceField.getText().isEmpty() && 
            			!itemRatingField.getText().isEmpty() && !itemCategoryField.getText().isEmpty() && !itemInventoryField.getText().isEmpty()) {
	                
            		try (Statement stmt = conn1.createStatement()){
	                	
	                	itemTableData.add(new Item(itemIDField.getText(), itemNameField.getText(), Double.parseDouble(itemPriceField.getText()), 
	                			Integer.parseInt(itemRatingField.getText()), itemCategoryField.getText(), Integer.parseInt(itemInventoryField.getText())));
	                	
	                	searchedItemTableData.add(new Item(itemIDField.getText(), itemNameField.getText(), Double.parseDouble(itemPriceField.getText()), 
	                			Integer.parseInt(itemRatingField.getText()), itemCategoryField.getText(), Integer.parseInt(itemInventoryField.getText())));
	                	
	                	String query = "INSERT INTO item (ItemID, ItemName, PriceOfItem, Rating, ItemCatagory, Inventory) VALUES ('" + itemIDField.getText() +"', '" + itemNameField.getText() + "', "
	                			+ itemPriceField.getText() + ", " + itemRatingField.getText() + ", '" + itemCategoryField.getText() + "', " + itemInventoryField.getText() + ")";
	                	
	                	stmt.executeUpdate(query);
	                	
	                	itemIDField.clear();
	                	itemNameField.clear();
	                	itemPriceField.clear();
	                	itemRatingField.clear();
	                	itemCategoryField.clear();
	                	itemInventoryField.clear();
	                  
	                }catch (SQLException ex) {   
        				ex.printStackTrace();                   				
            		} 
	                catch (RuntimeException a) {
	            		Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please make sure values of Rating and Inventory are INT and Price is DOUBLE");
	                    alert.show();
	            	}
            	}
            	else if (!uniqueID) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure your ItemID is unique.");
                    alert.show();
            	}
            	else {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("One or multiple values are blank");
                    alert.show();
            	}
            }
        });
        
        temp.add(items, 0, 1);
        
        temp.add(hb, 0, 2);
        temp.add(hb2, 0, 3);
        temp.add(hb3, 0, 4);
		
		return temp;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GridPane customerScreen(){
		GridPane temp = new GridPane();
		TableView<Customer> customers = new TableView<Customer>();
		
		temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
		
        hb = new HBox();
		hb2 = new HBox();
		hb3 = new HBox();
		
		searchedCustomerTableData.clear();
		customerTableData.clear();
		cusLoginTableData.clear();
		
		try (Statement stmt = conn1.createStatement()) {
    		String query = "SELECT * FROM customer";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int cusID = rs.getInt("CusID");
				String cusName = rs.getString("Name");
				String email = rs.getString("Email");
				String phoneNum = rs.getString("PhoneNum");
				String address = rs.getString("Address");
				String pcCard = rs.getString("PCCard");
				String savedCard = rs.getString("SavedCard");
				
				if (pcCard == null) {
					pcCard = "0";
				}
				
				if (savedCard == null) {
					savedCard = "0";
				}
								
				searchedCustomerTableData.add(new Customer(cusID, cusName, email, phoneNum, address, pcCard, savedCard));
				customerTableData.add(new Customer(cusID, cusName, email, phoneNum, address, pcCard, savedCard));
			}
			
			query = "SELECT * FROM cuslogin";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int userID = rs.getInt("UserID");
				char accountType = rs.getString("AccountType").charAt(0);
				String password = rs.getString("Password");				
				
				cusLoginTableData.add(new CusLogin(userID, accountType, password));
			}
			
			
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Database has not yet been initalized.");
            alert.show();
			
		}
		
		TableColumn cusid = new TableColumn("Cus ID");
		cusid.setMinWidth(25);
		cusid.setCellValueFactory(
                new PropertyValueFactory<Customer, Integer>("cusID"));                             
		cusid.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		cusid.setEditable(false);
		
		TableColumn cusName = new TableColumn("Name");
		cusName.setMinWidth(100);
		cusName.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("name"));                             
		cusName.setCellFactory(TextFieldTableCell.forTableColumn());
		cusName.setEditable(false);
        
        TableColumn cusEmail = new TableColumn("Email");
        cusEmail.setMinWidth(150);
        cusEmail.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("email"));                             
        cusEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        cusEmail.setEditable(false);
        
        TableColumn phoneNum = new TableColumn("Phone Num");
        phoneNum.setMinWidth(100);
        phoneNum.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("phoneNum"));                             
        phoneNum.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNum.setEditable(false);
        
        TableColumn address = new TableColumn("Address");
        address.setMinWidth(100);
        address.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("address"));                             
        address.setCellFactory(TextFieldTableCell.forTableColumn());
        address.setEditable(false);
        
        TableColumn pcCard = new TableColumn("PC Card");
        pcCard.setMinWidth(125);
        pcCard.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("pcCard"));                             
        pcCard.setCellFactory(TextFieldTableCell.forTableColumn());
        pcCard.setEditable(false);
        
        TableColumn savedCard = new TableColumn("Saved Card");
        savedCard.setMinWidth(125);
        savedCard.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("savedCard"));                             
        savedCard.setCellFactory(TextFieldTableCell.forTableColumn());
        savedCard.setEditable(false);
        
        customers.setItems(searchedCustomerTableData);
        customers.getColumns().addAll(cusid, cusName, cusEmail, phoneNum, address, pcCard, savedCard);
		
        searchCustomerName.setPromptText("Customer Name");
        
        cusIDField.setPromptText("Cus ID");
        cusIDField.setPrefWidth(100);
        cusNameField.setPromptText("Cus Name");
        cusNameField.setPrefWidth(100);
        cusEmailField.setPromptText("Email");
        cusEmailField.setPrefWidth(100);
        cusPhoneNumField.setPromptText("Phone Num");
        cusPhoneNumField.setPrefWidth(100);
        cusAddressField.setPromptText("Address");
        cusAddressField.setPrefWidth(100);
        cusPCCardField.setPromptText("PC Card");
        cusPCCardField.setPrefWidth(100);
        cusSavedCardField.setPromptText("Saved Card");
        cusSavedCardField.setPrefWidth(100);
        
        hb.getChildren().addAll(searchCustomerName,searchCustomer, del);
        hb2.getChildren().addAll(cusIDField, cusNameField, cusEmailField, cusPhoneNumField, cusAddressField, cusPCCardField, cusSavedCardField, add);
        hb3.getChildren().addAll(backEmp);
        
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try{
                	Customer temp = customers.getSelectionModel().getSelectedItem();
                	
                	if (temp == null) {
                        throw new RuntimeException();
                	}
                	
                	Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Are you sure you want to delete the selected entry");
                    
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK && temp != null) {
                        	int temp3 = 0;
                        	searchedCustomerTableData.remove(temp);
                        	for (int i = 0; i < cusLoginTableData.size(); i++) {                     		
                        		if (temp.getCusID() == cusLoginTableData.get(i).getUserID()) {
                        			temp3 = i;
                        		}
                        	}
                        	CusLogin temp2 = cusLoginTableData.get(temp3);
                        	cusLoginTableData.remove(temp2);
                        	try (Statement stmt = conn1.createStatement()) {
                        		String query = "\n" + 
                                        "   DELETE FROM cuslogin\n" +
                                        "    WHERE UserID = '" + temp2.getUserID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                        		stmt.executeUpdate(query);
                        		
                        		query = "\n" + 
                                        "   DELETE FROM customer\n" +
                                        "    WHERE CusID = '" + temp.getCusID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                    			stmt.executeUpdate(query);
                    			
                        	} catch (SQLException ex) {   
                				ex.printStackTrace();                   				
                    		}                    			
                        }
                    });
                    
                }catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Select a entry to Delete.");
                    alert.show();
            	}
            }
        });
        
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	boolean uniqueID = true;
            	boolean uniqueEmail = true;
            	
            	try {
            	for (int i = 0; i < customerTableData.size(); i++) {            		
	            		if (!cusIDField.getText().isEmpty()) {
		            		if (customerTableData.get(i).getCusID() == Integer.parseInt(cusIDField.getText())) {
		            			uniqueID = false;
		            		}
		            		if (customerTableData.get(i).getEmail().equalsIgnoreCase(cusEmailField.getText())) {
		            			uniqueEmail = false;
		            		}
	            		}
            		}
            	}catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure value of CusId is INT");
                    alert.show();
            	}
            	
            	if (uniqueID && uniqueEmail && !cusIDField.getText().isEmpty() && !cusNameField.getText().isEmpty() && !cusEmailField.getText().isEmpty() && 
            			!cusPhoneNumField.getText().isEmpty() && !cusAddressField.getText().isEmpty() && !cusPCCardField.getText().isEmpty() && !cusSavedCardField.getText().isEmpty()) {
	                
            		try (Statement stmt = conn1.createStatement()){
            			customerTableData.add(new Customer(Integer.parseInt(cusIDField.getText()), cusNameField.getText(), cusEmailField.getText(), 
	                			cusPhoneNumField.getText(), cusAddressField.getText(), cusPCCardField.getText(), cusSavedCardField.getText()));
	                	
	                	searchedCustomerTableData.add(new Customer(Integer.parseInt(cusIDField.getText()), cusNameField.getText(), cusEmailField.getText(), 
	                			cusPhoneNumField.getText(), cusAddressField.getText(), cusPCCardField.getText(), cusSavedCardField.getText()));
	                	
	                	String query = "INSERT INTO customer (CusID, Name, Email, PhoneNum, Address, PCCard, SavedCard) VALUES (" + cusIDField.getText() +", '" + cusNameField.getText() + "', '"
	                			+ cusEmailField.getText() + "', '" + cusPhoneNumField.getText() + "', '" + cusAddressField.getText() + "', '" + cusPCCardField.getText() + "', '" + cusSavedCardField.getText() + "')";
	                	
	                	stmt.executeUpdate(query);
	                	
	                	cusIDField.clear();
	                	cusNameField.clear();
	                	cusEmailField.clear();
	                	cusPhoneNumField.clear();
	                	cusAddressField.clear();
	                	cusPCCardField.clear();
	                	cusSavedCardField.clear();
	                  
	                }catch (SQLException ex) {   
        				ex.printStackTrace();                   				
            		} 
	                catch (RuntimeException a) {
	            		Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please make sure value of CusId is INT");
	                    alert.show();
	            	}
            	}
            	else if (!uniqueID) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure your CusID is unique.");
                    alert.show();
            	}
            	else if (!uniqueEmail) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure your Email is unique.");
                    alert.show();
            	}
            	else {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("One or multiple values are blank");
                    alert.show();
            	}
            }
        });
        
        temp.add(customers, 0, 1);
        
        temp.add(hb, 0, 2);
        temp.add(hb2, 0, 3);
        temp.add(hb3, 0, 4);
		
		return temp;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GridPane cusLoginScreen(){
		GridPane temp = new GridPane();
		TableView<CusLogin> cuslogins = new TableView<CusLogin>();
		boolean same = false;
		
		temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
		
        hb = new HBox();
		hb2 = new HBox();
		hb3 = new HBox();
		
		searchedCusLoginTableData.clear();
		cusLoginTableData.clear();
		
		try (Statement stmt = conn1.createStatement()) {
    		String query = "SELECT * FROM cuslogin";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int userID = rs.getInt("UserID");
				char accountType = rs.getString("AccountType").charAt(0);
				String password = rs.getString("Password");				
				
				cusLoginTableData.add(new CusLogin(userID, accountType, password));
				searchedCusLoginTableData.add(new CusLogin(userID, accountType, password));
			}
						
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Database has not yet been initalized.");
            alert.show();
			
		}
		
		TableColumn userID = new TableColumn("User ID");
		userID.setMinWidth(25);
		userID.setCellValueFactory(
                new PropertyValueFactory<CusLogin, Integer>("userID"));                             
		userID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		userID.setEditable(false);
		
		TableColumn accountType = new TableColumn("Account Type");
		accountType.setMinWidth(100);
		accountType.setCellValueFactory(
                new PropertyValueFactory<CusLogin, Character>("accountType"));                             
		accountType.setCellFactory(TextFieldTableCell.forTableColumn(new CharacterStringConverter()));
		accountType.setEditable(false);
        
        TableColumn cusPass = new TableColumn("Password");
        cusPass.setMinWidth(150);
        cusPass.setCellValueFactory(
                new PropertyValueFactory<CusLogin, String>("password"));                             
        cusPass.setCellFactory(TextFieldTableCell.forTableColumn());
        cusPass.setEditable(false);
        
        
        cuslogins.setItems(searchedCusLoginTableData);
        cuslogins.getColumns().addAll(userID, accountType, cusPass);
		
        searchCusLogID.setPromptText("User ID");
        
        cusUserIDField.setPromptText("User ID");
        cusUserIDField.setPrefWidth(100);
        cusAccountTypeField.setPromptText("Account Type");
        cusAccountTypeField.setPrefWidth(100);
        cusPasswordField.setPromptText("Password");
        cusPasswordField.setPrefWidth(100);
        
        
        hb.getChildren().addAll(searchCusLogID, searchCusLogin, del);
        hb2.getChildren().addAll(cusUserIDField, cusAccountTypeField, cusPasswordField, add);
        hb3.getChildren().addAll(backEmp);
        
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try{
                	CusLogin temp = cuslogins.getSelectionModel().getSelectedItem();                	
                	if (temp == null) {
                        throw new RuntimeException();
                	}
                	
                	Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Are you sure you want to delete the selected entry");
                    
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK && temp != null) {
                        	
                        	searchedCusLoginTableData.remove(temp);
                        	
                        	try (Statement stmt = conn1.createStatement()) {
                        		String query = "\n" + 
                                        "   DELETE FROM cuslogin\n" +
                                        "    WHERE UserID = '" + temp.getUserID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                        		stmt.executeUpdate(query);
                        		                    			
                        	} catch (SQLException ex) {   
                				ex.printStackTrace();                   				
                    		}                    			
                        }
                    });
                    
                }catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Select a entry to Delete.");
                    alert.show();
            	}
            }
        });
        
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	boolean uniqueID = true;
            	
            	try {
            	for (int i = 0; i < cusLoginTableData.size(); i++) {            		
	            		if (!cusUserIDField.getText().isEmpty()) {
		            		if (cusLoginTableData.get(i).getUserID() == Integer.parseInt(cusUserIDField.getText())) {
		            			uniqueID = false;
		            		}
	            		}
            		}
            	}catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure value of UserID is INT");
                    alert.show();
            	}
            	
            	if (uniqueID && !cusUserIDField.getText().isEmpty() && !cusAccountTypeField.getText().isEmpty() && !cusPasswordField.getText().isEmpty() && cusAccountTypeField.getText().length() == 1
            			&& (cusAccountTypeField.getText().equals("R") ||cusAccountTypeField.getText().equals("G"))) {
            		try (Statement stmt = conn1.createStatement()){
            			cusLoginTableData.add(new CusLogin(Integer.parseInt(cusUserIDField.getText()), cusAccountTypeField.getText().charAt(0), cusPasswordField.getText()));
	                	
            			searchedCusLoginTableData.add(new CusLogin(Integer.parseInt(cusUserIDField.getText()), cusAccountTypeField.getText().charAt(0), cusPasswordField.getText()));
	                	
	                	String query = "INSERT INTO cuslogin (UserID, Password, AccountType) VALUES (" + cusUserIDField.getText() +", '" + cusPasswordField.getText() + "', '"
	                			+ cusAccountTypeField.getText() + "')";
	                	
	                	stmt.executeUpdate(query);
	                	
	                	cusUserIDField.clear();
	                	cusAccountTypeField.clear();
	                	cusPasswordField.clear();

	                  
	                }catch (SQLException ex) {  
	                	Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please create a Customer with this CusID/UserID before making this customer login.");
	                    alert.show();
	                    searchedCusLoginTableData.remove(searchedCusLoginTableData.size()-1);
	                    cusLoginTableData.remove(cusLoginTableData.size()-1);
	                    //ex.printStackTrace();                   				
            		} 
	                catch (RuntimeException a) {
	            		Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please make sure value of UserID is INT");
	                    alert.show();
	            	}
            	}
            	else if (uniqueID && !cusUserIDField.getText().isEmpty() && cusAccountTypeField.getText().isEmpty() && !cusPasswordField.getText().isEmpty()) {
            		try (Statement stmt = conn1.createStatement()){
            			cusLoginTableData.add(new CusLogin(Integer.parseInt(cusUserIDField.getText()), 'G', cusPasswordField.getText()));
	                	
            			searchedCusLoginTableData.add(new CusLogin(Integer.parseInt(cusUserIDField.getText()), 'G', cusPasswordField.getText()));
	                	
	                	String query = "INSERT INTO cuslogin (UserID, Password) VALUES (" + cusUserIDField.getText() +", '" + cusPasswordField.getText() + "')";
	                	
	                	stmt.executeUpdate(query);
	                	
	                	cusUserIDField.clear();
	                	cusAccountTypeField.clear();
	                	cusPasswordField.clear();

	                  
	                }catch (SQLException ex) {   
        				//ex.printStackTrace(); 
	                	Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please create a Customer with this CusID/UserID before making this customer.");
	                    alert.show();
	                    searchedCusLoginTableData.remove(searchedCusLoginTableData.size()-1);
	                    cusLoginTableData.remove(cusLoginTableData.size()-1);
            		} 
	                catch (RuntimeException a) {
	            		Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please make sure value of UserID is INT");
	                    alert.show();
	            	}
            	}
            	else if(!(cusAccountTypeField.getText().length() <= 1) || !(cusAccountTypeField.getText().equals("R") || cusAccountTypeField.getText().equals("G")
            			|| cusAccountTypeField.getText().isEmpty())) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Account Type must be a CHAR 'R' or 'G'");
                    alert.show();
            	}
            	else if (!uniqueID) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure your UserID is unique.");
                    alert.show();
            	}
            	else {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("One or multiple values are blank");
                    alert.show();
            	}
            }
        });
        
        temp.add(cuslogins, 0, 1);
        
        temp.add(hb, 0, 2);
        temp.add(hb2, 0, 3);
        temp.add(hb3, 0, 4);
		
		return temp;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GridPane employeeScreen(){
		GridPane temp = new GridPane();
		TableView<Employee> employees = new TableView<Employee>();
		
		temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
		
        hb = new HBox();
		hb2 = new HBox();
		hb3 = new HBox();
		
		searchedEmployeeTableData.clear();
		employeeTableData.clear();
		empLoginTableData.clear();
		
		try (Statement stmt = conn1.createStatement()) {
    		String query = "SELECT * FROM employee";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int empID = rs.getInt("EmpID");
				String empName = rs.getString("Name");
				String info = rs.getString("Info");
				double empWage = rs.getDouble("Wage");
				int hoursWorked = rs.getInt("HoursWorked");
								
				searchedEmployeeTableData.add(new Employee(empID, empName, info, empWage, hoursWorked));
				employeeTableData.add(new Employee(empID, empName, info, empWage, hoursWorked));
			}
			
			query = "SELECT * FROM emplogin";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int userID = rs.getInt("UserID");
				String password = rs.getString("Password");
				String email = rs.getString("Email");
				
				empLoginTableData.add(new EmpLogin(userID, password, email));
			}
			
			
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Database has not yet been initalized.");
            alert.show();
			
		}
		
		TableColumn empid = new TableColumn("Emp ID");
		empid.setMinWidth(25);
		empid.setCellValueFactory(
                new PropertyValueFactory<Employee, Integer>("empID"));                             
		empid.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		empid.setEditable(false);
		
		TableColumn empName = new TableColumn("Name");
		empName.setMinWidth(100);
		empName.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("empName"));                             
		empName.setCellFactory(TextFieldTableCell.forTableColumn());
		empName.setEditable(false);
        
        TableColumn empInfo = new TableColumn("Info");
        empInfo.setMinWidth(150);
        empInfo.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("empInfo"));                             
        empInfo.setCellFactory(TextFieldTableCell.forTableColumn());
        empInfo.setEditable(false);
        
        TableColumn empWage = new TableColumn("Wage");
        empWage.setMinWidth(100);
        empWage.setCellValueFactory(
                new PropertyValueFactory<Employee, Double>("wage"));                             
        empWage.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        empWage.setEditable(false);
        
        TableColumn hoursWorked = new TableColumn("Hours Worked");
        hoursWorked.setMinWidth(100);
        hoursWorked.setCellValueFactory(
                new PropertyValueFactory<Employee, Integer>("hoursWorked"));                             
        hoursWorked.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        hoursWorked.setEditable(false);
        
        employees.setItems(searchedEmployeeTableData);
        employees.getColumns().addAll(empid, empName, empInfo, empWage, hoursWorked);
		
        searchEmployeeName.setPromptText("Customer Name");
        
        empIDField.setPromptText("Emp ID");
        empIDField.setPrefWidth(100);
        empNameField.setPromptText("Emp Name");
        empNameField.setPrefWidth(100);
        empInfoField.setPromptText("Info");
        empInfoField.setPrefWidth(100);
        empWageField.setPromptText("Wage");
        empWageField.setPrefWidth(100);
        empHoursWorkedField.setPromptText("Hours Worked");
        empHoursWorkedField.setPrefWidth(100);
        
        hb.getChildren().addAll(searchEmployeeName, searchEmployee, del);
        hb2.getChildren().addAll(empIDField, empNameField, empInfoField, empWageField, empHoursWorkedField, add);
        hb3.getChildren().addAll(backEmp);
        
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try{
                	Employee temp = employees.getSelectionModel().getSelectedItem();
                	
                	if (temp == null) {
                        throw new RuntimeException();
                	}
                	
                	Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Are you sure you want to delete the selected entry? It will also delete all dependent entries in other tables.");
                    
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK && temp != null) {
                        	int temp3 = 0;
                        	searchedEmployeeTableData.remove(temp);
                        	for (int i = 0; i < empLoginTableData.size(); i++) {                     		
                        		if (temp.getEmpID() == empLoginTableData.get(i).getUserID()) {
                        			temp3 = i;
                        		}
                        	}
                        	EmpLogin temp2 = empLoginTableData.get(temp3);
                        	empLoginTableData.remove(temp2);
                        	try (Statement stmt = conn1.createStatement()) {
                        		String query = "\n" + 
                                        "   DELETE FROM emplogin\n" +
                                        "    WHERE UserID = '" + temp2.getUserID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                        		stmt.executeUpdate(query);
                        		
                        		query = "\n" + 
                                        "   DELETE FROM employee\n" +
                                        "    WHERE EmpID = '" + temp.getEmpID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                    			stmt.executeUpdate(query);
                    			
                        	} catch (SQLException ex) {   
                				ex.printStackTrace();                   				
                    		}                    			
                        }
                    });
                    
                }catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Select a entry to Delete.");
                    alert.show();
            	}
            }
        });
        
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	boolean uniqueID = true;
            	
            	try {
            	for (int i = 0; i < employeeTableData.size(); i++) {            		
	            		if (!empIDField.getText().isEmpty()) {
		            		if (employeeTableData.get(i).getEmpID() == Integer.parseInt(empIDField.getText())) {
		            			uniqueID = false;
		            		}
	            		}
            		}
            	}catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure value of EmpID is INT");
                    alert.show();
            	}
            	
            	if (uniqueID && !empIDField.getText().isEmpty() && !empNameField.getText().isEmpty() && !empInfoField.getText().isEmpty() && 
            			!empWageField.getText().isEmpty() && !empHoursWorkedField.getText().isEmpty()) {
	                
            		try (Statement stmt = conn1.createStatement()){
            			employeeTableData.add(new Employee(Integer.parseInt(empIDField.getText()), empNameField.getText(), empInfoField.getText(), 
            					Double.parseDouble(empWageField.getText()), Integer.parseInt(empHoursWorkedField.getText())));
	                	
	                	searchedEmployeeTableData.add(new Employee(Integer.parseInt(empIDField.getText()), empNameField.getText(), empInfoField.getText(), 
            					Double.parseDouble(empWageField.getText()), Integer.parseInt(empHoursWorkedField.getText()))); 
	                	
	                	String query = "INSERT INTO employee (EmpID, Name, Info, Wage, HoursWorked) VALUES (" + empIDField.getText() +", '" + empNameField.getText() + "', '"
	                			+ empInfoField.getText() + "', " + empWageField.getText() + ", " + empHoursWorkedField.getText() + ")";
	                	
	                	stmt.executeUpdate(query);
	                	
	                	empIDField.clear();
	                	empNameField.clear();
	                	empInfoField.clear();
	                	empWageField.clear();
	                	empHoursWorkedField.clear();
	                  
	                }catch (SQLException ex) {   
        				ex.printStackTrace();                   				
            		} 
	                catch (RuntimeException a) {
	            		Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please make sure value of EmpId is INT");
	                    alert.show();
	            	}
            	}
            	else if (!uniqueID) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure your EmpId is unique.");
                    alert.show();
            	}
            	else {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("One or multiple values are blank");
                    alert.show();
            	}
            }
        });
        
        temp.add(employees, 0, 1);
        
        temp.add(hb, 0, 2);
        temp.add(hb2, 0, 3);
        temp.add(hb3, 0, 4);
		
		return temp;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GridPane purchaseScreen(){
		GridPane temp = new GridPane();
		TableView<Purchase> purchases = new TableView<Purchase>();
		
		temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
		
        hb = new HBox();
		hb2 = new HBox();
		hb3 = new HBox();
		
		searchedPurchaseTableData.clear();
		purchaseTableData.clear();
		customerTableData.clear();
		cusLoginTableData.clear();
		listOfItemsTableData.clear();
		
		try (Statement stmt = conn1.createStatement()) {
    		String query = "SELECT * FROM purchase";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String invoiceID = rs.getString("InvoiceID");
				String orderID = rs.getString("OrderID");
				int cusID = rs.getInt("CusID");
				double totalPrice = rs.getDouble("TotalPrice");
				double taxRate = rs.getDouble("TaxRate");
				String coupons = rs.getString("CouponCodes");
								
				searchedPurchaseTableData.add(new Purchase(invoiceID, orderID, cusID, totalPrice, taxRate, coupons));
				purchaseTableData.add(new Purchase(invoiceID, orderID, cusID, totalPrice, taxRate, coupons));
			}
	
			query = "SELECT * FROM customer";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int cusID = rs.getInt("CusID");
				String cusName = rs.getString("Name");
				String email = rs.getString("Email");
				String phoneNum = rs.getString("PhoneNum");
				String address = rs.getString("Address");
				String pcCard = rs.getString("PCCard");
				String savedCard = rs.getString("SavedCard");
				
				if (pcCard == null) {
					pcCard = "0";
				}
				
				if (savedCard == null) {
					savedCard = "0";
				}
								
				customerTableData.add(new Customer(cusID, cusName, email, phoneNum, address, pcCard, savedCard));
			}
			
			query = "SELECT * FROM cuslogin";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int userID = rs.getInt("UserID");
				char accountType = rs.getString("AccountType").charAt(0);
				String password = rs.getString("Password");				
				
				cusLoginTableData.add(new CusLogin(userID, accountType, password));
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
			Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Database has not yet been initalized.");
            alert.show();
			
		}
		
		TableColumn invoiceID = new TableColumn("Invoice ID");
		invoiceID.setMinWidth(25);
		invoiceID.setCellValueFactory(
                new PropertyValueFactory<Purchase, String>("invoiceID"));                             
		invoiceID.setCellFactory(TextFieldTableCell.forTableColumn());
		invoiceID.setEditable(false);
		
		TableColumn orderID = new TableColumn("Order ID");
		orderID.setMinWidth(150);
		orderID.setCellValueFactory(
                new PropertyValueFactory<Purchase, String>("orderID"));                             
		orderID.setCellFactory(TextFieldTableCell.forTableColumn());
		orderID.setEditable(false);
        
        TableColumn cusID = new TableColumn("Cus ID");
        cusID.setMinWidth(100);
        cusID.setCellValueFactory(
                new PropertyValueFactory<Purchase, Integer>("cusID"));                             
        cusID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        cusID.setEditable(false);
        
        TableColumn totalPrice = new TableColumn("Total Price");
        totalPrice.setMinWidth(100);
        totalPrice.setCellValueFactory(
                new PropertyValueFactory<Purchase, Double>("totalPrice"));                             
        totalPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        totalPrice.setEditable(false);
        
        TableColumn hoursWorked = new TableColumn("Tax Rate");
        hoursWorked.setMinWidth(100);
        hoursWorked.setCellValueFactory(
                new PropertyValueFactory<Purchase, Double>("taxRate"));                             
        hoursWorked.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        hoursWorked.setEditable(false);
        
        TableColumn couponCodes = new TableColumn("Coupon Codes");
        couponCodes.setMinWidth(100);
        couponCodes.setCellValueFactory(
                new PropertyValueFactory<Purchase, String>("couponCodes"));                             
        couponCodes.setCellFactory(TextFieldTableCell.forTableColumn());
        couponCodes.setEditable(false);
        
        purchases.setItems(searchedPurchaseTableData);
        purchases.getColumns().addAll(invoiceID, orderID, cusID, totalPrice, hoursWorked, couponCodes);
		
        searchInvoiceID.setPromptText("Invoice ID");
        
        invoiceIDField.setPromptText("Invoice ID");
        invoiceIDField.setPrefWidth(100);
        orderIDField.setPromptText("Order ID");
        orderIDField.setPrefWidth(100);
        cusIDField.setPromptText("Cus ID");
        cusIDField.setPrefWidth(100);
        totalPriceField.setPromptText("Total Price");
        totalPriceField.setPrefWidth(100);
        taxRateField.setPromptText("Tax Rate");
        taxRateField.setPrefWidth(100);
        couponCodesField.setPromptText("Coupon Codes");
        couponCodesField.setPrefWidth(100);
        
        hb.getChildren().addAll(searchInvoiceID, searchPurchase, del);
        hb2.getChildren().addAll(invoiceIDField, orderIDField, cusIDField, totalPriceField, taxRateField, couponCodesField, add);
        hb3.getChildren().addAll(backEmp);
        
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try{
                	Purchase temp = purchases.getSelectionModel().getSelectedItem();
                	
                	if (temp == null) {
                        throw new RuntimeException();
                	}
                	
                	Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Are you sure you want to delete the selected entry? It will also delete all dependent entries in other tables.");
                    
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK && temp != null) {
                        	int temp3 = 0;
                        	int temp5 = 0;
                        	int temp7 = 0;
                        	searchedEmployeeTableData.remove(temp);
                        	for (int i = 0; i < customerTableData.size(); i++) {                     		
                        		if (temp.getCusID() == customerTableData.get(i).getCusID()) {
                        			temp3 = i;
                        		}
                        	}
                        	
                        	for (int i = 0; i < cusLoginTableData.size(); i++) {                     		
                        		if (temp.getCusID() == cusLoginTableData.get(i).getUserID()) {
                        			temp5 = i;
                        		}
                        	}
                        	
                        	for (int i = 0; i < listOfItemsTableData.size(); i++) {                     		
                        		if (temp.getOrderID().equals(listOfItemsTableData.get(i).getOrderID())) {
                        			temp7 = i;
                        		}
                        	}
                        	
                        	Customer temp2 = customerTableData.get(temp3);
                        	CusLogin temp4 = cusLoginTableData.get(temp5);
                        	ListOfItems temp6 = listOfItemsTableData.get(temp7);
                        	//customerTableData.remove(temp2);
                        	//cusLoginTableData.remove(temp5);
                        	//listOfItemsTableData.remove(temp6);
                        	try (Statement stmt = conn1.createStatement()) {
                        		String query = "\n" + 
                                        "   DELETE FROM listofitems\n" +
                                        "    WHERE OrderID = '" + temp.getOrderID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                        		stmt.executeUpdate(query);
                        		
                        		query = "\n" + 
                                        "   DELETE FROM purchase \n" +
                                        "    WHERE CusID = '" + temp.getCusID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                    			stmt.executeUpdate(query);
                        		
                        		query = "\n" + 
                                        "   DELETE FROM cuslogin\n" +
                                        "    WHERE UserID = '" + temp.getCusID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                        		stmt.executeUpdate(query);
                        		
                        		query = "\n" + 
                                        "   DELETE FROM customer\n" +
                                        "    WHERE CusID = '" + temp.getCusID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                        		stmt.executeUpdate(query);
                        		
                        		searchedPurchaseTableData.clear();
                        		purchaseTableData.clear();
                        		customerTableData.clear();
                        		cusLoginTableData.clear();
                        		listOfItemsTableData.clear();
                        		
                        		query = "SELECT * FROM purchase";
                        		ResultSet rs = stmt.executeQuery(query);
                    			
                    			while(rs.next()) {
                    				String invoiceID = rs.getString("InvoiceID");
                    				String orderID = rs.getString("OrderID");
                    				int cusID = rs.getInt("CusID");
                    				double totalPrice = rs.getDouble("TotalPrice");
                    				double taxRate = rs.getDouble("TaxRate");
                    				String coupons = rs.getString("CouponCodes");
                    								
                    				searchedPurchaseTableData.add(new Purchase(invoiceID, orderID, cusID, totalPrice, taxRate, coupons));
                    				purchaseTableData.add(new Purchase(invoiceID, orderID, cusID, totalPrice, taxRate, coupons));
                    			}
                    	
                    			query = "SELECT * FROM customer";
                    			rs = stmt.executeQuery(query);
                    			
                    			while(rs.next()) {
                    				int cusID = rs.getInt("CusID");
                    				String cusName = rs.getString("Name");
                    				String email = rs.getString("Email");
                    				String phoneNum = rs.getString("PhoneNum");
                    				String address = rs.getString("Address");
                    				String pcCard = rs.getString("PCCard");
                    				String savedCard = rs.getString("SavedCard");
                    				
                    				if (pcCard == null) {
                    					pcCard = "0";
                    				}
                    				
                    				if (savedCard == null) {
                    					savedCard = "0";
                    				}
                    								
                    				customerTableData.add(new Customer(cusID, cusName, email, phoneNum, address, pcCard, savedCard));
                    			}
                    			
                    			query = "SELECT * FROM cuslogin";
                    			rs = stmt.executeQuery(query);
                    			
                    			while(rs.next()) {
                    				int userID = rs.getInt("UserID");
                    				char accountType = rs.getString("AccountType").charAt(0);
                    				String password = rs.getString("Password");				
                    				
                    				cusLoginTableData.add(new CusLogin(userID, accountType, password));
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
                				ex.printStackTrace();                   				
                    		}                    			
                        }
                    });
                    
                }catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Select a entry to Delete.");
                    alert.show();
            	}
            }
        });
        
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	boolean uniqueID = true;
            	
            	try {
            	for (int i = 0; i < purchaseTableData.size(); i++) {            		
	            		if (!invoiceIDField.getText().isEmpty()) {
		            		if (purchaseTableData.get(i).getInvoiceID().equals(invoiceIDField.getText())) {
		            			uniqueID = false;
		            		}
	            		}
            		}
            	}catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure value of InvoiceID has a value");
                    alert.show();
            	}
            	
            	if (uniqueID && !invoiceIDField.getText().isEmpty() && !orderIDField.getText().isEmpty() && !cusIDField.getText().isEmpty() && 
            			!totalPriceField.getText().isEmpty() && !taxRateField.getText().isEmpty()) {
	                
            		if (couponCodesField.getText().isEmpty()) {
            			couponCodesField.setText("");
            		}
            		
            		try (Statement stmt = conn1.createStatement()){
            			purchaseTableData.add(new Purchase(invoiceIDField.getText(), orderIDField.getText(), Integer.parseInt(cusIDField.getText()), 
            					Double.parseDouble(totalPriceField.getText()), Double.parseDouble(taxRateField.getText()), couponCodesField.getText()));
	                	
	                	searchedPurchaseTableData.add(new Purchase(invoiceIDField.getText(), orderIDField.getText(), Integer.parseInt(cusIDField.getText()), 
            					Double.parseDouble(totalPriceField.getText()), Double.parseDouble(taxRateField.getText()), couponCodesField.getText()));
	                	
	                	String query = "INSERT INTO purchase (InvoiceID, CusID, OrderID, TaxRate, TotalPrice, CouponCodes) VALUES ('" + invoiceIDField.getText() +"', " + cusIDField.getText() + ", '"
	                			+ orderIDField.getText() + "', " + taxRateField.getText() + ", " + totalPriceField.getText() + ", '" + couponCodesField.getText() +"')";
	                	
	                	stmt.executeUpdate(query);
	                	
	                	invoiceIDField.clear();
	                	orderIDField.clear();
	                	cusIDField.clear();
	                	totalPriceField.clear();
	                	taxRateField.clear();
	                	couponCodesField.clear();

	                  
	                }catch (SQLException ex) {   
        				ex.printStackTrace();                   				
            		} 
	                catch (RuntimeException a) {
	            		Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please make sure value of Cus ID is INT and Total Price and Tax Rate a DOUBLES ");
	                    alert.show();
	            	}
            	}
            	else if (!uniqueID) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure your InvoiceID is unique.");
                    alert.show();
            	}
            	else {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("One or multiple values are blank");
                    alert.show();
            	}
            }
        });
        
        temp.add(purchases, 0, 1);
        
        temp.add(hb, 0, 2);
        temp.add(hb2, 0, 3);
        temp.add(hb3, 0, 4);
		
		return temp;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GridPane empLoginScreen(){
		GridPane temp = new GridPane();
		TableView<EmpLogin> emplogins = new TableView<EmpLogin>();
		
		temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
		
        hb = new HBox();
		hb2 = new HBox();
		hb3 = new HBox();
		
		searchedEmpLoginTableData.clear();
		empLoginTableData.clear();
		
		
		try (Statement stmt = conn1.createStatement()) {
    		String query = "SELECT * FROM emplogin";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int userID = rs.getInt("UserID");
				String password = rs.getString("Password");
				String email = rs.getString("Email");
				
				empLoginTableData.add(new EmpLogin(userID, password, email));
				searchedEmpLoginTableData.add(new EmpLogin(userID, password, email));
			}
	

		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Database has not yet been initalized.");
            alert.show();
			
		}
		
		TableColumn userID = new TableColumn("User ID");
		userID.setMinWidth(25);
		userID.setCellValueFactory(
                new PropertyValueFactory<EmpLogin, Integer>("userID"));                             
		userID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		userID.setEditable(false);
		
		TableColumn empPassword = new TableColumn("Password");
		empPassword.setMinWidth(100);
		empPassword.setCellValueFactory(
                new PropertyValueFactory<EmpLogin, String>("password"));                             
		empPassword.setCellFactory(TextFieldTableCell.forTableColumn());
		empPassword.setEditable(false);
        
        TableColumn empEmail = new TableColumn("Email");
        empEmail.setMinWidth(150);
        empEmail.setCellValueFactory(
                new PropertyValueFactory<EmpLogin, String>("email"));                             
        empEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        empEmail.setEditable(false);
        
        emplogins.setItems(searchedEmpLoginTableData);
        emplogins.getColumns().addAll(userID, empPassword, empEmail);
		
        searchEmpLogID.setPromptText("User ID");
        
        empIDField.setPromptText("User ID");
        empIDField.setPrefWidth(100);
        empPasswordField.setPromptText("Password");
        empPasswordField.setPrefWidth(100);
        empEmailField.setPromptText("Email");
        empEmailField.setPrefWidth(100);
                
        hb.getChildren().addAll(searchEmpLogID, searchEmpLogin, del);
        hb2.getChildren().addAll(empIDField, empPasswordField, empEmailField, add);
        hb3.getChildren().addAll(backEmp);
        
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try{
                	EmpLogin temp = emplogins.getSelectionModel().getSelectedItem();
                	
                	if (temp == null) {
                        throw new RuntimeException();
                	}
                	
                	Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Are you sure you want to delete the selected entry?");
                    
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK && temp != null) {
                        	searchedEmpLoginTableData.remove(temp);

                        	try (Statement stmt = conn1.createStatement()) {
                        		String query = "\n" + 
                                        "   DELETE FROM emplogin\n" +
                                        "    WHERE UserID = '" + temp.getUserID() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                        		stmt.executeUpdate(query);
                        		                   			
                        	} catch (SQLException ex) {   
                				ex.printStackTrace();                   				
                    		}                    			
                        }
                    });
                    
                }catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Select a entry to Delete.");
                    alert.show();
            	}
            }
        });
        
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	boolean uniqueID = true;
            	boolean emailID = true;
            	
            	try {
            	for (int i = 0; i < empLoginTableData.size(); i++) {            		
	            		if (!empIDField.getText().isEmpty()) {
		            		if (empLoginTableData.get(i).getUserID() == Integer.parseInt(empIDField.getText())) {
		            			uniqueID = false;
		            		}
		            		if (empLoginTableData.get(i).getEmail().equalsIgnoreCase(empEmailField.getText())) {
		            			emailID = false;
		            		}
	            		}
            		}
            	
            	}catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure value of EmpID has a value of type INT and Email has a value of type STRING.");
                    alert.show();
            	}
            	
            	if (uniqueID && emailID && !empIDField.getText().isEmpty() && !empPasswordField.getText().isEmpty() && !empEmailField.getText().isEmpty()) {
	                            		
            		try (Statement stmt = conn1.createStatement()){
            			empLoginTableData.add(new EmpLogin(Integer.parseInt(empIDField.getText()), empPasswordField.getText(), empEmailField.getText()));
	                	
	                	searchedEmpLoginTableData.add(new EmpLogin(Integer.parseInt(empIDField.getText()), empPasswordField.getText(), empEmailField.getText()));
	                	
	                	String query = "INSERT INTO emplogin (UserID, Password, Email) VALUES (" + empIDField.getText() +", '" + empPasswordField.getText() + "', '"
	                			+ empEmailField.getText() + "')";
	                	
	                	stmt.executeUpdate(query);
	                	
	                	empIDField.clear();
	                	empPasswordField.clear();
	                	empEmailField.clear();
	                  
	                }catch (SQLException ex) {   
        				//ex.printStackTrace(); 
	                	Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please make sure to create an Employee with this UserID/EmpID before creating this employee login.");
	                    alert.show();
	                    empLoginTableData.remove(empLoginTableData.size()-1);
	                    searchedEmpLoginTableData.remove(searchedEmpLoginTableData.size()-1);
            		} 
	                catch (RuntimeException a) {
	            		Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please make sure value of UserID is INT.");
	                    alert.show();
	            	}
            	}
            	else if (!uniqueID || !emailID) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure your UserID and Email are unique.");
                    alert.show();
            	}
            	else {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("One or multiple values are blank");
                    alert.show();
            	}
            }
        });
        
        temp.add(emplogins, 0, 1);
        
        temp.add(hb, 0, 2);
        temp.add(hb2, 0, 3);
        temp.add(hb3, 0, 4);
		
		return temp;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GridPane listOfItemsScreen(){
		GridPane temp = new GridPane();
		TableView<ListOfItems> listofitems = new TableView<ListOfItems>();
		
		temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
		
        hb = new HBox();
		hb2 = new HBox();
		hb3 = new HBox();
		
		searchedListOfItemsTableData.clear();
		listOfItemsTableData.clear();
		
		try (Statement stmt = conn1.createStatement()) {
    		String query = "SELECT * FROM listofitems";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int listIndex = rs.getInt("ListIndex");
				String orderID2 = rs.getString("OrderID");
				String itemIDs = rs.getString("ItemIDs");
				
				listOfItemsTableData.add(new ListOfItems(listIndex, orderID2, itemIDs));
				searchedListOfItemsTableData.add(new ListOfItems(listIndex, orderID2, itemIDs));
			}
	

		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Database has not yet been initalized.");
            alert.show();
			
		}
		
		TableColumn index = new TableColumn("List Index");
		index.setMinWidth(25);
		index.setCellValueFactory(
                new PropertyValueFactory<ListOfItems, Integer>("listIndex"));                             
		index.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		index.setEditable(false);
		
		TableColumn orderID = new TableColumn("Order ID");
		orderID.setMinWidth(100);
		orderID.setCellValueFactory(
                new PropertyValueFactory<ListOfItems, String>("orderID"));                             
		orderID.setCellFactory(TextFieldTableCell.forTableColumn());
		orderID.setEditable(false);
        
        TableColumn itemIDs = new TableColumn("Item IDs");
        itemIDs.setMinWidth(150);
        itemIDs.setCellValueFactory(
                new PropertyValueFactory<ListOfItems, String>("itemIDs"));                             
        itemIDs.setCellFactory(TextFieldTableCell.forTableColumn());
        itemIDs.setEditable(false);
        
        listofitems.setItems(searchedListOfItemsTableData);
        listofitems.getColumns().addAll(index, orderID, itemIDs);
		
        searchOrderID.setPromptText("Order ID");
        
        indexField.setPromptText("List Index");
        indexField.setPrefWidth(100);
        orderIDField.setPromptText("Order ID");
        orderIDField.setPrefWidth(100);
        itemIDsField.setPromptText("Item IDs");
        itemIDsField.setPrefWidth(100);
                
        hb.getChildren().addAll(searchOrderID, searchListOfItems, del);
        hb2.getChildren().addAll(indexField, orderIDField, itemIDsField, add);
        hb3.getChildren().addAll(backEmp);
        
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try{
                	ListOfItems temp = listofitems.getSelectionModel().getSelectedItem();
                	
                	if (temp == null) {
                        throw new RuntimeException();
                	}
                	
                	Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Are you sure you want to delete the selected entry?");
                    
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK && temp != null) {
                        	searchedListOfItemsTableData.remove(temp);

                        	try (Statement stmt = conn1.createStatement()) {
                        		String query = "\n" + 
                                        "   DELETE FROM listofitems\n" +
                                        "    WHERE ListIndex = '" + temp.getListIndex() + "'\n" +
                                        "   \n" +
                                        "";
                        		
                        		stmt.executeUpdate(query);
                        		                   			
                        	} catch (SQLException ex) {   
                				ex.printStackTrace();                   				
                    		}                    			
                        }
                    });
                    
                }catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Select a entry to Delete.");
                    alert.show();
            	}
            }
        });
        
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	boolean uniqueID = true;
            	
            	try {
            	for (int i = 0; i < listOfItemsTableData.size(); i++) {            		
	            		if (!indexField.getText().isEmpty()) {
		            		if (listOfItemsTableData.get(i).getListIndex() == Integer.parseInt(indexField.getText())) {
		            			uniqueID = false;
		            		}
	            		}
            		}
            	
            	}catch (RuntimeException a) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure value of ListIndex has a value of type INT.");
                    alert.show();
            	}
            	
            	if (uniqueID && !indexField.getText().isEmpty() && !orderIDField.getText().isEmpty() && !itemIDsField.getText().isEmpty()) {
	                            		
            		try (Statement stmt = conn1.createStatement()){
            			listOfItemsTableData.add(new ListOfItems(Integer.parseInt(indexField.getText()), orderIDField.getText(), itemIDsField.getText()));
	                	
	                	searchedListOfItemsTableData.add(new ListOfItems(Integer.parseInt(indexField.getText()), orderIDField.getText(), itemIDsField.getText()));
	                	
	                	String query = "INSERT INTO listofitems (ListIndex, OrderID, ItemIDs) VALUES (" + indexField.getText() +", '" + orderIDField.getText() + "', '"
	                			+ itemIDsField.getText() + "')";
	                	
	                	stmt.executeUpdate(query);
	                	
	                	indexField.clear();
	                	orderIDField.clear();
	                	itemIDsField.clear();
	                  
	                }catch (SQLException ex) {   
        				//ex.printStackTrace(); 
	                	Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please make sure to create a Puchase with this OrderID before creating this List Of Items.");
	                    alert.show();
	                    listOfItemsTableData.remove(listOfItemsTableData.size()-1);
	                    searchedListOfItemsTableData.remove(searchedListOfItemsTableData.size()-1);
            		} 
	                catch (RuntimeException a) {
	            		Alert alert = new Alert(AlertType.ERROR);
	                    alert.setContentText("Please make sure value of List Index is INT.");
	                    alert.show();
	            	}
            	}
            	else if (!uniqueID) {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please make sure your List Index is unique.");
                    alert.show();
            	}
            	else {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("One or multiple values are blank");
                    alert.show();
            	}
            }
        });
        
        temp.add(listofitems, 0, 1);
        
        temp.add(hb, 0, 2);
        temp.add(hb2, 0, 3);
        temp.add(hb3, 0, 4);
		
		return temp;
	}
	
	
	public static void main(String[] args) {
        launch(args);
    }
	
}
