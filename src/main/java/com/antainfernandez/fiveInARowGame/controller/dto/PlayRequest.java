package com.antainfernandez.fiveInARowGame.controller.dto;

import com.antainfernandez.fiveInARowGame.model.Player;

public class PlayRequest {

    String gameID;
    Player player;
    int column;

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

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
