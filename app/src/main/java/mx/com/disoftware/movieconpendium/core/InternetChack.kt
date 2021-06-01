package mx.com.disoftware.movieconpendium.core

import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object InternetChack {
    suspend fun isNetworkAvailable() = coroutineScope {
        return@coroutineScope try {
            // Validar si hay internet (emulamos un ping a 8.8.8.8)
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(socketAddress, 2000)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}