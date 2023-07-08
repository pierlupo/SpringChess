package com.chess.controller;

import com.chess.entity.Player;
import com.chess.exception.*;
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
    private LoginService loginservice;
    @GetMapping("/signin")
    public ModelAndView signIn(){
        ModelAndView mv = new ModelAndView("signin");
        return mv;
    }

    @PostMapping("/signin")
    public ModelAndView postSignIn(@RequestParam String userName, @RequestParam String password) throws PlayerDoesNotExistException, IOException, NotSignedInException {
//        if(playerService.signIn(email, password)) {
//            return "redirect:/player/form";}
//
//        return null;
//    }
        if (playerService.signIn(userName, password)) {
            response.sendRedirect("/player/form");
        }
            ModelAndView mv = new ModelAndView("signin");
            return mv;
        }

    @ExceptionHandler(PlayerDoesNotExistException.class)
    public ModelAndView handleOPlayerDoesNotExist(PlayerDoesNotExistException ex) throws IOException {
        ModelAndView mv = new ModelAndView("signin");
        mv.addObject("message", ex.getMessage());
        return mv;
    }
    @GetMapping("/signup")
    public ModelAndView postSignIn() {
        ModelAndView mv = new ModelAndView("signup");
        return mv;
    }

    @PostMapping("/signup")
    public String postSignUp(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String userName,@RequestParam String email, @RequestParam String password) throws IOException, PlayerExistsException {
        if(playerService.signUp(firstName, lastName, userName, email, password)) {
            return "redirect:/player/signin";
        }
        return null;
    }
    @ExceptionHandler(PlayerExistsException.class)
    public ModelAndView handlePlayerExists(PlayerExistsException ex) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("message", ex.getMessage());
        return mv;
    }



    @GetMapping("/get")
    public ModelAndView get() throws NotSignedInException {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("players", playerService.getPlayers());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView formAddPlayer() throws IOException {
        if(!loginservice.isLogged()) {
            response.sendRedirect("/player/signin");
        }
        ModelAndView mv =new ModelAndView("form");
        mv.addObject("player", new Player());
        return mv;
    }

    @PostMapping("/Add")
    public String  submitAddPlayerStats(@RequestParam String userName, @RequestParam int victory, @RequestParam int defeat, @RequestParam int pat, @RequestParam int nbrOfGames, @RequestParam int Elo) throws NotSignedInException, PlayerExistsException, EmptyFieldsException, IOException {
        if(!loginservice.isLogged()) {
            response.sendRedirect("/player/signin");
        }
        if(playerService.savePlayerStats(userName, victory, defeat, pat, nbrOfGames, Elo)){
            return "redirect:/player";
        }
        return null;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView formEditPlayer(@PathVariable int id) throws NotSignedInException, PlayerDoesNotExistException, IOException {
        if(!loginservice.isLogged()) {
            response.sendRedirect("/player/signin");
        }
        ModelAndView mv = new ModelAndView("form");
        mv.addObject("player", playerService.getPlayerById(id));
        return mv;
    }

    @PostMapping("/edit/{id}")
    public String submitFormEditPlayer(@RequestParam String userName, @RequestParam int victory, @RequestParam int defeat, @RequestParam int pat, @RequestParam int nbrOfGames, @RequestParam int Elo) throws NotSignedInException, PlayerDoesNotExistException, EmptyFieldsException, PlayerExistsException, IOException {
        if(!loginservice.isLogged()) {
            response.sendRedirect("/player/signin");
        }
        if(playerService.updatePlayerStats(userName, victory, defeat, pat, nbrOfGames, Elo)){
            return "redirect:/profile";
        }
        return null;
    }

    @GetMapping("/form")
    public ModelAndView getForm() throws IOException {
//        if(!loginservice.isLogged()) {
//            response.sendRedirect("/player/signin");
//        }
        ModelAndView mv = new ModelAndView("form");
        mv.addObject("player", new Player());
        return mv;
    }

    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) throws Exception {
        if(!loginservice.isLogged()) {
            response.sendRedirect("/player/signin");
        }
        return playerService.deletePlayer(id);
    }

    @GetMapping("/search")
    public String searchTodoById(@RequestParam("playerId") Integer playerId, Model model) throws IOException, NotSignedInException, PlayerDoesNotExistException {
        Player player = playerService.getPlayerById(playerId);
        model.addAttribute("player", player);
        return "playerdetails";
    }
    @ExceptionHandler(NotSignedInException.class)
    public ModelAndView handleException(NotSignedInException ex) {
        ModelAndView mv = new ModelAndView("signin");
        mv.addObject("errormessage", ex.getMessage());
        return mv;
    }

}
