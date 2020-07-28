package com.example.tictactoe.modules

import com.example.tictactoe.MainActivityViewModel
import com.example.tictactoe.repositories.board.BoardRepository
import com.example.tictactoe.repositories.game.GameManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { GameManager() }

    single { BoardRepository() }

    viewModel { MainActivityViewModel(get(), get()) }
}