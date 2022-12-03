package com.example.moviles_vinils_app_grupo_32.repositories

import android.app.Application
import android.util.Log
import com.example.moviles_vinils_app_grupo_32.models.Musician
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class MusicianRepository (val application: Application) {
    suspend fun refreshData(): List<Musician> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getListFromCache("Musicians")
        if(potentialResp.isEmpty()){
            Log.d("Cache decision", "get from network")
            var musicians = NetworkServiceAdapter.getInstance(application).getMusicians()
            CacheManager.getInstance(application.applicationContext).addListToCache("Musicians", musicians)
            return musicians
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp as List<Musician>
        }
    }
}