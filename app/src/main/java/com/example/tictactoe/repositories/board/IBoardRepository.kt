package com.example.tictactoe.repositories.board

import com.example.tictactoe.models.Cell
import com.example.tictactoe.models.Position

interface IBoardRepository {
    fun getCell(position : Position) : Cell?

    fun getAllCells() : Array<Array<Cell>>

    fun updateCell(cell : Cell, isPlayer1Turn: Boolean)

    fun refreshCells()
}