package dev.melis.EngelsizGonuller.controller.loginwithgoogle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "Hello";
    }

    @GetMapping("/secure")
    public String secure(){
        return "Login";
    }
}
