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

</head>

<body>
	<div class="container-xl">
		<div class="table-responsive">
        	<div class="table-wrapper">
            	<div  style="padding-bottom: 0px;"class="table-title">
                	<div class="row">
                    <div class="col-sm-8"><h1>Médicos</h1>
                        <a href="Horario?Nuevo=1" name="nuevoHorario" class="btn btn-primary btn-ml">Agregar Horario</a>
		            </div>
		            </div>
 					<table class="table table-striped table-hover table-bordered">
                	<thead>
	                    <tr>
	                        <th>DIA</th>
	                        <th>HORARIO INGRESO </th>
	                        <th>HORARIO EGRESO</th>
	                        <th>EDIT</th>
	                    </tr>
                	</thead>
                    <% 
/* 						if(listaHorario !=null)
						for(Horario ho : listaHorario){ */
					%>
						<tr> 
 							<td><%--<%=ho.getDia()%> --%></td>
							<td><%--<%=ho.getHorarioIngreso()%> --%></td>
							<td><%--<%=ho.getHorarioEgreso()%> --%></td>

                        <td>
                           <a href="ModificarMedico.jsp" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>                        
                           <a href="#myModal" class="delete" title="Delete" data-toggle="modal" data-med-id="<%-- <%=ho.getIdHorario()%> --%>" ><i class="material-icons">&#xE872;</i></a>
                        </td>
        
					<%//} %>
						</tr> 
                </tbody>
            </table>
            </div>
            </div>
        </div>
	</div>
</body>
</html>