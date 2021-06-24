<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,studentApp.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<h2>Student Tracker App</h2>

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<%-- <%
List<Student> theStudent = (List<Student>)request.getAttribute("STUDENT_LIST");
%> --%>

<body>	

<div id = "wrapper">
 <div id ="header">
   <h2><u>Details</u></h2>
 </div>
</div>

<div id = "container">
 <div id = "content">
 
 <input type="button" value="Add Student" onclick="window.location.href='add-student-form.jsp'; return false;"/> 
 class="add-student-button"/> <br><br>
 
    <table border="1">
     <tbody>
      <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email ID</th>
          <th colspan="2">Action</th>
      </tr>
     <%--  <% for(Student student : theStudent) { %>
      
          <tr>
             <td><%= student.getFirstName()%></td>
             <td><%= student.getLastName()%></td>
             <td><%= student.getEmail()%></td>
          </tr>
      <%} %> --%>
      <c:forEach var="tempStudent" items="${STUDENT_LIST}">
           
           <c:url var="tempLink" value="StudentControllerServlet">
             <c:param name="command" value="LOAD"/>
             <c:param name="studentId" value="${tempStudent.id}"/>
           </c:url>
           
           <c:url var="tempLink2" value="StudentControllerServlet">
             <c:param name="command" value="DEL"/>
             <c:param name="studentId" value="${tempStudent.id}"/>
           </c:url>
           
           <tr>
              <td>${tempStudent.firstName}</td>
              <td>${tempStudent.lastName}</td>
              <td>${tempStudent.email}</td>
              <td><a href="${tempLink}">update</a></td>
              <td><a href="${tempLink2}">delete</a></td>
              
           </tr>
      </c:forEach>   
      </tbody>
    </table>
 </div>
</div>
</body>
</html>
