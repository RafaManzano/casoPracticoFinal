<%@page import="clases.PasajerosPorVuelo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="model.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%
	Model m;

	m = new Model();

	Map<String, List<PasajerosPorVuelo>> paVuelo = m.getPasajerosVuelos();

%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Hello, world!</title>
  </head>
  <body>
  <h1>Listado de vuelos por destino</h1>
<!--     <h1>Hello, world!</h1> -->
<!-- Nombre del destino -->
<%
for(Map.Entry<String, List<PasajerosPorVuelo>> entry: paVuelo.entrySet()) {
			String nombreDestino = entry.getKey();
			List<PasajerosPorVuelo> pasajeros = entry.getValue();
			%>
			<h3><p><%=nombreDestino %></p></h3>
			<table border="1">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Primer Apellido</th>						
						<th>Segundo Apellido</th>
					</tr>
				</thead>
				<tbody>
					<%
						for(PasajerosPorVuelo pa: pasajeros) {
					%>
						<tr>
							<td><%=pa.getNombre() %></td>
							<td><%=pa.getPrimerApellido() %></td>
							<td><%=pa.getSegundoApellido() %></td>							
						</tr>
					<%							
						}
					%>
				</tbody>
			</table>
			<br>
			<%
			
		}
	%>

<!-- Tabla con los datos de los pasajeros -->
<!--     <button type="button" class="btn btn-primary" id = "registrar" name = "registrar">Reservar</button> -->
<!-- <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"> -->
<a href="login.jsp" class="badge badge-dark">Registrar</a>
    
 

	<!-- 	Mi JavaScript -->
	<script type="text/javascript">
	//Ahora mismo el metodo no funciona
	/*
	document.getElementById("registrar").addEventListener("click", function(request, response) {
		request.getRequestDispatcher( "/WebContent/login.jsp" ).forward( request, response );
	}
	*/
	</script>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>