<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Men� Administrador</title>
</head>
<body>
 <!-- MENSAJE BIENVENIDA
 
 MENU 
 
MENU PACIENTE
MENU MEDICO
ASIGNACION DE TURNOS
LISTADO DE TURNOS
REPORTES 
  -->
 <div class="User"><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b>NombreUsuario</b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesi�n" class="btn btn-primary" style="margin-left:10px;"></div>
	<br>
	<div class="registro">
		<form>
			<h1>Men� Administrador</h1>
			
			 <div style="display: flex; flex-direction: row; flex-wrap: wrap">
                        <a href="" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/MenuPaciente.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Men� Paciente</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/MenuMedicos.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Men� M�dico</p>
                                </div>
                            </div>
                        </a>
                        
                       
                       <a href="" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/AsignacionTurno.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Asignaci�n de Turnos</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/ListadoTurnos.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Listado de Turnos</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/Reportes.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Reportes</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/AgregarAdministrador.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Agregar Administrador</p>
                                </div>
                            </div>
                        </a>
                        
                        
			</div>
			</form>
	</div>
	
</body>
</html>