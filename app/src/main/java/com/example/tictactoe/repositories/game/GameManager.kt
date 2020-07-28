package com.example.tictactoe.repositories.game

import com.example.tictactoe.models.Cell
import com.example.tictactoe.models.CellStatus

class GameManager {
    var isGameOver : Boolean = false
    var isPlayer1Turn : Boolean = true

    fun isAWin(cell: Cell, cells: Array<Array<Cell>>) : Boolean
    {
        val lineVertical = cells[cell.position.row]

        if (lineVertical.all { it!!.status == CellStatus.X } || lineVertical.all { it!!.status == CellStatus.O })
            return true

        return false
    }
}