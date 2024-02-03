package com.sesac.sesacspring.API.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Prac1Controller {
    @GetMapping("/prac1")
    public String getPrac1(Model model) {
        model.addAttribute("age",15);
        model.addAttribute("age2",25);
        return "prac1";
    }

    @GetMapping("/people")
    public String getPrac1_2(Model model) {
        ArrayList<Prac1_2> people = new ArrayList<Prac1_2>();
        people.add(new Prac1_2("죠르디",999));
        people.add(new Prac1_2("스카피",205));
        people.add(new Prac1_2("앙몬드",21));
        people.add(new Prac1_2("판다주니어",47));

        model.addAttribute("people",people);

        Prac1_2 p = new Prac1_2("콥",10);
        return "prac1_2";
}
@Getter
@Setter
    public class Prac1_2 {
        private String name;
        private int age;

        public Prac1_2(String name, int age) {
            this.name = name;
            this.age = age;
        }
}}
