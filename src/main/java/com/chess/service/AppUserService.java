package com.chess.service;

import com.chess.entity.AppUser;
import com.chess.exception.UserDoesNotExistException;
import com.chess.exception.UserExistsException;
import com.chess.exception.UserNotActiveException;
import com.chess.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private LoginService loginService;

    public boolean signUp(String firstName, String lastName, String nickName, String email, String password) throws UserExistsException {
        try {
            appUserRepository.findByEmail(email);
            throw new UserExistsException();
        }
        catch (Exception ex) {
            AppUser appUser = AppUser.builder().firstName(firstName).lastName(lastName).nickName(nickName).email(email).password(password).build();
            appUserRepository.save(appUser);
            return appUser.getId() > 0;
        }
    }

    public boolean signIn(String nickName, String password) throws UserDoesNotExistException {
        try {
            AppUser appUser = appUserRepository.findByNickNameAndPassword(nickName, password);
            if(appUser.isActive()) {
                return loginService.login(appUser);
            }
            throw new UserNotActiveException();
        }catch (Exception ex) {
            throw new UserDoesNotExistException();
        }
    }
}
