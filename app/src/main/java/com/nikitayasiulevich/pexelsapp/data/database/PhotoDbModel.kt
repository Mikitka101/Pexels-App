package com.nikitayasiulevich.pexelsapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_photos")
data class PhotoDbModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val height: Int,
    val width: Int,
    val url: String,
    val alt: String,
    val avgColor: String,
    val photographer: String,
    val srcOriginal: String,
    val srcLarge: String
)