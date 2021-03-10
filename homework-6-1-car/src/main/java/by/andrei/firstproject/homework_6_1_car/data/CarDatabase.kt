package by.andrei.firstproject.homework_6_1_car.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.andrei.firstproject.homework_6_1_car.dao.CarDAO
import by.andrei.firstproject.homework_6_1_car.dao.WorkDAO

@Database
(entities = [Car::class, Work::class], version = 3)

abstract class CarDatabase : RoomDatabase() {
    abstract fun getCarDAO(): CarDAO
    abstract fun getWorkDAO(): WorkDAO

    companion object{
        fun init(context: Context) = Room.databaseBuilder(context, CarDatabase::class.java, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

//    companion object {
//        private var INSTANCE: CarDatabase? = null
//        fun getDataBase(context: Context): CarDatabase {
//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(
//                        context,
//                        CarDatabase::class.java,
//                        "database")
//                        .allowMainThreadQueries()
//                        .build()
//            }
//            return INSTANCE as CarDatabase
//        }
//    }

}