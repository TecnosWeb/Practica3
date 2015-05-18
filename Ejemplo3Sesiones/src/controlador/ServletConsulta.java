package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ConexionBDMysql;

/**
 * Servlet implementation class servletconsulta
 */
@WebServlet("/consulta")
public class ServletConsulta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConexionBDMysql conConsulta ;   
	Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConsulta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		HttpSession s = request.getSession();
		conConsulta = (ConexionBDMysql) s.getAttribute("conexionMysql");
		String query=request.getParameter("querySQL");
		String result= new String();
		ResultSet r = null;
		try {
			r = conConsulta.select(query);
			result=conConsulta.stringRes(r);
		} catch (SQLException e) {
			result= e.getMessage();
			System.out.println("Error en la sentencia SELECT");
			e.printStackTrace();
		
		}
		
		response.setContentType("text/html");		
		response.getWriter().println("<html>"
				+ "<head>"
				+ "<title>Resultado consulta"
				+ "</title>"
				+ "<link rel='stylesheet' type='text/css' href='styles.css'>"
				+ "</head>"
				+ "<body>"
				+"<div id='header'>Consulta creada dinánicamente</div>"
				+ "<section>"+result
				+"<br/><a href='consultas.html'>Regresar</a>"
				+"</section>"
				+"<div id='footer'>ESCOM, 2015</div>"
				+ "</body>"
				+ "</html>");
		}

	
	/**
	 * 	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

}
