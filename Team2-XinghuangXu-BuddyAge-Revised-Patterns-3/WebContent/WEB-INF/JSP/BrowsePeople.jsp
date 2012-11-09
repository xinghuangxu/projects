<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<%@page import="org.dsrg.soenea.buddyAge.domLogic.Person"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Browse People</title>
</head>
<body>
  <p><%@ include file="include/version.txt" %></p>
  <ul>
	<c:forEach items="${people}" var="person">
	<li><span>${person.name}</span>: 
	    <a href="BuddyAge?command=ViewPerson&id=${person.id}">View</a>
		<a href="BuddyAge?command=DeletePerson&id=${person.id}&version=${person.version}">Delete</a>
	</li>
    </c:forEach>
  </ul>
  <c:if test="${!empty warning}"><p><b>Warning: </b> ${warning}</p></c:if>
  <p><a href="${pageContext.request.contextPath}">Home</a></p>
</body>
</html>