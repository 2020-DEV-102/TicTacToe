package com.example.tictactoe.repositories.board

import com.example.tictactoe.models.Cell

interface IBoardRepository {
    fun getCell(row: Int, column: Int) : Cell?

    fun getAllCells() : List<Cell?>

    fun updateCell(cell : Cell, isPlayer1Turn: Boolean)

    fun refreshCells()
}