package com.example.tictactoe

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.tictactoe.models.Position
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.GameManager
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.junit.*

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
        val gameManager = GameManager()
        return MainActivityViewModel(gameManager, boardRepository)
    }

    test("X always goes first") {
        enableMutableLiveDataComponent()
        forAll(
            row(Position(0,0)),
            row(Position(0,1))
        ) { position ->
            val viewModel = getViewModel()
            viewModel.updateGame(position)
            viewModel.getSelectedSquareValue() shouldBe ("X")
        }
    }

    test("when player2 plays return an O") {
        enableMutableLiveDataComponent()
        forAll(
            row(Position(0,0)),
            row(Position(0,1))
        ) { position ->
            val boardRepository = BoardRepository()
            val gameManager = GameManager()
            val viewModel = MainActivityViewModel(gameManager, boardRepository)
            gameManager.playingPlayer = gameManager.players[1]
            viewModel.updateGame(position)
            viewModel.getSelectedSquareValue() shouldBe ("O")
        }
    }

    test("cannot play on a played position") {
        enableMutableLiveDataComponent()

        forAll(
            row(Position(0,0)),
            row(Position(0,1))
        ) { position ->
            val viewModel = getViewModel()
            viewModel.updateGame(position)
            viewModel.canUpdateSelectedSquare(position) shouldBe (false)
        }
    }

    test("draw game if all squares are filled") {
        enableMutableLiveDataComponent()
        forAll(
            row(0, false),
            row(2, false),
            row(9, true)
        ) { roundCount, isADraw ->
            val gameManager = GameManager()
            gameManager.roundCount = roundCount
            gameManager.isADraw() shouldBe isADraw
        }
    }
})