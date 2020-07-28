package com.example.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tictactoe.models.Cell
import com.example.tictactoe.models.CellStatus
import com.example.tictactoe.models.Position
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.GameManager
import com.example.tictactoe.utilities.Constants
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock

class TicTacToeTest {
    private val cells = Array(Constants.boardSize) { Array(Constants.boardSize){Cell(Position(0,0))} }

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
        val cell = Cell(Position(0, 0), CellStatus.EMPTY)
        boardRepository.updateCell(cell, false)
        Assert.assertEquals(CellStatus.O, cell.status)
    }

    @Test
    fun cannot_play_on_a_X_played_position()
    {
        every { boardRepository.getCell(Position(0,0))!!.status } returns CellStatus.X
        val canUpdate = viewModel.canUpdateSelectedCell(Position(0,0))
        verify { boardRepository.getCell(Position(0,0)) }
        Assert.assertEquals(false, canUpdate)
    }

    @Test
    fun cannot_play_on_a_O_played_position()
    {
        every { boardRepository.getCell(Position(0,0))!!.status } returns CellStatus.O
        val canUpdate = viewModel.canUpdateSelectedCell(Position(0,0))
        verify { boardRepository.getCell(Position(0,0)) }
        Assert.assertEquals(false, canUpdate)
    }

    @Test
    fun three_in_a_row_wins()
    {
        cells[0].forEach { it.status = CellStatus.O }
        val isAWin = gameManager.isAWin(Cell(Position(0,0), CellStatus.O), cells)
        Assert.assertEquals(true, isAWin)
    }
}