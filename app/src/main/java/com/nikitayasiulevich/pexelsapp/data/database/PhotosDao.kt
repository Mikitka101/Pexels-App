package com.nikitayasiulevich.pexelsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotosDao {

    @Query("SELECT * FROM liked_photos")
    fun getLikedPhotosList(): LiveData<List<PhotoDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLikedPhoto(photoDbModel: PhotoDbModel)

    @Query("DELETE FROM liked_photos WHERE id=:photoId ")
    suspend fun deletePhotoFromLiked(photoId: String)

    @Query("SELECT * FROM liked_photos WHERE id=:photoId LIMIT 1")
    suspend fun getLikedPhoto(photoId: String): PhotoDbModel?
}