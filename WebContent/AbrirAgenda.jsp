<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.*"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="java.time.LocalDate"%>
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
	<div class="registro">
		<form action="ServletTurno" method="post">
			<h1>Registro de Turnos</h1>
			<table class="formulario">
				<tr><td><label>Especialidad:</label></td><td><select class="textbox" id="especialidadMedico" name="especialidades" required onchange="cargar_medicos()">
				
				
				<% ArrayList<Especialidad> listaEspecialidad = null;
				
				if(request.getAttribute("listaEspecialidad")!=null){
					
					listaEspecialidad = (ArrayList<Especialidad>)request.getAttribute("listaEspecialidad");
				}
				
				if(listaEspecialidad!=null){
					
				
					for(Especialidad es:listaEspecialidad){
					%>
					<option value="<%= es.getIdEspecialidad()%>"> <%= es.getDescripcion() %> </option>
					<%
					}
				}
				
				
				%>
				
				
				</select></tr>
				
				
				<tr><td><label>Medico:</label></td><td><select class="textbox" name="slcMedicos" id="medicoReal" required></select></tr> 
				<tr><td class="top"><label>Fecha Apertura:</label></td><td><input type="date" name="FechaApertura" id="datefield" min="" class="inputForm"></input></tr>
				<tr><td class="top"><label>Fecha Cierre:</label></td><td><input type="date" name="FechaCierre" id="datefield2" class="inputForm" min="" ></input></tr>
		
				 
		
			
			</table>
			<br>
				<input name="abrirAgenda" type="submit" value="NuevosTurnos" class="btn btn-primary btn-block btn-large">
			</form>
	</div>
	
	
	
  <select name="medicosaux" id="medico2"> 
  
  <% 
  ArrayList<Medico> listaMedico2 =null;
  
  if(request.getAttribute("listaMedico")!=null){
	  listaMedico2 = (ArrayList<Medico>) request.getAttribute("listaMedico");
  }
  
  
  if(listaMedico2 !=null){
	  
	  for(Medico me:listaMedico2){
		  
		  %>
		  	
		  	<option value="<%= me.geteEspecialidad().getIdEspecialidad()%>" data-uid="<%=me.getIdMedico().getIdUsuario()%>"> <%= me.getApellido() %> </option>
		  
		  <% 
	  }
	  
  }
	  %>
  </select>
  
  <script>
function myOnLoad() {
		var earrings = document.getElementById('medico2');
		earrings.style.visibility = 'hidden';
		cargar_medicos();
	
	}
</script>

<script>
function cargar_medicos() {
	document.getElementById("medicoReal").options.length = 0;
	
	var x = document.getElementById("medico2");
	var array = new Array();
	var a = new Array();
	var b = new Array();
	for (i = 0; i < x.length; i++) { 
		
		array.push(x.options[i].text);
		a.push(x.options[i].value);
		b.push(x.options[i].getAttribute('data-uid'));
		
}

	

	 addOptions("slcMedicos", array, a,b);
	}
</script>

<script>
function addOptions(domElement, array, a,b) {
	 var select = document.getElementsByName(domElement)[0];
	 var inde = document.getElementById('especialidadMedico').value;

	 for (value in array) {
		if(a[value] === inde){
	  var option = document.createElement("option");
	  option.text = array[value];
	  option.value = b[value];
	  select.add(option);
		}
	 }
	}
</script>

<script>
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; //January is 0!
var yyyy = today.getFullYear();

if (dd < 10) {
   dd = '0' + dd;
}

if (mm < 10) {
   mm = '0' + mm;
} 
    
today = yyyy + '-' + mm + '-' + dd;
document.getElementById("datefield").setAttribute("min", today);
</script>

<script>
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; //January is 0!
var yyyy = today.getFullYear();

if (dd < 10) {
   dd = '0' + dd;
}

if (mm < 10) {
   mm = '0' + mm;
} 
    
today = yyyy + '-' + mm + '-' + dd;
document.getElementById("datefield2").setAttribute("min", today);
</script>	
		
	
</body>
</html>
