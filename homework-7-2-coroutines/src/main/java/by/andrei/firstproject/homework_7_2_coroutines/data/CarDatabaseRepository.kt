package by.andrei.firstproject.homework_7_2_coroutines.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarDatabaseRepository (context: Context) {
    private val database = CarDatabase.getDatabase(context)
    private val thread = Dispatchers.IO

    suspend fun getCarList() : List<Car> {
        return withContext(thread) {
            database.getCarDAO().getCarsList()
        }
    }

    suspend fun getCar(carId: Int) : Car {
        return withContext(thread) {
            database.getCarDAO().getCar(carId)
        }
    }

    suspend fun insertAll(car: Car) {
        withContext(thread) {
            database.getCarDAO().insertAll(car)
        }
    }

    suspend fun updateCar(car: Car) {
        withContext(thread) {
            database.getCarDAO().update(car)
        }
    }

    suspend fun deleteCar(car: Car) {
        withContext(thread) {
            database.getCarDAO().delete(car)
        }
    }
}