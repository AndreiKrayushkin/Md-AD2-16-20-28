package by.andrei.firstproject.homework_8_2_cars.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Work(
        val workName: String?,
        val cost: String?,
        val description: String?,
        val applicationDate: String?,
        val progressWork: String?,
        val colorStatus: Int?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var parentCar: String? = null

    constructor(parcel: Parcel) : this(
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readInt()
    ) {
        parcel.readInt()
        parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(workName)
        parcel.writeString(cost)
        parcel.writeString(description)
        parcel.writeString(applicationDate)
        parcel.writeString(progressWork)
        parcel.writeValue(colorStatus)
        parcel.writeValue(id)
        parcel.writeString(parentCar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Work> {
        override fun createFromParcel(parcel: Parcel): Work {
            return Work(parcel)
        }

        override fun newArray(size: Int): Array<Work?> {
            return arrayOfNulls(size)
        }
    }
}
