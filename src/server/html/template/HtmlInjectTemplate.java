package server.html.template;

import server.html.Html;
import server.response.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class HtmlInjectTemplate implements IHtmlInjectTemplate {
    
    public static final Pattern pattern = Pattern.compile("[\\{]([^}]*)[\\}]"); //태그 정규식
    
    private Html html;
    
    public HtmlInjectTemplate(Html html) {
        this.html = html;
    }
    
    private static String removeTemplateTag(String line) { return line.replaceAll("[\\{|\\}]", ""); }
    
    /**
        @param line Line must be a template tag.
    
        @return Tag Type[0] and Tag Element[1]
    */
    public static String[] parseTemplateTag(String line) { return removeTemplateTag(line).split(":"); }
    
    public static boolean isVariable(String type) { return type.equalsIgnoreCase("var"); }
    public static boolean isCondition(String type) { return type.equalsIgnoreCase("if"); }
    public static boolean isLoop(String type) { return type.equalsIgnoreCase("for"); }
    public static boolean isEndCondition(String type) { return type.equalsIgnoreCase("endif"); }
    public static boolean isEndLoop(String type) { return type.equalsIgnoreCase("endfor:"); }
    public static boolean isConditionElse(String type) { return type.equalsIgnoreCase("else"); }
    
}
