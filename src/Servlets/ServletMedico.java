package Servlets;

import java.io.IOException;
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

import Entidad.Especialidad;
import Entidad.Localidad;
import Entidad.Medico;
import Entidad.Nacionalidad;
import Entidad.Paciente;
import Entidad.Provincia;
import Entidad.Usuario;
import Negocio.EspecialidadNegocio;
import Negocio.LocalidadNegocio;
import Negocio.MedicoNegocio;
import Negocio.NacionalidadNegocio;
import Negocio.ProvinciaNegocio;
import Negocio.UsuarioNegocio;
import NegocioImpl.EspecialidadNegocioImpl;
import NegocioImpl.LocalidadNegocioImpl;
import NegocioImpl.MedicoNegocioImpl;
import NegocioImpl.NacionalidadNegocioImpl;
import NegocioImpl.ProvinciaNegocioImpl;
import NegocioImpl.UsuarioNegocioImpl;


@WebServlet("/ServletMedico")
public class ServletMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletMedico() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedicoNegocio meNeg = new MedicoNegocioImpl();
		Medico me = new Medico();
		EspecialidadNegocio esNeg = new EspecialidadNegocioImpl();
		Especialidad es = new Especialidad();
		NacionalidadNegocio nacNeg = new NacionalidadNegocioImpl();
		ArrayList<Nacionalidad> listaNacionalidad = (ArrayList<Nacionalidad>)nacNeg.readAll();
		ProvinciaNegocio provNeg = new ProvinciaNegocioImpl();
		ArrayList<Provincia> listaProvincia = (ArrayList<Provincia>)provNeg.readAll();
		request.setAttribute("listaNacionalidad", listaNacionalidad);
		request.setAttribute("listaProvincia", listaProvincia);
		
		ArrayList<Especialidad> listaEspecialidad = (ArrayList<Especialidad>) esNeg.readAll();
		request.setAttribute("listaNacionalidad", listaNacionalidad);
		request.setAttribute("listaProvincia", listaProvincia);
		ArrayList<Medico> listaMedico = (ArrayList<Medico>) meNeg.readAll();
		

		LocalidadNegocio loNeg = new LocalidadNegocioImpl();
		
		ArrayList<Localidad> listaLocalidad = (ArrayList<Localidad>) loNeg.readAll();
		
		request.setAttribute("listaLocalidad", listaLocalidad);
		
		
	
		RequestDispatcher rd;
		
		request.setAttribute("listaEspecialidad", listaEspecialidad);
		
		if (request.getParameter("Param") != null) {
						
			request.setAttribute("listaMedico", listaMedico);
					
			rd = request.getRequestDispatcher("/MenuMedico.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("Nuevo") != null) {
			
			rd = request.getRequestDispatcher("/AgregarMedico.jsp");
			rd.forward(request, response);
		}
		
			
		
		
		
	}

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
		
		Usuario usu = new Usuario();
		UsuarioNegocio usNeg = new UsuarioNegocioImpl();
		
		LocalidadNegocio loNeg = new LocalidadNegocioImpl();
		
		ArrayList<Localidad> listaLocalidad = (ArrayList<Localidad>) loNeg.readAll();
		request.setAttribute("listaLocalidad", listaLocalidad);
		
		//BtnFiltrar
		if(request.getParameter("btnFiltrar")!=null) {
			
			int id = Integer.parseInt(request.getParameter("espe").toString());	
			if(id != 0) {
			ArrayList<Medico> listaMedicoFiltro= (ArrayList<Medico>) meNeg.readAllFiltro(id);	
			request.setAttribute("listaMedico", listaMedicoFiltro);	
			}else {
				ArrayList<Medico> listaMedicoFiltro= (ArrayList<Medico>) meNeg.readAll();	
				request.setAttribute("listaMedico", listaMedicoFiltro);	
			}
			rd = request.getRequestDispatcher("/MenuMedico.jsp");
			rd.forward(request, response);
		}
				
		//Eliminar		
		if (request.getParameter("Eliminar") != null) {
			String dni = request.getParameter("medId");
			
			if(meNeg.delete(dni)==true) {
				request.setAttribute("exito", true);
				request.setAttribute("mensaje", "");
				ArrayList<Medico> listaMedico = (ArrayList<Medico>) meNeg.readAll();
				request.setAttribute("listaMedico", listaMedico);
				rd = request.getRequestDispatcher("/MenuMedico.jsp");
				rd.forward(request, response);
			}
			
		}		
		
		//BtnBuscar
		if(request.getParameter("btnBuscar")!=null) {
			
			String nombre= request.getParameter("txtBuscar").toString();		
			ArrayList<Medico> listaMedicoBuscar = (ArrayList<Medico>) meNeg.readAllBuscar(nombre);
			request.setAttribute("listaMedico", listaMedicoBuscar);		
			rd = request.getRequestDispatcher("/MenuMedico.jsp");
			rd.forward(request, response);
		}
		
		//btnNuevoMedico
		if(request.getParameter("btnNuevoMedico")!=null) {
			String pass1 = request.getParameter("txtPass");
			String pass2 = request.getParameter("txtPass2");
			request.setAttribute("exito", false);
			if( !(pass1.equals(pass2))) {
				request.setAttribute("mensaje", "Las contraseñas no coinciden");
				request.getRequestDispatcher("AgregarMedico.jsp").forward(request, response);

			}
			else {
				me.setDni(request.getParameter("txtDni"));
				me.setNombre(request.getParameter("txtNombre"));
				me.setApellido(request.getParameter("txtApellido"));
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date dateFormateado = new Date();
				try {
					dateFormateado = formato.parse(request.getParameter("txtFechaNac"));
				} catch (ParseException e) {
					e.printStackTrace();
				}  
				java.sql.Date date1 = new java.sql.Date(dateFormateado.getTime());
				me.setFechaNacimiento(date1);
				me.setSexo(request.getParameter("slcSexo").charAt(0));
				Nacionalidad nacionalidad = new Nacionalidad();
				nacionalidad.setIdNacionalidad(Integer.parseInt(request.getParameter("slcNacionalidad")));
				me.setnNacionalidad(nacionalidad);
				Localidad localidad = new Localidad();
				localidad.setIdLocalidad(Integer.parseInt(request.getParameter("slcLocalidad")));
				me.setlLocalidad(localidad);
				me.setDireccion(request.getParameter("txtDireccion"));
				me.setEmail(request.getParameter("txtEmail"));
				me.setTelefono1(request.getParameter("txtTelefono1"));
				me.setTelefono2(request.getParameter("txtTelefono2"));
				Especialidad espe = new Especialidad();
				String especialidad = request.getParameter("slcEspecialidad");
				espe.setIdEspecialidad(Integer.parseInt(request.getParameter("slcEspecialidad")));
				me.seteEspecialidad(espe);
				usu.setNombreUsuario(request.getParameter("txtUser"));
				usu.setClave(request.getParameter("txtPass"));
				usu.setTipo("Medico");
				me.setIdMedico(usu);
				me.setEstado(true);
				
				meNeg.insert(me);
				int idUsu = usNeg.ultimoUsuario();
				usu.setIdUsuario(idUsu);
				me.setIdMedico(usu);
				
				meNeg.insertMe(me);
				
				request.getSession().setAttribute("medico", me);
				request.getRequestDispatcher("AgregarHorario.jsp").forward(request, response);
			}
		}
	}

}
