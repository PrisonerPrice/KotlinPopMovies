package com.prisonerprice.kotlinpopmovies.Utils

object ConstantVars {

    val STATE_POP = 42
    val STATE_HIGH = 41
    val STATE_FAV = 40

    // 1 day, for the ease of testing
    val EXPIRATION_TIME = 1000L * 60 * 1440

    /*
    Network Constants
     */
    val API_KEY = "You need to use your own"

    val GET_MOST_POPULAR_MOVIES = "https://api.themoviedb.org/3/movie/popular?api_key=" +
            API_KEY + "&language=en-US&page=1"

    val GET_TOP_RATED_MOVIES = "https://api.themoviedb.org/3/movie/top_rated?api_key=" +
            API_KEY + "&language=en-US&page=1"

    val GET_FAVORITE_MOVIES = "get_favorite_movies"

    val GET_VIDEOS = "https://api.themoviedb.org/3/movie/" + " " + "/videos?api_key=" +
            API_KEY

    val GET_COMMENTS = "https://api.themoviedb.org/3/movie/" + " " + "/reviews?api_key=" +
            API_KEY

    val VIDEO_PREFIX = "https://www.youtube.com/watch?v="
}
