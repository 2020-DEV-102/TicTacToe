package com.example.tictactoe.models

data class Cell(var position: Position, var status: CellStatus = CellStatus.EMPTY) {
}