package com.example.tictactoe.models

data class Cell(val row : Int, val column : Int, var status: CellStatus = CellStatus.EMPTY) {
}