package com.example.tictactoe.repositories.board

import com.example.tictactoe.models.Square
import com.example.tictactoe.models.Position

interface IBoardRepository {
    fun getSquare(position : Position) : Square?

    fun getAllSquares() : Array<Array<Square>>

    //fun updateCell(square : Square, isPlayer1Turn: Boolean)

    fun refreshSquares()
}