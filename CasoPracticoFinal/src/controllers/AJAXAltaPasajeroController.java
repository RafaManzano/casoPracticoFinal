package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import clases.PasajeroFull;
import model.Model;

/**
 * Servlet implementation class AJAXAltaPasajeroController
 */
@WebServlet("/AJAXAltaPasajeroController")
public class AJAXAltaPasajeroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AJAXAltaPasajeroController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// primero: Especificar el tipo de contenido que recibira el navegador web como respuesta a la peticion 
		response.setContentType("application/json; charset=UTF-8");
		
		// Segundo: recibir el parametro, es decir , el trabajador en formato JSON y convertirlo a un objeto de java, y  como java
		// no entiende JSON aqui entra la biblioteca de Google GSON
		Gson gs = new Gson();
		
		PasajeroFull pasajero = gs.fromJson(request.getParameter("pasajero"), PasajeroFull.class);
		
		// Instanciar el modelo para dar alta al pasajero
		Model m = new Model();
		m.setPasajero(pasajero);
		
		// Ahora el Listado de empleados lo tengo que convertir a un String en formato JSON y devolverlo como respuesta a la peticion formulada por AJAX
		//PrintWriter out = response.getWriter();
		
		//out.print("Todo Perfecto");
		
	}

}
