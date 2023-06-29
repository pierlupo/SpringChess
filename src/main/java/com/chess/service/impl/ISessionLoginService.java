package com.chess.service.impl;

import com.chess.entity.AppUser;
import com.chess.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISessionLoginService implements LoginService {

    @Autowired
    HttpSession httpSession;

    @Override
    public boolean login(AppUser appUser) {
        httpSession.setAttribute("isLogged", true);
        httpSession.setAttribute("fullName", appUser.getFirstName()+" "+appUser.getLastName());
        httpSession.setAttribute("userId", appUser.getId());
        return true;
    }

    @Override
    public boolean isLogged() {
        return httpSession.getAttribute("isLogged") != null && (boolean) httpSession.getAttribute("isLogged");
    }

    @Override
    public int getUserId() {
            return (int)httpSession.getAttribute("userId");
        }
    }

