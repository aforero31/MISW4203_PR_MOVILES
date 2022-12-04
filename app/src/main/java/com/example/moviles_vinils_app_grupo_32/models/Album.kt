package com.example.moviles_vinils_app_grupo_32.models

data class Album (
    val albumId:Int?,
    val name:String,
    val cover:String,
    val releaseDate:String?,
    val description:String?,
    val genre:String?,
    val recordLabel:String?,
    val performers: List<Performers>?,
    val tracksString: String?
)
