package com.example.tictactoe.repositories.game

import com.example.tictactoe.models.Player
import com.example.tictactoe.models.Position
import com.example.tictactoe.utilities.Constants

class GameManager {
    var isGameOver : Boolean = false
    var isPlayer1Turn : Boolean = true

    fun playerWin(positions: MutableList<Position>, lastPosition: Position) : Boolean
    {
        val lineVertical = positions.filter { it.row == lastPosition.row }
        if(lineVertical.size == Constants.boardSize)
            return true

        /*if (lineVertical.all { it!!.state == SquareState.X } || lineVertical.all { it!!.state == SquareState.O })
            return true*/

        return false
    }
}