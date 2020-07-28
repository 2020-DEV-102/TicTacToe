package com.example.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoe.models.Cell
import com.example.tictactoe.models.CellStatus
import com.example.tictactoe.models.Position
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.GameManager

class MainActivityViewModel constructor(private val gameManager: GameManager, private val boardRepository: BoardRepository ) : ViewModel(){

    companion object {
        private const val player1TurnText = "Player 1 turn"
        private const val player2TurnText = "Player 2 turn"
        private const val isAWinText = "It's a win"
    }

    var selectedCell : Cell? = null

    private val _gameStatusText = MutableLiveData<String>().apply {
        postValue(player1TurnText)
    }
    val gameStatusText: LiveData<String> = _gameStatusText


    fun getSelectedCellValue() : String { return selectedCell!!.status.toString() }

    fun canUpdateSelectedCell(position: Position) : Boolean
    {
        selectedCell = boardRepository.getCell(position)
        if(gameManager.isGameOver)
        {
            return false
        }
        return selectedCell?.status == CellStatus.EMPTY
    }

    fun updateSelectedCell() {
        val isPlayerOneTurn = gameManager.isPlayer1Turn
        boardRepository.updateCell(selectedCell!!, isPlayerOneTurn)
        gameManager.isPlayer1Turn = !isPlayerOneTurn
        updatePlayerTurnText()
    }

    fun checkForWin()
    {
        if(gameManager.isAWin(selectedCell!!, boardRepository.getAllCells()))
        {
            endGame()
        }
    }

    private fun updatePlayerTurnText()
    {
        when(gameManager.isPlayer1Turn){
            true -> _gameStatusText.postValue(player1TurnText)
            false -> _gameStatusText.postValue(player2TurnText)
        }
    }

    private fun endGame()
    {
        _gameStatusText.postValue(isAWinText)
        gameManager.isGameOver = true
    }
}