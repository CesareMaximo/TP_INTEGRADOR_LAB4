<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="Entidad.*"%>
<%@page import="NegocioImpl.*"%>
<%@page import="Negocio.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page buffer="64kb" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Nuevo Paciente</title>
</head>


<body onLoad="myOnLoad()">
<% 
HttpServletResponse res = (HttpServletResponse) response;
HttpSession sesion = ((HttpServletRequest) request).getSession();

	if(sesion.getAttribute("username")==null){
		res.sendRedirect("Login.jsp");
	}
	if(sesion.getAttribute("tipo")!=null){
		if(sesion.getAttribute("tipo").toString().equals("Medico")){
			res.sendRedirect("Error.jsp");
			return;
	}
	}
%>  

<!-- FORMULARIO PARA AGREGAR UN PACIENTE
DNI
NOMBRE
APELLIDO 
FECHA NACIMIENTO
SEXO DESPLEGABLE
NACIONALIDAD DESPLEGABLE 
PROVINCIA DESPLEGABLE
LOCALIDAD DESPLEGABLE
DIRECCION
EMAIL
TELEFONO 
-->
<div style="float: left; margin-left: 12px; margin-top:6px;">
<a href="ServletPaciente?Param=1"><img src="img/atras.png" height="20px" /></a>
<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
<form method="post" action ="logout" >
 <div  style=" font-family:Open Sans; margin-top:6px; float: right; margin-right: 12px; color: #fff; font-size: 12px; ">
 <a href="MiCuenta.jsp" title="Mi Cuenta" data-toggle="tooltip""><img width="16px"class="imag" src="img/user.png"/></a> Bienvenido <b> <%= session.getAttribute("username") %></b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	
	<div class="registro">
		<form method="post" action= "ServletPaciente">
			<h1>Registro de Nuevo Paciente</h1>
			<table class="formulario">
				<tr><td><label>DNI:</label></td><td><input name="txtDni" type="text" oninput="this.value = this.value.replace(/[^a-zA-Z0-9]/,'')" placeholder="Ingrese un DNI" class="inputForm" size="20" required></td></tr>
				<tr><td><label>Nombres:</label></td><td><input name="txtNombre" type="text" oninput="this.value = this.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]/,'')" placeholder="Ingrese un Nombre" class="inputForm" size="20" required></td></tr>
				<tr><td><label>Apellidos:</label></td><td><input name="txtApellido" type="text" oninput="this.value = this.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]/,'')" placeholder="Ingrese un Apellido" class="inputForm" size="20" required></td></tr>
				<tr><td><label>Fecha Nacimiento:</label></td><td><input id="datefield" name="txtFechaNac" type="date" class="inputForm" size="20" required></td></tr>
				<tr><td><label>Sexo</label></td><td><select class="select" name="slcSexo">
					
				<option value="M">Masculino</option>
				<option value="F">Femenino</option>
				<option value="O">Otro</option>
				
				</select></tr>
				<tr><td><label>Nacionalidad:</label></td><td><select class="select" name="slcNacionalidad">
				
				<% ArrayList<Nacionalidad> listaNacionalidad = null;
							
							if(request.getAttribute("listaNacionalidad")!=null){
							
								listaNacionalidad = (ArrayList<Nacionalidad>)request.getAttribute("listaNacionalidad");
							}
							
							
							if(listaNacionalidad!=null)
								for(Nacionalidad es : listaNacionalidad){
									
									%>
									<option value="<%= es.getIdNacionalidad()%>"> <%= es.getDescripcion() %>	</option> 
									
									<% 
								}
								
							
							
							%>
				
				</select></tr>
				<tr><td><label>Provincia:</label></td><td><select class="textbox"  onchange="cargar_localidades()" required  id="provincia1"  name="slcProvincia">
				
					<% ArrayList<Provincia> listaProvincia = null;
							
							if(request.getAttribute("listaProvincia")!=null){
							
								listaProvincia = (ArrayList<Provincia>)request.getAttribute("listaProvincia");
							}
							
							
							if(listaProvincia!=null)
								for(Provincia es : listaProvincia){
									
									%>
									<option value="<%= es.getIdProvincia()%>"> <%= es.getDescripcion() %>	</option> 
									
									<% 
								}
							
							%>
				
				</select></tr>
				<tr><td><label>Localidad:</label></td><td><select class="textbox" required id="localidadReal"  name="slcLocalidad"></select></tr>
				<tr><td><label>Direcci&oacuten:</label></td><td><textarea name="txtDireccion"   oninput="this.value = this.value.replace(/[^a-zA-Z0-9_á_é_í_ó_ú_Á_É_Í_Ó_ÚñÑ ]/,'')" placeholder="Ingrese una Dirección" style="resize: none;" class="inputForm" cols="21" rows="3" required></textarea></td></tr>
				<tr><td><label>E-mail:</label></td><td><input name="txtEmail" type="email" oninput="this.value = this.value.replace(/[^a-zA-Z0-9áéíóúÁÉÍÓÚ@._-]/,'')"  class="inputForm"  placeholder="Ingrese un Email" size="20" required></td></tr>
				<tr><td><label>Tel&eacutefono:</label></td><td><input name="txtTelefono1" type="text" oninput="this.value = this.value.replace(/[^0-9]/,'')" placeholder="Ingrese un Telefono" class="inputForm"size="20" required></td></tr>
				<tr><td><label>Tel&eacutefono Opcional:</label></td><td><input name="txtTelefono2"  oninput="this.value = this.value.replace(/[^0-9]/,'')" placeholder="Ingrese un Telefono" type="text" class="inputForm" size="20"></td></tr>				
			</table>
			<br>
	
			<div>
				<p style="color: red; margin-left: 125px;">
					<%
						String resultado = (String) request.getAttribute("mensaje");
								String mensaje = "";
								if (resultado != null) {
									mensaje = resultado;
								}
					%>
					<%=mensaje%>
				</p>

			</div>
				<input name="insert" type="submit" value="Aceptar" class="btn btn-primary btn-block btn-large">
				
			</form>
	</div>

		<select name="localidades" id="localidad2"> 
	<% 
	ArrayList<Localidad> listaLocalidad2 = null;
	
	if(request.getAttribute("listaLocalidad")!=null){
		listaLocalidad2 = (ArrayList<Localidad>) request.getAttribute("listaLocalidad");
	}
	
	
	if(listaLocalidad2 !=null){
		for(Localidad lo: listaLocalidad2){
	%>
		<option value="<%= lo.getpProvincia().getIdProvincia()%>" data-uid="<%=lo.getIdLocalidad()%>"><%=lo.getDescripcion()%></option>
	
	<%}
		}%>
	
	 </select>
	
	
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
	document.getElementById("datefield").setAttribute("max", today);
	</script>
	 
	 
	 <script>
function myOnLoad() {
		var earrings = document.getElementById('localidad2');
		earrings.style.visibility = 'hidden';
		cargar_localidades();
	
	}
</script>


<script>
function cargar_localidades() {
	document.getElementById("localidadReal").options.length = 0;
	
	var x = document.getElementById("localidad2");
	var array = new Array();
	var a = new Array();
	var b = new Array();
	for (i = 0; i < x.length; i++) { 
		
		array.push(x.options[i].text);
		a.push(x.options[i].value);
		b.push(x.options[i].getAttribute('data-uid'));
		
}

	

	 addOptions("slcLocalidad", array, a,b);
	}
</script>

<script>
function addOptions(domElement, array, a,b) {
	 var select = document.getElementsByName(domElement)[0];
	 var inde = document.getElementById('provincia1').value;

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
	
	
	
</body>
</html>