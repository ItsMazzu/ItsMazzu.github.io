package com.gameLib.GameLib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gameLib.GameLib.model.Game;

public interface GameRepository extends JpaRepository<Game, String> {
    Game findByName(String name);
}
