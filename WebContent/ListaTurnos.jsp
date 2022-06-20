<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Turnos</title>
</head>
<body>
<!-- LISTADO DE TURNOS CON FILTRO POR ESTADO, POR MEDICO, POR PACIENTE, POR FECHA -->

  <div class="Index"> <a href="IndexAdmin.jsp" class="btn btn-primary"  style="margin-left:10px;">Inicio</a> </div>
 <div class="User"><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b>NombreUsuario</b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary" style="margin-left:10px;"></div>
	<br>
		<div class="registro">
		<form>
			<h1>LISTADO DE TURNOS</h1>
			<div class="filtros">
				<table class="buscar">
					<tr>
						<td><label>Estado:</label></td><td><select class="select" >
							<option>LIBRE</option>
							<option>OCUPADO</option>
							<option>AUSENTE</option>
							<option>PRESENTE</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label>Fecha:</label></td><td><input type="date" >
						</td>
					</tr>
					<tr>
						<td><label>Especialidad:</label></td><td><select class="select"></select></td>
					</tr> 
					<tr>
						<td><label>Medicos:</label></td><td><select class="select" ></select></td>
					</tr>
					<tr>
						<td>
							<label>DNI Paciente:</label>
						</td>
						<td>
							<input type="search" name="Busqueda"></input>
						</td>
						<td><input name=" insert" type="submit" value="Buscar" class="btn btn-primary btn-large"></td>
					</tr>
				</table>
				<br>
			</div>
			<table class="tabla">
				<thead>
					<tr>
						<th>DNI Paciente</th><th>Medico</th><th>Especialidad</th><th>Fecha</th><th>Estado</th>
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
						</td>
					</tr>
					
				</thead>
				</table>
				<br></br>
			</form>
	</div>
</body>
</html>