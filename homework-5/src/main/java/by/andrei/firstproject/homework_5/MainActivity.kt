package by.andrei.firstproject.homework_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.andrei.firstproject.homework_5.adapter.CarAdapter
import by.andrei.firstproject.homework_5.adapter.OnCarClickListener
import by.andrei.firstproject.homework_5.data.Car
import by.andrei.firstproject.homework_5.data.CarDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var carAdapter: CarAdapter
    private lateinit var car: Car
    private lateinit var buttonGoToAddCar: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var onCarClickListener: OnCarClickListener
    private val ADD_KEY = 1
    private val EDIT_KEY = 2

    private lateinit var dao: CarDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewCars)

        dao = CarDatabase.init(this)

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        buttonGoToAddCar = findViewById(R.id.mainFloatingButtonAddCars)
        buttonGoToAddCar.setOnClickListener{
            val intentAddCar = Intent(this, AddCar::class.java)
            startActivityForResult(intentAddCar, ADD_KEY)
        }
        val intentEditCar = Intent(this, EditCar::class.java)
        onCarClickListener = object : OnCarClickListener {
            override fun invoke(car: Car, position: Int) {
                intentEditCar.putExtra("POSITION", car.id)
                startActivityForResult(intentEditCar, EDIT_KEY)
            }
        }
        carAdapter = CarAdapter(mutableListOf<Car>(), onCarClickListener  )
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = carAdapter
        }
        checkDataBase()
    }

    private fun checkDataBase() {
        val carLists = dao.getCarDAO().getCarsList()
        carAdapter.carList = carLists
        carAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //изменение для одной позиции
        val position = data?.getIntExtra("positionCar", 0)
        carAdapter.notifyItemChanged(position!!)
        val carLists = dao.getCarDAO().getCar(position!!)

//        checkDataBase()

    }

}