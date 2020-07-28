package com.example.tictactoe

import android.app.Application
import com.example.tictactoe.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TicTacToeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TicTacToeApplication)
            modules(
                listOf(
                    appModule
                )
            )
        }
    }
}