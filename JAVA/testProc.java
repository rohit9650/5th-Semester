import java.sql.*;  
public class testProc {  
	public static void main(String[] args){  
	  CallableStatement stmt = null;
	  Connection con = null; 
	 try{ 
	Class.forName("com.mysql.jdbc.Driver");  
	con = DriverManager.getConnection( "jdbc:mysql://localhost/student","root","");  
	stmt	= con.prepareCall("{call proc(?)}");  
		stmt.registerOutParameter(1,Types.INTEGER);
		stmt.execute();
		int row = stmt.getInt(1);  
		stmt.close();
		System.out.println(row);
		System.out.println("success");  
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
         if(con!=null)
            con.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   } 
}
} 
