package com.sesac.sesacspring.API.controller;

import com.sesac.sesacspring.API.dto.UserDTO;
import com.sesac.sesacspring.API.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // 템플릿을 불러올 땐 컨트롤러 사용!
//@RestController // @Controller + @ResponseBody 같은 느낌쓰
public class MainController<value> {
    @GetMapping("/")
    public String getMain() {
        return "request";
    }

    // ===GET===
    // 매개변수를 넘겨받는 방법
    // 1. ? 뒤에 쿼리를 사용하는 방법 : /test?id=123 () -> @RequestParam
    // 2. url 뒤에 값을 담는 방법 : /test/123 () -> @PathVariable


    @GetMapping("/get/response1")
    // ?key=value
    // ?name=
    // @RequestParam 는 기본값으로 required=true
    public String getResponse1(
            @RequestParam(value = "name") String i,
            Model model) {
        // RequestParam 어노테이션
        // - ?name=112&id=11&age=abc ( o )
        // - ?id=11&hashtag=abc ( x )
        // - string query (? 뒤의 값) 에서 key("name")에 대한 value("112")를 변수("i")에 매핑
        // - required=true 기본값 -> 요청 url 에서 설정한 key 가 필수로 있어야 한다!
        model.addAttribute("name", i);
        return "response";
    }

    @GetMapping("/get/response2")
    // required = false 옵션을 주고 싶다면  @RequestParam(value = "", required = false) 로 쓰면 된다!
    // - query string 에서 특정 key 를 옵셔널하게 받아야 하는 경우 사용!
    // ex) 검색할 때! ( 검색어는 필수, 해시태그는 선택 )
    // @RequestParam(value = "search") String search, 이렇게 사용!
    // @RequestParam(value = "hashtag", required = false) String hashtag, 이렇게 사용!
    // ?search=검색어
    // ?search=검색어&hashtag=코딩
    // 즉, 검색 기능을 구현할 때 RequestParam 를 false 로 해서 사용한다!
    public String getResponse2(
            @RequestParam(value = "name", required = false) String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "response";
    }

    @GetMapping("/get/response3/{param1}/{param2}")
    // url 안에 넣을 때 @PathVariable
    public String getResponse3(
            @PathVariable String param1,
            @PathVariable(value = "param2") String age,
            Model model) {
        // PathVariable 어노테이션
        // - /test/{id} 형식의 URL 경로로 데이터를 넘겨줄 때 받는 방법
        // - 기본적으로 경로 변수의 값을 필수로 받아야 하기 때문 ( 값을 안보내면 404 에러 발생 ) -> 값을 안보냈을 때 처리하는 방법은 아래 response4 로 구현!
        model.addAttribute("name", param1);
        model.addAttribute("age", age);
        return "response";
    }

    // PathVariable 을 보낼 때 값을 선택적(값을 안보내도 될 때)으로 처리해야 한다면?
    @GetMapping({"/get/response4/{param1}", "/get/response4/{param1}/{param2}"})
    public String getResponse4(
            @PathVariable String param1,
            @PathVariable(required = false, value = "param2") String age,
            Model model) {
        // 중요 ! 옵셔널한 parameter 는 맨 뒤에 오도록 설정해야 한다!
        model.addAttribute("name", param1);
        model.addAttribute("age", age); // age 를 옵셔널하게 구현한 코드임!
        return "response";
    }

    @PostMapping("/post/response1")
    public String postResponse1(
            @RequestParam(value = "name") String a,
            @RequestParam(value = "age") String b,
            Model model) {
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return "response";
    }

    @PostMapping("/post/response2")
    public String postResponse2(
            @RequestParam(value = "name", required = false) String a,
            @RequestParam(value = "age", required = false) String b,
            Model model) {
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return "response";
    }

    // @ResponseBody
    // 내가 요청을 보냈을 때 응답을 JSON 으로 리턴한다. ( 직렬화 )
    // express res.sen 와 유사
    // 응답 결과를 클라이언트에게 보내줄 때 사용한다. 즉, return "" 안의 텍스트 그대로 클라이언트에 보여짐!
    @PostMapping("/post/response3")
    @ResponseBody
    public String postResponse3(
            @RequestParam(value = "name", required = false) String a,
            @RequestParam(value = "age", required = false) String b,
            Model model) {
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return a + " - " + b;
    }

