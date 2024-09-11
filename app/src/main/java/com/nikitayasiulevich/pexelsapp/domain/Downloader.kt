package com.nikitayasiulevich.pexelsapp.domain

interface Downloader {

    fun downloadFile(url: String): Long

}