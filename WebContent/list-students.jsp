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
					
					<c:forEach var="tempStudent" items="${STUDENTS_LIST}">
						<tr>
							<td>${tempStudent.firstName }</td>
							<td>${tempStudent.lastName }</td>
							<td>${tempStudent.email }</td>
						</tr>
					
					</c:forEach>

									
				
				</table>
				
			</div>
		</div>
	
	</body>
</html>