package mx.com.disoftware.movieconpendium.data.model
/*
    Se utiliza para guardar la info en RAM.
* */
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