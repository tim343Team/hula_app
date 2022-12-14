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

    val basePath = "https://firebasestorage.googleapis.com/v0"
    val profilePath = "profile/"
    val myEventsPath = "my_events/"
    val myPhotosBasePath = "my-photos-"
    val profilePicPrefix = "profile-pic-"
    val charsPath = "chats/"
    val imagesuffix = ".jpg"


    @JvmStatic
    fun update(datePath: String, inputStream: InputStream): UploadTask {
        val reference = storage.reference
        val storageRef = reference.child(datePath)
        return storageRef.putStream(inputStream)
    }

    /**
     * 上传event图片
     */
    @JvmStatic
    fun updateEventPhotos(index: Int, filePath: String): UploadTask {
        return update(getNewEventPhotosPath(index), FileInputStream(filePath))
    }

    /**
     * 上传头像
     */
    @JvmStatic
    fun updateProfileImage(filePath: String): UploadTask {
        return update(getProfileImagePath(""), FileInputStream(filePath))
    }

    /**
     * 上传我的图片
     */
    fun updateMyPhotos(index: Int,filePath: String):UploadTask{
        return update(getMyPhotosPath(index,""),FileInputStream(filePath))
    }











    /////// /////// /////// /////// /////// /////// /////// /////// /////// /////// /////// ///////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @JvmStatic
    fun getProfileImagePath(name: String): String {
        val service = HService.getService(ServiceProfile::class.java)
        var firstName = service.userInfo?.user?.firstName
        if (firstName.isNullOrEmpty()){
            firstName = name
        }
        return "${firstName}/$profilePath$profilePicPrefix${UUID.randomUUID()}$imagesuffix"
    }


    @JvmStatic
    fun getMyPhotosPath(index: Int,name: String): String {
        val service = HService.getService(ServiceProfile::class.java)
        var firstName = service.userInfo?.user?.firstName
        if (firstName.isNullOrEmpty()){
            firstName = name
        }
        return "${firstName}/$profilePath$myPhotosBasePath$index-${UUID.randomUUID()}$imagesuffix"
    }


    @JvmStatic
    fun getNewEventPhotosPath(index: Int): String {
        return "${userDir()}/$myEventsPath${UUID.randomUUID()}/$index${imagesuffix}"
    }

    @JvmStatic
    fun userDir(): String {
        val service = HService.getService(ServiceProfile::class.java)
        return "${service.userInfo?.user?.firstName ?: ""}_${service.userId}"
    }


}