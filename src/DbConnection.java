import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnection {
	private static final String DB_NAME = "ims";
	private static final String DB_USER = "root";
	private static final String DB_USER_PASSWORD = "Sanda@12";
	private static final String URL  = "jdbc:mysql://localhost:3306/" + DB_NAME;
	
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL,DB_USER,DB_USER_PASSWORD);
	}
}
