package com.example.moviles_vinils_app_grupo_32.repositories

import android.app.Application
import android.util.Log
import com.example.moviles_vinils_app_grupo_32.models.Album
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application) {
    suspend fun refreshData(albumId: Int): Album {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getObjectFromCache("Album$albumId")
        if(potentialResp==null){
            Log.d("Cache decision", "get from network")
            var album = NetworkServiceAdapter.getInstance(application).getAlbum(albumId)
            CacheManager.getInstance(application.applicationContext).addObjectToCache("Album$albumId", album)
            return album
        }
        else{
            return potentialResp as Album
        }
    }
}