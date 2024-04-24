package com.reyhan.gamelibrary.controller;

import com.reyhan.gamelibrary.exception.ResourceNotFoundException;
import com.reyhan.gamelibrary.model.Game;
import com.reyhan.gamelibrary.repository.GameRepository;
import com.reyhan.gamelibrary.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @GetMapping("/game-library")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @PostMapping("/game-library")
    public Game addGame(@Valid @RequestBody Game game) {
        return gameRepository.save(game);
    }

    @GetMapping("/game-library/{id}")
    public ResponseEntity<Game> getGameByID(@PathVariable Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game with that ID can not be found."));
        return ResponseEntity.ok(game);
    }

    @PutMapping("/game-library/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @Valid @RequestBody Game newGameInfo) {
        try {
            gameService.updateGame(id, newGameInfo);
            return ResponseEntity.ok(newGameInfo);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Game with that ID can not be found.");
        }
    }

    @DeleteMapping("/game-library/{id}")
    public ResponseEntity<Boolean> deleteGame(@PathVariable Long id) {
        try {
            gameRepository.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Game with that ID can not be found.");
        }


    }
}
