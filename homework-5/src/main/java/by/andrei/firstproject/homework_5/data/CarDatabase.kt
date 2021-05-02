package by.andrei.firstproject.homework_5.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database
(entities = [Car::class, Work::class], version = 2)

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