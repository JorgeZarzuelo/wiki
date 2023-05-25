<%@ page language="java" contentType="text/html;encoding=UTF-8"
    pageEncoding="utf-8"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<c:set var="context" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>WIKI - TECNOLOGIAS WEB</title>
<link href="${context}/styles.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/x-icon" href="favicon.ico">
</head>
<body>
<div class="top_banner">
	<h1>WIKI - TECNOLOGIAS WEB</h1>
</div>
<div class="menu">

  
    <span class="user-info">
    	<c:if test="${sessionScope.user != null}">  
    		Usuario logueado:  ${sessionScope.user.username} - ID: ${sessionScope.user.id} - SessionID: ${pageContext.session.id}
    	</c:if>
    </span>
    
	<ul>
	
		<li><a href="${pageContext.request.contextPath}/">Inicio</a></li>
						
		<c:if test="${sessionScope.isGestor == true }"> 
		   <li><a href="${pageContext.request.contextPath}/users">Usuarios</a></li>
		</c:if>
		
		<c:if test="${sessionScope.isGestor == true }"> 
		   <li><a href="${pageContext.request.contextPath}/wikis">Wikis</a></li>
		</c:if>
		
		<c:if test="${sessionScope.isGestor == true }"> 
		   <li><a href="${pageContext.request.contextPath}/solicitudes">Solicitudes</a></li>
		</c:if>
		
		<c:if test="${sessionScope.isCoordinador == true }"> 
		   <li><a href="${pageContext.request.contextPath}/solicitudes_coordinador">Solicitudes</a></li>
		</c:if>
		
		<c:if test="${sessionScope.isCoordinador == true }"> 
		   <li><a href="${pageContext.request.contextPath}/articulos">Articulos</a></li>
		</c:if>
		
		<c:if test="${sessionScope.isLoggedIn == true && sessionScope.isGestor == false}"> 
		   <li><a href="${pageContext.request.contextPath}/mis_roles">Mis Roles</a></li>
		</c:if>
		
		<c:if test="${sessionScope.user != null}"> 
		   <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		</c:if>
		
		<c:if test="${sessionScope.user == null}"> 
		   <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
		</c:if>
		
	</ul>
</div>