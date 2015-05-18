package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ConexionBDMysql {
	
	private Connection con=null;

	public ConexionBDMysql() {
		// TODO Auto-generated constructor stub
	}
	
	public String getResultadoConsulta(String q)
	{
		String cadenaR = "sin resultados";
		try 
		{
			Statement st = this.con.createStatement();
			ResultSet resultados = st.executeQuery(q);
			resultados.next();
			cadenaR = (String) resultados.getObject(1);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cadenaR;
	}
	 
	
	public boolean InicializacionDB(String URL, String Usuario, String Password)
	{
		try
		{
			if (con!=null)
				System.out.println("Ya exixte una conexion");
			else {	
				Class.forName("com.mysql.jdbc.Driver");
				this.con = DriverManager.getConnection(URL, Usuario, Password);
				System.out.println("Conexion Establecida");
				}
			return true;
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	/*	
	public boolean Escritura(String URL, String Usuario, String Password)
	{
		boolean Prueba;
		Connection con = InicializacionDB(URL, Usuario, Password);
		if(con == null)
		{
			return Prueba = false;
		}
		try 
		{
			Statement consulta = (Statement) con.createStatement();
			consulta.executeUpdate("insert into archivo(nombre, appaterno, apmaterno, correo, contrasenhia) values('"+Nombre+"', '"+ApPaterno+"', '"+ApMaterno+"', '"+Correo+"', '"+Contrasenhia+"')");
			Prueba = true;
			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			Prueba = false;
		}
		
		return Prueba;
	}
	
	public int Lectura(String URL, String Usuario, String Password)
	{
		Connection con = InicializacionDB(URL, Usuario, Password);
		int R = 0;

		
		try 
		{
			Statement consulta = (Statement) con.createStatement();
			ResultSet resultados;

			switch(Modo)
			{
				case 1:
					resultados = consulta.executeQuery("select correo from archivo where correo = '"+Usuario+"'");
					if(resultados.next() == true)
					{
						R = 1;
					}
					break;
				case 2:
					resultados = consulta.executeQuery("select correo, contrasenhia from archivo where correo = '"+Usuario+"' && contrasenhia = '"+Password+"'");
					if(resultados.next() == true)
					{
						R = 1;
					}
					break;
			}
			
			
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return R;
	}
*/
//	public ResultSet select(String query){
//		
//		try{
//		Statement consulta = con.createStatement();
//		System.out.println("consulta creada");
//		ResultSet resultados = consulta.executeQuery(query);
//		return resultados;
//	} catch (SQLException e) {
//		System.out.println("Error en la sentencia SELECT");
//		e.printStackTrace();
//	
//	}
//		System.out.println("no se pudo ejecutar");
//		return null;
//	}
	
public ResultSet select(String query) throws SQLException{
		
		
		Statement consulta = con.createStatement();
		System.out.println("consulta creada");
		ResultSet resultados = consulta.executeQuery(query);
		return resultados;
		
	}
	public String stringRes(ResultSet Resultados){
		java.sql.ResultSetMetaData md;
		try {
			md = Resultados.getMetaData();
		String tabla = "<table id='tabla1'> \n";
		int i = 0, j =1;
		int m =md.getColumnCount();
		tabla+="<tr>";
		for(j = 1; j <= m; j++){
			tabla+="<th>"+md.getColumnName(j)+"</th>";
		}

		  tabla += "</tr> \n";

				while(  Resultados.next()) {
				  if(++i%2!=0)
					tabla += "<tr> \n";
				  else tabla+="<tr class='alt'>";
				  for (j = 1; j <= m; j++){
				    tabla += "<td>" + Resultados.getString( j ) + "</td> \n";
				  }
				  tabla += "</tr> \n";
				}
				tabla += "</table>";
			return tabla;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
