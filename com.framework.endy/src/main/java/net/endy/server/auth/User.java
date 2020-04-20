package net.endy.server.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.endy.server.model.AbstractModel;
import net.endy.server.model.ModelLoader;
import net.endy.server.model.ModelLoader.ModelInfo;
import net.endy.server.model.annotation.*;
import net.endy.server.model.type.ModelDataType;
import net.endy.server.mysql.MySql;
import net.endy.server.token.JwtTokenMapper;
import net.endy.server.token.JwtTokenService;

@Model(db_name = "dev", table_name = "auth")
public class User extends AbstractModel implements JwtTokenMapper {
    
    private static final String UPDATE_QUERY = "UPDATE {table} SET ";

    public int id;
    public boolean bAccountExists = false;
    private String accessToken;
    
    @Columm(name = "username", type = ModelDataType.VARCHAR, length = 50) 
    public String userName;
    
    @Columm(name = "email", type = ModelDataType.VARCHAR, length = 100)
    public String email;
    
    @Columm(name = "password", type = ModelDataType.VARCHAR, length = 64)
    public String password;
    
    @Columm(name = "account_serial", type = ModelDataType.VARCHAR, length = 100)
    public String accountSerial;
    
    @Columm(name = "refresh_token", type = ModelDataType.VARCHAR, length = 512)
    public String refreshToken;
    
    public static synchronized User getUser(String email) {
        ModelInfo model  = ModelLoader.getModelInfo(User.class);
        ResultSet result = MySql.select(model, "SELECT username, email, account_serial FROM {table}");
        User user        = new User();

        try {
            if(result.isBeforeFirst()) {                
                user.id             = result.getInt("id");
                user.userName       = result.getString("username");
                user.email          = result.getString("email");
                user.password       = result.getString("password");
                user.accountSerial  = result.getString("account_serial");
                user.refreshToken   = result.getString("refresh_token");
                user.bAccountExists = true;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
 
    /**
        @param userName user nickname
        @param email user email
        @param password encrypted password (sha-256)
        @param accountSerial certified user passbook id 
    */
    public static synchronized boolean register(String userName, String email, String password, String accountSerial) throws RegisteredUserException {
        User user = getUser(email);
        boolean bRegisterSuccess = false;
        
        if(!user.bAccountExists) {
            user.userName      = userName;
            user.email         = email;
            user.password      = password;
            user.accountSerial = accountSerial;
            user.refreshToken  = JwtTokenService.create(user, 120);
            user.accessToken   = JwtTokenService.create(user, 30);
            
            MySql.insert(ModelLoader.getModelInfo(User.class), user);
        } else {
            throw new RegisteredUserException(email);
        }
        
        return bRegisterSuccess;
    }
    
    /**
        @param email user email
        @param password encrypted password (sha-256)
    */
    public static synchronized User login(String email, String password) {
        User user = getUser(email);
        
        if(user.bAccountExists && user.password.equals(password)) {
            user.accessToken  = JwtTokenService.create(user, 30);
            
            if(!JwtTokenService.isTokenValid(user.refreshToken)) {
                user.refreshToken = JwtTokenService.create(user, 30 * 60 * 60);
                
                MySql.query(UPDATE_QUERY + "refresh_token=" + user.refreshToken + " WHERE email=" + user.email);
            }
        }
        
        return user;
    }
    
    @Override
    public Map<String, Object> makeClaims() {
        Map<String, Object> claims = new HashMap<>();
        
        claims.put("email", email);
        claims.put("username", userName);
        
        return claims;
    }
    
}
