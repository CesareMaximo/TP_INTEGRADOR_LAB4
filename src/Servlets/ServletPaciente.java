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
import Negocio.NacionalidadNegocio;
import Negocio.PacienteNegocio;
import Negocio.ProvinciaNegocio;
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
		
		if(request.getParameter("Nuevo")!= null) {
		request.setAttribute("listaNacionalidad", listaNacionalidad);
		request.setAttribute("listaProvincia", listaProvincia);
		rd = request.getRequestDispatcher("/AgregarPaciente.jsp");
		rd.forward(request, response);
		
		}
		
		ArrayList<Paciente> listaPaciente = (ArrayList<Paciente>) paNeg.readAll();
		
		if(request.getParameter("Param")!= null) {
			request.setAttribute("listaPaciente", listaPaciente);
			
			rd = request.getRequestDispatcher("/MenuPaciente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("Modificar")!=null)
		{
			String dni = request.getParameter("Modificar").toString();
			pa = paNeg.mostrarPaciente(dni);            
			request.getSession().setAttribute("paciente", pa);
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
			//paciente.setSexo(request.getParameter("slcSexo").charAt(0));
			paciente.setSexo('M'); // para prueba
			Nacionalidad nacionalidad = new Nacionalidad();
			//nacionalidad.setIdNacionalidad(Integer.parseInt(request.getParameter("slcNacionalidad")));
			nacionalidad.setIdNacionalidad(1); // para prueba
			paciente.setnNacionalidad(nacionalidad);
			Localidad localidad = new Localidad();
			//localidad.setIdLocalidad(Integer.parseInt(request.getParameter("slcLocalidad")));
			localidad.setIdLocalidad(1); // para prueba
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

			request.getRequestDispatcher("/AgregarPaciente.jsp").forward(request, response);	
		}
		
		if(request.getParameter("btnModificarPaciente") != null) {
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
			//paciente.setSexo(request.getParameter("slcSexo").charAt(0));
			paciente.setSexo('M'); // para prueba
			Nacionalidad nacionalidad = new Nacionalidad();
			//nacionalidad.setIdNacionalidad(Integer.parseInt(request.getParameter("slcNacionalidad")));
			nacionalidad.setIdNacionalidad(1); // para prueba
			paciente.setnNacionalidad(nacionalidad);
			Localidad localidad = new Localidad();
			//localidad.setIdLocalidad(Integer.parseInt(request.getParameter("slcLocalidad")));
			localidad.setIdLocalidad(1); // para prueba
			paciente.setlLocalidad(localidad);
			paciente.setDireccion(request.getParameter("txtDireccion"));
			paciente.setEmail(request.getParameter("txtEmail"));
			paciente.setTelefono1(request.getParameter("txtTelefono1"));
			paciente.setTelefono2(request.getParameter("txtTelefono2"));
			
			if(paNeg.update(paciente) == true) {
				request.setAttribute("exito", true);
				request.setAttribute("mensaje", "");				
			}

			request.getRequestDispatcher("ServletPaciente?Param=1").forward(request, response);
		}
		
	}

}
