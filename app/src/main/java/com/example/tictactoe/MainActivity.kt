package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.models.Position
import com.example.tictactoe.utilities.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val buttons = Array(  Constants.boardSize) { arrayOfNulls<Button>(Constants.boardSize) }
    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBoard()
        initObservers()
    }

    private fun initBoard()
    {
        for (i in 0 until Constants.boardSize) {
            for (j in 0 until Constants.boardSize) {
                val buttonId = "button_$i$j"
                val resourceId = resources.getIdentifier(buttonId, "id", packageName)
                buttons[i][j] = findViewById(resourceId)

                buttons[i][j]?.setOnClickListener {
                    if(mainActivityViewModel.canUpdateSelectedCell(Position(i,j))) {
                        mainActivityViewModel.updateSelectedCell()
                        mainActivityViewModel.checkForWin()
                        buttons[i][j]?.text = mainActivityViewModel.getSelectedCellValue()
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
}