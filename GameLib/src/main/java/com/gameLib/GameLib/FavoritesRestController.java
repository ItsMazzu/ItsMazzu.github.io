package com.gameLib.GameLib;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import com.gameLib.GameLib.model.Game;
import com.gameLib.GameLib.model.Console;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesRestController {

    private List<Game> favoriteGames = new ArrayList<>();
    private List<Console> favoriteConsoles = new ArrayList<>();

    @GetMapping("/games")
    public List<Game> getFavoriteGames() {
        return favoriteGames;
    }

    @PostMapping("/games")
    public void addFavoriteGame(@RequestBody Game game) {
        favoriteGames.add(game);
    }

    @DeleteMapping("/games/{name}")
    public void deleteFavoriteGame(@PathVariable String name) {
        favoriteGames.removeIf(game -> game.getName().equals(name));
    }

    @GetMapping("/consoles")
    public List<Console> getFavoriteConsoles() {
        return favoriteConsoles;
    }

    @PostMapping("/consoles")
    public void addFavoriteConsole(@RequestBody Console console) {
        favoriteConsoles.add(console);
    }

    @DeleteMapping("/consoles/{name}")
    public void deleteFavoriteConsole(@PathVariable String name) {
        favoriteConsoles.removeIf(console -> console.getName().equals(name));
    }
}
