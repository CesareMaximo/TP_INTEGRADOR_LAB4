<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Home</title>
</head>
<body>
 <!-- MENSAJE BIENVENIDA
 
 MENU 
 
MENU PACIENTE
MENU MEDICO
ASIGNACION DE TURNOS
LISTADO DE TURNOS
REPORTES 
  --><% 	
	
	try{
	
		if(session == null){
			
		}

	
		if (session.getAttribute("tipo").equals("Admin")) {
	%>
 <div class="User">
 	<form method="post" action ="logout" >
 		<img width="16px"class="imag" src="img/user.png"/> Bienvenido <b> <%= session.getAttribute("username") %></b>
 		<input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary" style="margin-left:10px;">
	</form>
 </div>
	<br>
	<div class="registro">
		<form>
			<h1>Menú Administrativo</h1>
			
			 <div style="display: flex; flex-direction: row; flex-wrap: wrap">
                        <a href="ServletPaciente?Param=1" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/MenuPaciente.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Menú Paciente</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="ServletMedico?Param=1" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/MenuMedicos.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Menú Médico</p>
                                </div>
                            </div>
                        </a>
                        
                       
                       <a href="AsignacionTurnos.jsp" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/AsignacionTurno.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Asignación de Turnos</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="ListaTurnos.jsp" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/ListadoTurnos.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Listado de Turnos</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="MenuReportes.jsp" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/Reportes.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Reportes</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="AgregarAdministrativo.jsp" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 8.5rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/AgregarAdministrador.png" style="width: 50px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 14px">Agregar Administrativo</p>
                                </div>
                            </div>
                        </a>
                        
                        
			</div>
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