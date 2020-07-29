package com.example.tictactoe.repositories.board

import com.example.tictactoe.models.Square
import com.example.tictactoe.models.Position

interface IBoardRepository {
    fun getSquare(position : Position) : Square?
    fun updateSquare(position: Position, isFree: Boolean)
    fun cleanBoard()
}