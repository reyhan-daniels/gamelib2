package com.reyhan.gamelibrary.repository;

import com.reyhan.gamelibrary.exception.ResourceNotFoundException;
import com.reyhan.gamelibrary.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {


}
