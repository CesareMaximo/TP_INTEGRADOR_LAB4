package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletLogout
 */
@WebServlet("/logout")
public class ServletLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion = request.getSession();
		if(request.getParameter("cerrarSesion") != null) {
			
			sesion.removeAttribute("username");
			sesion.removeAttribute("tipo");
			//sesion.invalidate();
			response.sendRedirect("Login.jsp");
			//rd = request.getRequestDispatcher("Login.jsp");
			//rd.forward(request, response);
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
