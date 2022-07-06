package Servlets;

import java.io.IOException;
import Negocio.*;
import NegocioImpl.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import Entidad.*;
/**
 * Servlet implementation class ServletEspecialidad
 */
@WebServlet("/ServletEspecialidad")
public class ServletEspecialidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEspecialidad() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EspecialidadNegocio esNeg = new EspecialidadNegocioImpl();
		Especialidad es = new Especialidad();
		ArrayList<Especialidad> listaEspecialidad = (ArrayList<Especialidad>) esNeg.readAll();
		
		
		
		RequestDispatcher rd;
		request.setAttribute("listaEspecialidad", listaEspecialidad);
		
		if (request.getParameter("Param") != null) {
			
			
					
			rd = request.getRequestDispatcher("/MenuEspecialidad.jsp");
			rd.forward(request, response);
		}
		
		
		if (request.getParameter("Nuevo") != null) {
			
			rd = request.getRequestDispatcher("/AgregarEspecialidad.jsp");
			rd.forward(request, response);
		}	
		
		if(request.getParameter("Modificar")!=null)
		{
			
			int idEspecialidad = Integer.parseInt(request.getParameter("Modificar").toString()) ;
			 es = esNeg.readAllxId(idEspecialidad);
			 
			request.getSession().setAttribute("especialidad", es);
			
			           
		
			rd = request.getRequestDispatcher("/ModificarEspecialidad.jsp");   
	        rd.forward(request, response);
	        
			
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EspecialidadNegocio esNeg = new EspecialidadNegocioImpl();
		Especialidad es = new Especialidad();
		
		
		
		RequestDispatcher rd;
		
		
		if (request.getParameter("Eliminar") != null) {
			int idEspecialidad= Integer.parseInt( request.getParameter("medId"));
			
			
			es.setIdEspecialidad(idEspecialidad);
			
			if(esNeg.delete(es)==true) {
				request.setAttribute("exito", true);
				request.setAttribute("mensaje", "");
				ArrayList<Especialidad> listaEspecialidad = (ArrayList<Especialidad>) esNeg.readAll();
				request.setAttribute("listaEspecialidad", listaEspecialidad);
				rd = request.getRequestDispatcher("/MenuEspecialidad.jsp");
				rd.forward(request, response);
			}
			
		}
		
		
		if(request.getParameter("btnNuevo")!=null) {
			
			String Descripcion= request.getParameter("txtDescripcion").toString();		
			es.setDescripcion(Descripcion);
			
			if(esNeg.existe(request.getParameter("txtDescripcion").toString())) {
				request.setAttribute("mensaje", "Especialidad ya registrada , por favor intente con otra");
				ArrayList<Especialidad> listaEspecialidad = (ArrayList<Especialidad>) esNeg.readAll();
				request.setAttribute("listaEspecialidad", listaEspecialidad);
				request.getRequestDispatcher("AgregarEspecialidad.jsp").forward(request, response);
				
			}
			else {
			if(esNeg.insert(es)==true) {
				
				request.setAttribute("exito", true);
				request.setAttribute("mensaje", "");
				ArrayList<Especialidad> listaEspecialidad = (ArrayList<Especialidad>) esNeg.readAll();
				request.setAttribute("listaEspecialidad", listaEspecialidad);
				rd = request.getRequestDispatcher("/MenuEspecialidad.jsp");
				rd.forward(request, response);
				
			}
			}
		
		}
		
		if(request.getParameter("btnModificarEspecialidad")!=null) {
			String Descripcion = request.getParameter("txtDescripcion").toString();
			int id = Integer.parseInt(request.getParameter("txtIdEspecialidad").toString());
			es.setIdEspecialidad(id);
			es.setDescripcion(Descripcion);
			ArrayList<Especialidad> listaEspecialidad = (ArrayList<Especialidad>) esNeg.readAll();
			
			if(esNeg.existe(request.getParameter("txtDescripcion").toString())) {
				request.setAttribute("mensaje", "Especialidad ya registrada, por favor intente con otra");
				
			
				request.setAttribute("listaEspecialidad", listaEspecialidad);
				request.getRequestDispatcher("MenuEspecialidad.jsp").forward(request, response);
				
			}
			else {
			
			if(esNeg.update(es)==true) {
				
				request.setAttribute("exito", true);
				request.setAttribute("mensaje", "");
				
				request.setAttribute("listaEspecialidad", listaEspecialidad);
				rd = request.getRequestDispatcher("/MenuEspecialidad.jsp");
				rd.forward(request, response);
			}
			}
		}
		
		
	}

}
