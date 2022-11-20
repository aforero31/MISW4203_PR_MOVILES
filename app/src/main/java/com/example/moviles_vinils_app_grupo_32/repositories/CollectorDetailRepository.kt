package com.example.moviles_vinils_app_grupo_32.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.moviles_vinils_app_grupo_32.models.Collector
import com.example.moviles_vinils_app_grupo_32.models.Musician
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class CollectorDetailRepository (val application: Application){
    suspend fun refreshData(collectorId: Int): Collector {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getCollector(collectorId)
    }
}