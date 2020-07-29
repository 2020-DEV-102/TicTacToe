package com.example.tictactoe.utilities

import kotlin.math.pow

object Operations {
    fun Int.pow(value : Int) : Int {
        // Why is there no power function for an Int ?????
        // See test -> OperationsTest
        return this.toDouble().pow(value).toInt()
    }
}