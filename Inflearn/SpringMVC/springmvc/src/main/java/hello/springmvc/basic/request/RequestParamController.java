package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}; age={}", username, age);

        response.getWriter().write("ok");
    }

    /**
     * @RequestParam 사용
     * - 파라미터 이름으로 바인딩
     * @RequestParam("username") String memberName -> requset.getParameter("username")과 같은 역할
     *
     * @ResponseBody 추가(RestController 와 같은 역할을 해줌)
     * - View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
     */
    @ResponseBody
    @RequestMapping("/mapping-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }

    /**
     * @RequestParam 사용
     * HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * @RequestParam 사용
     * String, int, Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * @RequestParam 애노테이션을 생략하면 스프링 MVC는 내부에서 required=false를 적용한다.
     */


    /** 파라미터 필수값 여부 체크
     * @RequestParam.required
     * -> 파라미터 필수 여부, 기본값은 필수(true)이다.
     * /request-param-required -> username이 없으므로 예외(400 에러 발생)
     *
     * 주의! 파라미터 이름만 사용할 경우
     * /request-param-required?username= -> 빈문자로 통과
     *
     * 주의! 기본형(primitive)에 null 입력
     * /request-param-required
     * int age -> null을 int에 입력하는 것은 불가능(500 error), 따라서 Integer 변경해야 함(또는 다음에 나오는 defaultValue 사용)
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * @RequestParam
     * - defaultValue 사용  *
     * 참고: defaultValue는 빈 문자의 경우에도 적용  * /request-param-default?username=
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * ?username=user1&username=user2...
     * -> MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    /**
     * @ModelAttribute 사용
     * HelloData 객체가 생성되고, 요청 파라미터의 값도 모두 들어가 있다.
     * 1. HelloData 객체 생성 -> 2.요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. -> 3. 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩)한다.
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); //toString으로 log를 자동생성 해준다.

        return "ok";
    }

    /**
     * @ModelAttribute 생략 가능
     * String, int 같은 단순 타입 = @RequestParam
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribute
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


    /**
     * 스프링은 String, int, Integer 같은 단순타입 = @RequestParam을 사용하고,
     * 나머지는 @ModelAttribute (argument resolver 로 지정해둔 타입 외)를 사용한다.
     */

}

