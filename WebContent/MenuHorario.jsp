<%@page import="Entidad.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Horario de Atención</title>

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

</head>

<body>
<form method="post" action ="logout" >
 <div  style=" font-family:Open Sans; margin-top:6px; float: right; margin-right: 12px; color: #fff; font-size: 12px; ">
 <a href="MiCuenta.jsp" title="Mi Cuenta" data-toggle="tooltip""><img width="16px"class="imag" src="img/user.png"/></a> Bienvenido <b> <%= session.getAttribute("username") %></b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	
<div style="float: left; margin-left: 12px; margin-top:6px;">
<a href="ServletMedico?Param=1"><img src="img/atras.png" height="20px" /></a>
<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
	<br>
	<div class="container-xl">
		<div class="table-responsive">
        	<div class="table-wrapper">
            	<div style="padding-bottom: 0px;"class="table-title">
            	 <div class="row">
            	 <% 
                	Medico me = (Medico)session.getAttribute("medico");
                	%>
                <div class="col-md-10">
                    <h1>Horarios: Dr/a <%=me.getNombre()%> <%=me.getApellido()%></h1>
                 </div>
                 <div class="col-md-2 d-flex justify-content-end">  
                 <a style="height:38px;" href="Horario?Nuevo=1" name="nuevoHorario" class="btn btn-primary btn-ml">Agregar Horario</a> 
                </div>
                </div>
		            </div>

	                	<%
						ArrayList<DiaXMedico> listaHorario = null;
	                	listaHorario = (ArrayList) session.getAttribute("listaHorario");
						/* if(session.getAttribute("listaHorario")!= null){
							listaHorario = (ArrayList<Horario>) request.getAttribute("listaHorario");
						} */
						%>


					
					<table id="table_id" class="display">
					   	<thead>
	                    <tr>
	                        <th>Dia</th>
	                        <th>Horario Ingreso </th>
	                        <th>Horario Egreso</th>
	                        <th>Edit</th>
	                    </tr>
                	</thead>
					    <tbody>
					     	<%
 					if(listaHorario !=null)
					for(DiaXMedico ho : listaHorario){ 
					%>
						<tr> 
 							<td><%=ho.getDia().getDescripcion()%></td>
							<td><%=ho.getHorarioIngreso()%></td>
							<td><%=ho.getHorarioEgreso()%></td>

                        <td>
                           <a href="ModificarHorario.jsp" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>                        
                           <a href="#myModal" class="delete" title="Delete" data-toggle="modal" data-med-id="<%=ho.getDia().getId()%>" ><i class="material-icons">&#xE872;</i></a>
                        </td>
        
					<%} %>
						</tr> 
                </tbody>
					</table>

            </div>
        </div>
	</div>
	<script>
			$(document).ready( function () {
			    $('#table_id').DataTable();
			} );
			</script>	
			<script>
			var table = $('#table_id').DataTable( {
			    columnDefs: [
			        { targets: [3], orderable: false},
			     
			    ]
			} );
			</script>	
	
</body>
</html>