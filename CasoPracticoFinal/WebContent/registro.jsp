<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		
	</style>
    <title>Hello, world!</title>
  </head>
	<body>
	<div class="container">
  		<div class="abs-center">
    		<form action="#" class="border p-3 form">
    		<h1>Registro de vuelo</h1>
    			<div class = "form-group">
    				<label for="destino"> Destino</label>
    				<select id = "destino" name = "destino">
    					<option></option>
    				</select>
    			</div>
      			<div class="form-group">
        			<label for="nombre">Nombre</label>
      				<input type="text" class="form-control" id="nombre" placeholder="Nombre" value="" required>
     			</div>
      			<div class="form-group">
        			<label for="primerApellido">Primer Apellido</label>
      				<input type="text" class="form-control" id="primerApellido" placeholder="primerApellido" value="" required>
     			</div>
     			<div class="form-group">
        			<label for="segundoApellido">Segundo Apellido</label>
      				<input type="text" class="form-control" id="segundoApellido" placeholder="segundoApellido" value="" required>
     			</div>
     			<div class="form-group">
        			<label for="telefono">Telefono</label>
      				<input type="text" class="form-control" id="telefono" placeholder="telefono" value="" required>
     			</div>
     			<div class="form-group">
        			<label for="correo">Correo Electronico</label>
      				<input type="text" class="form-control" id="correo" placeholder="correo" value="" required>
     			</div>
      			<button type="submit" class="btn btn-primary">Registrar</button>
    		</form>
  		</div>
	</div>
		
		<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>