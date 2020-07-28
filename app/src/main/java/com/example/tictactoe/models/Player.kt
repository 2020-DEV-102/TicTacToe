package com.example.tictactoe.models

data class Player(val symbol: Symbol = Symbol.X, var positions: MutableList<Position> = arrayListOf()) {
}