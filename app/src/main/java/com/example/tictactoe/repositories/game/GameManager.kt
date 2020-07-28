package com.example.tictactoe.repositories.game

import com.example.tictactoe.models.Position
import com.example.tictactoe.utilities.Constants

class GameManager {
    var isGameOver : Boolean = false
    var isPlayer1Turn : Boolean = true

    fun playerWin(positions: MutableList<Position>, lastPosition: Position) : Boolean
    {
        val verticalLine = positions.filter { it.row == lastPosition.row }
        if(verticalLine.size == Constants.boardSize)
            return true

        val horizontalLine = positions.filter { it.column == lastPosition.column }
        if(horizontalLine.size == Constants.boardSize)
            return true

        return false
    }
}