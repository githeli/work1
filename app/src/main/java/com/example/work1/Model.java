package com.example.work1;

import static com.example.work1.AppConsts.*;

import android.app.Activity;
import android.widget.Toast;

public class Model
{
    // 1 Board  - 2D Array
    private int[][] board;
    private int turnCounter = 0;

    public Model() {
        board = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // check Win
    public int checkWin(){
        int sumMainDiagonal = 0;
        int sumSecondaryDiagonal = 0;
        for (int i = 0; i < ROWS; i++) {
            int sumRow = 0;
            int sumColumn = 0;
            sumMainDiagonal += board[i][i];
            sumSecondaryDiagonal += board[i][COLUMNS - 1 - i];
            for (int j = 0; j < COLUMNS; j++) {
                sumRow += board[i][j];
                sumColumn+= board[j][i];
            }
            if (sumRow == 3 || sumColumn == 3) {
                return PLAYER_X;
            } else if (sumRow == -3 || sumColumn == -3) {
                return PLAYER_0;
            }
        }
        if (sumMainDiagonal == 3 || sumSecondaryDiagonal == 3) {
            return PLAYER_X;
        } else if (sumMainDiagonal == -3 || sumSecondaryDiagonal == -3) {
            return PLAYER_0;
        }
        return EMPTY;
    }

    // update Board (row,column)
    public int updateBoard(int row, int column) {
        if(turnCounter%2==0){
            board[row][column] = PLAYER_X;
            turnCounter++;
            return  PLAYER_X;
        }
        else{
            board[row][column] = PLAYER_0;
            turnCounter++;
            return  PLAYER_0;
        }
    }
    // is valid move (row,column)
    public boolean isValidMove(int row, int column) {
        return board[row][column] == EMPTY;
    }

    // TIE - turns are over and no one has won
    public boolean isTie() {
        return turnCounter == ROWS * COLUMNS && checkWin() == EMPTY;
    }
    // reset Board
    public void resetBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
        turnCounter = 0;
    }
}