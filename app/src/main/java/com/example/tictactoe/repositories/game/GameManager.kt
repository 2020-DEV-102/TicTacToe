package com.example.tictactoe.repositories.game

import com.example.tictactoe.models.Square
import com.example.tictactoe.models.SquareState

class GameManager {
    var isGameOver : Boolean = false
    var isPlayer1Turn : Boolean = true

    fun isAWin(square: Square, squares: Array<Array<Square>>) : Boolean
    {
        val lineVertical = squares[square.position.row]

        /*if (lineVertical.all { it!!.state == SquareState.X } || lineVertical.all { it!!.state == SquareState.O })
            return true*/

        return false
    }
}