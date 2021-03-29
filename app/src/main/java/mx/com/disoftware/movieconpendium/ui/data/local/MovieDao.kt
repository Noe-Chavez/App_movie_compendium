package mx.com.disoftware.movieconpendium.ui.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mx.com.disoftware.movieconpendium.ui.data.model.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies(): List<MovieEntity>

    /**
     * A la hora de guardar las peliculas pueden haber inconistencias, podemos guardar la misma pelicula
     * varias veces por lo que ocacionaria inconsistencia en la DB, para ello itilizamos la estrategia
     * Remplace, la cual consiste en que si una pelicula con el mismo id ya existe, entonces se sobre
     * escribe (actualiza).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

}