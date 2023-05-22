package hello.servlet11.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet
public class RequestHeaderServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(requset);
    }
        //start line 정보
        private void printStartLine (HttpServletRequest requset) {
            System.out.println("--- REQUEST-LINE - start ---");
            System.out.println("request.getMethod() = " + requset.getMethod()); //GET
            System.out.println("request.getProtocol() = " + requset.getProtocol()); //HTTP/1.1
            System.out.println("request.getScheme() = " + requset.getScheme()); //http
            // http://localhost:8080/request-header
            System.out.println("request.getRequestURL() = " + requset.getRequestURL());
            // /request-header
            System.out.println("request.getRequestURI() = " + requset.getRequestURI());//username=hi
            System.out.println("request.getQueryString() = " + requset.getQueryString());
            System.out.println("request.isSecure() = " + requset.isSecure()); //https 사용 유무
            System.out.println("--- REQUEST-LINE - end ---");     System.out.println();
        }
    }

