package com.beauty.Beautykiosk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/beauty")
    public String index()
    {
        if(1==1) {
            return "beauty_to_login_please_delete_just_test";
        }
        return "reirect:/";
    }   // 테스트 용도

    @GetMapping("/")
    public String root() {
        return "redirect:/beauty";
    }
}