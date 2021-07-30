package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?uesrname=hello&age=20
 *
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Enumeration<String> parameterNames = request.getParameterNames();
        // 모든 요청 파라미터를 다 꺼낼 수 있다

        // System.out.println("RequestParamServlet.service");
        // request.getParameterNames();

        System.out.println("[전체 파라미터 조회] - start");

        // 요즘 방식
        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        // 한 이름의 파라미터를 복수개로 꺼낼 때 getParameterValues 사용
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames ) {
            System.out.println("username = " + name);
        }

        response.getWriter().write("ok");
    }

}
