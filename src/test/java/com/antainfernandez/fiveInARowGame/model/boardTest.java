package com.antainfernandez.fiveInARowGame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class boardTest {

    @Test
    public void insertDiskOnlyAllowInColumnsInRange() {
        Board board = new Board();

        assertFalse(board.insert(0, Board.DISK_O), "column should be between 1 and 9");
        assertFalse(board.insert(10, Board.DISK_O), "column should be between 1 and 9");
        assertTrue(board.insert(1, Board.DISK_O), "column should be between 1 and 9");
    }

    @Test
    public void insertDiskShouldBeXorO() {
        Board board = new Board();
        assertTrue(board.insert(1, Board.DISK_O), "wrong disk");
        assertTrue(board.getMatrix()[0][0] == Board.DISK_O);
        assertTrue(board.insert(1, Board.DISK_X), "wrong disk");
        assertTrue(board.getMatrix()[1][0] == Board.DISK_X);
        assertFalse(board.insert(1, 'p'), "wrong disk");
    }

    @Test
    public void insertDiskOnlyAllowInColumnIfItIsNotful() {
        Board board = new Board();

        int column = 1;
        for (int disk = 1; disk <= Board.getRows(); disk++) {
            assertTrue(board.insert(column, Board.DISK_O));
            assertTrue(board.getMatrix()[disk - 1][column - 1] == Board.DISK_O);
        }
        assertFalse(board.insert(column, Board.DISK_O), "column should be full");


    }

    @Test
    public void checkWinner() {
        Board board = new Board();
        char[][] matrix = board.getMatrix();

//        //column1
//        matrix[5][0]=Board.DISK_O;
//        matrix[4][0]=Board.DISK_O;
//        matrix[3][0]=Board.DISK_O;
//        matrix[2][0]=Board.DISK_O;
//        matrix[1][0]=Board.DISK_O;
        //      matrix[0][0]=Board.DISK_O;
//
//        //column2
//        matrix[5][1]=Board.DISK_O;
//        matrix[4][1]=Board.DISK_O;
//        matrix[3][1]=Board.DISK_O;
//        matrix[2][1]=Board.DISK_O;
//        matrix[1][1]=Board.DISK_O;
        //     matrix[0][1]=Board.DISK_O;
//
//
//        //column3
//        matrix[5][2]=Board.DISK_O;
//        matrix[4][2]=Board.DISK_O;
//        matrix[3][2]=Board.DISK_O;
//        matrix[2][2]=Board.DISK_O;
//        matrix[1][2]=Board.DISK_O;
        matrix[0][2] = Board.DISK_O;
//
//
//
//        //column4
//        matrix[5][3]=Board.DISK_O;
//        matrix[4][3]=Board.DISK_O;
//        matrix[3][3]=Board.DISK_O;
//        matrix[2][3]=Board.DISK_O;
//        matrix[1][3]=Board.DISK_O;
        matrix[0][3] = Board.DISK_O;
//
//        //column5
//        matrix[5][4]=Board.DISK_O;
//        matrix[4][4]=Board.DISK_O;
//        matrix[3][4]=Board.DISK_O;
//        matrix[2][4]=Board.DISK_O;
//        matrix[1][4]=Board.DISK_O;
        matrix[0][4] = Board.DISK_O;
//
//
//        //column6
//        matrix[5][5]=Board.DISK_O;
//        matrix[4][5]=Board.DISK_O;
//        matrix[3][5]=Board.DISK_O;
//        matrix[2][5]=Board.DISK_O;
//        matrix[1][5]=Board.DISK_O;
        matrix[0][5] = Board.DISK_O;
//
//        //column7
//        matrix[5][6]=Board.DISK_O;
//        matrix[4][6]=Board.DISK_O;
//        matrix[3][6]=Board.DISK_O;
//        matrix[2][6]=Board.DISK_O;
//        matrix[1][6]=Board.DISK_O;
        matrix[0][6] = Board.DISK_O;
//
//
//        //column8
//        matrix[5][7]=Board.DISK_O;
//        matrix[4][7]=Board.DISK_O;
//        matrix[3][7]=Board.DISK_O;
//        matrix[2][7]=Board.DISK_O;
//        matrix[1][7]=Board.DISK_O;
//        matrix[0][7]=Board.DISK_O;


        //column9
        matrix[5][8] = Board.DISK_O;
        matrix[4][8] = Board.DISK_X;
        matrix[3][8] = Board.DISK_O;
        matrix[2][8] = Board.DISK_X;
        matrix[1][8] = Board.DISK_X;
        matrix[0][8] = Board.DISK_X;

        assertTrue(board.checkConnectWinner() == Board.DISK_O, "has winner " + Board.DISK_O);

    }


    @Test
    public void checkWinnerOnDiagonal() {
        Board board = new Board();
        char[][] matrix = board.getMatrix();

        matrix[0][0] = Board.DISK_O;
        matrix[1][1] = Board.DISK_O;
        matrix[2][2] = Board.DISK_O;
        matrix[3][3] = Board.DISK_O;
        matrix[4][4] = Board.DISK_O;

        assertTrue(board.checkConnectWinner() == Board.DISK_O, "has winner " + Board.DISK_O);
        System.out.println(board.print());
    }

    @Test
    public void checkWinnerOnVertical() {
        Board board = new Board();
        char[][] matrix = board.getMatrix();

        matrix[0][0] = Board.DISK_O;
        matrix[1][0] = Board.DISK_O;
        matrix[2][0] = Board.DISK_O;
        matrix[3][0] = Board.DISK_O;
        matrix[4][0] = Board.DISK_O;

        assertTrue(board.checkConnectWinner() == Board.DISK_O, "has winner " + Board.DISK_O);

    }

    @Test
    public void checkWinnerOnHorizontal() {
        Board board = new Board();
        char[][] matrix = board.getMatrix();

        matrix[0][3] = Board.DISK_O;
        matrix[0][4] = Board.DISK_O;
        matrix[0][5] = Board.DISK_O;
        matrix[0][6] = Board.DISK_O;
        matrix[0][7] = Board.DISK_O;

        assertTrue(board.checkConnectWinner() == Board.DISK_O, "has winner " + Board.DISK_O);

        // System.out.println(board.print());
    }

}