    ///////// 실습1. API-GET /////////
    @GetMapping("/introduce/{name}")
    @ResponseBody
    public String introduce(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "내 이름은 " + name;
    }

    @PostMapping("/post/prac")
    public String introduce1(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "gender") String gender,
            @RequestParam(value = "year") String year,
            @RequestParam(value = "month") String month,
            @RequestParam(value = "day") String day,
            @RequestParam(value = "hobby") String hobby,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("gender", gender);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("hobby", hobby);
        return "pracresult";
    }

    @GetMapping("/dto/response1")
    @ResponseBody
    public String dtoResponse1(UserDTO userDTO) {
        // DTO : getter setter 가 있는 객체
        // Get 방식에서 DTO 객체로 담아서 값이 받아진다!
        // @ModelAttribute : HTML 폼 데이터를 컨트롤러로 전달할 때 객체에 매핑
        // 매핑 : setter 함수 실행을 한다!
        // ?name=홍길동&age=10 -> setName("홍길동") setAge("10")
        return userDTO.getName() + " " + userDTO.getAge();
    }


    // @RequestBody : 요청의 본문에 있는 데이터(body)를 받는 친구
    @GetMapping("/dto/response11")
    @ResponseBody
    public String dtoResponse11(@RequestBody UserDTO userDTO) {
        return userDTO.getName() + " " + userDTO.getAge();}

    // 일반 폼 전송 시 : www-x-form-urlencoded 와 같은 url 형식으로 발송됨. => 쿼리 매개변수
    // 즉, 일반 폼 전송으로 데이터를 전송하면 RequestBody 값이 없다. 왜냐, 일반 폼 전송은 url 형식이라 본문에 값이 안담김!
    // RequestBody 는 본문에 있는 값을 가져온다 즉,  json, xml 일 때만 실행이 가능하다!
    // application/json

    // 일반 폼 전송 - DTO (getter, setter 모두 있는 친구다)
    // 1) 어노테이션 없이 DTO 로 받을 경우 -> o
    // 2) @ModelAttribute DTO 받을 경우 -> o
    // 3) @RequestBody DTO 받을 경우 -> 오류 발생

    // 일반 폼 전송은 www-x-form-urlencoded 형식이기 때문에
    // get 이든 post 든 요청의 본문에 데이터가 들어가는 게 아닌 폼 데이터 형대로 url 로 데이터가 전송됨. --> 즉, 일반 폼 전송은 RequestBody 사용 불가

    // 일반 폼전송 - VO
    @GetMapping("/vo/response1")
    @ResponseBody
    public String voResponse1(UserVO userVO){
        return userVO.getName() + " " + userVO.getAge();
    } // => 출력값 null (매핑은 setter를 실행시키는데  UserVO에 setter 가 없기 때문에 null이 나옴)

    @PostMapping("/vo/response2")
    @ResponseBody
    public String voResponse2(UserVO userVO){
        return userVO.getName() + " " + userVO.getAge();
    } // => 출력값 null (매핑은 setter를 실행시키는데  UserVO에 setter 가 없기 때문에 null이 나옴)


    @PostMapping("/vo/response3")
    @ResponseBody
    public String voResponse3(@RequestBody UserVO userVO){
        return userVO.getName() + " " + userVO.getAge();
    } // => 오류 발생

    ///////// axion 를 이용한 데이터 처리
    @GetMapping("/axios/response1")
    @ResponseBody
    public String axiosResponse1(@RequestParam String name, @RequestParam String age) {
        return name + " " + age;
    } // 1. Axios 와 get , @RequestParam 을 사용했을 때 -> 데이터 정상 전송됨!

    @GetMapping("/axios/response2")
    @ResponseBody
    public String axiosResponse2(UserDTO userDTO) {
        // @ModelAttribute 사용중임!
        // axios = application.json
        return userDTO.getName() + " " + userDTO.getAge();
    } // 2. Axios 와 get, @ModelAttribute 를 사용했을 때 -> 데이터 정상 전송됨!

