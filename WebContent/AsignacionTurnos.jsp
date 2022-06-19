<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Asignaci&oacuten de Turnos</title>
</head>
<body>
<!-- DESPLAGLE DE ESPECIALIDADES QUE FILTRE LOS MEDICOS
DESPLEGABLE DE MEDICOS
FECHAS CON HORARIOS DISPONIBLES FILTRAR (SELECT * DE TURNOS WHERE ID MEDICO ES EL SELECCIONADO EN EL DESPLEGABLE AND IDESTADO = 1)
INPUT PARA DNI PACIENTE 

BOTON RESERVAR TURNO, AL APRETAR BOTON DEBERA CHEQUEAR SI EL DNI ESTA REGISTRADO, SI ESTA REGISTRADO MENSAJE
DE CONFIRMACION Y EN EL BACK CAMBIAR EL ESTADO DE TURNO A OCUPADO Y ASIGNAR ID PACIENTE 
SI NO ESTA REGISTRADO, MENSAJE "PACIENTE NO REGISTRADO, BOTON PARA REGISTRAR"
-->

 <div class="User"><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b>NombreUsuario</b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary" style="margin-left:10px;"></div>
	<br>
	<div class="registro">
		<form method="post">
			<h1>Registro de Turnos</h1>
			<table class="formulario">
				<tr><td><label>Especialidad:</label></td><td><select class="select"></select></tr>
				<tr><td><label>Medico:</label></td><td><select class="select"></select></tr> 
				<tr><td class="top"><label>Fecha:</label></td><td><input type="date" name="Fecha" class="inputForm" min="2022-06-01" max="2022-07-30"></input></tr>
				<tr><td class="top"><label>Horario de Atenci&oacuten:</label></td><td>
				 
				<select class="select" >
					<option>08:00</option>
					<option>09:00</option>
					<option>10:00</option>
					<option>11:00</option>
					<option>12:00</option>
					<option>13:00</option>
					<option>14:00</option>
					<option>15:00</option>
					<option>16:00</option>
					<option>17:00</option>
					<option>18:00</option>
					<option>19:00</option>
				</select>			
			
				<tr><td><label>DNI:</label></td><td><input name="txtDni" type="text" class="inputForm" size="20" required></td></tr>
			</table>
			<br>
				<input name=" insert" type="submit" value="Reservar Turno" class="btn btn-primary btn-block btn-large">
			</form>
	</div>

</body>
</html>