package com.example.moviles_vinils_app_grupo_32.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.moviles_vinils_app_grupo_32.models.Album
import com.example.moviles_vinils_app_grupo_32.models.Musician
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class MusicianDetailRepository (val application: Application) {
    suspend fun refreshData(musicianId: Int): Musician {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getObjectFromCache("Musician$musicianId")
        if(potentialResp==null){
            Log.d("Cache decision", "get from network")
            var musician = NetworkServiceAdapter.getInstance(application).getMusician(musicianId)
            CacheManager.getInstance(application.applicationContext).addObjectToCache("Musician$musicianId", musician)
            return musician
        }
        else{
            return potentialResp as Musician
        }
    }
}