# Endy Web Framework
간단한 서버 프로그래밍을 지원하는 Endy Web Framework 입니다.  
기본적인 Http 통신 지식만 있으면 누구나 간단하게 서버 프로그래밍을 할 수 있습니다.

# Example JavaCode
Endy Framework의 서버 처리는 어노테이션 기반 사용자 지정 메소드로 처리합니다.
```java
package framework.example;

import server.http.HttpProtocol;
import server.page.AbstractPage;
import server.page.annotation.PageUrl;
import server.page.annotation.RequestHandler;
import server.request.HttpRequestPacket;
import server.response.HttpResponse.ResponseContext;

@PageUrl(location = "/workspace/Endy", html = "index.html") //페이지 경로, 작성한 html 파일 
public class Index extends AbstractPage {
    
    /**
        Response example
    */
    @Override
    public ResponseContext response() {
        context.setContext("db", "3306").setContext("coin", "1000");  
        
        return context;
    }
    
    /**
        Get request example
    */
    @RequestHandler(protocol = HttpProtocol.GET)
    public void get(HttpRequestPacket packet) {
        System.out.println("Call get method");
    }
    
    /**
        Post request example
    */
    @RequestHandler(protocol = HttpProtocol.POST)
    public void post(HttpRequestPacket packet) {
        System.out.println("Call post method");
    }
    
}
```

# Model example
Endy Framework는 간편한 DB 테이블 설계를 지원합니다.

```java
package framework.example;

import server.model.annotation.Columm;
import server.model.annotation.Model;

@Model(db_name = "dev", table_name = "test")
public class ModelExample {
 
    @Columm(name = "name", type = ModelDataType.VARCHAR, length = 100) 
    public String name;
    
    @Columm(name = "age", type = ModelDataType.INTEGER, length = 0) 
    public int age;
    
    @Columm(name = "school", type = ModelDataType.VARCHAR, length = 100) 
    public String school;
    
}
```

# Handle model example

```java
/**
    Select query example
*/
@RequestHandler(protocol = HttpProtocol.GET)
public void get(HttpRequestPacket packet) {    
    ModelInfo info = ModelLoader.getModelInfo(ModelExample.class);
    ResultSet rs = MySql.select(info, "SELECT * FROM {table}");

    try{ 
        while(rs.next()) {
            System.out.println("name: " + rs.getString("name"));
            System.out.println("age: " + rs.getInt("age"));
            System.out.println("school: " + rs.getString("school"));
        }
    } catch(SQLException e) {
        e.printStackTrace();
    }
}
```
```java
/**
    Insert query example
*/
@RequestHandler(protocol = HttpProtocol.GET)
public void get(HttpRequestPacket packet) {    
    ModelExample ex = new ModelExample();
    ex.age = 21;
    ex.name = "남대영";
    ex.school = "None";

    MySql.insert(ex.getModelInfo(ModelExample.class), ex);
}
```
# HTML Inject template  
HTML에 서버 ResponseContext로 넘겨준 값을 inject template로 치환하여 사용할 수 있습니다.  
또한 if와 for문을 지원하여 짧은 HTML 코드로 원하는 결과값을 출력할 수 있습니다.  

```html
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
```
