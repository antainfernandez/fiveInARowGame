package com.antainfernandez.fiveInARowGame.controller.dto;

import com.antainfernandez.fiveInARowGame.model.Player;

public class JoinGameRequest {

    String gameID;
    Player player;

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
