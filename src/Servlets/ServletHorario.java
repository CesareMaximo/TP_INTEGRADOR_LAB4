package Servlets;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jmx.snmp.Timestamp;

import Entidad.Dia;
import Entidad.DiaXMedico;
import Entidad.Medico;
import Negocio.DiaXMedicoNegocio;
import Negocio.MedicoNegocio;
import NegocioImpl.DiaXMedicoNegocioImpl;
import NegocioImpl.MedicoNegocioImpl;


@WebServlet("/Horario")
public class ServletHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletHorario() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Medico me = new Medico();
		int idMedico;
		MedicoNegocio meNeg = new MedicoNegocioImpl();
		DiaXMedicoNegocio hoNeg = new DiaXMedicoNegocioImpl();
		RequestDispatcher rd;
		if(request.getParameter("param") != null) {
			idMedico = Integer.parseInt((request.getParameter("param").toString()));
			me = meNeg.mostrarMedico(idMedico);
			ArrayList<DiaXMedico> listaHorario = (ArrayList<DiaXMedico>) hoNeg.readall(idMedico);
			request.getSession().setAttribute("medico", me);
			request.getSession().setAttribute("listaHorario", listaHorario);
			rd = request.getRequestDispatcher("/MenuHorario.jsp");
			rd.forward(request, response);
			return;
		}
		if(request.getParameter("Nuevo") != null) {
			request.getRequestDispatcher("/AgregarHorario.jsp").forward(request, response);
			return;
		}
		
		
		if( request.getParameter("IdD") != null) {
			
			request.setAttribute("eliminar", true);
			
			int dia = Integer.parseInt(request.getParameter("IdD"));
			
			request.setAttribute("idDia", dia);
			request.getRequestDispatcher("/MenuHorario.jsp").forward(request, response);
			return;
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiaXMedicoNegocio hoNeg = new DiaXMedicoNegocioImpl();
		DiaXMedico diaXMedico = new DiaXMedico();
		Medico me = (Medico)request.getSession().getAttribute("medico");
		Dia dia = new Dia();
		RequestDispatcher rd;
		
		int idMedico = me.getIdMedico().getIdUsuario();
		
		if (request.getParameter("btnNuevoHorario")!=null) {
			Time ingreso = Time.valueOf(request.getParameter("slcIngreso")); 
			Time egreso = Time.valueOf(request.getParameter("slcEgreso"));
			if(ingreso.compareTo(egreso) > 0 || ingreso.compareTo(egreso) == 0) {
				request.setAttribute("mensaje", "ERROR: Horario Ingreso debe ser menor al Horario Egreso");
				request.getRequestDispatcher("AgregarHorario.jsp").forward(request, response);
				return;
			}
			else {
				int diaMedico = Integer.parseInt(request.getParameter("slcDia"));
				if(hoNeg.diaTrabajoMedico(idMedico, diaMedico)) {
					request.setAttribute("mensaje", "ERROR: Ya tiene Horarios para ese dia");
					request.getRequestDispatcher("AgregarHorario.jsp").forward(request, response);
				}
				else {					
					dia.setId(Integer.parseInt(request.getParameter("slcDia")));
					diaXMedico.setDia(dia);
					diaXMedico.setMedico(me);
					diaXMedico.setHorarioIngreso(Time.valueOf(request.getParameter("slcIngreso")));
					diaXMedico.setHorarioEgreso(Time.valueOf(request.getParameter("slcEgreso")));
					if(hoNeg.estadoBaja(diaXMedico)) {
						//si es true es que existe y doy alta
						if(hoNeg.darAlta(diaXMedico) == true) {
							request.setAttribute("exito", true);
						}
						
					}
					else {
						if(hoNeg.insert(diaXMedico)) {
							request.setAttribute("exito", true);
						}
						
					}
					ArrayList<DiaXMedico> listaHorario = (ArrayList<DiaXMedico>) hoNeg.readall(idMedico);
					request.getSession().setAttribute("listaHorario", listaHorario);//hace un get y lo setea
					rd = request.getRequestDispatcher("/MenuHorario.jsp");
					rd.forward(request, response);
					return;
				}
			}
		}
		
		if (request.getParameter("btnBorrarHorario")!=null) {
			
			int idDia = Integer.parseInt(request.getParameter("idDia"));
			
			dia.setId(idDia);
			diaXMedico.setDia(dia);
			diaXMedico.setMedico(me);
			
			if(hoNeg.delete(diaXMedico)) {
				request.setAttribute("delete", true);
				ArrayList<DiaXMedico> listaHorario = (ArrayList<DiaXMedico>) hoNeg.readall(idMedico);
				request.getSession().setAttribute("listaHorario", listaHorario);//hace un get y lo setea
				rd = request.getRequestDispatcher("/MenuHorario.jsp");
				rd.forward(request, response);
				return;

			}
	
		}
		
			
			
		
	}

}
