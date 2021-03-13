package by.andrei.firstproject.homework_7_2_coroutines.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkDatabaseRepository (context: Context) {
    private val database = CarDatabase.getDatabase(context)
    private val thread = Dispatchers.IO

    suspend fun getWorkList() : List<Work> {
        return withContext(thread) {
            database.getWorkDAO().getWorkList()
        }
    }

    suspend fun getWork(workId: Int) : Work {
        return withContext(thread) {
            database.getWorkDAO().getWork(workId)
        }
    }

    suspend fun getWorkFromParentsCar(parentCar: String?) : List<Work> {
        return withContext(thread) {
            database.getWorkDAO().getWorkFromParentsCar(parentCar)
        }
    }

    suspend fun insertAll(work: Work) {
        withContext(thread) {
            database.getWorkDAO().insertAll(work)
        }
    }

    suspend fun update(work: Work) {
        withContext(thread) {
            database.getWorkDAO().update(work)
        }
    }

    suspend fun delete(work: Work) {
        withContext(thread) {
            database.getWorkDAO().delete(work)
        }
    }
}