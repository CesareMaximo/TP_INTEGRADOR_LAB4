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
  <div class="Index"> <a href="IndexAdmin.jsp" class="btn btn-primary"  style="margin-left:10px;">Inicio</a> </div>
 <div class="User"><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b>NombreUsuario</b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary" style="margin-left:10px;"></div>
	<br>
	<div class="registro">
		<form>
			<h1>Modificar Paciente</h1>
			<table class="formulario">
				<tr><td><label>DNI:</label></td><td><input name="txtDni" type="text" class="inputForm" size="20" required></td></tr>
				<tr><td><label>Nombres:</label></td><td><input name="txtNombre" type="text" class="inputForm" size="20" required></td></tr>
				<tr><td><label>Apellidos:</label></td><td><input name="txtApellido" type="text" class="inputForm" size="20" required></td></tr>
				<tr><td><label>Fecha Nacimiento:</label></td><td><input name="txtFechaNac" type="date" class="inputForm" size="20" required></td></tr>
				<tr><td><label>Sexo</label></td><td><select class="select"></select></tr>
				<tr><td><label>Nacionalidad:</label></td><td><select class="select"></select></tr>
				<tr><td><label>Provincia:</label></td><td><select class="select"></select></tr>
				<tr><td><label>Localidad:</label></td><td><select class="select"></select></tr>
				<tr><td><label>Direcci&oacuten:</label></td><td><textarea name="txtDireccion" style="resize: none;" class="inputForm" cols="21" rows="3" required></textarea></td></tr>
				<tr><td><label>E-mail:</label></td><td><input name="email " type="email" class="inputForm" size="20" required></td></tr>
				<tr><td><label>Tel&eacutefono:</label></td><td><input name="txtTelefono1" type="text"  class="inputForm"size="20" required></td></tr>
				<tr><td><label>Tel&eacutefono Opcional:</label></td><td><input name="txtTelefono2" type="text" class="inputForm" size="20"></td></tr>				
			</table>
			<br>
				<input name=" insert" type="submit" value="Aceptar" class="btn btn-primary btn-block btn-large">
			</form>
	</div>

</body>
</html>