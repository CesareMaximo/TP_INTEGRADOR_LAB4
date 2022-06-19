<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Menu Paciente</title>
</head>
<body>

<!-- LISTADO DE PACIENTES CON BOTON AGREGAR PACIENTE Y BOTONES MODIFICAR Y ELIMINAR EN CADA FILA 
CUANDO APRETAS ELIMINAR SALTA VENTANA DE CONFIRMACIÓN 
FILTRO DE BUSQUEDA-->
 <div class="User"><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b>NombreUsuario</b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary" style="margin-left:10px;"></div>
	<br>
		<div class="registro">
		<form>
			<h1>MENU PACIENTE</h1>
			<div class="filtros">
				<table class="buscar">
					<tr><td><label>DNI</label></td><td><input type="search" name="Busqueda"></input></td>
					<td><input name=" insert" type="submit" value="Buscar" class="btn btn-primary btn-large"></td></tr>
				</table>
				<br>
			</div>
			<table class="tabla">
				<thead>
					<tr>
						<th>DNI</th><th>Nombre</th><th>Apellido</th><th>Email</th><th>Modificar</th><th>Eliminar</th>
					</tr>
					<tr>
						<td>
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
							<input type="image" src="img/escribir.png"  name="modificar">
						</td>
						<td>
							<input type="image" src="img/tacho.png"  name="eliminar">
						</td>
					</tr>
					
				</thead>
				</table>
				<br></br>
				<input name=" insert" type="submit" value="Agregar Paciente" class="btn btn-primary btn-block btn-large">
			</form>
	</div>
</body>
</html>