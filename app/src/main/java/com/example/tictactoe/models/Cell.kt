package com.example.tictactoe.models

data class Cell(val position: Position, var status: CellStatus = CellStatus.EMPTY) {
}