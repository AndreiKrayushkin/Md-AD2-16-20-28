package by.andrei.firstproject.homework_9.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeatherDAO {
    @Query("SELECT * FROM Cities")
    fun getCitiesList(): List<Cities>

    @Query("SELECT * FROM Cities WHERE cityId = :cityId")
    fun getCity(cityId: Int): Cities

    @Delete
    fun delete(city: Cities)

    @Update
    fun update(city: Cities)

    @Insert
    fun addCityToDB(city: Cities)
}