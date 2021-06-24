package studentApp;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/student")
	private DataSource dataSource;
	
  	@Override
	public void init() throws ServletException {
		super.init();
		
     try 
     {
        studentDbUtil = new StudentDbUtil(dataSource);  	 
     }
     catch(Exception exc)
     {
    	 throw new ServletException(exc);
     }
     
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String theCommand = request.getParameter("command");
		
		System.out.println("********************");
		System.out.println(theCommand);
		
		if(theCommand == null)
		{ 
		   theCommand = "LIST";
		}
		
	  try 
	   {
		
		
		if(theCommand.equals("LIST")) {
			listStudent(request,response);
	    }
			
		else if(theCommand.equals("LOAD")) {
			loadStudent(request,response);
		}
		
		else if(theCommand.equals("ADD")) {
			addStudent(request,response);
		}
		
		else if(theCommand.equals("UPD")) {
			updateStudent(request,response);
		}
		else if(theCommand.equals("DEL")) {
			deleteStudent(request,response);
		}
			
		
	   }
	   catch(Exception exp)
	   {
		  exp.printStackTrace();
	   }
	}



	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{

		String id = request.getParameter("studentId");
		
        Student theStudent = studentDbUtil.getStudent(id);
		        
        request.setAttribute("THE_STUDENT", theStudent);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		
		dispatcher.forward(request, response);
	}



	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String id = request.getParameter("studentId");
		System.out.println("am1");
		System.out.println(id);
		int studentId = Integer.parseInt(id);
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email    = request.getParameter("email");
		
		Student theStudent = new Student(studentId,firstName, lastName, email);
		
		studentDbUtil.updateStudent(theStudent);
		
		listStudent(request,response);
	}



	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

	String id = request.getParameter("studentId");
	
	int studentId = Integer.parseInt(id);
	
	System.out.println(studentId);
	studentDbUtil.deleteStudentData(studentId);
	
	listStudent(request,response);
	
	}



	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
	String firstName =  request.getParameter("firstName"); 
	String lastName =  request.getParameter("lastName"); 
	String email =  request.getParameter("email");
	
	Student student = new Student(firstName, lastName, email);
	
	studentDbUtil.addStudents(student);
	
	listStudent(request,response);
	 
	 
	}



	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Student> lst = studentDbUtil.getStudents();
		
		request.setAttribute("STUDENT_LIST", lst);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		
		dispatcher.forward(request, response);
	}

}
