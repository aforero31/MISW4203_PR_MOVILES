package com.example.moviles_vinils_app_grupo_32.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.moviles_vinils_app_grupo_32.models.Album
import com.example.moviles_vinils_app_grupo_32.models.Musician
import com.example.moviles_vinils_app_grupo_32.models.Performers
import org.json.JSONArray
import org.json.JSONObject

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://back-vynils-equipo-32.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getAlbums(onComplete:(resp:List<Album>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),
                                    name = item.getString("name"),
                                    cover = item.getString("cover"),
                                    recordLabel = item.getString("recordLabel"),
                                    releaseDate = item.getString("releaseDate"),
                                    genre = item.getString("genre"),
                                    description = item.getString("description"),
                                    performers = getListPerformers(item.getJSONArray("performers")),
                                    tracksString = getListTracks(item.getJSONArray("tracks"))
                            ))
                }
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }

    private fun getListTracks(listTracks: JSONArray): String {

        var stringList = StringBuilder()
        for (i in 0 until listTracks.length()) {
            val item = listTracks.getJSONObject(i)
            stringList.append(
                i,
                ". ",
                item.getString("name"),
                " - ",
                item.getString("duration"),
                "\n"
            )
        }
        return stringList.toString()
    }

    private fun getListPerformers(listPerformers: JSONArray): List<Performers> {
        val list = mutableListOf<Performers>()
        for (i in 0 until listPerformers.length()) {
            val item = listPerformers.getJSONObject(i)
            list.add(i, Performers(id = item.getInt("id"),
                                    name= item.getString("name"),
                                    image = item.getString("image"),
                                    description = item.getString("description")))
        }
        return list
    }

    fun getAlbum(albumId: Int, onComplete:(resp:Album)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("albums/$albumId",
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                val album = Album(albumId = resp.getInt("id"),
                    name = resp.getString("name"),
                    cover = resp.getString("cover"),
                    recordLabel = resp.getString("recordLabel"),
                    releaseDate = resp.getString("releaseDate"),
                    genre = resp.getString("genre"),
                    description = resp.getString("description"),
                    performers = getListPerformers(resp.getJSONArray("performers")),
                    tracksString = getListTracks(resp.getJSONArray("tracks"))
                )
                onComplete(album)
            },
            Response.ErrorListener {
                onError(it)
            }))

    }
    fun getMusicians(onComplete:(resp:List<Musician>)->Unit, onError: (error:VolleyError)->Unit) {
        requestQueue.add(getRequest("musicians",
            Response.Listener<String> { response ->
                Log.d("tagb", response)
                val resp = JSONArray(response)
                val list = mutableListOf<Musician>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Musician(musicianId = item.getInt("id"),
                                        name = item.getString("name"),
                                        image = item.getString("image"),
                                        description = item.getString("description"),
                                        birthDate = item.getString("birthDate")))
                }
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
                Log.d("", it.message.toString())
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
}