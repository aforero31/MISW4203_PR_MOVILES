package com.example.moviles_vinils_app_grupo_32.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.moviles_vinils_app_grupo_32.models.Musician
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class MusicianDetailRepository (val application: Application) {
    fun refreshData(musicianId: Int, callback: (Musician)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getMusician(musicianId, {
            //Guardar los Artistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}