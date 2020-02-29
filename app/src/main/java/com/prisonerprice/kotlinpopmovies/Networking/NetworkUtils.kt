package com.prisonerprice.kotlinpopmovies.Networking

import com.prisonerprice.kotlinpopmovies.Database.Movie
import com.prisonerprice.kotlinpopmovies.Utils.ConstantVars
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

object NetworkUtils {

    fun getResponseFromHttpUrl(url: String): String {
        val client: OkHttpClient = OkHttpClient()
        val request: Request = Request.Builder().url(url).build()
        try {
            val response: Response = client.newCall(request).execute()
            return response.body().toString()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            return ""
        }
    }

    fun getComments(id: Int): String {
        val url_GET_COMMENTS = ConstantVars.GET_COMMENTS.replace(" ".toRegex(), id.toString())
        return getResponseFromHttpUrl(url_GET_COMMENTS)
    }

    fun getVideos(id: Int): String {
        val url_GET_VIDEOS = ConstantVars.GET_VIDEOS.replace(" ".toRegex(), id.toString())
        return getResponseFromHttpUrl(url_GET_VIDEOS)
    }

    fun jsonParsing(jsonString: String, query: String): ArrayList<Movie> {
        if (jsonString.isEmpty()) return ArrayList<Movie>()

        val data: ArrayList<Movie> = ArrayList()
        val root: JSONObject = JSONObject(jsonString)
        val movieArray: JSONArray = root.getJSONArray("results")

        for (i in 0..19) {
            val movie = movieArray.getJSONObject(i)

            val posterUrl = "https://image.tmdb.org/t/p/w780" + movie.getString("poster_path")
            val title = movie.getString("title")
            val userRating = movie.getString("vote_average")
            val releaseYear = movie.getString("release_date").substring(0, 4)
            val description = movie.getString("overview")
            val id = movie.getInt("id")

            var comment = ""
            var trailerUrl1 = ""
            var trailerUrl2 = ""

            val movieComments = JSONObject(getComments(id))
            val commentsArray = movieComments.getJSONArray("results")
            try {
                comment = "User comment: " + commentsArray.getJSONObject(0).getString("content")
                    .replace("\n".toRegex(), "").replace("  ".toRegex(), " ")
                if (comment.length > 300) comment = comment.substring(0, 300) + "..."
                comment += "\n" + "by " + commentsArray.getJSONObject(0).getString("author")
            } catch (e: Exception) {
                comment = "No comment yet"
            }

            val movieTrailers = JSONObject(getVideos(id))
            val trailerArray = movieTrailers.getJSONArray("results")
            try {
                trailerUrl1 = ConstantVars.VIDEO_PREFIX + trailerArray.getJSONObject(0).getString("key")
            } catch (e: Exception) {
                trailerUrl1 = "Fail to get the URL"
            }
            try{
                trailerUrl2 = ConstantVars.VIDEO_PREFIX + trailerArray.getJSONObject(1).getString("key");
            } catch (e: Exception){
                trailerUrl2 = "Fail to get the URL";
            }

            var isPopular = 0
            var isHighlyRanked = 0

            if (query.equals(ConstantVars.GET_MOST_POPULAR_MOVIES)) {
                isPopular = 1
                isHighlyRanked = 0
            }

            if (query.equals(ConstantVars.GET_TOP_RATED_MOVIES)) {
                isPopular = 0
                isHighlyRanked = 1
            }

            val record: Movie = Movie(id,
                title,
                posterUrl,
                userRating,
                Integer.parseInt(releaseYear),
                description,
                comment,
                trailerUrl1,
                trailerUrl2,
                0,
                isPopular,
                isHighlyRanked,
                System.currentTimeMillis())
        }
        return data
    }
}