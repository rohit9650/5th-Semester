public class TestBean{

	public static void main(String args[]){
	
		EmployeeBean e = new EmployeeBean();
		e.setfirstName("Sahil");
		e.setlastName("Nishal");
		e.setage(20);
		e.setsalary(300);
		e.setdepartment("PACS");
		e.setskillSet("C++");
		e.setskillSet("Java");

		System.out.println(e.getfirstName() + " " + e.getlastName());
		System.out.println(e.getage() + " : " + e.getsalary() + " : "  + e.getdepartment() + " : " + e.getskillSet());
		
		System.out.println();
		
		StudentBean s = new StudentBean();
		s.setfirstName("Sahil");
		s.setlastName("Nishal");
		s.setage(20);
		s.setgrade(3);
		s.setaverageMarks(-2);
		
		System.out.println(s.getfirstName() + " " + s.getlastName());
		System.out.println(s.getage() + " : " + s.getgrade() + " : " +  s.getaverageMarks());
	
	}

}
