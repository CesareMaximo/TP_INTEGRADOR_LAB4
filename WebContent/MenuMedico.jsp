<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Menu Medico</title>
</head>
<body>
<!-- LISTADO DE MEDICOS CON BOTON AGREGAR PACIENTE Y BOTONES MODIFICAR Y ELIMINAR EN CADA FILA 
CUANDO APRETAS ELIMINAR SALTA VENTANA DE CONFIRMACIÓN 
FILTRO DE BUSQUEDA-->
<div class="User"><img width="16px"class="imag" src="https://i.ibb.co/M5PCn3q/user.png"/> Bienvenido <b>NombreUsuario</b></div>
	<div class="registro">
		<form>
			<h1>LISTADO MEDICOS</h1>
			<div class="filtros">
				<table class="filtro">
					<tr><td><label>Especialidad:</label></td><td><select class="select"></select>
					<td><input name=" insert" type="submit" value="Filtrar" class="btn btn-primary btn-large"></td></tr> 
				</table>
				<br>
			</div>
			<table class="tabla">
				<thead>
					<tr>
						<th>Nombre</th><th>Apellido</th><th>Especialidad</th><th>Modificar</th><th>Eliminar</th>
					</tr>
					<tr>
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
				<input name=" insert" type="submit" value="Agregar Medico" class="btn btn-primary btn-block btn-large">
			</form>
	</div>
</body>
</html>