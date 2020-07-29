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
        private const val isADraw = "It's a draw"
    }

    private val _gameStatusText = MutableLiveData<String>().apply {
        postValue(player1TurnText)
    }
    val gameStatusText: LiveData<String> = _gameStatusText

    private var selectedPlayerSymbol : String? = ""
    fun getSelectedSquareValue() : String { return selectedPlayerSymbol!!}

    fun updateGame(position: Position)
    {
        updateSelectedSquare(position)
        updatePlayerPositions(position)
        checkForWin(position)
        checkForDraw()
        if(!gameManager.isGameOver) {
            gameManager.playingPlayer = if(gameManager.playingPlayer == gameManager.players[0]) gameManager.players[1] else gameManager.players[0]
            increaseRoundCount()
            updatePlayerTurnText()
        }
    }

    fun canUpdateSelectedSquare(position: Position) : Boolean
    {
        if(gameManager.isGameOver) {
            return false
        }
        return boardRepository.getSquare(position)!!.state == SquareState.EMPTY
    }

    private fun updateSelectedSquare(position: Position) {
        boardRepository.getSquare(position)!!.state = SquareState.FULL
        selectedPlayerSymbol = gameManager.playingPlayer.symbol.toString()
    }

    private fun updatePlayerPositions(position: Position)
    {
        gameManager.playingPlayer.positions.add(position)
    }

    private fun checkForWin(lastPositionPlayed: Position)
    {
        if(gameManager.playerWin(gameManager.playingPlayer.positions, lastPositionPlayed))
            endGame(isAWinText)
    }

    private fun checkForDraw() {
        if(gameManager.isADraw())
            endGame(isADraw)
    }

    private fun updatePlayerTurnText()
    {
        when(gameManager.playingPlayer){
            gameManager.players[0] -> _gameStatusText.postValue(player1TurnText)
            gameManager.players[1] -> _gameStatusText.postValue(player2TurnText)
        }
    }

    private fun increaseRoundCount() {
        gameManager.roundCount++
    }

    private fun endGame(endText : String)
    {
        _gameStatusText.postValue(endText)
        gameManager.isGameOver = true
    }
}