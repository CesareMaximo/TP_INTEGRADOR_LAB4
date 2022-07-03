<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.*"%>
    <%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
	
	
	
        
</style>
<title>Asignaci&oacuten de Turnos</title>
</head>


<body  onLoad="myOnLoad()" >
<!-- DESPLAGLE DE ESPECIALIDADES QUE FILTRE LOS MEDICOS
DESPLEGABLE DE MEDICOS
FECHAS CON HORARIOS DISPONIBLES FILTRAR (SELECT * DE TURNOS WHERE ID MEDICO ES EL SELECCIONADO EN EL DESPLEGABLE AND IDESTADO = 1)
INPUT PARA DNI PACIENTE 

BOTON RESERVAR TURNO, AL APRETAR BOTON DEBERA CHEQUEAR SI EL DNI ESTA REGISTRADO, SI ESTA REGISTRADO MENSAJE
DE CONFIRMACION Y EN EL BACK CAMBIAR EL ESTADO DE TURNO A OCUPADO Y ASIGNAR ID PACIENTE 
SI NO ESTA REGISTRADO, MENSAJE "PACIENTE NO REGISTRADO, BOTON PARA REGISTRAR"
-->
  <div style="float: left; margin-left: 12px; margin-top:6px;">
<a href="IndexAdmin.jsp"><img src="img/atras.png" height="20px" /></a>
<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
 <form method="post" action ="logout" >
 <div  style=" font-family:Open Sans; margin-top:6px; float: right; margin-right: 12px; color: #fff; font-size: 12px; "><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b><%= session.getAttribute("username") %></b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	
	<% Turno tu = new Turno();
	   tu = (Turno)session.getAttribute("Turno");
	%>
	<div class="registro">
		<form method="post" action="ServletTurno">
			<h1>Registro de Turnos</h1>
			<table class="formulario">
				
				<tr><td><label>Medico:</label></td><td><input class="inputForm" name="medicos" id="medicoReal" readonly value="${Turno.mMedico.apellido}"></input></tr> 
				<tr><td><label>Especialidad:</label></td><td><input class="inputForm" name="especialidad" id="especialidad" readonly value="${Turno.mMedico.eEspecialidad.descripcion}"></input></tr>
				<tr><td><label>Fecha:</label></td><td><input class="inputForm" name="fecha" id="fecha" readonly value="${Turno.fecha}"></input></tr> 
				<tr><td><label>Horario de Atenci&oacuten:</label></td><td><input class="inputForm" name="atencion" id="atencion" readonly value="${Turno.hora}"></input></tr> 
				<tr><td><label>DNI:</label></td><td><input name="txtDni" type="text" class="inputForm" size="20" required></input></td></tr>
			</table>
			<br>
				<input name="reservar" type="submit" value="Reservar Turno" class="btn btn-primary btn-block btn-large">
			</form>
	</div>
	
	
</body>
</html>
