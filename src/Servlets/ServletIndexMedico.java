package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import com.sun.beans.editors.IntegerEditor;

import Entidad.Paciente;
import Entidad.Turno;
import Negocio.PacienteNegocio;
import Negocio.TurnoNegocio;
import NegocioImpl.PacienteNegocioImpl;
import NegocioImpl.TurnoNegocioImpl;

/**
 * Servlet implementation class ServletIndexMedico
 */
@WebServlet("/ServletIndexMedico")
public class ServletIndexMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletIndexMedico() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		TurnoNegocio tNeg = new TurnoNegocioImpl();
		PacienteNegocio pNeg = new PacienteNegocioImpl();
		Paciente pa = new Paciente();
		int idMedico = 0;
		if(request.getSession().getAttribute("idUsuario") != null) {			
			idMedico = (int)request.getSession().getAttribute("idUsuario");
		}
		ArrayList<Turno> listaMisTurnos = (ArrayList<Turno>) tNeg.readPorMedico(idMedico);
		request.setAttribute("listaMisTurnos", listaMisTurnos);
		RequestDispatcher rd;
		
		
		
		if(request.getParameter("Index") != null) {
		
			request.setAttribute("listaMisTurnos", listaMisTurnos);
			rd = request.getRequestDispatcher("/IndexMedico.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("Modificar") != null && request.getParameter("Pax") != null) {
			Turno tu = new Turno();
			Paciente pax = new Paciente();
			int idTurno;
			idTurno = Integer.parseInt(request.getParameter("Modificar"));
			String dni;
			dni = request.getParameter("Pax");
			tu = tNeg.devuelveTurno(idTurno);
			request.setAttribute("Turno", tu);
			pax = pNeg.mostrarPaciente(dni);
			request.setAttribute("Paciente", pax);
			request.setAttribute("ModalEdit", true);
			request.getRequestDispatcher("/IndexMedico.jsp").forward(request, response);
			
		}
		
		if(request.getParameter("Detalle") != null) {
			
			
			String dni = request.getParameter("Detalle");
			pa = pNeg.mostrarPaciente(dni);
			
			request.setAttribute("Paciente", pa);
			request.setAttribute("detallePaciente", true);
			rd = request.getRequestDispatcher("/IndexMedico.jsp");
			rd.forward(request, response);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("actualizarTurno") != null) {
			RequestDispatcher rd;
			TurnoNegocio tuNeg = new TurnoNegocioImpl();
			int idTurno, estado;
			String observacion;
			idTurno = Integer.parseInt(request.getParameter("lblidTurno"));
			estado = Integer.parseInt(request.getParameter("lblEstado"));
			observacion = request.getParameter("txtObservacion");
			if(tuNeg.update2(idTurno, estado, observacion) == true ) {
				request.setAttribute("exito", true);
			}
			TurnoNegocio tNeg = new TurnoNegocioImpl();
			int idMedico = 0;
			if(request.getSession().getAttribute("idUsuario") != null) {			
				idMedico = (int)request.getSession().getAttribute("idUsuario");
			}
			ArrayList<Turno> listaMisTurnos = (ArrayList<Turno>) tNeg.readPorMedico(idMedico);
			request.setAttribute("listaMisTurnos", listaMisTurnos);
			
			rd = request.getRequestDispatcher("/IndexMedico.jsp");
			rd.forward(request, response);
			
		}
		
		
	}

}
