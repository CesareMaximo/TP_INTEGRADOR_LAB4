package Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Entidad.Localidad;
import Entidad.Nacionalidad;
import Entidad.Paciente;
import Entidad.Provincia;
import Negocio.LocalidadNegocio;
import Negocio.NacionalidadNegocio;
import Negocio.PacienteNegocio;
import Negocio.ProvinciaNegocio;
import NegocioImpl.LocalidadNegocioImpl;
import NegocioImpl.NacionalidadNegocioImpl;
import NegocioImpl.PacienteNegocioImpl;
import NegocioImpl.ProvinciaNegocioImpl;


@WebServlet("/ServletPaciente")
public class ServletPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletPaciente() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PacienteNegocio paNeg = new PacienteNegocioImpl();
		NacionalidadNegocio nacNeg = new NacionalidadNegocioImpl();
		ArrayList<Nacionalidad> listaNacionalidad = (ArrayList<Nacionalidad>)nacNeg.readAll();
		ProvinciaNegocio provNeg = new ProvinciaNegocioImpl();
		ArrayList<Provincia> listaProvincia = (ArrayList<Provincia>)provNeg.readAll();
		RequestDispatcher rd;
		Paciente pa = new Paciente();
		
		
		request.setAttribute("listaNacionalidad", listaNacionalidad);
		request.setAttribute("listaProvincia", listaProvincia);
		
		LocalidadNegocio loNeg = new LocalidadNegocioImpl();
		
		ArrayList<Localidad> listaLocalidad = (ArrayList<Localidad>) loNeg.readAll();
		
		request.setAttribute("listaLocalidad", listaLocalidad);
		
		if(request.getParameter("Nuevo")!= null) {
		request.setAttribute("exito", false);
		rd = request.getRequestDispatcher("/AgregarPaciente.jsp");
		rd.forward(request, response);
		
		}
		
		ArrayList<Paciente> listaPaciente = (ArrayList<Paciente>) paNeg.readAll();
		
		
		
		if(request.getParameter("Param")!= null) {
			request.setAttribute("listaPaciente", listaPaciente);
			request.setAttribute("exito", false);
			
			rd = request.getRequestDispatcher("/MenuPaciente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("Modificar")!=null)
		{
			
			String dni = request.getParameter("Modificar").toString();
			pa = paNeg.mostrarPaciente(dni);            
			request.getSession().setAttribute("paciente", pa);
			ArrayList<Localidad> listaLocalidad2 = (ArrayList<Localidad>) loNeg.readAllxid(pa.getlLocalidad().getpProvincia().getIdProvincia());
			request.setAttribute("listaLocalidad2", listaLocalidad2);
			rd = request.getRequestDispatcher("/ModificarPaciente.jsp");   
	        rd.forward(request, response);
	        
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PacienteNegocio paNeg = new PacienteNegocioImpl();
		Paciente paciente = new Paciente();
		RequestDispatcher rd;
		
		LocalidadNegocio loNeg = new LocalidadNegocioImpl();
		
		ArrayList<Localidad> listaLocalidad = (ArrayList<Localidad>) loNeg.readAll();
		
		request.setAttribute("listaLocalidad", listaLocalidad);
		
		
		if (request.getParameter("Eliminar") != null) {
			String dni = request.getParameter("pacId");
			
			if(paNeg.delete(dni)==true) {
				request.setAttribute("delete", true);
				request.setAttribute("mensaje", "");
				ArrayList<Paciente> listaPaciente = (ArrayList<Paciente>) paNeg.readAll();
				request.setAttribute("listaPaciente", listaPaciente);
				rd = request.getRequestDispatcher("/MenuPaciente.jsp");
				rd.forward(request, response);
			}
			
		}
		
		
		
		if(request.getParameter("btnBuscar")!=null) {
			
			String nombre= request.getParameter("txtBuscar").toString();
			
			ArrayList<Paciente> listaPacienteBuscar = (ArrayList<Paciente>) paNeg.readAllBuscar(nombre);
			request.setAttribute("listaPaciente", listaPacienteBuscar);
			
			
			rd = request.getRequestDispatcher("/MenuPaciente.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("insert") != null) {
			
			paciente.setDni(request.getParameter("txtDni").toString());
			paciente.setNombre(request.getParameter("txtNombre").toString());
			paciente.setApellido(request.getParameter("txtApellido").toString());
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date dateFormateado = new Date();
			try {
				dateFormateado = formato.parse(request.getParameter("txtFechaNac"));
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			java.sql.Date date1 = new java.sql.Date(dateFormateado.getTime());
			paciente.setFechaNacimiento(date1);
			paciente.setSexo(request.getParameter("slcSexo").charAt(0));
			Nacionalidad nacionalidad = new Nacionalidad();
			nacionalidad.setIdNacionalidad(Integer.parseInt(request.getParameter("slcNacionalidad")));
			paciente.setnNacionalidad(nacionalidad);
			Localidad localidad = new Localidad();
			localidad.setIdLocalidad(Integer.parseInt(request.getParameter("slcLocalidad")));
			paciente.setlLocalidad(localidad);
			paciente.setDireccion(request.getParameter("txtDireccion"));
			paciente.setEmail(request.getParameter("txtEmail"));
			paciente.setTelefono1(request.getParameter("txtTelefono1"));
			paciente.setTelefono2(request.getParameter("txtTelefono2"));
			
			if(paNeg.insert(paciente) == true) {
				request.setAttribute("exito", true);
				request.setAttribute("txtDni", "");
				request.setAttribute("txtNombre", "");
				request.setAttribute("txtApellido", "");
				request.setAttribute("txtFechaNac", "");
				request.setAttribute("txtDireccion", "");
				request.setAttribute("txtEmail", "");
				request.setAttribute("txtTelefono1", "");
				request.setAttribute("txtTelefono2", "");
				request.setAttribute("mensaje", "");				
			}
			
			ArrayList<Paciente> listaPaciente = (ArrayList<Paciente>) paNeg.readAll();
			request.setAttribute("listaPaciente", listaPaciente);
			rd = request.getRequestDispatcher("/MenuPaciente.jsp");
			rd.forward(request, response);	
		}
		
		if(request.getParameter("btnModificarPaciente") != null) {
			paciente.setNombre(request.getParameter("txtNombre").toString());
			paciente.setApellido(request.getParameter("txtApellido").toString());
			paciente.setDni(request.getParameter("txtDni").toString());
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date dateFormateado = new Date();
			try {
				dateFormateado = formato.parse(request.getParameter("txtFechaNac"));
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			java.sql.Date date1 = new java.sql.Date(dateFormateado.getTime());
			paciente.setFechaNacimiento(date1);
			paciente.setSexo(request.getParameter("slcSexo").charAt(0));
			Nacionalidad nacionalidad = new Nacionalidad();
			nacionalidad.setIdNacionalidad(Integer.parseInt(request.getParameter("slcNacionalidad")));
			paciente.setnNacionalidad(nacionalidad);
			Localidad localidad = new Localidad();
			localidad.setIdLocalidad(Integer.parseInt(request.getParameter("slcLocalidad")));
			paciente.setlLocalidad(localidad);
			paciente.setDireccion(request.getParameter("txtDireccion"));
			paciente.setEmail(request.getParameter("txtEmail"));
			paciente.setTelefono1(request.getParameter("txtTelefono1"));
			paciente.setTelefono2(request.getParameter("txtTelefono2"));
			
			if(paNeg.update(paciente) == true) {
				request.setAttribute("update", true);
				request.setAttribute("mensaje", "");
				ArrayList<Paciente> listaPaciente = (ArrayList<Paciente>) paNeg.readAll();
				request.setAttribute("listaPaciente", listaPaciente);
				rd = request.getRequestDispatcher("/MenuPaciente.jsp");
				rd.forward(request, response);
			}
		}
	}
}
