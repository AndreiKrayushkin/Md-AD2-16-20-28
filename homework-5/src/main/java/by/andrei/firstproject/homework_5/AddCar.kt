package by.andrei.firstproject.homework_5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import by.andrei.firstproject.homework_5.data.Car
import by.andrei.firstproject.homework_5.data.CarDatabase

class AddCar : AppCompatActivity() {
    private lateinit var car: Car
    private lateinit var addOwnerName: EditText
    private lateinit var addProducer: EditText
    private lateinit var addModel: EditText
    private lateinit var addPlateNumber: EditText
    private lateinit var buttonAddCar: Button
    private lateinit var dao: CarDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_car)

        addOwnerName = findViewById(R.id.editOwnerNameInAddCarLayout)
        addProducer = findViewById(R.id.editProducerInAddCarLayout)
        addModel = findViewById(R.id.editModelInAddCarLayout)
        addPlateNumber = findViewById(R.id.editNumberInAddCarLayout)
        buttonAddCar = findViewById(R.id.buttonAddCarInAddCarLayout)

        dao = CarDatabase.init(this)

        buttonAddCar.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            val car = createCarObject()
            dao.getCarDAO().insertAll(car)

            intent.putExtra("TEXT", car.id)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun createCarObject(): Car {
        return Car(
        addOwnerName.text.toString(),
        addProducer.text.toString(),
        addModel.text.toString(),
        addPlateNumber.text.toString(),
        R.drawable.ic_baseline_photo_camera_24
        )
    }
}