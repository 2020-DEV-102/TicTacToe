package com.example.tictactoe.repositories.game

import com.example.tictactoe.models.Player
import com.example.tictactoe.models.Position

abstract class GameManager {
    abstract var isGameOver : Boolean
    abstract var players : MutableList<Player>

    abstract fun restartGame()

    abstract fun playerWin(positions: MutableList<Position>, lastPositionPlayed: Position) : Boolean

    abstract fun isADraw() : Boolean
}