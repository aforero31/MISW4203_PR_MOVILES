package com.example.moviles_vinils_app_grupo_32.repositories

import android.app.Application
import com.example.moviles_vinils_app_grupo_32.models.Album
import com.example.moviles_vinils_app_grupo_32.models.Track
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class AlbumRepository (val application: Application) {
    suspend fun refreshData(): List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbums()
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