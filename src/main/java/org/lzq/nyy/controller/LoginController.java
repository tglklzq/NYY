package org.lzq.nyy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "login success2";
    }
    @RequestMapping("/logout")
    public String logout() {
        return "logout success2";
    }
}
