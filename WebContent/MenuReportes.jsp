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
<!-- 
MENU 
OPCIONES:
TOTAL DE PACIENTES ATENDIDOS POR MEDICO 
TOTAL DE TURNOS AUSENTES POR ENTRE FECHA Y FECHA
PROMEDIO DE PACIENTES POR MEDICO
PROMEDIO DE PACIENTES POR ESPECIALIDAD
 -->

 
  <div class="Index"> <a href="IndexAdmin.jsp" class="btn btn-primary"  style="margin-left:10px;">Inicio</a> </div>
 <div class="User"><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b>NombreUsuario</b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary" style="margin-left:10px;"></div>
	<br>
	<div class="registro">
		<form>
			<h1>Reportes</h1>
			
			 <div style="display: flex; flex-direction: row; flex-wrap: wrap">
                        <a href="#openModalMedico" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 12px">Total de pacientes atendidos por medico</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="#openModalFechas" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 12px">Total turnos ausente entre fechas</p>
                                </div>
                            </div>
                        </a>
                        
                       
                       <a href="#openModalMes" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 12px">Cantidad turnos atendido por mes y año</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="#openModalAnio" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 12px">Porcentaje de asistencia por año</p>
                                </div>
                            </div>
                        </a>
                        
                      
                        
                        
			</div>
			</form>
	</div>
 
 
 <div id="openModalMedico" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<h2>Seleccione el Medico:</h2>
		<label>Medico:</label></td><td><select class="select"></select>
		<input  name="buscarMedico" type="submit" value="Buscar" class="btn btn-primary" style="margin-left:10px;" ></input>
		
	</div>
</div>

 <div id="openModalFechas" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<h2>Ingrese las fechas:</h2>
		<label>Primer Fecha:</label></td><td><input type="date" name="Fecha1" class="inputForm"></input>
		<label>Segunda Fecha:</label></td><td><input type="date" name="Fecha2" class="inputForm" ></input>
		<input  name="buscarFechas" type="submit" value="Buscar" class="btn btn-primary" style="margin-left:10px;" ></input>
		
		
	</div>
</div>


<div id="openModalMes" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<h2>Ingrese el mes y el año:</h2>
		<label> Fecha:</label></td><td><input type="month" name="Fecha1" class="inputForm"></input>
	
		<input  name="buscarMes" type="submit" value="Buscar" class="btn btn-primary" style="margin-left:10px;" ></input>
		
		
	</div>
</div>

<div id="openModalAnio" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<h2>Ingrese el año:</h2>
		
		<input type="number" min="1900" max="2099" step="1" value="2022" />
		<input  name="buscarAnio" type="submit" value="Buscar" class="btn btn-primary" style="margin-left:10px;" ></input>
		
		
	</div>
</div>


</body>
</html>