package hello.servlet11.basic.request;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData {

    private String username;
    private int age;

}
