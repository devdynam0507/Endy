package net.endy.server.page.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.endy.server.response.HttpResponse;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface PageUrl {
    
    String location() default "/";
    String html();
    HttpResponse.Type response_type() default HttpResponse.Type.Render;
    
}
