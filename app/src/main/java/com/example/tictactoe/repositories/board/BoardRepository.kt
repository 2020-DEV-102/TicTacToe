package com.example.tictactoe.repositories.board

import com.example.tictactoe.models.Square
import com.example.tictactoe.models.SquareState
import com.example.tictactoe.models.Position
import com.example.tictactoe.utilities.Constants

class BoardRepository : IBoardRepository {
    private val board = Array(Constants.boardSize) { Array(Constants.boardSize){Square(Position(0,0))} }

    init
    {
        for (i in 0 until Constants.boardSize) {
            for (j in 0 until Constants.boardSize) {
                board[i][j].position = Position(i,j)
            }
        }
    }

    override fun getSquare(position: Position): Square? {
        return board[position.row][position.column]
    }

    override fun getAllSquares(): Array<Array<Square>> {
        return board
    }

    override fun refreshSquares() {
        for (i in 0 until Constants.boardSize) {
            for (j in 0 until Constants.boardSize) {
                board[i][j].state = SquareState.EMPTY
            }
        }
    }
}