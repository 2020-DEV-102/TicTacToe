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

    private var playingPlayer = player1

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
        checkForWin(position)
        if(!gameManager.isGameOver) {
            playingPlayer = if(playingPlayer == player1) player2 else player1
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

    private fun updateSelectedSquare(position: Position) {
        boardRepository.getSquare(position)!!.state = SquareState.FULL
        selectedSymbol = playingPlayer.symbol.toString()
    }

    private fun updatePlayerPositions(position: Position)
    {
        playingPlayer.positions.add(position)
    }

    private fun checkForWin(lastPositionPlayed: Position)
    {
        if(gameManager.playerWin(playingPlayer.positions, lastPositionPlayed))
        {
            endGame()
        }
    }

    private fun updatePlayerTurnText()
    {
        when(playingPlayer){
            player1 -> _gameStatusText.postValue(player1TurnText)
            player2 -> _gameStatusText.postValue(player2TurnText)
        }
    }

    private fun endGame()
    {
        _gameStatusText.postValue(isAWinText)
        gameManager.isGameOver = true
    }
}