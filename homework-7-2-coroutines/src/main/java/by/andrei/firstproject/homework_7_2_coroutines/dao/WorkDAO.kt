package by.andrei.firstproject.homework_7_2_coroutines.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import by.andrei.firstproject.homework_7_2_coroutines.data.Work

@Dao
interface WorkDAO {
    @Query ("SELECT * FROM work_item")
    fun getWorkList(): List<Work>

    @Query("SELECT * FROM work_item WHERE id = :workId")
    fun getWork(workId: Int): Work

    @Query("SELECT * FROM work_item WHERE parentCar LIKE :parentCar")
    fun getWorkFromParentsCar(parentCar: String?): List<Work>

    @Insert
    fun insertAll(work: Work)

    @Update
    fun update(work: Work)

    @Delete
    fun delete(work: Work)
}