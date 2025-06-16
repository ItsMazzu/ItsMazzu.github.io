package com.gameLib.GameLib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gameLib.GameLib.repository.GameRepository;
import com.gameLib.GameLib.repository.ConsoleRepository;
import com.gameLib.GameLib.repository.ReviewRepository;

import java.util.Optional;

@Service
public class IdGeneratorService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public synchronized String generateGameId() {
        String prefix = "g";
        int nextId = 1;
        Optional<String> maxIdOpt = gameRepository.findAll().stream()
                .map(game -> game.getId())
                .filter(id -> id != null && id.startsWith(prefix))
                .max((a, b) -> a.compareTo(b));
        if (maxIdOpt.isPresent()) {
            String maxId = maxIdOpt.get();
            try {
                int num = Integer.parseInt(maxId.substring(prefix.length()));
                nextId = num + 1;
            } catch (NumberFormatException e) {
                // ignore and use 1
            }
        }
        return prefix + String.format("%03d", nextId);
    }

    public synchronized String generateConsoleId() {
        String prefix = "c";
        int nextId = 1;
        Optional<String> maxIdOpt = consoleRepository.findAll().stream()
                .map(console -> console.getId())
                .filter(id -> id != null && id.startsWith(prefix))
                .max((a, b) -> a.compareTo(b));
        if (maxIdOpt.isPresent()) {
            String maxId = maxIdOpt.get();
            try {
                int num = Integer.parseInt(maxId.substring(prefix.length()));
                nextId = num + 1;
            } catch (NumberFormatException e) {
                // ignore and use 1
            }
        }
        return prefix + String.format("%03d", nextId);
    }

    public synchronized String generateReviewId() {
        String prefix = "rv";
        int nextId = 1;
        Optional<String> maxIdOpt = reviewRepository.findAll().stream()
                .map(review -> review.getId())
                .filter(id -> id != null && id.startsWith(prefix))
                .max((a, b) -> a.compareTo(b));
        if (maxIdOpt.isPresent()) {
            String maxId = maxIdOpt.get();
            try {
                int num = Integer.parseInt(maxId.substring(prefix.length()));
                nextId = num + 1;
            } catch (NumberFormatException e) {
                // ignore and use 1
            }
        }
        return prefix + String.format("%03d", nextId);
    }
}
