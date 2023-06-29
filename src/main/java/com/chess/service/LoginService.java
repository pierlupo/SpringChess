package com.chess.service;

import com.chess.entity.AppUser;


public interface LoginService {

    public boolean login(AppUser appUser);

    public boolean isLogged();

    public int getUserId();
}
