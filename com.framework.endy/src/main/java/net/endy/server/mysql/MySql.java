package net.endy.server.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import net.endy.server.model.AbstractModel;
import net.endy.server.model.ModelLoader;
import net.endy.server.model.ModelLoader.ModelInfo;
import net.endy.server.model.annotation.Columm;
import net.endy.server.model.annotation.Model;

public class MySql {
    
    /*
        리팩토링 필요
    */
    
    private static final String URL_FORMAT = "jdbc:mysql://%host%:%port%/%db_name%";
    
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
    
    public static Connection getConnector(final String host, final String port, String dbName, String user, String pw) {
        Connection con = null;
        
        try {
            String url = URL_FORMAT;

            url = url.replace("%host%", host).replace("%port%", port).replace("%db_name%", dbName);
            System.out.println(url);
                    
            con = DriverManager.getConnection(url, user, pw);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
    
    public static boolean isExistsTable(String dbName, String tableName) {
        boolean bExists = false;
        
        try {
            DatabaseMetaData dbm = getConnector("localhost", "3306", dbName, "root", "dy050700").getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName, null);

            bExists = tables.next();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return bExists;
    }
    
    public static ResultSet query(String query) {
        ResultSet resultSet = null;
        
        try {
            Connection con = getConnector("localhost","3306", "dev", "root", "dy050700");
            PreparedStatement statement = con.prepareStatement(query);
            
            resultSet = statement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return resultSet;
    }
    
    public static void update(String query) {
        try {
            Connection con = getConnector("localhost","3306", "dev", "root", "dy050700");
            Statement statement = con.createStatement();
            
            statement.execute(query);
        } catch(SQLException e) {
            e.printStackTrace();
        }      
    }
    
    public static String makeInsertQuery(String tableName, String... colummNames) {
        String columms = "(";
        String values = "VALUES(";
        int len = colummNames.length;
        
        for(int i = 0; i < len; i++) {
            columms += colummNames[i] + " ";
            values += "? ";
            
            if(i < len - 1) {
                columms += ",";
                values += ",";
            }
        }
        
        columms += ") ";
        values += ")";
        
        return "INSERT INTO " + tableName + columms + values;
    }
    
    public static void insert(ModelInfo modelInfo, AbstractModel modelInstance) {
        Model model = modelInfo.getModel();
        Connection con = MySql.getConnector("localhost", "3306", model.db_name(), "root", "dy050700");
        String sql = MySql.makeInsertQuery(model.table_name(), modelInfo.getColummNames());
        Map<Columm, Object> data = ModelLoader.load(modelInstance);
        Columm[] columms = modelInfo.getColumms();
        PreparedStatement state = null;
        
        try {
            state = con.prepareStatement(sql);
            for(int i = 1; i <= columms.length; i++) {
                Columm col = columms[i - 1];
                
                col.type().convertor().setPrepareStatementElement(i, data.get(col), state);
            }
            
            state.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ResultSet select(ModelInfo modelInfo, String query) {
        Model model = modelInfo.getModel();
        Connection con = MySql.getConnector("localhost", "3306", model.db_name(), "root", "dy050700");       
        String sql = query.replace("{table}", model.table_name());
        ResultSet rs = null;
        Statement statement = null;
       
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
}
