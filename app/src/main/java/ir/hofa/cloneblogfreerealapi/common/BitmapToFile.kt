package ir.hofa.cloneblogfreerealapi.common

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class BitmapToFile(private val context: Context) {

    fun saveImageInInternalStorage(bitmap: Bitmap): String {
        val fileAndPath = buildFile("test${System.currentTimeMillis()}.jpg")
        try {
            val portal = buildOutputStream(fileAndPath)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, portal)
            portal.flush()
            portal.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return fileAndPath.absolutePath
    }

    fun buildOutputStream(file: File): FileOutputStream {
        return FileOutputStream(file)
    }

    fun buildFile(fileName: String): File {//amir.mp4
        return File(context.filesDir, fileName)
    }
}