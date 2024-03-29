package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import clases.Pasajero;
import model.Model;

/**
 * Servlet implementation class AJAXPasajerosVueloController
 */
@WebServlet("/AJAXPasajerosVueloController")
public class AJAXPasajerosVueloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AJAXPasajerosVueloController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub
				// response.getWriter().append("Served at: ").append(request.getContextPath());

				// Primero: Especificar el tipo de contenido que recibirá
				// el navegador web como respuesta a la peticion
				response.setContentType("application/json; charset=UTF-8");
				
				int idVuelo = Integer.parseInt(request.getParameter("idVuelo"));
				
				
				Model m = new Model();
				List<Pasajero> pasajeros = m.getPasajeros(idVuelo);
				
				
				PrintWriter out = response.getWriter();
				
				Gson gson = new Gson();
				
				
				out.print(gson.toJson(pasajeros));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
