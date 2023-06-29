package com.chess.controller;

import com.chess.entity.Player;
import com.chess.exception.EmptyFieldsException;
import com.chess.exception.NotSignedInException;
import com.chess.exception.PlayerDoesNotExistException;
import com.chess.exception.PlayerExistsException;
import com.chess.service.LoginService;
import com.chess.service.PlayerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private LoginService ISessionLoginservice;

    @GetMapping("")
    public ModelAndView get() throws NotSignedInException {
        ModelAndView mv = new ModelAndView("form");
        mv.addObject("players", playerService.getPlayers());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView formAddPlayer() throws IOException {
//        if(!ISessionLoginservice.isLogged()) {
//            response.sendRedirect("/user/signin");
//        }
        ModelAndView mv =new ModelAndView("form");
        mv.addObject("player", new Player());
        return mv;
    }

    @PostMapping("/Add")
    public String  submitAddPlayer(@RequestParam String nickName, @RequestParam int victory, @RequestParam int defeat, @RequestParam int pat, @RequestParam int nbrOfGames, @RequestParam int Elo) throws NotSignedInException, PlayerExistsException, EmptyFieldsException, IOException {
//        if(!ISessionLoginservice.isLogged()) {
//            response.sendRedirect("/user/signin");
//        }
        if(playerService.savePlayer(nickName, victory, defeat, pat, nbrOfGames, Elo)){
            return "redirect:/player";
        }
        return null;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView formEditPlayer(@PathVariable int id) throws NotSignedInException, PlayerDoesNotExistException, IOException {
//        if(!ISessionLoginservice.isLogged()) {
//            response.sendRedirect("/user/signin");
//        }
        ModelAndView mv = new ModelAndView("form");
        mv.addObject("player", playerService.getPlayerById(id));
        return mv;
    }

    @PostMapping("/edit/{id}")
    public String submitFormEditPlayer(@RequestParam String nickName, @RequestParam int victory, @RequestParam int defeat, @RequestParam int pat, @RequestParam int nbrOfGames, @RequestParam int Elo) throws NotSignedInException, PlayerDoesNotExistException, EmptyFieldsException, PlayerExistsException, IOException {
//        if(!ISessionLoginservice.isLogged()) {
//            response.sendRedirect("/user/signin");
//        }
        if(playerService.updatePlayer(nickName, victory, defeat, pat, nbrOfGames, Elo)){
            return "redirect:/profile";
        }
        return null;
    }

    @GetMapping("/form")
    public ModelAndView getForm() throws IOException {
//        if(!ISessionLoginservice.isLogged()) {
//            response.sendRedirect("/user/signin");
//        }
        ModelAndView mv = new ModelAndView("form");
        mv.addObject("player", new Player());
        return mv;
    }

    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) throws Exception {
//        if(!ISessionLoginservice.isLogged()) {
//            response.sendRedirect("/user/login");
//        }
        return playerService.deletePlayer(id);
    }

    @GetMapping("/search")
    public String searchTodoById(@RequestParam("playerId") Integer playerId, Model model) throws IOException, NotSignedInException, PlayerDoesNotExistException {
        Player player = playerService.getPlayerById(playerId);
        model.addAttribute("player", player);
        return "playerdetails";
    }

    @ExceptionHandler(PlayerDoesNotExistException.class)
    public ModelAndView handleUserDoesNotExist(PlayerDoesNotExistException ex) {
        ModelAndView mv = new ModelAndView("signin");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(PlayerExistsException.class)
    public ModelAndView handleUserExists(PlayerExistsException ex) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("message", ex.getMessage());
        return mv;
    }
    @ExceptionHandler(NotSignedInException.class)
    public ModelAndView handleException(NotSignedInException ex) {
        ModelAndView mv = new ModelAndView("signin");
        mv.addObject("errormessage", ex.getMessage());
        return mv;
    }

}
