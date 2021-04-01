package com.antainfernandez.fiveInARowGame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {


    @Test
    public void assignPlayer1ToGameTest() {
        Game game = new Game(new Board());
        Player p1 = new Player();
        p1.setName("p1");
        game.setPlayer1(p1);
        assertTrue(p1 == game.getCurrentPlayer());
    }

    @Test
    public void assignPlayerToGameShouldGiveDifferentDisksTest() {
        Game game = new Game(new Board());
        Player p1 = new Player();
        p1.setName("p1");
        Player p2 = new Player();
        p1.setName("p2");
        game.setPlayer1(p1);
        game.setPlayer2(p2);
        assertTrue(p1.getDisk() != p2.getDisk());
    }

    @Test
    public void turnPlayerGameShouldSwitchPlayersTest() {
        Game game = new Game(new Board());
        Player p1 = new Player();
        p1.setName("p1");
        Player p2 = new Player();
        p1.setName("p2");
        game.setPlayer1(p1);
        game.setPlayer2(p2);
        assertTrue(p1 == game.getCurrentPlayer());
        game.updateTurn();
        assertTrue(p2 == game.getCurrentPlayer());

    }


}
