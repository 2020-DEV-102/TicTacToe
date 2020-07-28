package com.example.tictactoe.repositories.board

import com.example.tictactoe.models.Square
import com.example.tictactoe.models.SquareState
import com.example.tictactoe.models.Position
import com.example.tictactoe.utilities.Constants

class BoardRepository : IBoardRepository {
    private val cells = Array(Constants.boardSize) { Array(Constants.boardSize){Square(Position(0,0))} }

    init
    {
        for (i in 0 until Constants.boardSize) {
            for (j in 0 until Constants.boardSize) {
                cells[i][j].position = Position(i,j)
            }
        }
    }

    override fun getSquare(position: Position): Square? {
        return cells[position.row][position.column]
    }

    override fun getAllSquares(): Array<Array<Square>> {
        return cells
    }

    /*override fun updateCell(square: Square, isPlayer1Turn: Boolean) {
        when(isPlayer1Turn){
            true -> square.state = SquareState.X
            false -> square.state = SquareState.O
        }
    }*/

    override fun refreshCells() {
        for (i in 0 until Constants.boardSize) {
            for (j in 0 until Constants.boardSize) {
                cells[i][j].state = SquareState.EMPTY
            }
        }
    }
}