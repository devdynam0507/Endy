package net.endy.register;

import net.endy.server.page.AbstractPage;

public class PageRegister extends Register<String, Class<? extends AbstractPage>> {
    
    private static PageRegister pageRegister;
    
    public static synchronized PageRegister getRegister() {
        if(pageRegister == null) {
            pageRegister = new PageRegister();
        }
        
        return pageRegister;
    }
        
}