//    ?key=value
//    일반폼전송은 ( get, post 상관없이 url )
//    @RequestParam

    // 객체로 받는 방법
    // 1. @ModelAttribute : url 로 들어온 값을 처리하는 친구 -> 객체에 setter 함수를 실행해주는 친구
    //  1) 넘어온 key 를 @ModelAttribute 뒤의 객체에서 필드가 존재하는 지 확인
    //  -> UserDTO 에 private String name;
    //  2) 필드가 존재한다면 그 필드에 해당하는 set 함수를 실행 ( setName, setAge 함수 )
    //  -> UserDTO.setName("홍길동")

    // 2. @RequestBody : 클라이언트의 요청 중 본문(body)에 들어있는 데이터를 처리
    //  - setter 함수 실행 x 필드 자체에 값을 넣어준다!
    // @RequestBody 는 각각의 필드(변수)에 직접적으로 값을 주입 ( 필드에 내장된 set 함수를 실행)
    //  -> 일반 폼 전송 : url 에 데이터 표시 o, body 에 데이터 표시 x
    //

    @PostMapping("/axios/response3")
    @ResponseBody
    // url 이었는데, axios post 는 url 에 데이터가 x!
    // url 에 아무것도 없는데 name, age required = true 기 때문에 필수값이라 에러가 발생!
    public String axiosRes3(@RequestParam String name, @RequestParam String age){
        return "이름: " + name + ", 나이: "+ age;
    }

    @PostMapping("/axios/response4")
    @ResponseBody
    public String axiosRes4(UserDTO userDTO){
        return "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
    }
    // axios + post 데이터 -> @ResponseBody o (null 로 나옴!)
    //@ ModelAttribute 를 이용해 데이터를 보냈을 때 값이 null
    // axios 를 보내면 url 로 데이터를 보내는 게 아니라 본문으로 데이터를 보내게 된다
    // 즉, @ModelAttribute 가 값을 볼 수가 없음!!

    @PostMapping("/axios/response5")
    @ResponseBody
    public String axiosRes5(@RequestBody UserDTO userDTO){
        return "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
    } // axios + post 데이터 -> @ResponseBody o!

    // ========== VO 이용 with. axios ==========
    // get 은 데이터를 url에 담는다!
    @GetMapping("/axios/vo/response1")
    @ResponseBody
    public String axiosVoRes1(@RequestParam String name, @RequestParam String age) {
        return "이름: " + name + ", 나이: " + age;
    }

    @GetMapping("/axios/vo/response2")
    @ResponseBody
    public String axiosVoRes2(UserVO userVO) {
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }

    @PostMapping("/axios/vo/response3")
    @ResponseBody
    public String axiosVoRes3(@RequestParam String name, @RequestParam String age) {
        return "이름: " + name + ", 나이: " + age;
    }

    @PostMapping("/axios/vo/response4")
    @ResponseBody
    public String axiosVoRes4(UserVO userVO){
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }

    @PostMapping("/axios/vo/response5")
    @ResponseBody
    public String axiosVoRes5(@RequestBody UserVO userVO){
        // axios post 로 데이터를 보내면 요청의 본문(body)에 데이터가 들어간다.
        // @RequestBody 는 요청의 본문에 있는 데이터를 읽을 수 있다.
        // UserVO 클래스는 setter 메소드가 현재 없는 상태!
        // @RequestBody 는 데이터를 각각의 필드(변수)에 직접적으로 값을 주입한다!
        // @RequestBody 는 UserVO UserDTO 상관없이 setter 메소드의 유무와 관계없이 변수에 값을 넣을 수 있다.
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }

    // 1. 일반 폼 전송에서 데이터를 받을 수 있는 메소드들!
    // - @RequestParam : GET, POST 메소드
    // - @PathVariable : GET 메소드

    // 2. DTO 이용해서 일반 폼 전송을 보냈을 때!
    // - GET : o
    // - POST + ModelAttribute? : o -> 데이터가 url 에 담겨있기 때문에 ModelAttribute 에서 데이터를 가져올 수 있다.
    // - POST + RequestBody : x

    // 3. VO 이용 - 일반 폼 전송
    // - GET : null
    // - POST + ModelAttribute : null
    // - POST + RequestBody : x

    // 4. AXIOS - DTO
    // - GET RequestParam : o
    // - GET ModelAttribute : o
    // - GET RequestBody : x
    // - POST RequestParam : x
    // - POST ModelAttribute : null
    // - POST RequestBody : o

    // 5. AXIOS VO
    // - GET RequestParam : o
    // - GET ModelAttribute : NULL
    // - GET RequestBody : x
    // - POST RequestParam : x
    // - POST ModelAttribute : null
    // - POST RequestBody : o


}

