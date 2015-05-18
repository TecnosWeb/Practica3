package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ConexionBDMysql;

/**
 * Servlet implementation class servletsesion
 */
@WebServlet("/sesionBD")
public class ServletSesionBD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConexionBDMysql conSesionBD=new ConexionBDMysql();
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSesionBD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String datoscon[] = new String[2];
		datoscon[0] = request.getParameter("txtUsuario");
		datoscon[1] = request.getParameter("txtPassword");
		String ip = request.getParameter("txtIP");
		String puerto = request.getParameter("txtPort");
		String base = request.getParameter("txtBase");
		String strCon="jdbc:mysql://"+ip+":"+puerto+"/"+base;
		if(conSesionBD.InicializacionDB(strCon,datoscon[0], datoscon[1]))
		{		
		HttpSession sesion = request.getSession();
		
		sesion.setAttribute("conexionMysql", conSesionBD);
		response.sendRedirect("consultas.html");
		}
		else{ 
		response.setContentType("text/html");		
		response.getWriter().println("<h1>Datos incorrectos</h1><br/>"
				+ "<a href='index.html'>Regresar</a>");
		}
	}

}
