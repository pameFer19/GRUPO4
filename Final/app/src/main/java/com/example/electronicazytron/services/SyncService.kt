package com.example.electronicazytron.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.electronicazytron.utils.NetworkConnection

class SyncService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val networkConnection = NetworkConnection(applicationContext)
        if (networkConnection.isNetworkAvailable()) {
            //logica para la sincronizacion aun falta desarrollar
        }
        return START_NOT_STICKY
    }
}