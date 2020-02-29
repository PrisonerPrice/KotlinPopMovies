package com.prisonerprice.kotlinpopmovies.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY updated_at")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE is_popular = 1 ORDER BY updated_at DESC")
    fun getPopularMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE is_highly_ranked = 1 ORDER BY updated_at DESC")
    fun getHighlyRankedMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE is_liked = 1 ORDER BY updated_at DESC")
    fun getFavoriteMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE is_popular = 1 ORDER BY updated_at DESC")
    fun getLivePopularMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE is_highly_ranked = 1")
    fun getLiveHighlyRankedMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE is_liked = 1 ORDER BY updated_at DESC")
    fun getLiveFavoriteMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): Movie

    @Insert
    fun insertMovie(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movie)

    @Query("DELETE FROM movie")
    fun deleteMovie()

}
