<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<%@page import="org.dsrg.soenea.buddyAge.domLogic.Person"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>View Person</title>
</head>
<body>
  <h2>View ${person.name}</h2>
  <p><%@ include file="include/version.txt" %></p>
  <p>${person.name} is ${person.age} years old.</p>
  <p>And has
  <c:if test="${person.buddy == null}"> no </c:if>
  <c:if test="${person.buddy != null}">
  	<a href="BuddyAge?command=ViewPerson&id=${person.buddy.id}">${person.buddy.name}</a> as a
  </c:if>
  buddy.</p>
  <p><a href="BuddyAge?command=IncreaseAge&id=${person.id}&version=${person.version}">Make ${person.name} older</a>.</p>
  <p><a href="BuddyAge?command=BrowsePeople">Browse More People</a></p>
  <c:if test="${!empty warning}"><p><b>Warning: </b> ${warning}</p></c:if>
</body>
</html>
