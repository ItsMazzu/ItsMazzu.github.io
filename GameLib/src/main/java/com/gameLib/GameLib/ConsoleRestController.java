package com.gameLib.GameLib;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.gameLib.GameLib.model.Console;
import com.gameLib.GameLib.repository.ConsoleRepository;
import com.gameLib.GameLib.service.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/consoles")
public class ConsoleRestController {

    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @GetMapping
    public List<Console> getConsoles() {
        return consoleRepository.findAll();
    }

    @PostMapping
    public void addConsole(@RequestBody Console console) {
        if (console.getId() == null || console.getId().isEmpty()) {
            console.setId(idGeneratorService.generateConsoleId());
        }
        consoleRepository.save(console);
    }
}
