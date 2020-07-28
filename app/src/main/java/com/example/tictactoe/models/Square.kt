package com.example.tictactoe.models

data class Square(var position: Position, var state: SquareState = SquareState.EMPTY) {
}