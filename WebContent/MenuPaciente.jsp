<%@page import="Entidad.*"%>
<%@page import="java.util.ArrayList"%>
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
<title>Pacientes</title>
</head>
<body>

<!-- LISTADO DE PACIENTES CON BOTON AGREGAR PACIENTE Y BOTONES MODIFICAR Y ELIMINAR EN CADA FILA 
CUANDO APRETAS ELIMINAR SALTA VENTANA DE CONFIRMACIÓN 
FILTRO DE BUSQUEDA-->
<form method="post" action ="logout" >
 <div  style=" font-family:Open Sans; margin-top:6px; float: right; margin-right: 12px; color: #fff; font-size: 12px; "><img width="16px"class="imag" src="img/user.png"/> Bienvenido <b><%= session.getAttribute("username") %></b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	
<div style="float: left; margin-left: 12px; margin-top:6px;">
	<a href="IndexAdmin.jsp"><img src="img/atras.png" height="20px" /></a>
	<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
	
	<br>
	
	<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row justify-content-center">
                    <div class="col-sm-8"><h1>Pacientes</h1>
                    
                    <a href="ServletPaciente?Nuevo=1" name="nuevoPaciente" class="btn btn-primary btn-ml">Nuevo Paciente</a>
                    </div>
                
                    <form action="ServletPaciente" method="post" >
					    <div class="col-md-3 col-md-offset-9 text-right">
					        <div class="btn-group d-flex w-100" role="group">
					            <input type="text" name="txtBuscar" class="resizedTextbox" placeholder="Buscar">
			                    <input class="btn btn-primary btn-sm" type="submit" name="btnBuscar" value="Buscar">
					        </div>
					    </div>
                    </form>
                </div>
            </div>
            <form action="Paciente" method="get">
	            <%
					ArrayList<Paciente> listaPaciente = null;
					if(request.getAttribute("listaPaciente")!= null){
						listaPaciente = (ArrayList<Paciente>) request.getAttribute("listaPaciente");
					}
				 %>
            </form>
            <table class="table table-striped table-hover table-bordered">
            	<thead>
                    <tr>
                        <th>DNI</th>
                        <th>Nombre <i class="fa fa-sort"></i> </th>
                        <th>Apellido<i class="fa fa-sort"></i></th>
                        <th>Email</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                	<%  
                		
						if(listaPaciente !=null)
						for(Paciente pa : listaPaciente){
					
					%>
	                    <tr>
	                    	<td><%=pa.getDni()%></td>
							<td><%=pa.getNombre()%></td>
							<td><%=pa.getApellido()%></td>
							<td><%=pa.getEmail()%></td>
							<td>
	                    	<a href="ServletPaciente?Modificar=<%=pa.getDni() %>" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
	                       	<a href="#myModal" class="delete" title="Delete" data-toggle="modal" data-pac-id="<%=pa.getDni()%>" ><i class="material-icons">&#xE872;</i></a>
	                       	</td>
	              
	                       	
	                 	
	               	<%} %>
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


						<script type="text/javascript">
	                        $(document).ready(function (e) {
	                        	  $('#myModal').on('show.bs.modal', function(e) {
	                        	 
	                        		  var dni = $(e.relatedTarget).data('pac-id'); 
	                        		  $(e.currentTarget).find('input[name="pacId"]').val(dni);
										
	                        		  
	                        	  });
	                        	});
							
							</script>	

	
	
			<form action="ServletPaciente?Eliminar=1" method="post">
							<div id="myModal" class="modal fade">
								<div class="modal-dialog modal-confirm">
									<div class="modal-content">
										<div class="modal-header flex-column">
											<div class="icon-box">
												<i class="material-icons">&#xE5CD;</i>
											</div>						
											<h4 class="modal-title w-100">¿Estás seguro?</h4>	
							                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										</div>
										<div class="modal-body">
											<p>¿Desea eliminar el paciente? Esta operación no se puede deshacer</p>
											<input type="hidden" name="pacId" id="pacId">
										</div>
										<div class="modal-footer justify-content-center">
											<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
											<button type="submit" class="btn btn-danger">Borrar</button>
										</div>
									</div>
								</div>
							</div>    
						</form>
						
						<% 		
			
				
					boolean x = (boolean)request.getAttribute("exito");
					if(x == true){
						%>
						<div id="miModal" class="modificar">
						  <div class="modificar-contenido">
				
						    <a href="ServletPaciente?Param=1.jsp">X</a>
						    <h2>PACIENTE MODIFICADO CON ÉXITO</h2>
						  </div>  
						</div>
						 <%} 
							%>
	
	
</body>
</html>