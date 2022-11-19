package com.example.moviles_vinils_app_grupo_32.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.moviles_vinils_app_grupo_32.models.Musician
import com.example.moviles_vinils_app_grupo_32.repositories.MusicianDetailRepository

class MusicianDetailViewModel (application: Application, musicianId: Int): AndroidViewModel(application) {
    private val musicianDetailRepository = MusicianDetailRepository(application)

    private val _musician = MutableLiveData<Musician>()

    val musician: LiveData<Musician>
        get() = _musician

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = musicianId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        musicianDetailRepository.refreshData(id, {
            _musician.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory (val app: Application, val musicianId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MusicianDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app, musicianId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}