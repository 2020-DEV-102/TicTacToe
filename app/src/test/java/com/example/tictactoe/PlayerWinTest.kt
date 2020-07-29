package com.example.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tictactoe.models.*
import com.example.tictactoe.repositories.game.GameManager
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class PlayerWinTest(private val positions: MutableList<Position>) {

    private var gameManager: GameManager = GameManager()

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
            arrayListOf (Position(0,0), Position (1,0), Position(2,0)),
            // The player filled the second column
            arrayListOf (Position(0,1), Position (1,1), Position(2,1)),
            // The player filled the third column
            arrayListOf (Position(0,2), Position (1,2), Position(2,2)),

            // The player filled the diagonal \
            arrayListOf (Position(0,0), Position (1,1), Position(2,2)),

            // The player filled the diagonal /
            arrayListOf (Position(2,0), Position (1,1), Position(0,2))
        )
    }

    @Test
    fun playerWins() {
        val isAWin = gameManager.playerWin(positions, positions[0])
        Assert.assertEquals(true, isAWin)
    }
}