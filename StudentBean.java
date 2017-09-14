import java.io.Serializable;

public class StudentBean implements Serializable{

	String firstName, lastName;
	int age, grade;
	float averageMarks;
	
	public StudentBean(){
	
		this.firstName = "";
		this.lastName = "";
		this.age = 0;
		this.grade = 0;
		this.averageMarks = 0.0f;
	
	}
	
	public StudentBean(String fname, String lname){
	
		firstName = fname;
		lastName = lname;
	
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
	
	public int getgrade(){
	
		return this.grade;
	
	}	

	public void setgrade(int grade){

		this.grade = grade;
	
	}
	
	public float getaverageMarks(){
	
		return this.averageMarks;
	
	}	

	public void setaverageMarks(float marks){

		this.averageMarks = marks;
	
	}

}
