package com.example.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tictactoe.models.Square
import com.example.tictactoe.models.SquareState
import com.example.tictactoe.models.Position
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.GameManager
import com.example.tictactoe.utilities.Constants
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.SpyK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class TicTacToeTest {
    private val cells = Array(Constants.boardSize) { Array(Constants.boardSize){Square(Position(0,0))} }

    @SpyK
    private var boardRepository : BoardRepository = BoardRepository()

    @Mock
    private var gameManager: GameManager = GameManager()

    @InjectMockKs
    private lateinit var viewModel: MainActivityViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        for (i in 0 until Constants.boardSize) {
            for (j in 0 until Constants.boardSize) {
                cells[i][j].position = Position(i,j)
            }
        }
        viewModel = MainActivityViewModel(gameManager, boardRepository)
    }

    @Test
    fun when_player2_play_return_an_O() {
        /*val cell = Square(Position(0, 0), SquareState.EMPTY)
        boardRepository.updateCell(cell, false)
        Assert.assertEquals(SquareState.O, cell.state)*/
    }

    @Test
    fun cannot_play_on_a_X_played_position()
    {
        /*every { boardRepository.getSquare(Position(0,0))!!.state } returns SquareState.X
        val canUpdate = viewModel.canUpdateSelectedSquare(Position(0,0))
        verify { boardRepository.getSquare(Position(0,0)) }
        Assert.assertEquals(false, canUpdate)*/
    }

    @Test
    fun cannot_play_on_a_O_played_position()
    {
        /*every { boardRepository.getSquare(Position(0,0))!!.state } returns SquareState.O
        val canUpdate = viewModel.canUpdateSelectedSquare(Position(0,0))
        verify { boardRepository.getSquare(Position(0,0)) }
        Assert.assertEquals(false, canUpdate)*/
    }

    @Test
    fun three_in_a_row_wins()
    {
        /*cells[0].forEach { it.state = SquareState.O }
        val isAWin = gameManager.isAWin(Square(Position(0,0), SquareState.O), cells)
        Assert.assertEquals(true, isAWin)*/
    }
}