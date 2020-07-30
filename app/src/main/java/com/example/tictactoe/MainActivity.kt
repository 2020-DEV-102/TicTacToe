package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.models.Position
import com.example.tictactoe.utilities.Constants.Companion.boardSize
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val buttons = Array(boardSize) { arrayOfNulls<Button>(boardSize) }
    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBoard()
        initObservers()
        initListeners()
    }

    private fun initBoard()
    {
        // We put every buttons of the layout in side a 2D array of buttons
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                val buttonId = "button_$i$j"
                val resourceId = resources.getIdentifier(buttonId, "id", packageName)
                buttons[i][j] = findViewById(resourceId)

                val position = Position(i,j)
                // Every time we click on a button we send its position in the 2D array to the viewModel
                buttons[i][j]?.setOnClickListener {
                    if(mainActivityViewModel.canUpdateSelectedSquare(position)) {
                        mainActivityViewModel.updateGame(position)
                        buttons[i][j]?.text = mainActivityViewModel.getSelectedSquareValue()
                    }
                }
            }
        }
    }

    private fun initObservers()
    {
        mainActivityViewModel.gameStatusText.observe(this, Observer {
            binding.textViewGameStatus.text = it
        })
    }

    private fun initListeners()
    {
        binding.buttonRetry.setOnClickListener {
            mainActivityViewModel.resetGame()
            for (i in 0 until boardSize) {
                for (j in 0 until boardSize) {
                    buttons[i][j]!!.text = ""
                }
            }
        }
    }
}