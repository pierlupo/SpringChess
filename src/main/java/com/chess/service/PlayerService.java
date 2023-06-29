package com.chess.service;

import com.chess.entity.Game;
import com.chess.entity.Player;
import com.chess.exception.*;
import com.chess.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {


    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LoginService loginService;

    public boolean savePlayer(String nickName, int victory, int defeat, int pat, int nbrOfGames, int elo) throws PlayerExistsException, EmptyFieldsException, NotSignedInException {
        if(loginService.isLogged()) {
                if(nbrOfGames > 0) {
                    if(!playerRepository.existsBynbrOfGames(nbrOfGames)) {
                        Player player = Player.builder().nickName(nickName).victory(victory).defeat(defeat).pat(pat).elo(elo).build();
                        playerRepository.save(player);
                        return player.getId() > 0;
                    }
                    throw new PlayerExistsException();
                }
                throw EmptyFieldsException.with("nickName");

        }
        throw new NotSignedInException();
    }

    public boolean updatePlayer(String nickName, int victory, int defeat, int pat, int nbrOfGames, int Elo) throws PlayerExistsException, EmptyFieldsException, NotSignedInException, PlayerDoesNotExistException
    {
        if(loginService.isLogged()) {
                if(nbrOfGames > 0) {
                    try {
                        Player player = playerRepository.findByNbrOfGames(nbrOfGames);
                        player.setNickName(nickName);
                        player.setVictory(victory);
                        player.setDefeat(defeat);
                        player.setPat(pat);
                        player.setNbrOfGames(nbrOfGames);
                        player.setElo(Elo);
                        playerRepository.save(player);
                        return true;
                    }catch (Exception ex) {
                        throw new PlayerDoesNotExistException();
                    }
                }
                throw EmptyFieldsException.with("nickName");
        }
        throw new NotSignedInException();
    }

    public boolean deletePlayer(int id) throws Exception {
        Player player = playerRepository.deleteById(id);
        if(player != null) {
            playerRepository.delete(player);
            return true;
        }
        throw new Exception("No player with this id");
    }

    public List<Player> getPlayers() throws NotSignedInException {
        if(loginService.isLogged()) {
            return (List<Player>) playerRepository.findAll();
        }
        throw new NotSignedInException();
    }

    public Player getPlayerById(int id) throws NotSignedInException, PlayerDoesNotExistException {
        if(loginService.isLogged()) {
            try {
                return playerRepository.findById(id).get();
            }catch (Exception ex) {
                throw new PlayerDoesNotExistException();
            }
        }
        throw new NotSignedInException();
    }

    public List<Game> getGamesByPlayerId(int id) throws NotSignedInException {
        if(loginService.isLogged()) {
            List<Game> games = playerRepository.findById(id).get().getGames();
            return games;
        }
        throw new NotSignedInException();
    }
}
