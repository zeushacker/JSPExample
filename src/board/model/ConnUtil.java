package board.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnUtil {

         private static DataSource ds;	

         static {
		
		try {
			Context initContext = new InitialContext();
		    ds =(DataSource)initContext.lookup(
		    		 "java:/comp/env/jdbc/myoracle");
		
		}catch(NamingException e) { }
		
	}
         
     public static Connection getConnection() throws SQLException {
    	 return ds.getConnection();
     }
	
}
