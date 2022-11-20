package com.example.moviles_vinils_app_grupo_32.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.moviles_vinils_app_grupo_32.models.Collector
import com.example.moviles_vinils_app_grupo_32.models.CollectorAlbum
import com.example.moviles_vinils_app_grupo_32.repositories.CollectorDetailRepository

class CollectorDetailViewModel (application: Application, collectorId: Int): AndroidViewModel(application){

    private val collectorDetailRepository = CollectorDetailRepository(application)

    private val _collector = MutableLiveData<Collector>()

    private val _collectorAlbums = MutableLiveData<List<CollectorAlbum>>()

    val collector: LiveData<Collector>
        get() = _collector

    val albums: LiveData<List<CollectorAlbum>>
        get() = _collectorAlbums

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = collectorId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        collectorDetailRepository.refreshData(id, {
            _collector.postValue(it)
            _collectorAlbums.postValue(it.albums)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory (val app: Application, private val collectorId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectorDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorDetailViewModel(app, collectorId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}