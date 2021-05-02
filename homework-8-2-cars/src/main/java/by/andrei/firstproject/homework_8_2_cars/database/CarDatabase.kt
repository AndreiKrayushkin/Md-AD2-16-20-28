package by.andrei.firstproject.homework_8_2_cars.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.andrei.firstproject.homework_8_2_cars.data.Car
import by.andrei.firstproject.homework_8_2_cars.data.Work

@Database
(entities = [Car::class, Work::class], version = 1)

abstract class CarDatabase : RoomDatabase() {
    abstract fun getCarDAO(): CarDAO
    abstract fun getWorkDAO(): WorkDAO

    companion object {
        private var DATABASE: CarDatabase? = null
        fun getDatabase(context: Context): CarDatabase {
            if (DATABASE == null) {
                DATABASE = Room.databaseBuilder(context, CarDatabase::class.java, "database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return DATABASE as CarDatabase
        }
    }
}