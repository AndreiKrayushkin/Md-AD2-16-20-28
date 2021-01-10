package by.andrei.firstproject.task4contacts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact (val imageView: Int,
                    val textContactName: String,
                    val textContactPhoneOrEmail: String) : Parcelable