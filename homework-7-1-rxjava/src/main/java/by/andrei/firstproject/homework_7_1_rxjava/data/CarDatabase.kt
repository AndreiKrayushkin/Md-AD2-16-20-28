package by.andrei.firstproject.homework_7_1_rxjava.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.andrei.firstproject.homework_7_1_rxjava.dao.CarDAO
import by.andrei.firstproject.homework_7_1_rxjava.dao.WorkDAO

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