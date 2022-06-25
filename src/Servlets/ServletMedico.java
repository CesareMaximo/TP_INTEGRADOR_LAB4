package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Especialidad;
import Entidad.Medico;
import Negocio.EspecialidadNegocio;
import Negocio.MedicoNegocio;
import NegocioImpl.EspecialidadNegocioImpl;
import NegocioImpl.MedicoNegocioImpl;

/**
 * Servlet implementation class ServletMedico
 */
@WebServlet("/ServletMedico")
public class ServletMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMedico() {
        super();
        
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedicoNegocio meNeg = new MedicoNegocioImpl();
		Medico me = new Medico();
		EspecialidadNegocio esNeg = new EspecialidadNegocioImpl();
		Especialidad es = new Especialidad();
		
		
		ArrayList<Especialidad> listaEspecialidad = (ArrayList<Especialidad>) esNeg.readAll();
		
		ArrayList<Medico> listaMedico = (ArrayList<Medico>) meNeg.readAll();
		
		
	
		RequestDispatcher rd;
		
		request.setAttribute("listaEspecialidad", listaEspecialidad);
		
		if (request.getParameter("Param") != null) {
			
			
			request.setAttribute("listaMedico", listaMedico);
			
			
			rd = request.getRequestDispatcher("/MenuMedico.jsp");
			rd.forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Especialidad
		
		EspecialidadNegocio esNeg = new EspecialidadNegocioImpl();
		Especialidad es = new Especialidad();
		ArrayList<Especialidad> listaEspecialidad = (ArrayList<Especialidad>) esNeg.readAll();
		
		request.setAttribute("listaEspecialidad", listaEspecialidad);
		
		
		//BtnFiltrar
		MedicoNegocio meNeg = new MedicoNegocioImpl();
		Medico me = new Medico();
		
		
		
		//BtnBuscar
		
	
		
		RequestDispatcher rd;
		
		
		//BtnFiltrar
			if(request.getParameter("btnFiltrar")!=null) {
			
				int id = Integer.parseInt(request.getParameter("espe").toString());
				
				ArrayList<Medico> listaMedicoFiltro= (ArrayList<Medico>) meNeg.readAllFiltro(id);
				
			request.setAttribute("listaMedico", listaMedicoFiltro);
			
			
			rd = request.getRequestDispatcher("/MenuMedico.jsp");
			rd.forward(request, response);
		}
			
			
			//BtnBuscar
			if(request.getParameter("btnBuscar")!=null) {
				
				String nombre= request.getParameter("txtBuscar").toString();
				
				ArrayList<Medico> listaMedicoBuscar = (ArrayList<Medico>) meNeg.readAllBuscar(nombre);
				request.setAttribute("listaMedico", listaMedicoBuscar);
				
				
				rd = request.getRequestDispatcher("/MenuMedico.jsp");
				rd.forward(request, response);
			}
	}

}
