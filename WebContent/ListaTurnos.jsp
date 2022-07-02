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
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
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
                <div class="row justify-content-left">
                    <div class="col-md-6">
                    	<h1>Turnos</h1></div>
                    	<div class="col-md-6 d-flex justify-content-end">
                    	<a style="height:38px;" href="ServletTurno?Agenda=1" name="abrirAgenda" class="btn btn-primary btn-ml">Abrir agenda</a></div>
                    	
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
                    
                
              
                </div>
     


				<table id="table_turnos" class="display">
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

        </div>
    </div>  
</div>  
	<script>
			$(document).ready( function () {
			    $('#table_turnos').DataTable();
			} );
			</script>	
			<script>
			var table = $('#table_turnos').DataTable( {
			    columnDefs: [
			        { targets: [6], orderable: false},
			     
			    ]
			} );
			</script>	
</body>
</html>