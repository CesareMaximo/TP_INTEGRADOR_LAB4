<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Nuevo Administrador</title>
</head>
<body>

<div style="float: left; margin-left: 12px; margin-top:6px;">
<a href="IndexAdmin.jsp"><img src="img/atras.png" height="20px" /></a>
<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
	<div class="User">
		<img width="16px" class="imag" src="img/user.png" /> Bienvenido <b><%= session.getAttribute("username") %></b>
		<input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary" style="margin-left: 10px;">
	</div>
	<br>
	<div class="registro">
		<form>
			<h1>Registro de Nuevo Administrativo</h1>
			<table class="formulario">
				<tr>
					<td><label>Nombre de Usuario:</label></td>
					<td><input name="txtNombre" type="user" class="inputForm" size="20" required></td>
				</tr>
				<tr>
					<td><label>Contrase&ntildea:</label></td>
					<td><input type="password" class="inputForm" name="txtPass" placeholder="Password" class="inputForm" required></td>
				</tr>
				<tr>
					<td><label>Confirmar contrase&ntildea:</label></td>
					<td><input type="password" class="inputForm" name="txtPass2" placeholder="Password" class="inputForm" required></td>
				</tr>
				<tr>
			</table>
			<br> <input name=" insert" type="submit" value="Aceptar" class="btn btn-primary btn-block btn-large">
		</form>
	</div>

</body>
</html>