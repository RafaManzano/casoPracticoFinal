<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:useBean id="m" class="model.Model"></jsp:useBean> 
<!DOCTYPE html>
<html>
 <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<style type="text/css">
	.abs-center {
	  display: flex;
	  align-items: center;
	  justify-content: center;
	  min-height: 100vh;
	}
 
	.form {
 		width: 450px;
	}
	
	#equisE, #equisPA, #equisSA, #equisE, #equisT, #equisN  {
		color: red;
     	font-weight: bold;
    	display: none;
		
	}	
	</style>
    <title>Registrar Vuelo</title>
  </head>
	<body>
	<div class="container">
  		<div class="abs-center">
<!--   		El metodo no funciona asi que la validacion manda los datos incorrectos> -->
    		<form class="border p-3 form" id = "form"> 
    		<h1>Registro de vuelo</h1>
    			<div class = "form-group">
    				<label for="destino"> Destino</label>
    				<select id = "destino" name = "destino">
    					<option></option> 
    					<c:forEach items = "${ m.vuelos }" var = "v">
    						<option value = "${v.getId() }"><c:out value = "${v.getDestino() }"></c:out></option>
    					</c:forEach>
    				</select>
    				<input type="hidden" value="" id="idVuelo" name="idVuelo">
    				
    			</div>
      			<div class="form-group">
        			<label for="nombre">Nombre</label>
      				<input type="text" class="form-control" id="nombre" placeholder="Nombre" value="" required>
      				<span id = "equisN" name = "equisN"> X El nombre debe ser primera letra mayuscula y solo 1 espacio y sin caracteres especiales</span>
     			</div>
      			<div class="form-group">
        			<label for="primerApellido">Primer Apellido</label>
      				<input type="text" class="form-control" id="primerApellido" placeholder="primerApellido" value="" required>
       				<span id = "equisPA" name = "equisN"> X El nombre debe ser primera letra mayuscula y solo 1 espacio y sin caracteres especiales</span>
     			</div>
     			<div class="form-group">
        			<label for="segundoApellido">Segundo Apellido</label>
      				<input type="text" class="form-control" id="segundoApellido" placeholder="segundoApellido" value="" required>
       				<span id = "equisSA" name = "equisN"> X El nombre debe ser primera letra mayuscula y solo 1 espacio y sin caracteres especiales</span>
     			</div>
     			<div class="form-group">
        			<label for="telefono">Telefono</label>
      				<input type="text" class="form-control" id="telefono" placeholder="telefono" value="" required>
      				<span id = "equisT" name = "equisT"> X El telefono son 9 digitos</span>
     			</div>
     			<div class="form-group">
        			<label for="correo">Correo Electronico</label>
      				<input type="text" class="form-control" id="correo" placeholder="correo" value="" required>
      				<span id = "equisE" name = "equisE"> X El email tiene que tener el formato correcto</span>
     			</div>
      			<button type="button" class="btn btn-primary" id = "btn1" name = "btn1">Registrar</button>
    		</form>
  		</div>
	</div>
	
	<h3>Trabajadores de toda la empresa</h3>
	<table border="1">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Primer Apellido</th>
				<th>Segundo Apellido</th>
			</tr>
		</thead>
		<tbody id="filasTabla2">
		</tbody>
	</table>
		
		<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script type="text/javascript">
    var idVuelo = 0;
    document.getElementById("destino").addEventListener("change", function() {

		// Indice de la option seleccionada
		var indice = this.selectedIndex;

		// TODO. borrar o comentar la siguiente linea posteriormente
		//console.log(indice);

		// Recuperar el atributo value de la option seleccionada

		var valueOptionSelected = this.options[indice].value;
		// si necesitas el texto de la option seleccionada 

		var textOptionSelected = this.options[indice].text;

		// TODO. Borrar
		//console.log("IdDpto " + valueOptionSelected
		//		+ ", Nombre del Dpto: " + textOptionSelected)

		idVuelo = valueOptionSelected;
		});
    
		//if(validarFormulario(this) != false) {
			document.getElementById("btn1").addEventListener("click", function() {
		    	
				var objPasajero = new Object();

				objPasajero.idVuelo = idVuelo;
				objPasajero.nombre = document.getElementById("nombre").value;
				objPasajero.primerApellido = document.getElementById("primerApellido").value;
				objPasajero.segundoApellido = document.getElementById("segundoApellido").value;
				objPasajero.telefono = document.getElementById("telefono").value;
				objPasajero.correo = document.getElementById("correo").value;

				// TODO. Borrar o comentar 
				// console.log(objTrabajador);

				/*
				El trabajador sera enviado al servidor, a un controlador que lo insertara
				en BBDD y devolvera una lista de todos los trabajadores dado de alta hasta
				la fecha. Este proceso llamado AJAX (Asynchronic JavaScript and XML) tiene
				lugar de forma asincronica, es decir, los scripts de la pagina no se detienen
				a esperar por la respuesta, sino que se preparan las condiciones para que una vez
				se reciba la respuesta por parte del servidor se pueda procesar
				 */
				var http = new XMLHttpRequest();

				// Preparar las condiciones para recibir la respuesta del servidor
				http.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						alert("Pasajero introducido correctamente");
						/*var rtaRecibida = this.responseText;
						console.log("Respuesta Recibida: "
								+ rtaRecibida);
						var objRtaRecibida = JSON
								.parse(rtaRecibida);
						var filasTabla = "";
						for (var i = 0; i < objRtaRecibida.length; i++) {
							filasTabla += "<tr><td>"
									+ objRtaRecibida[i].id
									+ "</td><td>"
									+ objRtaRecibida[i].nombre
									+ "</td><td>"
									+ objRtaRecibida[i].apellidos
									+ "</td><td>"
									+ objRtaRecibida[i].salario
									+ "</td></tr>"
						}
						document.getElementById("filasTabla1").innerHTML = filasTabla;*/
						//prompt("Pasajero introducido correctamente");
					}
				};

				var stringJSONObjPasajero = JSON.stringify(objPasajero);

				//console.log(stringJSONObjPasajero);
				// Formular la peticion

				http.open("Post", "AJAXAltaPasajeroController", true);
				http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				http.send("pasajero=" + stringJSONObjPasajero);

			});

    			//}
   			 	
   			 	
			 /*
			Validacion del Formulario con JavaScript y Expresiones Regulares
			*/
			function validarNombre(){
				var nombreCorrecto = true;
				var elementoHTML = document.getElementById("nombre");
				// expresion regular para comprobar si el nombre es correcto.
				var patronNombre = /^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\s]?)+$/;
				
				// Validar si el nombre es correcto, y si no lo es cambia a false el valor de la variable
				var nombreEscrito = elementoHTML.value;
				if(!patronNombre.test(nombreEscrito)){
					nombreCorrecto = false;
					//elementoHTML.focus();
					document.getElementById("equisN").style = "display: inline";
				} 
				else {
					document.getElementById("equisN").style = "display: none";
				}

				
				return nombreCorrecto;
			}
			 
			function validarPrimerApellido(){
				var nombreCorrecto = true;
				var elementoHTML = document.getElementById("primerApellido");
				// expresion regular para comprobar si el nombre es correcto.
				var patronNombre = /^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\s]?)+$/;
				
				// Validar si el nombre es correcto, y si no lo es cambia a false el valor de la variable
				var nombreEscrito = elementoHTML.value;
				if(!patronNombre.test(nombreEscrito)){
					nombreCorrecto = false;
					//elementoHTML.focus();
					document.getElementById("equisPA").style = "display: inline";
				} 
				else {
					document.getElementById("equisPA").style = "display: none";
				}

				
				return nombreCorrecto;
			}
			
			
			function validarSegundoApellido(){
				var nombreCorrecto = true;
				var elementoHTML = document.getElementById("segundoApellido");
				// expresion regular para comprobar si el nombre es correcto.
				var patronNombre = /^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\s]?)+$/;
				
				// Validar si el nombre es correcto, y si no lo es cambia a false el valor de la variable
				var nombreEscrito = elementoHTML.value;
				if(!patronNombre.test(nombreEscrito)){
					nombreCorrecto = false;
					//elementoHTML.focus();
					document.getElementById("equisSA").style = "display: inline";
				} 
				else {
					document.getElementById("equisSA").style = "display: none";
				}

				
				return nombreCorrecto;
			}
			
			function validarEmail(){
				var nombreCorrecto = true;
				var elementoHTML = document.getElementById("correo");
				// expresion regular para comprobar si el nombre es correcto.
				var patronNombre = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;
				
				// Validar si el nombre es correcto, y si no lo es cambia a false el valor de la variable
				var nombreEscrito = elementoHTML.value;
				if(!patronNombre.test(nombreEscrito)){
					nombreCorrecto = false;
					//elementoHTML.focus();
					document.getElementById("equisE").style = "display: inline";
				} 
				else {
					document.getElementById("equisE").style = "display: none";
				}

				
				return nombreCorrecto;
			}
			
			function validarTelefono(){
				var nombreCorrecto = true;
				var elementoHTML = document.getElementById("telefono");
				// expresion regular para comprobar si el nombre es correcto.
				var patronNombre = /^[\d]{3}[-]*([\d]{2}[-]*){2}[\d]{2}$/;
				
				// Validar si el nombre es correcto, y si no lo es cambia a false el valor de la variable
				var nombreEscrito = elementoHTML.value;
				if(!patronNombre.test(nombreEscrito)){
					nombreCorrecto = false;
					//elementoHTML.focus();
					document.getElementById("equisT").style = "display: inline";
				} 
				else {
					document.getElementById("equisT").style = "display: none";
				}

				return nombreCorrecto;
			}
			
			
			function validarFormulario(event){
				event.preventDefault();
				event.stopPropagation();
				
				if(validarNombre() == false){
					return false;
				} 
				
				return event.target.submit();
			}
			
			document.getElementById("nombre").addEventListener("blur", validarNombre);
			document.getElementById("primerApellido").addEventListener("blur", validarPrimerApellido);
			document.getElementById("segundoApellido").addEventListener("blur", validarSegundoApellido);
			document.getElementById("telefono").addEventListener("blur", validarTelefono);
			document.getElementById("correo").addEventListener("blur", validarEmail);
			
			document.getElementById("form").addEventListener("submit", validarFormulario);
				
		/*
		Crear una funcion change para mostrar los pasajeros cuando cambia en el desplegable el destino
		*/
		document.getElementById("destino").addEventListener("change", function() {
					/*
					// Indice de la option seleccionada			
					var indice = this.selectedIndex;

					// TODO. Borrar o comentar la siguiente linea
					// posteriormente
					console.log(indice);

					// Recuperar el atributo value de la option seleccionada
					var valueOptionSelected = this.options[indice].value;

					// Si necesitas el texto de la option seleccionada
					var textOptionSelected = this.options[indice].text;

					// TODO. Borrar
					console.log("IdDpto: " + valueOptionSelected
							+ ", Nombre del Dpto: "
							+ textOptionSelected);

					idDptoSeleccionado = valueOptionSelected;
					*/

					// Generar una peticion por AJAX, para que el servidor
					// nos devuelva un listado de empleados que pertenecen
					// al Departamento Seleccionado
					var https = new XMLHttpRequest();

					https.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							var respuesta = this.responseText;

							// TODO. Borrar posteriormente
							//console.log(rtaRecibida);

							// Renderizar la respuesta en un elemento HTML
							var objetoRec = JSON.parse(respuesta);
							
							//console.log(objRtaRecibida);

							var filasTabla = "";
							
							for (var i = 0; i < objetoRec.length; i++) {
								filasTabla += "<tr><td>"
										+ objetoRec[i].nombre
										+ "</td><td>"
										+ objetoRec[i].primerApellido
										+ "</td><td>"
										+ objetoRec[i].segundoApellido 
										+ "</td></tr>";

							}

							document.getElementById("filasTabla2").innerHTML = filasTabla;

						}
					};

					https.open("GET", "AJAXPasajerosVueloController?idVuelo="
							+ idVuelo, true);
					https.send();

				});

    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>