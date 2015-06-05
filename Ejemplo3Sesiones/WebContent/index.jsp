<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import='modelo.ConexionBDMysql,
	java.sql.ResultSet,
	java.sql.SQLException,
	java.sql.Connection,
	javax.servlet.http.HttpServletResponse'%>
<% ConexionBDMysql conSesionBD=new ConexionBDMysql(); %>    
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<header>
	<div id="header">
		Conexion  para base de datos
	</div>
</header>
<section>	
<div id="section">
	<form action="index.jsp" method = "post">
		<table style="width:80%;display: block;">
		<tr>
		<td><label for="txtIP">
			IP:
		</label></td>
		<td><input type = "text" name = "txtIP" value = "localhost" required><br/>
		</td></tr> 
				
		<tr>
		<td><label for="txtPort">
			Puerto:
		</label></td>
		<td><input type = "text" name = "txtPort" value = "3306" required><br/>
		</td>
		</tr> 
				
		<tr>
		<td><label for="txtBase">
			Base (tabla a usar):
		</label></td>
		<td>
		<input type = "text" name = "txtBase" value = "usuarios_web" required><br/>
		</td>
		<!--label for="txtURL">
			URL
		</label>
		<input type = "url" name = "txtURL" value = "jdbc:mysql://localhost:3306/usuarios" required>
		<br/-->
		</tr> 
				
		<tr>
		<td>
		<label for="txtUsuario">
			Usuario
		</label></td>
		<td>
		<input type = "text" name = "txtUsuario" value = "root" required><br/>
		</td></tr> 
				
		<tr>
		<td>
		<label for="txtPassword">
			Contraseña
		</label></td>
		<td>
		<input type = "password" name = "txtPassword" value = "uzu-rendan" ><br/>
		</td>
		</tr>
		</table>
		<button onCLick = "crearConexion()">Acceder</button>
	</form>
	<%
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
				+ "<a href='index.jsp'>Regresar</a>");
		}	
	%>
	</div>
	</section>
	<div id="footer">ESCOM, 2015</div>
	</body>
</html>