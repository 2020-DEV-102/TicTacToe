package com.example.tictactoe.repositories.board

import com.example.tictactoe.models.Square
import com.example.tictactoe.models.Position
import com.example.tictactoe.utilities.Constants
import com.example.tictactoe.utilities.Constants.Companion.boardSize

class BoardRepository : IBoardRepository {
    private val board = Array(boardSize) { Array(boardSize){Square(Position(0,0))} }

    init
    {
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                board[i][j].position = Position(i,j)
            }
        }
    }

    override fun getSquare(position: Position): Square? {
        return board[position.row][position.column]
    }

    override fun updateSquare(position: Position, isFree: Boolean) {
        board[position.row][position.column].isFree = isFree
    }

    override fun cleanBoard() {
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                board[i][j].isFree = true
            }
        }
    }
}