<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
<title>Mi Cuenta</title>
</head>
<body>


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
			<h1 style="font-size: 25px;  margin-top:30px; margin-bottom:30px" >Mi Cuenta</h1>
			<table class="formulario">
				<tr>
					<td><label>Nombre de Usuario:</label></td>
					<td><label name="txtUser" type="user"  size="20"><%= session.getAttribute("username") %></label></td>
				</tr>
				<tr>
					<td><label>Nuevo Nombre de Usuario:</label></td>
					<td><input name="txtUserNuevo" type="user" oninput="this.value = this.value.replace(/[^a-zA-Z0-9]/,'')" class="inputForm" size="20" ></td>
				</tr>
				<tr>
					<td><label>Contrase&ntildea anterior:</label></td>
					<td><input type="password" class="inputForm" name="txtPassAnterior" placeholder="Password" class="inputForm" required></td>
				</tr>
				<tr>
					<td><label>Nueva Contrase&ntildea:</label></td>
					<td><input type="password" class="inputForm" name="txtPassNueva" placeholder="Password" class="inputForm" ></td>
				</tr>
				<tr>
					<td><label>Confirmar Nueva Contrase&ntildea:</label></td>
					<td><input type="password" class="inputForm" name="txtPassNueva2" placeholder="Password" class="inputForm" ></td>
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
		
		
			<br> <input name="btnModificarUser" type="submit" value="Modificar Usuario" class="btn btn-primary btn-block btn-large">
			
		</form>
	</div>

		<% 		
						boolean exito = false;
						
						if(request.getAttribute("exito")!=null){
						
							exito = (boolean)request.getAttribute("exito");
							
						}
							if(exito == true){
								%>
								<script type="text/javascript">
										window.onload = function() {
											OpenBootstrapPopup();
										};
										function OpenBootstrapPopup() {
											$("#simpleModal").modal('show');
										}
									</script>
									
									<div id="simpleModal" class="modal fade">
			<div class="modal-dialog modal-ok">
				<div class="modal-content">
					<div class="modal-header justify-content-center">
						<div class="icon-box">
							<i style="color: green" class="material-icons">&#xE876;</i>
						</div>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body text-center">
						<h4>Exito!</h4>	
						<p>El usuario se ha modificado satisfactoriamente.</p>
					</div>
				</div>
			</div>
		</div>     
								 <%} 
									%>
</body>
</html>