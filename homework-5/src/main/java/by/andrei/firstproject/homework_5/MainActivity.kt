package by.andrei.firstproject.homework_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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
    private lateinit var buttonEditCarMainActivity:
    private lateinit var onCarClickListener: CarAdapter
    private val ADD_KEY = 1
    private val EDIT_KEY = 2

    private lateinit var dao: CarDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewCars)
        buttonEditCarMainActivity = findViewById(R.id.buttonClickEdit)

        dao = CarDatabase.init(this)

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        carAdapter = CarAdapter(mutableListOf<Car>(), onCarClickListener)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = carAdapter
        }
//        recyclerView.layoutManager = linearLayoutManager
//        recyclerView.adapter = carAdapter

        buttonGoToAddCar = findViewById(R.id.mainFloatingButtonAddCars)
        buttonGoToAddCar.setOnClickListener{
            val intentAddCar = Intent(this, AddCar::class.java)
            startActivityForResult(intentAddCar, ADD_KEY)
        }

        buttonEditCarMainActivity.setOnClickListener {
            val intentEditCar = Intent(this, EditCar::class.java)

            startActivityForResult(intentEditCar, EDIT_KEY)
        }

        checkDataBase()
    }

    private fun checkDataBase() {
        val carLists = dao.getCarDAO().getCarsList()
        if (carLists.isNotEmpty()) {
            carAdapter.carList = carLists
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        checkDataBase()
    }

}