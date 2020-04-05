package server.html.template;

import server.html.Html;
import server.response.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlInjectTemplate implements IHtmlInjectTemplate {
    
    /*
        :: Non-finish lang ::
        var: { var:context_name }
        
        :: finishable lang ::
        if: { if:context_name?condition } { else: } { endif: }
        foreach: { for-each:array } { endfor: }
        
        :: example ::
        <h1> { var:title } </h1>
        
        <nav>
            { if:is_authenticated }
                <p>로그아웃</p> 
            { else: }
                <p>로그인</p> <p>회원가입</p>
            { endif: }
        </nav>
        
        <div> 
            <li>
            { for:array }
                { var:array-context-name }
            { endif: }
            </li>
        </div>
    */
    
    public static final Pattern pattern = Pattern.compile("[\\{]([^}]*)[\\}]");
    
    private Html html;
    
    public HtmlInjectTemplate(Html html) {
        this.html = html;
    }
    
    @Override
    public String compile(HttpResponse.ResponseContext context) {
        return null;
    }
    
    private static String removeTemplateTag(String line) {
        return line.replaceAll("[\\{|\\}]", "");
    }
    
    /**
        @param line Line must be a template tag.
    
        @return Tag Type[0] and Tag Element[1]
    */
    public static String[] parseTemplateTag(String line) {
        return removeTemplateTag(line).split(":");
    }
    
    public static boolean isVariable(String[] splitedTag) {        
        return splitedTag[0].equalsIgnoreCase("var");
    }
    
}
