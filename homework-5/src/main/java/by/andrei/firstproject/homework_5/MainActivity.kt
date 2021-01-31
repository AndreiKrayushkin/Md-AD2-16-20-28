package by.andrei.firstproject.homework_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.andrei.firstproject.homework_5.adapter.CarAdapter
import by.andrei.firstproject.homework_5.data.Car
import by.andrei.firstproject.homework_5.data.CarDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var carAdapter: CarAdapter
    private lateinit var car: Car
    private lateinit var buttonGoToAddCar: FloatingActionButton
    private lateinit var recyclerView: RecyclerView

    private lateinit var dao: CarDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewCars)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        dao = CarDatabase.init(this)

        carAdapter = CarAdapter(mutableListOf())
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = carAdapter

        buttonGoToAddCar = findViewById(R.id.mainFloatingButtonAddCars)
        buttonGoToAddCar.setOnClickListener{
            val intentAddCar = Intent(this, AddCar::class.java)
            startActivityForResult(intentAddCar, 1)
        }
        checkDataBase()
    }

    private fun checkDataBase() {
        val carLists = dao.getCarDAO().getCarsList()
        if (carLists.isNotEmpty()) {
            carAdapter.carList = carLists as MutableList<Car>
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        checkDataBase()

//        var carView: Car?
//        if (requestCode == 1) {
//            if(resultCode == RESULT_OK) {
//                carView = data?.getParcelableExtra("TEXT")
//                if (carView != null) {
//                    carAdapter.addCar(carView)
//                }
//            }
//        }

    }
}