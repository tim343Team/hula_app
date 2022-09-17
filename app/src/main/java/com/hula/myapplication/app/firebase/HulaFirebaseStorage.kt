package com.hula.myapplication.app.firebase

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.hula.myapplication.app.service.HService
import com.hula.myapplication.app.service.ServiceProfile
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

object HulaFirebaseStorage {
    private val storage = Firebase.storage("gs://eventbuddies-staging.appspot.com")

    val profilePath = "profile/"
    val myEventsPath = "my_events/"
    val myPhotosBasePath = "my-photos-"
    val profilePicPrefix = "profile-poc"
    val charsPath = "chats/"
    val imagesuffix = ".jpg"


    @JvmStatic
    fun update(datePath: String, inputStream: InputStream): UploadTask {
        val reference = storage.reference
        val storageRef = reference.child(datePath)
        return storageRef.putStream(inputStream)
    }


    @JvmStatic
    fun updateEventPhotos(index: Int, filePath: String): UploadTask {
        return update(getNewEventPhotosPath(index), FileInputStream(filePath))
    }


    fun getNewEventPhotosPath(index: Int): String {
        return "${userDir()}/$myEventsPath${UUID.randomUUID()}/$index${imagesuffix}"
    }

    fun userDir(): String {
        val service = HService.getService(ServiceProfile::class.java)
        return "${service.userInfo?.user?.firstName ?: "android"}_${service.userId}"
    }


}