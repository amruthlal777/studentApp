<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,studentApp.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<title>Add Student</title>

<link type="text/css" rel="stylesheet" href="css/add-student-style.css"/>
<link type="text/css" rel="stylesheet" href="style.css"/>

</head>
<body>

<div id="wrapper">
 <div id="header">
   <h2>Student tracker Application</h2>
 </div>
</div>

<div id = "container">
<h3>Add Student</h3>

<form action="StudentControllerServlet" method="GET">

<input type="hidden" name="command" value="ADD"/>

<table>
    <tbody>
      <tr>
        <td><label>First Name:</label></td>
        <td><input type="text" name="firstName"></td>
      </tr>
      <tr>
        <td><label>Last Name:</label></td>
        <td><input type="text" name="lastName"></td>
      </tr>
      <tr>
        <td><label>Email:</label></td>
        <td><input type="text" name="email"></td>
      </tr>
      <tr>
        <td><label></label></td>
        <td><input type="submit" value="save" class="save"></td>
      </tr>
      </tbody>
</table>
</form>

<p> <a href="StudentControllerServlet">Back to List</a></p>
</div>
</body>
</html>
