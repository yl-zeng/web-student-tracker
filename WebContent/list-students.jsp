<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*, com.web.student.tracker.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>Student Tracker</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
	

	<body>
		<div id="wrapper">
			<div id="header">
				<h2>Student Tracker</h2>
				
			</div>
		</div>
		
		<div id="container">
			<div id="content">
			
				<input type="button" value="Add Student"
					onclick="window.location.href='add-student-form.jsp';return false;"
					class="add-student-button"
				/>
			
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
					
					<c:forEach var="tempStudent" items="${STUDENTS_LIST}">
					
						<!-- set up a link for each student -->
						<c:url var="loadLink" value="StudentControllerServlet">
							<c:param name="command" value="LOAD"/>
							<c:param name="studentId" value="${tempStudent.id }"/>
						</c:url>
						
						<c:url var="deleteLink" value="StudentControllerServlet">
							<c:param name="command" value="DELETE"/>
							<c:param name="studentId" value="${tempStudent.id }"/>
						</c:url>
						
						<tr>
							<td>${tempStudent.firstName }</td>
							<td>${tempStudent.lastName }</td>
							<td>${tempStudent.email }</td>
							<td>
								<a href="${loadLink}">Update</a>
								 | 
								<a href="${deleteLink }"
									onclick="if(!(confirm('Are you sure delete this student?'))) return false">Delete</a>
							</td>
						</tr>
					
					</c:forEach>

									
				
				</table>
				
			</div>
		</div>
	
	</body>
</html>