package com.example.tictactoe.repositories.board

import com.example.tictactoe.models.Cell
import com.example.tictactoe.models.CellStatus
import com.example.tictactoe.models.Position
import com.example.tictactoe.utilities.Constants

class BoardRepository : IBoardRepository {
    private val cells = Array(Constants.boardSize) { arrayOfNulls<Cell>(Constants.boardSize) }

    init
    {
        for (i in 0 until Constants.boardSize) {
            for (j in 0 until Constants.boardSize) {
                cells[i][j] = Cell(Position(i,j))
            }
        }
    }

    override fun getCell(position: Position): Cell? {
        return cells[position.row][position.column]
    }

    override fun getAllCells(): List<Cell?> {
        return cells.flatten()
    }

    override fun updateCell(cell: Cell, isPlayer1Turn: Boolean) {
        when(isPlayer1Turn){
            true -> cell.status = CellStatus.X
            false -> cell.status = CellStatus.O
        }
    }

    override fun refreshCells() {
        for (i in 0 until Constants.boardSize) {
            for (j in 0 until Constants.boardSize) {
                cells[i][j]!!.status = CellStatus.EMPTY
            }
        }
    }
}