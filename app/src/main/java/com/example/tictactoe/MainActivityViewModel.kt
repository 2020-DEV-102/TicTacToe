package com.example.tictactoe

import androidx.lifecycle.ViewModel
import com.example.tictactoe.models.Cell
import com.example.tictactoe.models.CellStatus
import com.example.tictactoe.models.Position
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.GameManager

class MainActivityViewModel constructor(private val gameManager: GameManager, private val boardRepository: BoardRepository ) : ViewModel(){

    var selectedCell : Cell? = null

    fun getSelectedCellValue() : String { return selectedCell!!.status.toString() }

    fun canUpdateSelectedCell(position: Position) : Boolean
    {
        selectedCell = boardRepository.getCell(position)
        return selectedCell?.status == CellStatus.EMPTY
    }

    fun updateSelectedCell() {
        val isPlayerOneTurn = gameManager.isPlayer1Turn
        boardRepository.updateCell(selectedCell!!, isPlayerOneTurn)
        gameManager.isPlayer1Turn = !isPlayerOneTurn
    }
}