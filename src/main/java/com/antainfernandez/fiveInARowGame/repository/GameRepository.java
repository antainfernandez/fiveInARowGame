package com.antainfernandez.fiveInARowGame.repository;

import com.antainfernandez.fiveInARowGame.model.Game;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class GameRepository {

    private ConcurrentHashMap<String, Game> games= new ConcurrentHashMap<>();


    public void addGame(Game game){
        games.put(game.getId(),game);

    }

    public Game getGame(String gameId){
        return games.get(gameId);
    }

    public List<Game> getAll(){
        return games.values().stream().collect(Collectors.toList());
    }
}
