package com.sesac.sesacspring.controller;
import com.sesac.sesacspring.vo.PracVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class PracFormController {
    @GetMapping("/form")
    public String getPracForm() {
        return "pracform";
    }

    @PostMapping("/post/form2")
    @ResponseBody
    public String introduce3(@ModelAttribute PracVO pracVO)
    {
        return "이름: "+ pracVO.getName() + " \n성별: "+ pracVO.getGender()+ " \n생년월일: "+ pracVO.getYear()+ "." + pracVO.getMonth() + "." + pracVO.getDay() + "\n취미: "+ pracVO.getHobby() + "\n" + pracVO.getName() +"님 회원가입이 완료되었습니다.";
    }

}