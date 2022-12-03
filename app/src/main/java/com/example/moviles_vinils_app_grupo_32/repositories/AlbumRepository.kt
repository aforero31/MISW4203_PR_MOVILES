package com.example.moviles_vinils_app_grupo_32.repositories

import android.app.Application
import android.util.Log
import com.example.moviles_vinils_app_grupo_32.models.Album
import com.example.moviles_vinils_app_grupo_32.models.Track
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class AlbumRepository (val application: Application) {
    suspend fun refreshData(): List<Album> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getListFromCache("Albums")
        if(potentialResp.isEmpty()){
            Log.d("Cache decision", "get from network")
            var albums = NetworkServiceAdapter.getInstance(application).getAlbums()
            CacheManager.getInstance(application.applicationContext).addListToCache("Albums", albums)
            return albums
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp as List<Album>
        }
    }

    suspend fun createAlbum(album: Album): Album {
        return NetworkServiceAdapter.getInstance(application).postAlbum(album)
    }

    suspend fun addTracksToAlbum(albumId: Int, tracks: List<Track>): List<Track>{
        var tracksList = ArrayList<Track>()
        var createdTrack: Track
        for (track in tracks){
            createdTrack = NetworkServiceAdapter.getInstance(application).postTrack(albumId, track)
            tracksList.add(createdTrack)
        }
        return tracksList
    }
}