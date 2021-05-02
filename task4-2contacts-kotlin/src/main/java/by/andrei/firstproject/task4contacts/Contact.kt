package by.andrei.firstproject.task4contacts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact (
        var imageView: Int,
        var textContactName: String,
        var textContactPhoneOrEmail: String
        ) : Parcelable