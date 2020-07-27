package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.utilities.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val buttons = Array(  Constants.boardSize) { arrayOfNulls<Button>(Constants.boardSize) }
    private lateinit var mainActivityViewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        initBoard()
    }

    private fun initBoard()
    {
        for (i in 0 until Constants.boardSize) {
            for (j in 0 until Constants.boardSize) {
                val buttonId = "button_$i$j"
                val resourceId = resources.getIdentifier(buttonId, "id", packageName)
                buttons[i][j] = findViewById(resourceId)

                buttons[i][j]?.setOnClickListener {
                    buttons[i][j]?.text = "X"
                }
            }
        }
    }
}