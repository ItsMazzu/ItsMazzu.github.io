package com.gameLib.GameLib;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.gameLib.GameLib.model.Game;
import com.gameLib.GameLib.repository.GameRepository;
import com.gameLib.GameLib.service.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/games")
public class GameRestController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @GetMapping
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @PostMapping
    public void addGame(@RequestBody Game game) {
        if (game.getId() == null || game.getId().isEmpty()) {
            game.setId(idGeneratorService.generateGameId());
        }
        gameRepository.save(game);
    }
}
