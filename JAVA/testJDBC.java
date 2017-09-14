//STEP 1. Import required packages
import java.sql.*;

public class testJDBC {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/student";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      
      
      //Num Of Student
      
      sql = "select count(distinct (roll_no)) from stu";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         //Retrieve by column name
		System.out.println("No. Of Studtnts : " + rs.getInt(1));
         //Display values
      }
      
      //
      
      //avgerage marks per subject
      
      sql = "select sub_name,avg(marks) as avg_marks from result GROUP BY sub_name";
      rs = stmt.executeQuery(sql);
      
      while(rs.next()){
         //Retrieve by column name
		System.out.println("Subjct : " + rs.getString("sub_name") + " Avg Marks : " + rs.getInt("avg_marks"));
         //Display values
      }
      
      //
      
       //Highest marks
      
      sql = "select name from stu where roll_no in (select roll_no from (select roll_no,sum(marks) as marks from result GROUP by roll_no) as total WHERE marks = (SELECT max(marks) as max_marks from (select roll_no,sum(marks) as marks from result GROUP by roll_no) as total))";
      rs = stmt.executeQuery(sql);
      
      while(rs.next()){
         //Retrieve by column name
		System.out.println("\nHighest marks getter student : " + rs.getString("name"));
         //Display values
      }
      
      //
      
      //Num of students with Division
      
      sql = "select sum(avg(marks) > 400) as first, as avg_marks from result GROUP BY sub_name";
      rs = stmt.executeQuery(sql);
      
		//System.out.println("Number of first division student : " + rs.getInt("first") + "\nNumber of first division student :  " + rs.getInt("sec") + "Number of third division student : " + rs.getInt("third"));
      
      
      //
      
       //Subject toppper
      
      sql = "select roll_no, marks, sub_name from result where (marks, sub_name) in (select max(marks), sub_name from result group by sub_name)";
      rs = stmt.executeQuery(sql);
      
      while(rs.next()){
         //Retrieve by column name
		System.out.println("Student : " + rs.getInt("roll_no") + " has highest marks " + rs.getInt("marks") + " in " + rs.getString("sub_name"));
         //Display values
      }
      
      //
      
       //Average marks
      
      sql = "select roll_no,avg(marks) as avg from result GROUP BY roll_no";
      rs = stmt.executeQuery(sql);
      
      while(rs.next()){
         //Retrieve by column name
		System.out.println("Roll No : " + rs.getString("roll_no") + "  Avg. Marks  : " + rs.getInt("avg"));
         //Display values
      }
      
      //
      
       //Second Division
      
      sql = "select sum(marks) as tot ,roll_no from result group by roll_no order by tot desc";
      rs = stmt.executeQuery(sql);
      
      rs.first();rs.next();
         //Retrieve by column name
		System.out.println("Second ighest marks getter student Roll Number: " + rs.getInt("roll_no"));
         //Display values
      
      //
      
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample
