package com.reyhan.gamelibrary.service;

import com.reyhan.gamelibrary.exception.ResourceNotFoundException;
import com.reyhan.gamelibrary.model.Game;
import com.reyhan.gamelibrary.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private GameRepository gameRepository;

    @Transactional
    public void updateGame(Long id, Game newGameInfo) {
        logger.info("Updating game with ID: {}", id);
        Game gameToUpdate = gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game with that ID can not be found."));
        gameToUpdate.setTitle(newGameInfo.getTitle());
        gameToUpdate.setYear(newGameInfo.getYear());
        gameToUpdate.setRating(newGameInfo.getRating());
        Game updatedGame = gameRepository.save(gameToUpdate);
        logger.info("Game updated successfully");
    }

    @Transactional
    public void deleteGame(Long id) {
        logger.info("Deleting game with ID: {}", id);
        Game gameToDelete = gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game with that ID can not be found."));
        gameRepository.delete(gameToDelete);
        logger.info("Game deleted successfully");
    }
}
