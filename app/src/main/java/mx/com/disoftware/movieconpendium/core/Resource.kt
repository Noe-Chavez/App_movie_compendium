package mx.com.disoftware.movieconpendium.core

import java.lang.Exception

/**
 * Esta clase contiene los tres estados que se deben verificar al pasar por una petición de datos
 * al servidor remoto (en este caso una API), e primero es para indicar que se está trayendo la
 * información del servidor remoto. La segunda es para avisar que ya se han traido toda la info.
 * Y por último un estado en caso de que fallé la peticón.
 */
sealed class Resource<out T> {
    /**
     * Regresa un valor vacio ya que no se mete nada por constructor. Pero si se establece un
     * dato generico por un en algún futuro se requiere de ingresar datos al constructor.
     */

    class Loanding<out T>: Resource<T>()
    /**
     * como se tiene que retornar un valor se establece como data class, se pasa un parámetro
     * al constructor de tipo genético y regresamos Resource de success.
     */
    data class Success<out T>(val data: T): Resource<T>()

    /**
     * Pasamos al constructor una exception generica que se lanzará en caso de que no se pueda
     * ontener la infromación del servidor. Y se regresa la exception principal que se arrojaria.
     */
    data class Failure(val exception: Exception): Resource<Nothing>()
}