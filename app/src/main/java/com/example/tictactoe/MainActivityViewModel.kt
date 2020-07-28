package com.example.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoe.models.*
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.GameManager

class MainActivityViewModel constructor(private val gameManager: GameManager, private val boardRepository: BoardRepository ) : ViewModel(){

    companion object {
        private const val player1TurnText = "Player 1 turn"
        private const val player2TurnText = "Player 2 turn"
        private const val isAWinText = "It's a win"
    }

    private val player1 = Player(Symbol.X)
    private val player2 = Player(Symbol.O)

    private var selectedSymbol : String? = ""

    private val _gameStatusText = MutableLiveData<String>().apply {
        postValue(player1TurnText)
    }
    val gameStatusText: LiveData<String> = _gameStatusText

    fun getSelectedSquareValue() : String { return selectedSymbol!!}

    fun updateGame(position: Position)
    {
        updateSelectedSquare(position)
        updatePlayerPositions(position)
        checkForWin()
        if(!gameManager.isGameOver) {
            gameManager.isPlayer1Turn = !gameManager.isPlayer1Turn
            updatePlayerTurnText()
        }
    }

    fun canUpdateSelectedSquare(position: Position) : Boolean
    {
        if(gameManager.isGameOver)
        {
            return false
        }
        return boardRepository.getSquare(position)!!.state == SquareState.EMPTY
    }

    fun updateSelectedSquare(position: Position) {
        boardRepository.getSquare(position)!!.state = SquareState.FULL
        selectedSymbol = when(gameManager.isPlayer1Turn) {
            true -> {
                Symbol.X.toString()
            }
            false -> {
                Symbol.O.toString()
            }
        }
    }

    fun updatePlayerPositions(position: Position)
    {
        val isPlayerOneTurn = gameManager.isPlayer1Turn
        when(isPlayerOneTurn) {
            true -> {
                player1.positions.add(position)
            }
            false -> {
                player2.positions.add(position)
            }
        }
    }

    fun checkForWin()
    {
        /*if(gameManager.isAWin(selectedSquare!!, boardRepository.getAllSquares()))
        {
            endGame()
        }*/
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