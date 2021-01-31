package by.andrei.firstproject.homework_5.data

import androidx.room.*

@Dao
interface CarDAO {
    @Query("SELECT * FROM car")
    fun getCarsList(): MutableList<Car>

    @Query("SELECT * FROM car WHERE id = :carId")
    fun getCar(carId: Long): Car

    @Insert
    fun insertAll(car: Car)

    @Update
    fun update(car: Car)

    @Delete
    fun delete(car: Car)
}