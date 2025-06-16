package com.gameLib.GameLib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gameLib.GameLib.model.Console;

public interface ConsoleRepository extends JpaRepository<Console, String> {
}
