package com.nikitayasiulevich.pexelsapp.data.mappers

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.toColorInt
import com.nikitayasiulevich.pexelsapp.data.database.PhotoDbModel
import com.nikitayasiulevich.pexelsapp.data.model.CollectionsListDto
import com.nikitayasiulevich.pexelsapp.data.model.PhotosListDto
import com.nikitayasiulevich.pexelsapp.domain.FeaturedCollection
import com.nikitayasiulevich.pexelsapp.domain.Photo

class PhotosMapper {

    fun mapPhotosListDtoToPhotos(photosListDto: PhotosListDto): List<Photo> {
        val result = mutableListOf<Photo>()
        val photosFromDto = photosListDto.photos
        for (photoFromDto in photosFromDto) {
            val photo = Photo(
                id = photoFromDto.id,
                height = photoFromDto.height.toInt(),
                width = photoFromDto.width.toInt(),
                url = photoFromDto.url,
                alt = photoFromDto.alt,
                avgColor = Color(photoFromDto.avgColor.toColorInt()),
                photographer = photoFromDto.photographer,
                srcOriginal = photoFromDto.src.original,
                srcLarge = photoFromDto.src.large
            )
            result.add(photo)
        }
        return result
    }

    fun mapCollectionsListDtoToFeaturedCollections(collectionsListDto: CollectionsListDto): List<FeaturedCollection> {
        val result = mutableListOf<FeaturedCollection>()
        val collectionsFromDto = collectionsListDto.collections
        for (collectionFromDto in collectionsFromDto) {
            val featuredCollection = FeaturedCollection(
                id = collectionFromDto.id,
                title = collectionFromDto.title,
            )
            result.add(featuredCollection)
        }
        return result
    }

    fun mapDbModelToEntity(photoDbModel: PhotoDbModel) = Photo(
        id = photoDbModel.id,
        height = photoDbModel.height,
        width = photoDbModel.width,
        url = photoDbModel.url,
        alt = photoDbModel.alt,
        avgColor = Color(photoDbModel.avgColor.toInt()),
        photographer = photoDbModel.photographer,
        srcOriginal = photoDbModel.srcOriginal,
        srcLarge = photoDbModel.srcLarge
    )

    fun mapEntityToDbModel(photo: Photo) = PhotoDbModel(
        id = photo.id,
        height = photo.height,
        width = photo.width,
        url = photo.url,
        alt = photo.alt,
        avgColor = photo.avgColor.toArgb().toString(),
        photographer = photo.photographer,
        srcOriginal = photo.srcOriginal,
        srcLarge = photo.srcLarge
    )

    fun mapListDbModelToListEntity(list: List<PhotoDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}