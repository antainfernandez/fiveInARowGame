package com.antainfernandez.fiveInARowGame.model;

import com.antainfernandez.fiveInARowGame.enums.GameStatus;

public class Game {

    private String id;
    
    private Board board;
    
    private Player player1;

    private Player player2;
    
    private Player currentPlayer;
    
    private Player winner;

    private GameStatus gameStatus;

    public Game(){
        super();
    }

    public Game(Board board){
this.board=board;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        player1.setDisk(Board.DISK_O);
        this.player1 = player1;
        this.currentPlayer=player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        player2.setDisk(Board.DISK_X);
        this.player2 = player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void updateTurn() {
        if(player2 == null)
        {
            return;
        }
        if(currentPlayer == player1){
            currentPlayer = player2;
        }else{
            currentPlayer=player1;
        }

    }
}
