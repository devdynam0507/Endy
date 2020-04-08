package server.html.template;

public class HtmlInjectTemplateCondition extends HtmlInjectTemplate {
    
    private int conditionStartLine, conditionFinishLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL;LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
    
    
    @Override
    public String compile(ResponseContext context, Html html, String[] templateTag, String tag, int line) {
        String element = context.getContext(templateTag[1]);
        html.replaceTag(line, tag, element);
        
        return null;LLLLLLLLLLLLLLL
    }
    
}
