package com.example.tictactoe.repositories.game

import com.example.tictactoe.models.Player
import com.example.tictactoe.models.Position
import com.example.tictactoe.models.Symbol
import com.example.tictactoe.utilities.Constants
import com.example.tictactoe.utilities.Operations.pow

class GameManager {
    var isGameOver : Boolean = false
    var roundCount : Int = 0

    private val player1 = Player(Symbol.X)
    private val player2 = Player(Symbol.O)

    var players = arrayOf(player1, player2)
    var playingPlayer = players[0]

    fun playerWin(positions: MutableList<Position>, lastPositionPlayed: Position) : Boolean
    {
        if(hasARow(positions, lastPositionPlayed)) return true

        if(hasAColumn(positions, lastPositionPlayed)) return true

        if(hasADiagonal(positions, lastPositionPlayed)) return true

        return false
    }

    fun isADraw() : Boolean {
        return roundCount == Constants.boardSize.pow(2)
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