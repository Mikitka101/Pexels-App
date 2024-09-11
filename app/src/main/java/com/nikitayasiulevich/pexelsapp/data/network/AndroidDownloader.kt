package com.nikitayasiulevich.pexelsapp.data.network

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.nikitayasiulevich.pexelsapp.data.utils.Constants.API_KEY
import com.nikitayasiulevich.pexelsapp.domain.Downloader

class AndroidDownloader(
    context: Context
): Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val uri = Uri.parse(url)
        val filename = uri.lastPathSegment
        val request = DownloadManager.Request(uri)
            .setMimeType("image/jpeg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(filename)
            .addRequestHeader("Authorization", API_KEY)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, "PexelsApp")
        return downloadManager.enqueue(request)
    }
}