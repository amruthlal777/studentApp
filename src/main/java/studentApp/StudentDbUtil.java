package studentApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource)
	{
		 dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception
	{
		List<Student> studList = new ArrayList<Student>();
				
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try 
		{
		myConn = dataSource.getConnection();
		
		String sql = "select * from student order by last_name";
		
		myStmt = myConn.createStatement();
		
		myRs = myStmt.executeQuery(sql);
		
		while(myRs.next())
		{
			int id = myRs.getInt("id");
			
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			
			Student st = new Student(id, firstName, lastName, email);
		    
			studList.add(st);
		}
      
    		return studList;
		}
		finally
		{
			close(myConn,myStmt,myRs);
		}
		
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			
			if(myRs != null)
			{
				myRs.close();
			}
			if(myConn != null)
			{
				myConn.close();
			}
			if(myStmt != null)
			{
				myStmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addStudents(Student student) throws Exception{
		
     Connection myConn = null;
     
     PreparedStatement myStmt = null;
     
    try 
    {
		myConn = dataSource.getConnection();
		
		String sql = "insert into student "
				     + "(first_name,last_name,email) "
				     + "values(?,?,?)";
		
		myStmt = myConn.prepareStatement(sql);
		
		myStmt.setString(1,student.getFirstName());
		myStmt.setString(2,student.getLastName());
		myStmt.setString(3,student.getEmail());
		
		myStmt.execute();
		
	} 
    finally
    {
       close(myConn,myStmt,null);
	}
	}

	public Student getStudent(String id) throws Exception  {
		
		Connection myConn = null;
		PreparedStatement myStmt  = null;
		ResultSet myRs    = null;
		Student theStudent = null;
		
		
	  try 
	  {
		int studentId = Integer.parseInt(id);
		System.out.println(studentId);

	    myConn = dataSource.getConnection();
	    
	    String sql = "select * from student where id=?";
	    
	    myStmt = myConn.prepareStatement(sql);
	    
	    myStmt.setInt(1,studentId);                                                                                                                                                                                                                                                                                                                                                                   
	    
	    System.out.println(sql);
	    
	    System.out.println(myStmt);
	    
	    myRs = myStmt.executeQuery();
		
	    if(myRs.next())
	    {
	    String firstName = myRs.getString("first_name");
	    String lastName  = myRs.getString("last_name");
	    String email     = myRs.getString("email");
	    int theId = myRs.getInt("id");
	    
	    theStudent = new Student(theId,firstName, lastName, email);
	    
	    System.out.println(theStudent);
	    }
	    else
	    {
	    	throw new Exception("could not find student Id");
	    }
	    
	    return theStudent;
	  }
	  finally
	  {
		  close(myConn,myStmt,myRs);
	  }
	    
	}

	public void updateStudent(Student theStudent) throws Exception{
		
	   Connection myConn = null;
	   PreparedStatement myStmt = null;
	   
	   System.out.println("am");
	   System.out.println(theStudent.getId());
	   
	   try
	   {
	       myConn = dataSource.getConnection();
           String sql = "update student set "
        		        +"first_name = ? ,last_name = ?,email =? "
        		        +"where id = ?";
           
           myStmt = myConn.prepareStatement(sql);
           
           myStmt.setString(1,theStudent.getFirstName());
           myStmt.setString(2,theStudent.getLastName());
           myStmt.setString(3,theStudent.getEmail());
           myStmt.setInt(4, theStudent.getId());
           
           myStmt.execute();  
           System.out.println("amruth");
           System.out.println(myStmt);
	   }
	   finally
	   {
		   close(myConn, myStmt, null);
	   
	   }
	   
	}

	public void deleteStudentData(int studentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt =null;
		
		try
		{
		
		myConn = dataSource.getConnection();
		
		String sql = "delete from student where id=?";
		
		myStmt = myConn.prepareStatement(sql);
		
		myStmt.setInt(1,studentId);
		
		myStmt.execute();
		
		System.out.println(myStmt);
		}
		finally
		{
			close(myConn, myStmt, null);
		}
	}

}
