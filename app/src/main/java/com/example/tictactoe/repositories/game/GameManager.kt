package com.example.tictactoe.repositories.game

import com.example.tictactoe.models.Position
import com.example.tictactoe.utilities.Constants

class GameManager {
    var isGameOver : Boolean = false
    var isPlayer1Turn : Boolean = true

    fun playerWin(positions: MutableList<Position>, lastPosition: Position) : Boolean
    {
        // check on horizontal lines
        val verticalLine = positions.filter { it.row == lastPosition.row }
        if(verticalLine.size == Constants.boardSize)
            return true

        // check on vertical lines
        val horizontalLine = positions.filter { it.column == lastPosition.column }
        if(horizontalLine.size == Constants.boardSize)
            return true

        // check on down diagonal
        if(lastPosition.row == lastPosition.column)
        {
            val lineDiagonal = positions.filter { it.row == it.column}
            if(lineDiagonal.size == Constants.boardSize)
                return true
        }

        return false
    }
}