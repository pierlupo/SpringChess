package com.chess.controller;

import com.chess.exception.UserDoesNotExistException;
import com.chess.exception.UserExistsException;
import com.chess.service.AppUserService;
import com.chess.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpServletResponse response;

    @ExceptionHandler(UserDoesNotExistException.class)
    public ModelAndView handleUserDoesNotExist(UserDoesNotExistException ex) throws IOException {
        ModelAndView mv = new ModelAndView("signin");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(UserExistsException.class)
    public ModelAndView handleUserExists(UserExistsException ex) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @GetMapping("/signin")
    public ModelAndView signIn(){
        ModelAndView mv = new ModelAndView("signin");
        return mv;
    }

    @PostMapping("/signin")
    public String signUp(@RequestParam String nickName, @RequestParam String password) throws UserDoesNotExistException {
        if(appUserService.signIn(nickName, password)) {
            return "redirect:";
        }
        return null;
    }

    @GetMapping("/signup")
    public ModelAndView postSignIn() {
        ModelAndView mv = new ModelAndView("signup");
        return mv;
    }

    @PostMapping("/signup")
    public String postSignUp(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String nickName,@RequestParam String email, @RequestParam String password) throws IOException, UserExistsException {
        if(appUserService.signUp(firstName, lastName, nickName, email, password)) {
            return "redirect:/user/signin";
        }
        return null;
    }
}
