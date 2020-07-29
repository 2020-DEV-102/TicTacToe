package com.example.tictactoe.repositories.game

import com.example.tictactoe.models.Position
import com.example.tictactoe.utilities.Constants

class GameManager {
    var isGameOver : Boolean = false

    fun playerWin(positions: MutableList<Position>, lastPositionPlayed: Position) : Boolean
    {
        if(hasARow(positions, lastPositionPlayed)) return true

        if(hasAColumn(positions, lastPositionPlayed)) return true

        if(hasADiagonal(positions, lastPositionPlayed)) return true

        return false
    }

    private fun hasARow(positions: MutableList<Position>, lastPositionPlayed: Position) : Boolean {
        val row = positions.filter { it.row == lastPositionPlayed.row }
        return row.size == Constants.boardSize
    }

    private fun hasAColumn(positions: MutableList<Position>, lastPositionPlayed: Position) : Boolean {
        val column = positions.filter { it.column == lastPositionPlayed.column }
        return column.size == Constants.boardSize
    }

    private fun hasADiagonal(positions: MutableList<Position>, lastPositionPlayed: Position) : Boolean {
        // check on diagonal \
        if (lastPositionPlayed.row == lastPositionPlayed.column) {
            val diagonalDown = positions.filter { it.row == it.column }
            if (diagonalDown.size == Constants.boardSize)
                return true
        }

        // check on diagonal /
        if(lastPositionPlayed.row + lastPositionPlayed.column == Constants.boardSize - 1) {
            val diagonalUp = positions.filter { it.row + it.column == Constants.boardSize - 1 }
            if (diagonalUp.size == Constants.boardSize)
                return true
        }

        return false
    }
}