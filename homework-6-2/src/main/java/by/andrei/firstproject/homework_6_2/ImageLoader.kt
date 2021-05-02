package by.andrei.firstproject.homework_6_2

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

class ImageLoader {
    companion object {
        fun loadAddPhotos(activity: MainActivity) : ArrayList<Uri> {
            val photoUriList = arrayListOf<Uri>()
            val uriExternal: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val cursor: Cursor?
            val columnIndexID: Int
            val projection = arrayOf(MediaStore.Images.Media._ID)
            var imageID: Int
            cursor = activity.contentResolver.query(uriExternal, projection, null, null, null)
            if (cursor != null) {
                columnIndexID = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                while (cursor.moveToNext()) {
                    imageID = cursor.getInt(columnIndexID)
                    val uriImage = Uri.withAppendedPath(uriExternal, "" + imageID)
                    photoUriList.add(uriImage)
                }
                cursor.close()
            }
            return photoUriList
        }
    }
}