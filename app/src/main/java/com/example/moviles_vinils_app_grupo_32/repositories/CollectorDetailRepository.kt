package com.example.moviles_vinils_app_grupo_32.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.moviles_vinils_app_grupo_32.models.Collector
import com.example.moviles_vinils_app_grupo_32.models.Musician
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class CollectorDetailRepository (val application: Application){
    fun refreshData(collectorId: Int, callback: (Collector)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getCollector(collectorId, {
            //Guardar los Artistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}