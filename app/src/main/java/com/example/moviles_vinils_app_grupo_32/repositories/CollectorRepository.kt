package com.example.moviles_vinils_app_grupo_32.repositories

import android.app.Application
import android.util.Log
import com.example.moviles_vinils_app_grupo_32.models.Collector
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class CollectorRepository (val application: Application) {
    suspend fun refreshData(): List<Collector> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getListFromCache("Collectors")
        if(potentialResp.isEmpty()){
            Log.d("Cache decision", "get from network")
            var collectors = NetworkServiceAdapter.getInstance(application).getCollectors()
            CacheManager.getInstance(application.applicationContext).addListToCache("Collectors", collectors)
            return collectors
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp as List<Collector>
        }

    }
}