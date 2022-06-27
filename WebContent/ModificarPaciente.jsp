<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="Entidad.*"%>
<%@page import="NegocioImpl.*"%>
<%@page import="Negocio.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Modificar Paciente</title>
</head>
<body>
<!-- MISMO FORMULARIO QUE AGREGAR PERO CON DATOS PRECARGADOS -->
<% 	
	
	try{
	
		if(session == null){
			
		}

		if (session.getAttribute("tipo").equals("Admin")) {
			
			Paciente pa = new Paciente();
			pa = (Paciente)session.getAttribute("paciente");
	%>
  <div style="float: left; margin-left: 12px; margin-top:6px;">
<a href="ServletPaciente?Param=1"><img src="img/atras.png" height="20px" /></a>
<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
 <form method="post" action ="logout" >
 <div  style=" font-family:Open Sans; margin-top:6px; float: right; margin-right: 12px; color: #fff; font-size: 12px; "><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b><%= session.getAttribute("username") %></b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	
	<div class="registro">
		<form method="post" action ="ServletPaciente">
			<h1>Modificar Paciente</h1>
			<table class="formulario">
				<tr><td><label>DNI</label></td><td><input name="txtDni" type="text" class="inputForm" size="20"  disabled value="${paciente.dni}"></td></tr>
				<tr><td><label>Nombres</label></td><td><input name="txtNombre" type="text" class="inputForm" size="20" required value="${paciente.nombre}"></td></tr>
				<tr><td><label>Apellidos</label></td><td><input name="txtApellido" type="text" class="inputForm" size="20" required value="${paciente.apellido}"></td></tr>
				<tr><td><label>Fecha Nacimiento</label></td><td><input name="txtFechaNac" type="date" class="inputForm" size="20" required value="${paciente.fechaNacimiento}"></td></tr>
				<tr><td><label>Sexo</label></td><td><select class="select" name="slcSexo">
				<%
				
	
				if(pa.getSexo() == 'M'){ %>
					
				<option selected value="M">Masculino</option>
				<option value="F">Femenino</option>
				<option value="O">Otro</option>
				<% } else if(pa.getSexo() == 'F'){ %> 
				
				<option value="M">Masculino</option>
				<option selected value="F">Femenino</option>
				<option value="O">Otro</option> <%
				}else{ %>
					<option value="M">Masculino</option>
					<option  value="F">Femenino</option>
					<option selected value="O">Otro</option>
				<% }
				%>
				</select></tr>
				<tr><td><label>Nacionalidad</label></td><td><select class="select" name="slcNacionalidad">
				<%
				
				int value = pa.getnNacionalidad().getIdNacionalidad();  %>
									<option  selected="selected" value=<%=value%> >${paciente.nNacionalidad}</option>
				<% ArrayList<Nacionalidad> listaNacionalidad = null;
							
							if(request.getAttribute("listaNacionalidad")!=null){
							
								listaNacionalidad = (ArrayList<Nacionalidad>)request.getAttribute("listaNacionalidad");
							}
							
							
							if(listaNacionalidad!=null)
								for(Nacionalidad es : listaNacionalidad){
									
									
									if(es.getIdNacionalidad() != value){%>
								<option value="<%= es.getIdNacionalidad()%>"> <%= es.getDescripcion() %>	</option> 
									
									<% }
								}
							
							%>
				
				</select></tr>
				<tr><td><label>Provincia</label></td><td><select class="select" name="slcProvincia" value="">
				
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
				<tr><td><label>Localidad</label></td><td><select class="select" name="slcLocalidad"></select></tr>
				<tr><td><label>Direcci&oacuten</label></td><td><textarea name="txtDireccion" style="resize: none;" class="inputForm" cols="21" rows="3" required >${paciente.direccion}</textarea></td></tr>
				<tr><td><label>E-mail</label></td><td><input name="txtEmail " type="email" class="inputForm" size="20" required value="${paciente.email}"></td></tr>
				<tr><td><label>Tel&eacutefono</label></td><td><input name="txtTelefono1" type="text"  class="inputForm"size="20" required value="${paciente.telefono1}"></td></tr>
				<tr><td><label>Tel&eacutefono Opcional</label></td><td><input name="txtTelefono2" type="text" class="inputForm" size="20" value="${paciente.telefono2}"></td></tr>				
			</table>
			<br>
				<input name="btnModificarPaciente" type="submit" value="Aceptar" class="btn btn-primary btn-block btn-large">
			</form>
	</div>
 <%
		} else {

			response.sendRedirect("Error.jsp");
		}
	}
	catch(Exception e){
		response.sendRedirect("Login.jsp");
	}
	finally{
	}
	
	%>	
</body>
</html>