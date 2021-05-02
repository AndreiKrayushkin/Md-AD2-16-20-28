package by.andrei.firstproject.homework_5.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface WorkDAO {
    @Query ("SELECT * FROM work")
    fun getWorkList(): MutableList<Work>

    @Query("SELECT * FROM work WHERE id = :workId")
    fun getWork(workId: Int): Work

    @Query("SELECT * FROM work WHERE parentCar LIKE :parentCar")
    fun getWorkFromParentsCar(parentCar: String?): MutableList<Work>

    @Insert
    fun insertAll(work: Work)

    @Update
    fun update(work: Work)

    @Delete
    fun delete(work: Work)
}