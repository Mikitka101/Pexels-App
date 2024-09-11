package com.nikitayasiulevich.pexelsapp.data.mappers

import com.nikitayasiulevich.pexelsapp.data.model.PhotosListDto
import com.nikitayasiulevich.pexelsapp.domain.Photo

class PhotosMapper {

    fun mapPhotosListDtoToPhotos(photosListDto: PhotosListDto): List<Photo> {
        val result = mutableListOf<Photo>()
        val photosFromDto = photosListDto.photos
        for(photoFromDto in photosFromDto) {
            val photo = Photo(
                id = photoFromDto.id,
                height = photoFromDto.height.toInt(),
                width = photoFromDto.width.toInt(),
                url = photoFromDto.url,
                alt = photoFromDto.alt,
                avgColor = photoFromDto.avgColor,
                photographer = photoFromDto.photographer,
                src = photoFromDto.src.original
            )
            result.add(photo)
        }
        return result
    }
}