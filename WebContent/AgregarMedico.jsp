<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Insert title here</title>
</head>
<body>

<!-- FORMULARIO PARA AGREGAR UN MEDICO
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
ESPECIALIDAD DESPLEGABLE
DIA Y HORARIO DE ATENCIÓN
NOMBRE USUARIO
CONTRASEÑA
-->
	<div class="registro">
		<form>
			<h1>Registro de Nuevo Medico</h1>
			<p>
				<label>DNI:</label><input name="txtDni" type="text" size="20" required><br>
				<label>Nombres:</label><input name="txtNombre" type="text" size="20" required><br>
				<label>Apellidos:</label><input name="txtApellido" type="text" size="20" required><br>
				<label>Fecha Nacimiento:</label><input name="txtFechaNac" type="text" size="20" required>
				<br>
				<label>Sexo</label><br>
				<label>Nacionalidad:</label><br>
				<label>Provincia:</label><br>
				<label>Localidad:</label><br>
				<label>Dirección:</label><textarea name="txtDireccion" cols="26" rows="3" required></textarea><br>
				<label>E-mail:</label><input name="email " type="email" size="20" required><br>
				<label>Telefono:</label><input name="txtTelefono1" type="text" size="20" required><br>
				<label>Telefono Opcional:</label><input name="txtTelefono2" type="text" size="20"><br>
				<label>Especialidad:</label><br>
				<label>Dia de atención:</label><input name="txtDia" type="text" size="20"><br>
				<label>Horario de Atención:</label><input name="txtHorario" type="text" size="20"><br>
				<label>Nombre de Usuario:</label><input name="txtUser" type="text" size="20"><br>
				<label>Contrase&ntildea:</label><input name="txtTelefono1" type="password" size="20"><br>
				<br>
				<input name=" insert" type="submit" value="Aceptar" class="btn btn-primary btn-block btn-large">
			</p>
			</form>
	</div>
		
</body>
</html>