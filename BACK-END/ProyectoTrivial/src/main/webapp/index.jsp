<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<header>
		<input type="button" class="botonInicioSesion" value="Iniciar Sesión">
		<input type="button" class="botonRegistro" value="Registrarse">
		<img alt="ImagenLogo" src="img/logoTrivWorld.png" class="imagenLogo">
		<%@include file="menu.html"%>
	</header>

</body>
</html>