package com.example.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tictactoe.models.*
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.GameManager
import com.example.tictactoe.utilities.Constants
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock

@RunWith(Parameterized::class)
class PlayerWinTest(private val positions: MutableList<Position>) {

    @Mock
    private var gameManager: GameManager = GameManager()

    @Mock
    private var boardRepository: BoardRepository = BoardRepository()

    @InjectMockKs
    private lateinit var viewModel: MainActivityViewModel

    var row1 = arrayListOf (Position(0,0), Position (0,1), Position(0,2))

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}->{0}")
        fun data() = listOf(
            // The player filled the first row
            arrayListOf (Position(0,0), Position (0,1), Position(0,2)),
            // The player filled the second row
            arrayListOf (Position(1,0), Position (1,1), Position(1,2)),
            // The player filled the third row
            arrayListOf (Position(2,0), Position (2,1), Position(2,2)),

            // The player filled the first column
            arrayListOf (Position(0,0), Position (1,0), Position(2,0))
        )
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainActivityViewModel(gameManager, boardRepository)
    }

    @Test
    fun playerWins() {
        val isAWin = gameManager.playerWin(positions, positions.first())
        Assert.assertEquals(true, isAWin)
    }
}