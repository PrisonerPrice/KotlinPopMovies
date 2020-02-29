package com.prisonerprice.kotlinpopmovies.Networking

import android.util.Log
import com.prisonerprice.kotlinpopmovies.Database.Movie
import com.prisonerprice.kotlinpopmovies.Utils.ConstantVars
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

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
        
    }
}