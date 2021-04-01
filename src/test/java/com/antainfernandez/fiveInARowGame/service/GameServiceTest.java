package com.antainfernandez.fiveInARowGame.service;

import com.antainfernandez.fiveInARowGame.model.Game;
import com.antainfernandez.fiveInARowGame.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Test
    public void simulateGameTest() {
        Player p1 = new Player();
        p1.setName("p1");

        Player p2 = new Player();
        p2.setName("p2");

        Game game = gameService.startNewGame(p1);

        gameService.connectToGame(p2, game.getId());
        int disks = 10;
        boolean weHaveAWinner = false;
        while (disks >= 0 && !weHaveAWinner) {
            disks--;
            Player p = game.getCurrentPlayer();
            int column = p == p1 ? 1 : 2;
            weHaveAWinner = gameService.play(game.getId(), p, column).getWinner() != null;
        }

        assertTrue(game.getWinner() == p1);
    }
}
