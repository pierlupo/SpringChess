package com.chess.service;

import com.chess.entity.Player;


public interface LoginService {

    public boolean login(Player player);

    public boolean isLogged();

    public int getPlayerId();
}
