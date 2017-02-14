package connector;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DbUtils {
	
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void disableAutoCommit( Connection conn ) {
		try {
			conn.setAutoCommit(false);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void enableAutoCommit( Connection conn ) {
		try {
			conn.setAutoCommit(true);
		} 
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static Connection connect() {
		try {
			Class.forName(DbAccessConfig.DB_DRIVE_NAME);
		} 
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			return (Connection) DriverManager.getConnection( DbAccessConfig.DB_CONNECTION_URL,
                    DbAccessConfig.DB_CONNECTION_USERNAME,
                    DbAccessConfig.DB_CONNECTION_PWD );
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
			return null;	
		}
	}
	
}
