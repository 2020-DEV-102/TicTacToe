package com.example.tictactoe

import com.example.tictactoe.models.Cell
import com.example.tictactoe.models.CellStatus
import com.example.tictactoe.models.Position
import com.example.tictactoe.modules.appModule
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.GameManager
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin

class TicTacToeTest {

    @SpyK
    private var boardRepository : BoardRepository = BoardRepository()

    @RelaxedMockK
    private lateinit var gameManager: GameManager

    @InjectMockKs
    private lateinit var viewModel: MainActivityViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
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
}