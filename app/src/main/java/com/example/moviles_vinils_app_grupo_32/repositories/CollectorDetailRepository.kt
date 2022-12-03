package com.example.moviles_vinils_app_grupo_32.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.moviles_vinils_app_grupo_32.models.Collector
import com.example.moviles_vinils_app_grupo_32.models.Musician
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class CollectorDetailRepository (val application: Application){
    suspend fun refreshData(collectorId: Int): Collector {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getObjectFromCache("Collector$collectorId")
        if(potentialResp==null){
            Log.d("Cache decision", "get from network")
            var collector = NetworkServiceAdapter.getInstance(application).getCollector(collectorId)
            CacheManager.getInstance(application.applicationContext).addObjectToCache("Collector$collectorId", collector)
            return collector
        }
        else{
            return potentialResp as Collector
        }
    }
}