package com.example.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoe.models.*
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.managers.game.TicTacToeGameManager
import com.example.tictactoe.utilities.Constants.Companion.isADraw
import com.example.tictactoe.utilities.Constants.Companion.isAWinText
import com.example.tictactoe.utilities.Constants.Companion.player1TurnText
import com.example.tictactoe.utilities.Constants.Companion.player2TurnText

class MainActivityViewModel constructor(private val boardRepository: BoardRepository) : ViewModel(){

    private val ticTacToeGameManager  = TicTacToeGameManager()

    private val _gameStatusText = MutableLiveData<String>().apply {
        postValue(player1TurnText)
    }
    val gameStatusText: LiveData<String> = _gameStatusText

    private var selectedSquareSymbol : String = ""
    fun getSelectedSquareValue() : String { return selectedSquareSymbol}

    fun canUpdateSelectedSquare(position: Position) : Boolean
    {
        // If the game is over, we can't play anymore
        if(ticTacToeGameManager.isGameOver) return false

        // If the selected square is free, we can update it
        return boardRepository.getSquare(position)!!.isFree
    }

    fun updateGame(position: Position)
    {
        updateSelectedSquare(position)
        updatePlayerPositions(position)
        checkForWin(position)
        checkForDraw()
        if(!ticTacToeGameManager.isGameOver) {
            changePlayingPlayer()
            increaseTurnCount()
            updateGameStatusText()
        }
    }

    private fun updateSelectedSquare(position: Position) {
        boardRepository.updateSquare(position, false)
        // The symbol written on the button will be the symbol of the playing player (X or O)
        selectedSquareSymbol = ticTacToeGameManager.playingPlayer.symbol.toString()
    }

    private fun updatePlayerPositions(position: Position) {
        // We add the selected square position to the list of the player's positions
        ticTacToeGameManager.playingPlayer.positions.add(position)
    }

    private fun checkForWin(lastPositionPlayed: Position) {
        if(ticTacToeGameManager.playerWin(ticTacToeGameManager.playingPlayer.positions, lastPositionPlayed))
            endGame(isAWinText)
    }

    private fun checkForDraw() {
        if(ticTacToeGameManager.isADraw())
            endGame(isADraw)
    }

    private fun changePlayingPlayer() {
        // If the player 1 was playing it's now player 2 turn and vice versa
        ticTacToeGameManager.playingPlayer = if(ticTacToeGameManager.playingPlayer == ticTacToeGameManager.players[0])
                                                    ticTacToeGameManager.players[1]
                                            else ticTacToeGameManager.players[0]
    }

    private fun increaseTurnCount() {
        ticTacToeGameManager.turnNumber++
    }

    private fun updateGameStatusText() {
        when(ticTacToeGameManager.playingPlayer){
            ticTacToeGameManager.players[0] -> _gameStatusText.postValue(player1TurnText)
            ticTacToeGameManager.players[1] -> _gameStatusText.postValue(player2TurnText)
        }
    }

    private fun endGame(endText : String) {
        _gameStatusText.postValue(endText)
        ticTacToeGameManager.isGameOver = true
    }

    fun resetGame() {
        ticTacToeGameManager.restartGame()
        boardRepository.cleanBoard()
        _gameStatusText.postValue(player1TurnText)
    }
}