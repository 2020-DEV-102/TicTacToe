package com.example.tictactoe

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.tictactoe.models.Position
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.managers.game.TicTacToeGameManager
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class TicTacToeTest : FunSpec( {

    fun enableMutableLiveDataComponent() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean = true
        })
    }

    fun getViewModel() : MainActivityViewModel
    {
        val boardRepository = BoardRepository()
        return MainActivityViewModel(boardRepository)
    }

    test("X always goes first") {
        enableMutableLiveDataComponent()
        forAll(
            row(Position(0,0)),
            row(Position(0,1)),
            row(Position(2,1))
        ) { position ->
            val viewModel = getViewModel()
            viewModel.updateGame(position)
            viewModel.getSelectedSquareValue() shouldBe ("X")
        }
    }

    test("cannot play on a played position") {
        enableMutableLiveDataComponent()

        forAll(
            row(Position(0,0)),
            row(Position(0,1)),
            row(Position(2,1))
        ) { position ->
            val viewModel = getViewModel()
            viewModel.updateGame(position)
            viewModel.canUpdateSelectedSquare(position) shouldBe (false)
        }
    }

    test("draw game if all squares are filled") {
        forAll(
            row(0, false),
            row(2, false),
            row(9, true)
        ) { turnNumber, isADraw ->
            val gameManager = TicTacToeGameManager()
            gameManager.turnNumber = turnNumber
            gameManager.isADraw() shouldBe isADraw
        }
    }

    test("3 in a row wins") {
        forAll(
            // The player filled the first row
            row (arrayListOf(Position(0,0), Position (0,1), Position(0,2))),
            // The player filled the second row
            row (arrayListOf(Position(1,0), Position (1,1), Position(1,2))),
            // The player filled the third row
            row (arrayListOf(Position(2,0), Position (2,1), Position(2,2)))
        ) { playerPositions ->
            val gameManager = TicTacToeGameManager()
            gameManager.playerWin(playerPositions, playerPositions[0]) shouldBe true
        }
    }

    test("3 in a column wins") {
        forAll(
            // The player filled the first column
            row(arrayListOf (Position(0,0), Position (1,0), Position(2,0))),
            // The player filled the second column
            row(arrayListOf (Position(0,1), Position (1,1), Position(2,1))),
            // The player filled the third column
            row(arrayListOf (Position(0,2), Position (1,2), Position(2,2)))
        ) { playerPositions ->
            val gameManager = TicTacToeGameManager()
            gameManager.playerWin(playerPositions, playerPositions[0]) shouldBe true
        }
    }

    test("3 in diagonal wins") {
        forAll(
            // The player filled the diagonal \
            row(arrayListOf (Position(0,0), Position (1,1), Position(2,2))),
            // The player filled the diagonal /
            row(arrayListOf (Position(2,0), Position (1,1), Position(0,2)))
        ) { playerPositions ->
            val gameManager = TicTacToeGameManager()
            gameManager.playerWin(playerPositions, playerPositions[0]) shouldBe true
        }
    }
})