package net.endy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ServerProperties extends File {
    
    private static final long serialVersionUID = 802030127813901L;
    private static ServerProperties instance;
    
    private Map<String, String> properties;
    
    public static synchronized ServerProperties getProperties() {
        if(instance == null) {
            instance = new ServerProperties("server.properties", AbstractProjectFileManager.getRootFolder());
        }
        
        return instance;
    }
    
    private ServerProperties(String fileName, File root) {
        super(root, fileName);
        
        properties = new HashMap<>();
        
        initialize();
     }
    
    public void initialize() {
        try {
            if(!exists()) {
                createNewFile();
                write();
            } else {
                load();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void load() throws Exception {
        FileInputStream stream = new FileInputStream(this);
        
        byte[] buffer = new byte[512];
        int buf = 0;
        while((buf = stream.read(buffer)) != -1) { 
            System.out.write(buffer, 0, buf);
            
            String[] splitedValues = new String(buffer).split(System.getProperty("line.separator"));
            for(String element : splitedValues) {
                String[] splitedElement = element.trim().split("=");
                
                if(splitedElement.length > 1) {
                     properties.put(splitedElement[0], splitedElement[1]);   
                }                
            }
        }
        
        stream.close();
    }
    
    private void write() throws Exception {
        FileOutputStream stream = new FileOutputStream(this);
        String[] elements = new String[] {
            "mysql_id=root\n",
            "mysql_password=root\n",
            "port=8080\n",
        };
        
        for(String element : elements) {
            stream.write(element.getBytes(Charset.forName("UTF-8")));
            
            String[] datas = element.replaceAll(System.getProperty("line.separator"), "").split("=");
            properties.put(datas[0], datas[1]);
        }
        
        stream.close();
    }
    
    @Override
    public String toString() { return properties.toString(); }
    public String getMysqlId() { return properties.get("mysql_id"); }
    public String getMysqlPassword() { return properties.get("mysql_password"); }
    public short getPort() { return Short.parseShort(properties.get("port")); }
    
}
