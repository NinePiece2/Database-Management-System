package project;

public class Employee {
	private int empID;
	private String empName;
	private String empInfo;
	private	double wage;
	private int hoursWorked;
	
	
	public Employee(int empID, String empName, String empInfo, double wage, int hoursWorked) {
		this.empID = empID;
		this.empName = empName;
		this.empInfo = empInfo;
		this.wage = wage;
		this.hoursWorked = hoursWorked;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(String empInfo) {
		this.empInfo = empInfo;
	}

	public double getWage() {
		return wage;
	}

	public void setRating(double wage) {
		this.wage = wage;
	}

	public int getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	
}	
