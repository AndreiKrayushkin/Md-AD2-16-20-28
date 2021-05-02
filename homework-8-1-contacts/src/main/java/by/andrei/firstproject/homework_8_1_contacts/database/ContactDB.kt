package by.andrei.firstproject.homework_8_1_contacts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.andrei.firstproject.homework_8_1_contacts.data.Contact

@Database
(entities = [Contact::class], version = 1)

abstract class ContactDB : RoomDatabase() {
    abstract fun getContactDAO(): ContactDAO

    companion object {
        private var DATABASE: ContactDB? = null
        fun getDatabase(context: Context): ContactDB {
            if (DATABASE == null) {
                DATABASE = Room.databaseBuilder(context, ContactDB::class.java, "ContactDB")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return DATABASE as ContactDB
        }
    }
}