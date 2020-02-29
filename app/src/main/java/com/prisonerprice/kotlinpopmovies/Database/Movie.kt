package com.prisonerprice.kotlinpopmovies.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo(name = "poster_url") val posterUrl: String,
    @ColumnInfo(name = "user_rating") val userRating: String,
    @ColumnInfo(name = "release_year") val releaseYear: Int,
    @ColumnInfo val description: String,
    @ColumnInfo val comment: String,
    @ColumnInfo(name = "trailer_url_1") val trailerUrl1: String,
    @ColumnInfo(name = "trailer_url_2") val trailerUrl2: String,
    @ColumnInfo(name = "is_liked") val isLiked: Int,
    @ColumnInfo(name = "is_popular") val isPopular: Int,
    @ColumnInfo(name = "is_highly_ranked") val isHighlyRanked: Int,
    @ColumnInfo(name = "updated_at") val updatedAt: Long
    ) {

    fun encoder(): String {
        var record: String? = null
        record = (id.toString() + "  "
                + title + "  "
                + posterUrl + "  "
                + userRating + "  "
                + releaseYear + "  "
                + description + "  "
                + comment + "  "
                + trailerUrl1 + "  "
                + trailerUrl2 + "  "
                + isLiked + "  "
                + isPopular + "  "
                + isHighlyRanked + "  "
                + updatedAt)
        return record
    }
}
