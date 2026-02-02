package com.example.electronicazytron.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CameraUtils {

    fun createTempImageFile(context: Context): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = context.getExternalFilesDir("images")
        if (storageDir != null && !storageDir.exists()) {
            storageDir.mkdirs()
        }
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    fun getUriForFile(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    /**
     * Simula la subida de una imagen a la nube.
     * Si no hay red (simulado), retorna la ruta local.
     */
    suspend fun uploadImageSimulated(file: File): String {
        // Simulamos un retraso de red
        kotlinx.coroutines.delay(1000)
        
        val isNetworkAvailable = true // Simulación de chequeo de red
        
        return if (isNetworkAvailable) {
            // Retornamos una URL ficticia
            "https://zytron-cloud.com/images/${file.name}"
        } else {
            "file://${file.absolutePath}"
        }
    }
}
