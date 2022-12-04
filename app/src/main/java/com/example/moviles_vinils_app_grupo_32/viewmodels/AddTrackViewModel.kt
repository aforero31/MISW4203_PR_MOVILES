package com.example.moviles_vinils_app_grupo_32.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.moviles_vinils_app_grupo_32.models.Track
import com.example.moviles_vinils_app_grupo_32.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddTrackViewModel(application: Application, albumId: Int) :  AndroidViewModel(application) {

    private val albumsRepository = AlbumRepository(application)

    private var _albumId: Int = albumId

    private var _tracksForNewAlbumLiveData = MutableLiveData<List<Track>?>()
    private var _tracksForNewAlbum = ArrayList<Track>()

    val tracksForNewAlbumLiveData: MutableLiveData<List<Track>?>
        get() = _tracksForNewAlbumLiveData

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    fun addTrackToList(track: Track ){
        _tracksForNewAlbum.add(track)
        _tracksForNewAlbumLiveData.value =_tracksForNewAlbum
    }

    fun saveAllTracks(){
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var newTracks = tracksForNewAlbumLiveData.value?.let {tracksToAdd ->
                        albumsRepository.addTracksToAlbum(_albumId ,
                            tracksToAdd
                        )
                    }
                    _tracksForNewAlbumLiveData.postValue(newTracks)
                }
            }
        }
        catch (e: Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory (val app: Application, val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddTrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddTrackViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
