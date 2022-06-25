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
<% 	
	
	try{
	
		if(session == null){
			
		}

	
	if (session.getAttribute("tipo").equals("Admin")) {
		%>


<div style="float: left; margin-left: 12px; margin-top:6px;">
<a href="IndexAdmin.jsp"><img src="img/atras.png" height="20px" /></a>
<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
	<div class="User">
		<form method="post" action ="logout" >
 <div  style=" font-family:Open Sans; margin-top:6px; float: right; margin-right: 12px; color: #fff; font-size: 12px; "><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b><%= session.getAttribute("username") %></b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	
	</div>
	<br>
	<div class="registro">
		<form method="post" action="usuario">
			<h1>Registro de Nuevo Administrativo</h1>
			<table class="formulario">
				<tr>
					<td><label>Nombre de Usuario:</label></td>
					<td><input name="txtUser" type="user" class="inputForm" size="20" required></td>
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
			<div>
			<p style="color:red; margin-left:125px;;">
				<%
					String resultado = (String)request.getAttribute("mensaje");
					String mensaje = "";
					if (resultado != null) {
						mensaje = resultado;
					}
					
				%>
				<%=mensaje %>
			</p>
			
		</div> 
		
		
			<br> <input name="btnNuevoUser" type="submit" onClick="BorrarCliente" value="Registrar" class="btn btn-primary btn-block btn-large">
			<% 		boolean x = (boolean)request.getAttribute("exito");
					if(x == true){
						%>
						<div id="miModal" class="modal">
						  <div class="modal-contenido">
				
						    <a href="AgregarAdministrativo.jsp">X</a>
						    <h2>USUARIO AGREGADO CON ÉXITO</h2>
						  </div>  
						</div>
						 <% } 
							%>
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