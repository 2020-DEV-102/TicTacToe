package com.example.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoe.models.*
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.TicTacToeGameManager

class MainActivityViewModel constructor(private val boardRepository: BoardRepository ) : ViewModel(){

    companion object {
        private const val player1TurnText = "Player 1 turn"
        private const val player2TurnText = "Player 2 turn"
        private const val isAWinText = "It's a win"
        private const val isADraw = "It's a draw"
    }

    private val ticTacToeGameManager  = TicTacToeGameManager()

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
        if(!ticTacToeGameManager.isGameOver) {
            ticTacToeGameManager.playingPlayer = if(ticTacToeGameManager.playingPlayer == ticTacToeGameManager.players[0]) ticTacToeGameManager.players[1] else ticTacToeGameManager.players[0]
            increaseRoundCount()
            updatePlayerTurnText()
        }
    }

    fun resetGame()
    {
        ticTacToeGameManager.restartGame()
        boardRepository.cleanBoard()
        _gameStatusText.postValue(player1TurnText)
    }

    fun canUpdateSelectedSquare(position: Position) : Boolean
    {
        if(ticTacToeGameManager.isGameOver) {
            return false
        }
        return boardRepository.getSquare(position)!!.isFree
    }

    private fun updateSelectedSquare(position: Position) {
        boardRepository.updateSquare(position, false)
        selectedPlayerSymbol = ticTacToeGameManager.playingPlayer.symbol.toString()
    }

    private fun updatePlayerPositions(position: Position)
    {
        ticTacToeGameManager.playingPlayer.positions.add(position)
    }

    private fun checkForWin(lastPositionPlayed: Position)
    {
        if(ticTacToeGameManager.playerWin(ticTacToeGameManager.playingPlayer.positions, lastPositionPlayed))
            endGame(isAWinText)
    }

    private fun checkForDraw() {
        if(ticTacToeGameManager.isADraw())
            endGame(isADraw)
    }

    private fun updatePlayerTurnText()
    {
        when(ticTacToeGameManager.playingPlayer){
            ticTacToeGameManager.players[0] -> _gameStatusText.postValue(player1TurnText)
            ticTacToeGameManager.players[1] -> _gameStatusText.postValue(player2TurnText)
        }
    }

    private fun increaseRoundCount() {
        ticTacToeGameManager.turnNumber++
    }

    private fun endGame(endText : String)
    {
        _gameStatusText.postValue(endText)
        ticTacToeGameManager.isGameOver = true
    }
}