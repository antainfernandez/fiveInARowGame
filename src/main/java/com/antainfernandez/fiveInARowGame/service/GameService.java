package com.antainfernandez.fiveInARowGame.service;

import com.antainfernandez.fiveInARowGame.enums.GameStatus;
import com.antainfernandez.fiveInARowGame.exception.InvalidGameException;
import com.antainfernandez.fiveInARowGame.model.Board;
import com.antainfernandez.fiveInARowGame.model.Game;
import com.antainfernandez.fiveInARowGame.model.Player;
import com.antainfernandez.fiveInARowGame.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;


    public Game startNewGame(Player player1) {
        Game game = new Game(new Board());
        game.setId(UUID.randomUUID().toString());
        game.setPlayer1(player1);
        game.setGameStatus(GameStatus.NEW);
        gameRepository.addGame(game);
        return game;
    }


    public Game connectToGame(Player player2, String gameId) throws InvalidGameException {
        Game game = gameRepository.getGame(gameId);
        if (game.getGameStatus() != GameStatus.NEW) {
            throw new InvalidGameException("Can not connect to game");
        }
        game.setPlayer2(player2);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        return game;
    }

    public Game play(String gameId, Player player, int column) throws InvalidGameException {
        Game game = gameRepository.getGame(gameId);
        if (game.getGameStatus() != GameStatus.IN_PROGRESS) {
            throw new InvalidGameException("Game is no longer in progress");
        }
        if (!game.getCurrentPlayer().getName().equals(player.getName())) {
            throw new InvalidGameException("no your turn");
        }
        game.getBoard().insert(column, player.getDisk());
        if (game.getBoard().checkConnectWinner() == player.getDisk()) {
            game.setWinner(player);
            game.setGameStatus(GameStatus.OVER);
          return game;
        }
        game.updateTurn();
        return game;
    }

}
