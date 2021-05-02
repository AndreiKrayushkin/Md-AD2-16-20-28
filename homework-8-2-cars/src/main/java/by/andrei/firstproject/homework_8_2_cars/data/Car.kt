package by.andrei.firstproject.homework_8_2_cars.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
        val nameOwner: String?,
        val producer: String?,
        val model: String?,
        val registerNumber: String?,
        val photo: Int
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    constructor(parcel: Parcel) : this(
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readInt()
    ){
        parcel.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(nameOwner)
        dest.writeString(producer)
        dest.writeString(model)
        dest.writeString(registerNumber)
        dest.writeInt(photo)
        dest.writeInt(id ?: -1)
    }

    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }

        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }
    }
}
