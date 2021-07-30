package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
// name, urlPatterns 겹치면 안됨
public class HelloServlet extends HttpServlet {

    // ctrl + o => service
    // ~/hello 주소로 서블릿을 호출하면 service 실행
    // httpServletRequest 객체 : http 요청 메시지, http 응답 메시지를 편리하게 사용하도록 도와주는 객체
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 단축어 soutm
        System.out.println("HelloServlet.service");
        // 서블릿 http 요청이 오면 was(서블릿 컨테이너)가 request, response 객체를 만들어 서블릿에 전달
        // soutv
        System.out.println("request = " + request);
        // url -> 웹 브라우저가 http 요청 메시지를 만들어 서버에 전달
        System.out.println("response = " + response);
        // response 객체를 만들어서 서버에 전달
        // request = org.apache.catalina.connector.RequestFacade@49dc9f81
        // response = org.apache.catalina.connector.ResponseFacade@471b78ca
        // org.apache.cat~ => 톰캣 쪽 라이브러리

        String username = request.getParameter("username");
        // 쿼리 파라미터를 서블릿에서 편하게 읽도록 지원
        // ctrl + alt + v
        // queryParameter를 편하게 조회할 수 있음 uesrname = kim
        System.out.println("username = " + username);

        // 헤더 정보에 들어감
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // http 메시지 body에 들어감
        response.getWriter().write("hello " + username);

    }
}
