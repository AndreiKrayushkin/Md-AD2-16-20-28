package by.andrei.firstproject.homework_9.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cities::class], version = 1)
abstract class WeatherDB : RoomDatabase() {
    abstract fun getCitiesDAO(): WeatherDAO

    companion object {
        private var DATABASE: WeatherDB? = null
        fun getDatabase(context: Context): WeatherDB {
            if (DATABASE == null) {
                DATABASE = Room.databaseBuilder(context, WeatherDB::class.java, "CitiesDB")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return DATABASE as WeatherDB
        }
    }
}