package com.example.tictactoe.models

data class Player(val symbol : Symbol, var positions : MutableList<Position> = arrayListOf()) {
}