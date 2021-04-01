package com.antainfernandez.fiveInARowGame.model;

public class Board {

    final static private int rows = 6;
    final static private int columns = 9;

    final static public char DISK_X='X';
    final static public char DISK_O='O';

    private char[][] matrix = new char[rows][columns];


    public Board(){
        super();
    }

    public static int getRows() {
        return rows;
    }

    public static int getColumns() {
        return columns;
    }

    public char[][] getMatrix() {
        return matrix;
    }

//    public String getPrintedBoard() {
//        return printedBoard;
//    }
//
//    public void setPrintedBoard(String printedBoard) {
//        this.printedBoard = printedBoard;
//    }

    public boolean insert(int column, char disk){
        //starting from top row of the column
        int currentRow=this.rows-1;
        if((disk==DISK_O || disk==DISK_X) && (column <= columns && column > 0) && (!cellIsUsed(currentRow,column-1))){
                while(currentRow >= 0 && !cellIsUsed(currentRow,column-1)){
                    currentRow--;
                }
                matrix[currentRow+1][column-1] = disk;
                return true;
        }else{
            return false;
        }

    }

    private boolean cellIsUsed(int row, int column) {
        return matrix[row][column] == Character.MIN_VALUE ? false : true;
    }


    public char checkConnectWinner() {
        // horizontalCheck
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < columns - 4; col++)
            {
                char cell = matrix[row][col];
                if (cell == matrix[row][col + 1] &&
                        cell == matrix[row][col + 2] &&
                        cell == matrix[row][col + 3] &&
                        cell == matrix[row][col + 4] )
                {
                    if (cell != Character.MIN_VALUE) {
                        return cell;
                    }
                }
            }
        }

        // verticalCheck
        for (int row = 0; row < rows - 4; row++)
        {
            for (int col = 0; col < columns; col++)
            {
                char cell = matrix[row][col];
                if (cell == matrix[row + 1][col] &&
                        cell == matrix[row + 2][col] &&
                        cell == matrix[row + 3][col] &&
                        cell == matrix[row + 4][col])
                {
                    if (cell != Character.MIN_VALUE) {
                        return cell;
                    }
                }
            }
        }

// ascendingDiagonalCheck
        for (int row = 0; row < rows - 4; row++) {
            for (int col = 0; col < columns - 4; col++) {
                char cell = matrix[row][col];
                if (cell == matrix[row + 1][col + 1] &&
                        cell == matrix[row + 2][col + 2] &&
                        cell == matrix[row + 3][col + 3] &&
                        cell == matrix[row + 4][col + 4]) {
                    if (cell != Character.MIN_VALUE) {
                        return cell;
                    }
                }
            }
        }

// DescendingDiagonalCheck
        for (int row = 0; row < rows - 4; row++) {
            for (int col = 4; col < columns; col++) {
                char cell = matrix[row][col];
                if (cell == matrix[row + 1][col - 1] &&
                        cell == matrix[row + 2][col - 2] &&
                        cell == matrix[row + 3][col - 3] &&
                        cell == matrix[row + 4][col - 4]) {
                    if (cell != Character.MIN_VALUE) {
                        return cell;
                    }
                }
            }
        }

        return Character.MIN_VALUE;
    }


    public String print(){
        StringBuilder b= new StringBuilder();
        for (int i = rows -1; i >= 0; i--) {
            for (int j = 0; j < columns; j++) {
               b.append("[").append(matrix[i][j] == Character.MIN_VALUE ? " " : matrix[i][j] ).append("] ");
            }
            b.append("\n");
        }
        return b.toString();
    }
}
