package net.endy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AbstractProjectFileManager {
    
    private static String PROJECT_ROOT_PATH;
    
    private static File htmlDirs, jsDirs, cssDirs, moduleDirs, staticFiles, dynamicFiles;
    private static File serverPropertiesFile;
    
    static {
        try {
            PROJECT_ROOT_PATH = new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getPath();
            File root = getRootFolder();
            
            htmlDirs = new File(root, "/html");
            jsDirs = new File(root, "/js");
            cssDirs = new File(root, "/css");
            moduleDirs = new File(root, "/modules");
            staticFiles = new File(root, "staticfiles");
            dynamicFiles = new File(root, "dynamicfiles");

            serverPropertiesFile = new File(root, "server.properties");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static File getRootFolder() { return new File(PROJECT_ROOT_PATH); }
    public static File getHtmlDirectory() { return htmlDirs; }
    public static File getJavascriptDirectory() { return jsDirs; }
    public static File getCssDirectory() { return cssDirs; }
    public static File getModuleDirectory() { return moduleDirs; }
    public static File getStaticFiles() { return staticFiles; }
    public static File getDynamicFiles() { return dynamicFiles; }
     
    public static void makeSourceDirectories() throws Exception {        
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
