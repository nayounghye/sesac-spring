package com.sesac.sesacspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// @Controller : 해당 클래스가 Controller 의 역할을 하는 클래스라는 것을 Spring Controller 에게 알려준다.
public class HelloController {

    @GetMapping("/hi")
    // @GetMapping : URL 을 매핑시켜주는 역할.
    // 클라이언트가 "/hi" 라는 경로로 GET method 로 접근한다면 아래 메소드를 실행시킴.
    public String getHi(Model model) {
        // Model : Controller 안의 메서드가 파라미터로 받을 수 있는 객체 중 하나
        // Model 안에 정보를 담아서 view 로 전달
        // IoC : 개발자가 직접 model 을 생성하지 않는다!

        model.addAttribute("name","죠르디"); // res.render("hi, {name: '홍길동'}) -> X
//        model.addAttribute("key","죠르디");
        model.addAttribute("name2","<strong>스카피</strong>");

        String[] x = {"a", "b", "c", "d", "e"};
        model.addAttribute("item1", x);
        char[] alphabetArray = new char[26];
        char alphabet = 'A';

        for (int i = 0; i < 26; i++) {
            alphabetArray[i] = alphabet;
            alphabet++;
        }
        model.addAttribute("item2", alphabetArray);

        return "hi"; // "hi" 는 템플릿 파일의 이름을 의미, 텍스트가 이님!
        // node 로 치면 res.render("hi") 와 같음.
    }



}
