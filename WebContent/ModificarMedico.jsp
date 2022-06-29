<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>




<title>Modificar Médico</title>
</head>
<body>
<div style="float: left; margin-left: 12px; margin-top:6px;">
<a href="ServletMedico?Param=1"><img src="img/atras.png" height="20px" /></a>
<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
<!-- MISMO FORMULARIO QUE AGREGAR PERO CON DATOS PRECARGADOS -->
<form method="post" action ="logout" >
 <div  style=" font-family:Open Sans; margin-top:6px; float: right; margin-right: 12px; color: #fff; font-size: 12px; "><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b><%= session.getAttribute("username") %></b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	
	<div class="registro">
		<form>
			<h1>Modificar M&eacutedico</h1>
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
				<tr><td><label>Especialidad:</label></td><td><select class="select"></select></tr> 
				<tr><td class=top><label>Dia de atenci&oacuten:</label></td><td>
				<div class="control-group">
    <label class="control control-checkbox">
        Lunes
            <input type="checkbox" checked="checked" />
        <div class="control_indicator"></div>
    </label>
    <label class="control control-checkbox">
        Martes
            <input type="checkbox" />
        <div class="control_indicator"></div>
    </label>
    <label class="control control-checkbox">
        Miercoles
            <input type="checkbox" />
        <div class="control_indicator"></div>
    </label>
    <label class="control control-checkbox">
        Jueves
            <input type="checkbox" />
        <div class="control_indicator"></div>
    </label>
    <label class="control control-checkbox">
        Viernes
            <input type="checkbox" />
        <div class="control_indicator"></div>
    </label>
    <label class="control control-checkbox">
        S&aacutebado
            <input type="checkbox" />
        <div class="control_indicator"></div>
    </label>
</div>
				<tr><td class="top"><label>Horario de Atenci&oacuten:</label></td><td>
				
				<select class="select" ><option>08:00</option>
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
				</select><label class="lbl">  Ingreso </label><br>
				<select class="select">
				<option>09:00</option>
				<option>11:00</option>
				<option>12:00</option>
				<option>13:00</option>
				<option>14:00</option>
				<option>15:00</option>
				<option>16:00</option>
				<option>17:00</option>
				<option>18:00</option>
				<option>19:00</option>
				<option>20:00</option></select><label class="lbl">  Salida   </label>
				
				</td></tr>
				<tr><td><label>Nombre de Usuario:</label></td><td><input name="txtUser" type="text" class="inputForm" size="20"></td></tr>
				<tr><td><label>Contrase&ntildea:</label></td><td><input name="txtPass" type="password"class="inputForm" size="20"></td></tr>
				<tr><td><label>Confirmar contrase&ntildea:</label></td><td><input name="txtPass2" type="password"class="inputForm" size="20"></td></tr>
			</table>
			<br>
				<input name=" insert" type="submit" value="Aceptar" class="btn btn-primary btn-block btn-large" ">
			</form>
	</div>

	
</body>
</html>