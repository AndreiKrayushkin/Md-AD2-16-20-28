package by.andrei.firstproject.homework_8_1_contacts.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import by.andrei.firstproject.homework_8_1_contacts.data.Contact

@Dao
interface ContactDAO {
    @Query ("SELECT * FROM contact")
    fun getContactList(): List<Contact>

    @Query ("SELECT * FROM contact WHERE id = :contactId")
    fun getThisContact(contactId: Int): Contact

    @Query ("DELETE FROM contact WHERE id = :contactId")
    fun deleteThisContact(contactId: Int)

    @Insert
    fun addContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)
}