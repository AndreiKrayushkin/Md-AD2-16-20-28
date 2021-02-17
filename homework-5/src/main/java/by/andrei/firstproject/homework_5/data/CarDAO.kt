package by.andrei.firstproject.homework_5.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface CarDAO {
    @Query("SELECT * FROM car")
    fun getCarsList(): MutableList<Car>

    @Query("SELECT * FROM car WHERE id = :carId")
    fun getCar(carId: Int): Car

    @Insert
    fun insertAll(car: Car)

    @Update
    fun update(car: Car)

    @Delete
    fun delete(car: Car)
}