package by.andrei.firstproject.homework_5

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import by.andrei.firstproject.homework_5.data.Car
import by.andrei.firstproject.homework_5.data.CarDatabase

class AddCar : AppCompatActivity() {
    private lateinit var addOwnerName: EditText
    private lateinit var addProducer: EditText
    private lateinit var addModel: EditText
    private lateinit var addPlateNumber: EditText
    private lateinit var buttonAddCar: ImageButton
    private lateinit var buttonBackToCarList: ImageButton
    private lateinit var dao: CarDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_car)

        addOwnerName = findViewById(R.id.editOwnerNameInAddCarLayout)
        addProducer = findViewById(R.id.editProducerInAddCarLayout)
        addModel = findViewById(R.id.editModelInAddCarLayout)
        addPlateNumber = findViewById(R.id.editNumberInAddCarLayout)
        buttonAddCar = findViewById(R.id.checkButtonAddCarListActivity)
        buttonBackToCarList = findViewById(R.id.backButtonAddCarListActivity)

        dao = CarDatabase.getDatabase(this)
        buttonAddCar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val car = createCarObject()
            dao.getCarDAO().insertAll(car)
            intent.putExtra("TEXT", car.id)
            setResult(RESULT_OK, intent)
            finish()
        }
        buttonBackToCarList.setOnClickListener {
            finish()
        }
    }

    private fun createCarObject() =
        Car(
                nameOwner = addOwnerName.text.toString(),
                producer = addProducer.text.toString(),
                model = addModel.text.toString(),
                registerNumber = addPlateNumber.text.toString(),
                photo = R.drawable.ic_baseline_photo_camera_24
        )
}