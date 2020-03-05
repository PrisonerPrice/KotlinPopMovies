package com.prisonerprice.kotlinpopmovies.ViewModel

import androidx.lifecycle.ViewModel

class MainScreenViewModel: ViewModel{

    private constructor()

    companion object {

        @Volatile
        private var INSTANCE: MainScreenViewModel? = null

        fun getInstance(): MainScreenViewModel{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            } else {
                val instance = MainScreenViewModel()
                INSTANCE = instance
                return instance
            }
        }
    }
}