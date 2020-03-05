package com.prisonerprice.kotlinpopmovies.ViewModel

import androidx.lifecycle.ViewModel
import com.prisonerprice.kotlinpopmovies.Database.Movie

class DetailScreenViewModel: ViewModel {

    private lateinit var movie: Movie
    private var position: Int = -1
    private var state: Int = -1
    private val dataExchanger = DataExchanger.getInstance()

    private constructor()

    companion object {

        @Volatile
        private var INSTANCE: DetailScreenViewModel? = null

        fun getInstance(): DetailScreenViewModel{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            } else {
                val instance = DetailScreenViewModel()
                INSTANCE = instance
                return instance
            }
        }
    }

    fun setMovie(position: Int) {
        this.position = position
        this.movie = dataExchanger.mainScreenAdapter.getDetailMovie(position)
        this.state = dataExchanger.mainScreenAdapter.getCurrState
    }

    fun getMovie(): Movie {
        return movie
    }

    fun getState(): Int {
        return state
    }

    fun setBackInformation(movie: Movie) {
        if (this.movie.isLiked != movie.isLiked) {
            dataExchanger.updateMovieFavorite(movie)
            dataExchanger.mainScreenAdapter.setMovieFavorite(position, movie)
            MainScreenViewModel.getInstance().notifyDataChanged(movie)
        }
    }
}