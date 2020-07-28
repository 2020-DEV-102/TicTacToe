package com.example.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tictactoe.models.Cell
import com.example.tictactoe.models.CellStatus
import com.example.tictactoe.models.Position
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.GameManager
import com.example.tictactoe.utilities.Constants
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.SpyK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock

@RunWith(Parameterized::class)
class PlayerWinTest(private val position: Position) {

    private val cells =
        Array(Constants.boardSize) { Array(Constants.boardSize) { Cell(Position(0, 0)) } }

    @Mock
    private var gameManager: GameManager = GameManager()

    @Mock
    private var boardRepository: BoardRepository = BoardRepository()

    @InjectMockKs
    private lateinit var viewModel: MainActivityViewModel

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            // We can add here all the positions of the last cell clicked we want to test
            Position(0, 0),
            Position(1, 0),
            Position(2, 0)
        )
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        for (i in 0 until Constants.boardSize) {
            for (j in 0 until Constants.boardSize) {
                cells[i][j].position = Position(i, j)
            }
        }
        viewModel = MainActivityViewModel(gameManager, boardRepository)
    }

    @Test
    fun three_in_a_row_wins() {
        cells[position.row].forEach { it.status = CellStatus.O }
        val isAWin = gameManager.isAWin(Cell(position, CellStatus.O), cells)
        Assert.assertEquals(true, isAWin)
    }
}