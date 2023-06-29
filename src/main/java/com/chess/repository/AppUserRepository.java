package com.chess.repository;

import com.chess.entity.AppUser;
import com.chess.entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    public Player findByEmailAndPassword(String email, String password);

    public Player findByNickNameAndPassword(String nickName, String password);

    public Player findByEmail(String email);

    public Player findByNickName(String nickName);
}
