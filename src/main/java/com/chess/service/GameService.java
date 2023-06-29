package com.chess.service;

import com.chess.entity.Game;
import com.chess.exception.EmptyFieldsException;
import com.chess.exception.GameDoesNotExistException;
import com.chess.exception.GameExistsException;
import com.chess.exception.NotSignedInException;
import com.chess.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LoginService loginService;

    public boolean saveGame(int id) throws GameExistsException, EmptyFieldsException, NotSignedInException {
        if(loginService.isLogged()) {
                if(id > 0) {
                    if(!gameRepository.existsGameById(id)) {
                        Game game = Game.builder().id(id).build();
                        gameRepository.save(game);
                        return game.getId() > 0;
                    }
                    throw new GameExistsException();
                }
                throw EmptyFieldsException.with("id");
        }
        throw new NotSignedInException();
    }

    public boolean updateGame(int id,int victory,int defeat,int pat,String winner) throws GameExistsException, EmptyFieldsException, NotSignedInException, GameDoesNotExistException {
        if(loginService.isLogged()) {
                if(victory > 0) {
                    try {
                        Game game = gameRepository.findById(id).get();
                        game.setVictory(victory);
                        game.setDefeat(defeat);
                        game.setPat(pat);
                        game.setWinner(winner);
                        gameRepository.save(game);
                        return true;
                    }catch (Exception ex) {
                        throw new GameDoesNotExistException();
                    }
                }
                throw EmptyFieldsException.with("title");

        }
        throw new NotSignedInException();
    }


    public List<Game> getGames() throws NotSignedInException {
        if(loginService.isLogged()) {
            return (List<Game>) gameRepository.findAll();
        }
        throw new NotSignedInException();
    }

    public Game getGameById(int id) throws NotSignedInException, GameDoesNotExistException {
        if(loginService.isLogged()) {
            try {
                return gameRepository.findById(id).get();
            }catch (Exception ex) {
                throw new GameDoesNotExistException();
            }
        }
        throw new NotSignedInException();
    }
}
