package com.nikitayasiulevich.pexelsapp.data.repository

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.nikitayasiulevich.pexelsapp.data.database.AppDatabase
import com.nikitayasiulevich.pexelsapp.data.utils.Constants.API_KEY
import com.nikitayasiulevich.pexelsapp.data.mappers.PhotosMapper
import com.nikitayasiulevich.pexelsapp.data.network.AndroidDownloader
import com.nikitayasiulevich.pexelsapp.data.network.ApiFactory
import com.nikitayasiulevich.pexelsapp.domain.FeaturedCollection
import com.nikitayasiulevich.pexelsapp.domain.Photo

class PhotosRepository(
    application: Application
) {

    private val mapper = PhotosMapper()
    private val apiService = ApiFactory.apiService
    private val photosDao = AppDatabase.getInstance(application).photosDao()
    private val downloader = AndroidDownloader(application)

    private var nextPage: String? = null

    private val _photos = mutableListOf<Photo>()
    val photos: List<Photo>
        get() = _photos.toList()

    private val _collections = mutableListOf<FeaturedCollection>()
    val collections: List<FeaturedCollection>
        get() = _collections.toList()

    val likedPhotos: LiveData<List<Photo>> =
        photosDao.getLikedPhotosList().map { mapper.mapListDbModelToListEntity(it) }

    suspend fun loadCuratedPhotos(): List<Photo> {
        val startFrom = nextPage

        if (startFrom == null && photos.isNotEmpty()) return photos

        val response = if (startFrom == null) {
            apiService.loadCuratedPhotos(API_KEY)
        } else {
            val uri = Uri.parse(startFrom)
            val nextPageNumber = uri.getQueryParameter("page")?.toInt()
            apiService.loadCuratedPhotos(
                key = API_KEY,
                page = nextPageNumber
            )
        }
        nextPage = response.nextPage
        _photos.addAll(mapper.mapPhotosListDtoToPhotos(response))
        return photos
    }

    suspend fun loadFeaturedCollections(): List<FeaturedCollection> {
        val response =
            apiService.loadFeaturedCollections(API_KEY)
        _collections.clear()
        _collections.addAll(mapper.mapCollectionsListDtoToFeaturedCollections(response).sortedBy {
            it.title.length
        })
        return collections
    }

    fun downloadFile(url: String) {
        downloader.downloadFile(url)
    }

    suspend fun addPhotoToLiked(photo: Photo) {
        photosDao.addLikedPhoto(mapper.mapEntityToDbModel(photo))
    }

    suspend fun deletePhotoFromLiked(photoId: String) {
        photosDao.deletePhotoFromLiked(photoId)
    }

    suspend fun getPhoto(photoId: String): Photo? {
        val dbModel = photosDao.getLikedPhoto(photoId)
        return dbModel?.let { it1 -> mapper.mapDbModelToEntity(it1) }
    }
}