package framework.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AbstractProjectFileManager {
    
    private static final String PROJECT_ROOT_PATH = System.getProperty("user.dir");
    
    public static File getRootFolder() { return new File(PROJECT_ROOT_PATH); }
    
    public static void makeSourceDirectories() throws Exception {
        File root = getRootFolder();
        
        File htmlDirs = new File(root, "/html");
        File jsDirs = new File(root, "/js");
        File cssDirs = new File(root, "/css");
        File moduleDirs = new File(root, "/modules");
        File staticFiles = new File(root, "staticfiles");
        File dynamicFiles = new File(root, "dynamicfiles");
        
        File serverPropertiesFile = new File(root, "server.properties");
        
        htmlDirs.mkdirs();
        jsDirs.mkdirs();
        cssDirs.mkdirs();
        staticFiles.mkdirs();
        dynamicFiles.mkdirs();
        moduleDirs.mkdirs();
        serverPropertiesFile.createNewFile();
    }
    
    private static void copy(File target, File dest) {
        try {
            
            FileInputStream fis = new FileInputStream(target); //읽을파일
            FileOutputStream fos = new FileOutputStream(dest); //복사할파일
            
            int fileByte = 0; 

            while((fileByte = fis.read()) != -1) {
                fos.write(fileByte);
            }

            fis.close();
            fos.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }      
    }
    
}
