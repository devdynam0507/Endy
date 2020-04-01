package server.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import server.model.annotation.Columm;
import server.model.annotation.Model;
import server.mysql.MySql;

public class ModelLoader {
    
    private static Map<Class<? extends AbstractModel>, ModelInfo> loadedModelsMap = new HashMap<>();
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE {table}(id INTEGER NOT NULL AUTO_INCREMENT, ";

    
    public static void load(Class<? extends AbstractModel> modelsClass) {
        try {
            Model model = modelsClass.getAnnotation(Model.class);
            Field[] fields = modelsClass.getDeclaredFields();
            
            List<Columm> columms = new ArrayList<>();
            String table = model.table_name();
            String dbName = model.db_name();
            
            boolean bExistsTable = MySql.isExistsTable(dbName, table);
            String query = !bExistsTable ? CREATE_TABLE_QUERY.replace("{table}", table) : null;

            for(Field field : fields) {
                if(field.isAnnotationPresent(Columm.class)) {
                    Columm columm = field.getAnnotation(Columm.class);
                    columms.add(columm);
                    
                    if(!bExistsTable){
                        query += columm.name() + " " + columm.type().name() + "(" + columm.length() + "), ";                        
                    }
                }
            }
            
            if(!bExistsTable) {
                query += "PRIMARY KEY (id))";
                
                MySql.update(query);
            }
            
            loadedModelsMap.put(modelsClass, new ModelInfo(model, columms));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Map<Columm, Object> load(AbstractModel model) {
        Map<Columm, Object> data = new HashMap<>();
        
        try {
            Class<? extends AbstractModel> clazz = model.getClass();
            Field[] fields = clazz.getDeclaredFields();
            
            for(Field field : fields) {
                if(field.isAnnotationPresent(Columm.class)) {
                    field.setAccessible(true);
                    
                    data.put(field.getAnnotation(Columm.class), field.get(model));
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return data;
    }
    
    public static ModelInfo getModelInfo(Class<? extends AbstractModel> modelsClass) { return loadedModelsMap.get(modelsClass); }
    
    /*
    TODO
    
    변경된 테이블 컬럼이 있다면 update
    삭제된 테이블이 있다면 update
    */
    
    public static class ModelInfo {
        
        private Model model;
        private Map<String, Columm> colummsMap = new HashMap<>();
        
        protected ModelInfo(Model model, List<Columm> columms) {
            this.model = model;
            
            for(Columm col : columms) {
                colummsMap.put(col.name(), col);
            }
        }
        
        public Model getModel() { return model; }
        public synchronized Columm getColumm(String colName) { return colummsMap.get(colName); }
        public synchronized Columm[] getColumms() { return colummsMap.values().stream().toArray(Columm[]::new); }
        public synchronized String[] getColummNames() { return colummsMap.keySet().stream().toArray(String[]::new); }
        
    }
    
}
