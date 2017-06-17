
<%@ page import="java.util.*, com.web.student.tracker.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>Student Tracker</title>
	</head>
	
<%
	//get students from request object(sent by servlet)
	List<Student> theStudents = 
		(List<Student>) request.getAttribute("STUDENTS_LIST");
	
%>

	<body>
		<div id="wrapper">
			<div id="header">
				<h2>FooBar University</h2>
				
			</div>
		</div>
		
		<div id="container">
			<div id="content">
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
					</tr>
					
					<% for(Student tempStudent: theStudents){ %>
						
						<tr>
							<td><%= tempStudent.getFirstName() %></td>
							<td><%= tempStudent.getLastName() %></td>
							<td><%= tempStudent.getEmail() %></td>
						</tr>
						
					<% }%>
					
				
				</table>
				
			</div>
		</div>
	
	</body>
</html>