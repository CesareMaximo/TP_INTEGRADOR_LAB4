<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>

<% 	
if(session.getAttribute("tipo") != null){
if (session.getAttribute("tipo").equals("Admin")) {
%>


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>Turnos</title>
</head>



<body>
<!-- LISTADO DE TURNOS CON FILTRO POR ESTADO, POR MEDICO, POR PACIENTE, POR FECHA -->

  <div style="float: left; margin-left: 12px; margin-top:6px;">
<a href="IndexAdmin.jsp"><img src="img/atras.png" height="20px" /></a>
<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
<form method="post" action ="logout" >
 <div  style=" font-family:Open Sans; margin-top:6px; float: right; margin-right: 12px; color: #fff; font-size: 12px; "><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b><%= session.getAttribute("username") %></b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	

<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div style="padding-bottom: 0px;" class="table-title">
                <div class="row justify-content-center">
                    <div class="col-sm-8">
                    	<h1>Turnos</h1>
                		<table class ="filtrosListado">
                			<tr>
							<td><label>Estado:</label></td>
							<td><select class="select">
									<option>LIBRE</option>
									<option>OCUPADO</option>
									<option>AUSENTE</option>
									<option>PRESENTE</option>
							</select></td>
							<td><label>Fecha:</label></td>
							<td><input type="date" ></td>

							<td><label>Especialidad:</label></td>
							<td><select class="select"></select></td>

							<td><label>Medicos:</label></td>
							<td><select class="select"></select></td>
							<td><input name="btnFiltrar" type="submit" value="Filtrar" class="btn btn-primary btn-sm"></td>
						</tr>
						
                		</table>
                    </div>
                    <div class="col-sm-4">
                        <div class="search-box">
                            <i class="material-icons">&#xE8B6;</i>
                            <input type="text" class="form-control" placeholder="Search&hellip;">
                        </div>
                    </div>
                
                	<div>
                	</div>
                </div>
            </div>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>DNI Paciente</th>
                        <th>Medico <i class="fa fa-sort"></i> </th>
                        <th>Especialidad<i class="fa fa-sort"></i></th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th>Acciones </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <a href="#openModalDetallePaciente" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                            <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="clearfix">
                <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                <ul class="pagination">
                    <li class="page-item disabled"><a href="#"><i class="fa fa-angle-double-left"></i></a></li>
                    <li class="page-item active"><a href="#" class="page-link">1</a></li>
                    <li class="page-item"><a href="#" class="page-link">2</a></li>
                    <li class="page-item"><a href="#" class="page-link">3</a></li>
                    <li class="page-item"><a href="#" class="page-link">4</a></li>
                    <li class="page-item"><a href="#" class="page-link">5</a></li>
                    <li class="page-item"><a href="#" class="page-link"><i class="fa fa-angle-double-right"></i></a></li>
                </ul>
            </div>
        </div>
    </div>  
</div>  
<%
		} else {

			response.sendRedirect("Error.jsp");
		}
		}else{
			response.sendRedirect("Login.jsp");
		}
	
	%>	
</body>
</html>