# Database Management System

## Table of Contents
- [Database Management System](#database-management-system)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Development](#development)
  - [Planning](#planning)
  - [Use Cases](#use-cases)
    - [Customer](#customer)
    - [Database Administrator (DBA)/Employee](#database-administrator-dbaemployee)
  - [Conclusion](#conclusion)


## Introduction

This ```Database Management System``` uses ```JDBC``` to connect a java application to a ```SQL``` server with the ability to create tables, drop them, populate them, search through them, as well as adding and deleting entries. This DBMS is meant to be functional to create and manage a Point of Sale (POS) System for a store.

- ```Database Management System``` created using ```SQL``` and ```Java```.
- The ```IDE``` that was used to create the project is ```Eclipse```.
- The ```Database``` that was used for this project was ```Oracle 21c```.

This project can also be used as a framework for creating similar applications for database management in the future.

## Development 

Originally this project was meant to be created using ```Visual Studio Code``` due to the better ```Git``` integration that it provides however due to some issues with ```Maven``` and ```Visual Studio Code``` not accessing the correct ```JDBC``` driver the ```IDE``` was changed to ```Eclipse```. After the project was completed, it is also runnable on ```Visual Studio Code``` after adding the required arguments due to the use of a ```Maven``` project in ```Eclipse``` which uses the correct ```JDBC``` driver.

## Planning

To streamline the database creation a plan was needed for the database. To complete this an ```Entity Relationship Diagram``` was used:

[<img src=resources/ER_Diagram.png height=700>](resources/ER_Diagram.png)

## Use Cases

This application was developed for two main use cases, first is the customer and second is the Database Administrator (DBA)/Employee.

To attach the database to the application a ```command line argument``` is expected:

```bat
java -jar DatabaseManagementSystem.jar jdbc:oracle:thin:system/password@localhost:1521:xe

:: start javaw -jar DatabaseManagementSystem.jar jdbc:oracle:thin:system/password@localhost:1521:xe
:: Change 'system' to be the user, 'password' to be the password, 'localhost' to be the host, 1521 
::  to be the port and 'xe' to be the service id.
:: Example: jdbc:oracle:thin:system/RomitSagu@localhost:1521:xe
:: Note this DBMS only works with Java 19+ and Oracle 21c XE at the time of its creation.
```
This is the ```launch.bat``` which is included in the release.

### Customer

Customers are expected to be able to interface with the application like they would any e-retailer had have a similar experience. 

After the configuration above in [Use Cases](#use-cases) is complete to access the DBA/Employee use case from the main menu select DBA/Employee.

[<img src=resources/main_menu.png height=200>](resources/main_menu.png)

From this screen the customer can login using the email and password of a customer in the database:

[<img src=resources/customer_login.png height=225>](resources/customer_login.png)

After loging in, the customer can search for and select items they which to purchase, logout or buy the selected items:

[<img src=resources/customer_purchase.png height=450>](resources/customer_purchase.png)

When the customer presses the buy button, they are presented with a confirmation screen to checkout with what they have selected and are given the subtotal and total after tax. From here they can press the checkout button or cancel the order and be returned to the screen where they can choose the items they wish to purchase:

[<img src=resources/customer_checkout.png height=350>](resources/customer_checkout.png)

If the customer checks out they are presented with a screen that has their InvoiceID for the order they just put in and they are given the option to logout:

[<img src=resources/customer_invoice.png height=225>](resources/customer_invoice.png)

### Database Administrator (DBA)/Employee

The Database Administrator (DBA)/Employees are expected to have the ability to create the tables for the application, populate the tables, query the tables, add entries to the tables, delete entries from the tables and to drop the tables.

After the configuration above in [Use Cases](#use-cases) is complete to access the DBA/Employee use case from the main menu select Employees.

[<img src=resources/main_menu.png height=200>](resources/main_menu.png)

From this screen the DBA/Employee can login using the email and password of an Employee in the database:

[<img src=resources/employee_login.png height=225>](resources/employee_login.png)

The default login for the only local user is Email: Admin and Password: Admin. After this is entered the DBA is met with an error message saying that the database has not yet been created and that error will persist for any tab that the DBA tries to enter:

[<img src=resources/employee_menu.png height=225>](resources/employee_menu.png)

After the error message has been cleared the options for the DBA become available:

[<img src=resources/employee_menu2.png height=225>](resources/employee_menu2.png)

After clicking out of the error message the DBA should first create the tables and populate them with their respective buttons:

[<img src=resources/employee_create_tables.png height=225>](resources/employee_create_tables.png)

[<img src=resources/employee_populate_tables.png height=225>](resources/employee_populate_tables.png) 

From here the many options open for the DBA, they could drop the tables as a test, they could go and view the tables that were created and populated with data, they could also logout of the local user’s account and login with the information of an employee. Below the data for the Customer table is shown as well as the options that are associated with it.

When accessing one of the table’s interfaces the DBA can search for specific information based on the search available for that table for example in the Customer table customers can be searched for by name. The DBA can also enter data to create a new entry in the database at this point as well. 

[<img src=resources/employee_customer_table.png height=500>](resources/employee_customer_table.png) 

The DBA can then also drop the tables:

[<img src=resources/employee_drop_tables.png height=225>](resources/employee_drop_tables.png)

## Conclusion

To conclude the application was created to manage a database that would act as a ```Point of Sale System``` for a store such as Shoppers Drugmart. There are two parts of the application one for the ```Database Administrator (DBA)``` and a second for ```Customers``` who wish to purchase items. The application uses ```Oracle 21c``` and a ```JDBC``` driver that works to connect the application to a database. A production ```release``` is available to download and will work as documented after changing the ```command line arguments``` in the ```launch.bat``` file.

The final release was packaged using Eclipse’s inbuilt export tool and was exported as a ```.jar``` file.