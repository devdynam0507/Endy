package server.page;

public abstract class AbstractPage {
    
    private String html;
    private String htmlFileName;
    
    public AbstractPage(String htmlFileName) {
        this.htmlFileName = htmlFileName;
        //TODO html parse
    }
    
    
    
}
