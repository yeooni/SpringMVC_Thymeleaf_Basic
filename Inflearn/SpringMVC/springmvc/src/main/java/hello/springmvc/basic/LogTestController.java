package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass()); -> @Slf4j로 대체됨

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name); // 운영 서버에 로그를 남기면 안되기 때문에 사용을 지양해야 한다.

        log.trace(" trace log={}", name);
        log.debug(" debug log={}", name);
        log.info(" info log ={}", name);
        log.warn(" warn log={}", name);
        log.error(" error log={}", name);

        return "ok";
    }
}
