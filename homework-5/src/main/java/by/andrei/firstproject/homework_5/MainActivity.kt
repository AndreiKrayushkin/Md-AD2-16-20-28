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
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

private const val ADD_CAR_KEY = 1
private const val EDIT_CAR_KEY = 2
private const val ADD_WORK_KEY = 3
private const val OPERATION_EDIT_CAR: Int = 1
private const val OPERATION_WORK_ADD: Int = 2

class MainActivity : AppCompatActivity() {
    private lateinit var carAdapter: CarAdapter
    private lateinit var buttonGoToAddCar: FloatingActionButton
    private lateinit var searchButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var onCarClickListener: OnCarClickListener
    private lateinit var dao: CarDatabase
    private val carID = "carID"
    private val carModel = "model"
    private val carProducer = "producer"
    private val carNumber = "number"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewCars)
        buttonGoToAddCar = findViewById(R.id.mainFloatingButtonAddCars)

        //не описано действие для данной кнопки поиска автомобилей
        searchButton = findViewById(R.id.searchButtonCarListActivity)

        dao = CarDatabase.init(this)

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //клик по кнопке добавления автомобилей
        buttonGoToAddCar.setOnClickListener{
            val intentAddCar = Intent(this, AddCar::class.java)
            startActivityForResult(intentAddCar, ADD_CAR_KEY)
        }

        //клик по кнопке "редактировать автомобиль", либо по объекту автомобиля
        val intentEditCar = Intent(this, EditCar::class.java)
        val intentWorkList = Intent(this, WorkListActivity::class.java)
        onCarClickListener = object : OnCarClickListener {
            override fun invoke(car: Car, position: Int, operation: Int) {
                when(operation){
                    OPERATION_EDIT_CAR -> operation(intentEditCar, car, EDIT_CAR_KEY)
                    OPERATION_WORK_ADD -> operation(intentWorkList, car, ADD_WORK_KEY)
                }
            }
        }

        carAdapter = CarAdapter(mutableListOf(), onCarClickListener  )
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
        checkDataBase()
    }

    private fun operation(intent: Intent, car: Car, key: Int) {
        if (key == EDIT_CAR_KEY) {
            intent.putExtra(carID, car.id)
            startActivityForResult(intent, key)
        } else {
            intent.apply {
                putExtra(carModel, car.model)
                putExtra(carProducer, car.producer)
                putExtra(carNumber, car.registerNumber)
                putExtra(carID, car.id)
            }
            startActivityForResult(intent, key)
        }
    }
}