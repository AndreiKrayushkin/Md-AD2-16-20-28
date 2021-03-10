package by.andrei.firstproject.homework_6_1_car.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import by.andrei.firstproject.homework_6_1_car.data.Work

@Dao
interface WorkDAO {
    @Query ("SELECT * FROM work_item")
    fun getWorkList(): MutableList<Work>

    @Query("SELECT * FROM work_item WHERE id = :workId")
    fun getWork(workId: Int): Work

    @Query("SELECT * FROM work_item WHERE parentCar LIKE :parentCar")
    fun getWorkFromParentsCar(parentCar: String?): MutableList<Work>

    @Query("SELECT * FROm work_item")
    fun getWorkProvider(): Cursor

    @Query("DELETE FROM work_item")
    fun deleteAllList()

    @Insert
    fun insertAll(work: Work)

    @Update
    fun update(work: Work)

    @Delete
    fun delete(work: Work)
}