package by.andrei.firstproject.homework_8_1_contacts.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
        var imageView: Int,
        var nameContact: String,
        var phoneOrEmailContact: String
) : Parcelable