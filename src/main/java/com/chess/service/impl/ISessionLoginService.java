package com.chess.service.impl;

import com.chess.entity.Player;
import com.chess.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISessionLoginService implements LoginService {

    @Autowired
    HttpSession httpSession;

    @Override
    public boolean login(Player player) {
        httpSession.setAttribute("isLogged", true);
        httpSession.setAttribute("fullName", player.getFirstName()+" "+player.getLastName());
        httpSession.setAttribute("playerId", player.getId());
        return true;
    }

    @Override
    public boolean isLogged() {
        return httpSession.getAttribute("isLogged") != null && (boolean) httpSession.getAttribute("isLogged");
    }

    @Override
    public int getPlayerId() {
            return (int)httpSession.getAttribute("playerId");
        }
    }

