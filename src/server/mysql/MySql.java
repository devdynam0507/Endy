package server.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {
    
    public static boolean load(String database, String id, String passwd) {
        Connection conn = null;
        boolean success = true;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/" + database;

            conn = DriverManager.getConnection(url, id, passwd);
            System.out.println("Success mysql connect!");

        }
        catch(ClassNotFoundException e){
            System.out.println("Failed load driver");
            success = false;
        }
        catch(SQLException e){
            System.out.println("SQL Error: " + e);
            success = false;
        }
        finally{
            try{
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                success = false;
            }
        }
        
        return success;
    }
    
}
