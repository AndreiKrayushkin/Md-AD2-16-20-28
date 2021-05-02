package by.andrei.firstproject.homework_9.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cities(
        val city: String
) {
    @PrimaryKey(autoGenerate = true)
    var cityId: Int = 0
}