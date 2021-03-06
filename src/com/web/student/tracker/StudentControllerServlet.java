package com.web.student.tracker;

import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;

	
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		// create our student db util and pass in the conn pool / datasource
		
		try{
			studentDbUtil = new StudentDbUtil(dataSource);
			
		}catch (Exception exc){
			throw new ServletException(exc);
		}
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			if(theCommand == null){
				theCommand = "LIST";
			}
			
			switch(theCommand){
			case "LIST":
				listStudents(request,response);
				break;
			case "ADD":
				addStudent(request,response);
				break;
			case "LOAD":
				loadStudent(request,response);
				break;
			case "UPDATE":
				updateStudent(request,response);
				break;
			case "DELETE":
				deleteStudent(request,response);
				break;
			default:
				listStudents(request,response);
			}
			
			//route to appropriate method
			
			// list students ... 
			
		}catch (Exception exc){
			throw new ServletException(exc);
		}
	}




	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		//read id
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		
		//delete student in Db
		studentDbUtil.deleteStudent(studentId);
		
		//refresh
		listStudents(request,response);
	}



	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		//read info from form
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		//create student object
		Student theStudent = new Student(id,firstName,lastName,email);
		
		//update on db
		studentDbUtil.updateStudent(theStudent);
		
		//back to list page
		listStudents(request,response);
		
	}



	private void loadStudent(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		//read student id from form data
		String theStudentId = request.getParameter("studentId");

		//get student from database 
		Student theStudent = studentDbUtil.getStudent(theStudentId);

		// place student in request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}



	private void addStudent(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		//read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		
		//create a new Student Object
		
		Student theStudent = new Student(firstName,lastName,email);
		
		//add student to database
		
		studentDbUtil.addStudent(theStudent);
		
		//send back to main page
		listStudents(request,response);
	}



	private void listStudents(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		// get students from db util
		List<Student> students = studentDbUtil.getStudents();
		
		//add students to request
		request.setAttribute("STUDENTS_LIST", students);
		
		//send to JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/list-students.jsp");
		dispatcher.forward(request, response);
		
	}

}
