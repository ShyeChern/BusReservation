package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection doConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection conn=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/busreservationsystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","123456");
		return conn;
	}
	
	/*	for testing only
	
 	public static void main(String [] args) {
		try {
			System.out.println(DBConnection.doConnection());
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	*/
}
