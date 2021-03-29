package mx.com.disoftware.movieconpendium.ui.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    Se utiliza para guardar la info en RAM.
* */
// Retrofit2
data class Movie(
    val id: Int = -1,
    val adult: Boolean = false,
    val genre_ids: List<Int> = listOf(),
    val backdrop_path: String = "not path",
    val original_title: String = "no title established",
    val original_language: String = "no language established",
    val overview: String = "no overview established",
    val popularity: Double = -0.0,
    val poster_path: String = "no image",
    val release_date: String = "0000-00-00",
    val title: String = "not name",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1
)

data class MovieList(val results: List<Movie> = listOf())

// ROOM
@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "not path",
    @ColumnInfo(name = "original_title")
    val original_title: String = "no title established",
    @ColumnInfo(name = "original_language")
    val original_language: String = "no language established",
    @ColumnInfo(name = "overview")
    val overview: String = "no overview established",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -0.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "no image",
    @ColumnInfo(name = "release_date")
    val release_date: String = "0000-00-00",
    @ColumnInfo(name = "title")
    val title: String = "not name",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = -1
)