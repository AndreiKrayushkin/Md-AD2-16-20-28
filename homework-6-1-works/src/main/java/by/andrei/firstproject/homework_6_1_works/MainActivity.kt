package by.andrei.firstproject.homework_6_1_works

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val WORK_URI = "by.andrei.firstproject.homework_6_1_car.data.WorkProvider"
private const val WORK_PATH = "database"
private const val URL = "content://$WORK_URI/$WORK_PATH"

class MainActivity : AppCompatActivity() {
    private lateinit var workAdapter: WorkAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recyclerViewWorksAtTheCar)
        workAdapter = WorkAdapter(mutableListOf())
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.apply {
            adapter = workAdapter
            layoutManager = linearLayoutManager
            workAdapter.updateList(getWorkData())
        }
        getWorkData()
    }

    private fun getWorkData() : ArrayList<Work>{
        val workList = arrayListOf<Work>()
        val contentResolver: ContentResolver = contentResolver
        val cursor = contentResolver.query(
                Uri.parse(URL),
                null,
                null,
                null,
                null
        )
        if (cursor == null) {
            Log.v("Log_view", "null")
        }
        cursor?.run {
            while(cursor.moveToNext()){
                Log.v("Log_view", "hello")
                workList.add(
                        Work (
                                this.getColumnIndex("workName").toString(),
                                this.getColumnIndex("cost").toString(),
                                this.getColumnIndex("description").toString(),
                                this.getColumnIndex("applicationData").toString(),
                                this.getColumnIndex("progressWork").toString(),
                                this.getColumnIndex("colorStatus")
                        )
                )
            }
            cursor.close()
        }
        return workList
    }
}