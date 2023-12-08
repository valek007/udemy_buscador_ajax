<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="modelo.AgendaContactos,beans.Contacto,java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta http-equiv="cache-control" content="max-age=0"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>

<title>Insert title here</title>
</head>
<body>
	<center>
		<c:choose>
		<c:when test="${! empty requestScope.contactos}">
			<table border="1">
				<tr><th>Nombre</th><th>Email</th><th>Telefono</th><th></th></tr>
				
				<c:forEach var="ct" items="${requestScope.contactos}">
					<tr>
						<td>${ct.nombre}</td>
						<td>${ct.email}</td>
						<td>${ct.edad}</td>
						<td><a href="Controller?op=doEliminar&idContacto=${ct.idContacto}">Eliminar</a></td>
					</tr>
				</c:forEach>	
			</table>
		</c:when>
		<c:otherwise>
			<h1>No hay contactos registrados</h1>
		</c:otherwise>
		</c:choose>
		<br/><br/>
		
		
		<a href="Controller?op=toInicio">Volver</a>
	</center>
</body>
</html>