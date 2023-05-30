package hello.springmvc.basic.requsetmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * 요청매핑 - API 예시
     * 회원목록조회:      GET      /users
     * 회원등록:         POST     /users
     * 회원조회:         GET      /users/{userId}
     * 회원수정:         PATCH    /users/{userId}
     * 회원삭제:         DELETE   /users/{userId}
    */

    /**
     * GET /mapping/users
     */
    @GetMapping
    public String user() {
        return "get users";

    }

    /**
     * POST /mapping/users
     */
    @PostMapping
    public String addUser() {
        return "post user";
    }

    /**
     * GET /mapping/users/{userId}
     */
    @GetMapping("/{userID}")
    public String findUser(@PathVariable String userId){
        return "get userId=" + userId;
    }

    /**
     * PATCH /mapping/users/{userId}
     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId=" + userId;
    }

    /**
     * DELETE /mapping/users/{userId}
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId=" + userId;
    }

}
