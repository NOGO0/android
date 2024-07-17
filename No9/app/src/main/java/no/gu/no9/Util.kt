package no.gu.no9

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Environment
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream


/**
 * this is a function that image change file
 * @param context
 * @param uri this is image uri that taken from gallery
 * @return get file name & extension
 */
fun toFile(context: Context, uri: Uri): File {
    val fileName = getFileName(context, uri)

    val file = createTempFile(context, fileName)
    copyToFile(context, uri, file)

    return File(file.absolutePath)
}

private fun getFileName(context: Context, uri: Uri): String {
    val name = uri.toString().split("/").last()
    val ext = context.contentResolver.getType(uri)!!.split("/").last()

    return "$name.$ext"
}

private fun createTempFile(context: Context, fileName: String): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File(storageDir, fileName)
}

@SuppressLint("Recycle")
private fun copyToFile(context: Context, uri: Uri, file: File) {
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = FileOutputStream(file)

    val buffer = ByteArray(4 * 1024)
    while (true) {
        val byteCount = inputStream!!.read(buffer)
        if (byteCount < 0) break
        outputStream.write(buffer, 0, byteCount)
    }

    outputStream.flush()
}

/**
 * Converts an application/json file to a MultipartBody.Part for use in a form-data request.
 *
 * @param key The name to associate with the file in the multipart request.
 * @param file The application/json file to be converted.
 * @return A MultipartBody.Part representing the file to be sent to the server.
 */
fun getImageMultipart(key: String, file: File): MultipartBody.Part {
    return MultipartBody.Part.createFormData(
        name = key,
        filename = file.name,
        body = file.asRequestBody("multipart/form-data".toMediaType())
    )
}
