package com.antainfernandez.fiveInARowGame.controller.dto;

public class GameStatusDto {

    private String id;
    private String player1;
    private String player2;
    private String player1Disk;
    private String player2Disk;
    private String gameStatus;
    private String currentPlayer;
    private String winner;
    private String board;

    public GameStatusDto() {
        super();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer1Disk() {
        return player1Disk;
    }

    public void setPlayer1Disk(String player1Disk) {
        this.player1Disk = player1Disk;
    }

    public String getPlayer2Disk() {
        return player2Disk;
    }

    public void setPlayer2Disk(String player2Disk) {
        this.player2Disk = player2Disk;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
}
