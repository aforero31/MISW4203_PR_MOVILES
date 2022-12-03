package com.example.moviles_vinils_app_grupo_32.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.moviles_vinils_app_grupo_32.models.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

    suspend fun getAlbums() = suspendCoroutine<List<Album>>{ cont ->
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                var item:JSONObject? = null
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),
                                    name = item.getString("name"),
                                    cover = item.getString("cover"),
                                    recordLabel = null,
                                    releaseDate = null,
                                    genre = null,
                                    description = null,
                                    performers = getListPerformers(item.getJSONArray("performers")),
                                    tracksString = null
                            ))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    private fun getListTracks(listTracks: JSONArray): String {

        var stringList = StringBuilder()
        var item:JSONObject? = null
        for (i in 0 until listTracks.length()) {
            item = listTracks.getJSONObject(i)
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
        var item:JSONObject? = null
        for (i in 0 until listPerformers.length()) {
            item = listPerformers.getJSONObject(i)
            list.add(i, Performers(id = item.getInt("id"),
                                    name= item.getString("name"),
                                    image = item.getString("image"),
                                    description = item.getString("description")))
        }
        return list
    }


    suspend fun getAlbum(albumId: Int) = suspendCoroutine<Album>{ cont ->
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
                cont.resume(album)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))

    }

    suspend fun getMusicians() = suspendCoroutine<List<Musician>> { cont ->
        requestQueue.add(getRequest("musicians",
            Response.Listener<String> { response ->
                Log.d("tagb", response)
                val resp = JSONArray(response)
                val list = mutableListOf<Musician>()
                var item:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Musician(musicianId = item.getInt("id"),
                        name = item.getString("name"),
                        image = item.getString("image"),
                        description = item.getString("description"),
                        birthDate = item.getString("birthDate")))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
                Log.d("", it.message.toString())
            }))
    }

    suspend fun getMusician(musicianId: Int) = suspendCoroutine<Musician>{ cont ->
        requestQueue.add(getRequest("musicians/$musicianId",
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                val musician = Musician(musicianId = resp.getInt("id"),
                    name = resp.getString("name"),
                    image = resp.getString("image"),
                    description = resp.getString("description"),
                    birthDate = resp.getString("birthDate")
                )
                cont.resume(musician)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))

    }

    suspend fun getCollector(collectorId: Int) = suspendCoroutine<Collector>{ cont ->
        requestQueue.add(getRequest("collectors/$collectorId",
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                val collector = Collector(
                    collectorId = resp.getInt("id"),
                    name = resp.getString("name"),
                    telephone = resp.getString("telephone"),
                    email = resp.getString("email"),
                    albums = this.getListOfCollectorAlbums(resp.getJSONArray("collectorAlbums"))
                )
                cont.resume(collector)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>>{ cont ->
        requestQueue.add(getRequest("collectors",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                var item:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i,
                        Collector(
                            collectorId = item.getInt("id"),
                            name = item.getString("name"),
                            telephone = item.getString("telephone"),
                            email = item.getString("email"),
                            albums = emptyList()
                    ))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun postAlbum(album: Album) = suspendCoroutine<Album>{ cont ->
        requestQueue.add(postRequest("albums", getPostAlbumBody(album),
            Response.Listener<JSONObject> { resp ->
                val album = Album(albumId = resp.getInt("id"),
                    name = resp.getString("name"),
                    cover = resp.getString("cover"),
                    recordLabel = resp.getString("recordLabel"),
                    releaseDate = resp.getString("releaseDate"),
                    genre = resp.getString("genre"),
                    description = resp.getString("description"),
                    performers = null,
                    tracksString = null
                )
                cont.resume(album)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))

    }

    suspend fun postTrack(albumId: Int, track: Track) = suspendCoroutine<Track> { cont ->
        requestQueue.add(postRequest("albums/$albumId/tracks", getPostTrackBody(track),
            Response.Listener<JSONObject> { resp ->
                val track = Track(
                    id = resp.getInt("id"),
                    name = resp.getString("name"),
                    duration = resp.getString("duration")
                )
                cont.resume(track)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    private fun getListOfCollectorAlbums(listOfCollectorAlbum: JSONArray): List<CollectorAlbum> {
        val list = mutableListOf<CollectorAlbum>()
        var item:JSONObject? = null
        for (i in 0 until listOfCollectorAlbum.length()) {
            item = listOfCollectorAlbum.getJSONObject(i)
            list.add(i, CollectorAlbum(id = item.getInt("id"),
                price = item.getInt("price"),
                status = item.getString("status")))
        }
        return list
    }

    private fun getPostAlbumBody(album: Album): JSONObject{
        val body = JSONObject()
        body.put("name", album.name)
        body.put("cover", album.cover)
        body.put("recordLabel", album.recordLabel)
        body.put("releaseDate", album.releaseDate)
        body.put("genre", album.genre)
        body.put("description", album.description)
        return body
    }

    private fun getPostTrackBody(track: Track): JSONObject{
        val body = JSONObject()
        body.put("name", track.name)
        body.put("duration", track.duration)
        return body
    }

    private fun postRequest(path: String, requestBody: JSONObject , responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener): JsonObjectRequest {
        return JsonObjectRequest(Request.Method.POST, BASE_URL+path, requestBody, responseListener, errorListener)
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
}