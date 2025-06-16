package com.gameLib.GameLib;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class PagesController {

    @GetMapping("/")
    public String landing(Model model) {
        return "landing";
    }

    @GetMapping("/favorite-games")
    public String favoriteGames(Model model) {
        return "favoriteGames";
    }

    @GetMapping("/favorite-consoles")
    public String favoriteConsoles(Model model) {
        return "favoriteConsoles";
    }

    @GetMapping("/eras")
    public String eras(Model model) {
        return "eras";
    }

    @GetMapping("/reviews")
    public String reviews(Model model) {
        return "reviews";
    }
}
