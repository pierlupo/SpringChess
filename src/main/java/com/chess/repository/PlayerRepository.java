package com.chess.repository;

import com.chess.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    public Player findByEmailAndPassword(String email, String password);

    public Player findByUserNameAndPassword(String userName, String password);

    public Player findByUserName(String userName);
    public Player findByEmail(String email);

    public Player findByNbrOfGames(int nbrOfGames);

    public boolean existsBynbrOfGames(int nbrOfGames);

    public Player deleteById(int id);
}
