package com.example.tictactoe

import androidx.lifecycle.ViewModel
import com.example.tictactoe.repositories.board.BoardRepository

class MainActivityViewModel : ViewModel() {

    private var isPlayer1Turn : Boolean = true
    private lateinit var selectedCellValue : String

    fun getSelectedCellValue() : String { return selectedCellValue }

    fun updateSelectedCell(row: Int, column: Int) {
        val selectedCell = BoardRepository.getCell(row, column)
         BoardRepository.updateCell(selectedCell!!, isPlayer1Turn)
         selectedCellValue = selectedCell.status.toString()
        isPlayer1Turn = !isPlayer1Turn
    }
}