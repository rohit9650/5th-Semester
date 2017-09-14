import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeBean implements Serializable{

	String firstName, lastName, department;
	int age;
	float salary;
	ArrayList<String> skillSet;
	
	public EmployeeBean(){

		skillSet = new ArrayList<String>();
		this.firstName = "";
		this.lastName = "";
		this.age = 0;
		this.salary = 0.0f;
		this.department = "";
		
	}
	
	public EmployeeBean(String fname, String lname){
	
		firstName = fname;
		lastName = lname;
		skillSet = new ArrayList<String>();
	
	}
	
	public String getfirstName(){
	
		return this.firstName;
	
	}
	
	public void setfirstName(String fname){
	
		this.firstName = fname;
	
	}	

	public String getlastName(){
	
		return this.lastName;
	
	}
	
	public void setlastName(String lname){
	
		this.lastName = lname;
	
	}	
	
	public int getage(){
	
		return this.age;
	
	}	

	public void setage(int age){

		this.age = age;
	
	}
	
	public String getdepartment(){
	
		return this.department;
	
	}	

	public void setdepartment(String department){

		this.department = department;
	
	}
	
	public float getsalary(){
	
		return this.salary;
	
	}	

	public void setsalary(float marks){

		this.salary = marks;
	
	}

	public String getskillSet(){
	
		String skills = String.join(", ", skillSet);
		
		return skills;
	
	}	

	public void setskillSet(String skill){

		skillSet.add(skill);
	
	}

}
