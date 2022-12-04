package com.example.moviles_vinils_app_grupo_32.repositories

import android.content.Context

class CacheManager(context: Context) {
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }
    private var cacheObjects: HashMap<String, List<Any>> = hashMapOf()
    private var cacheObject: HashMap<String, Any> = hashMapOf()
    fun addListToCache(key: String, cacheObject: List<Any>){
        if (!cacheObjects.containsKey(key)){
            cacheObjects[key] = cacheObject
        }
    }
    fun getListFromCache(key: String) : List<Any>{
        return if (cacheObjects.containsKey(key)) cacheObjects[key]!! else listOf<Any>()
    }

    fun addObjectToCache(key: String, cacheObject: Any){
        if (!this.cacheObject.containsKey(key)){
            this.cacheObject[key] = cacheObject
        }
    }
    fun getObjectFromCache(key: String) : Any? {
        return if (cacheObject.containsKey(key)) cacheObject[key]!! else null
    }


}