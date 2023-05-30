package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    /**
     * @RequestHeader MultiValueMap<String, String> headerMap -> 모든 HTTP 헤더를 MultiValueMap 형식으로 조회한다.
     * @RequestHeader("host") String host -> 특정 HTTP 헤더를 조회한다.
     * @CookieValue(value = "myCookie", required = false) String cookie -> 특정 쿠키를 조회한다.
     */
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletRequest response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String aa,
                          @CookieValue(value = "myCookie", required = false) String cookie) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", aa);
        log.info("myCookie={}", cookie);

        return "ok";
    }


}
