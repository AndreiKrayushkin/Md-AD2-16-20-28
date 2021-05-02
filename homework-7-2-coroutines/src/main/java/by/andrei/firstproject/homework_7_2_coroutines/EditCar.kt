package by.andrei.firstproject.homework_7_2_coroutines

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import by.andrei.firstproject.homework_7_2_coroutines.data.Car
import by.andrei.firstproject.homework_7_2_coroutines.data.CarDatabaseRepository
import by.andrei.firstproject.homework_7_2_coroutines.data.WorkDatabaseRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EditCar : AppCompatActivity() {
    private lateinit var photoImageViewInEdit: ImageView
    private lateinit var editOwnerNameInEditCar: EditText
    private lateinit var editProducerInEditCar: EditText
    private lateinit var editModelInEditCar: EditText
    private lateinit var editPlateNumberInEditCar: EditText
    private lateinit var buttonApplyInEditCar: ImageButton
    private lateinit var buttonBackToCarList: ImageButton
    private lateinit var buttonEditImageInEditCar: FloatingActionButton
    private lateinit var carDatabaseRepository: CarDatabaseRepository
    private lateinit var workDatabaseRepository: WorkDatabaseRepository

    private var car: Car? = null
    private var carId: Int = 0
    private val carID = "carID"

    private val activityScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_car)

        photoImageViewInEdit = findViewById(R.id.imageViewCarInEditCarLayout)
        editOwnerNameInEditCar = findViewById(R.id.editOwnerNameInEditCarLayout)
        editProducerInEditCar = findViewById(R.id.editProducerInEditCarLayout)
        editModelInEditCar = findViewById(R.id.editModelInEditCarLayout)
        editPlateNumberInEditCar = findViewById(R.id.editNumberInEditCarLayout)
        buttonApplyInEditCar = findViewById(R.id.checkButtonEditCarListActivity)
        buttonEditImageInEditCar = findViewById(R.id.photoFloatingButtonInEditCarLayout)
        buttonBackToCarList = findViewById(R.id.backButtonEditCarListActivity)

        carDatabaseRepository = CarDatabaseRepository(this)
        workDatabaseRepository = WorkDatabaseRepository(this)

        getCarExtra(intent)
        if (car != null) {
            setTextInView()
        }
        buttonApplyInEditCar.setOnClickListener {
            val intentUpdateCar = Intent(this, MainActivity::class.java)
            val updateCar = updateCarObject().also { it.id = carId }
            activityScope.launch {
                carDatabaseRepository.updateCar(updateCar)
                setResult(RESULT_OK, intentUpdateCar)
                finish()
            }
        }
        buttonBackToCarList.setOnClickListener {
            finish()
        }
    }

    private fun setTextInView() {
        photoImageViewInEdit.setImageResource(R.drawable.ic_baseline_arrow_forward_24)
        car?.let {
            editOwnerNameInEditCar.setText(it.nameOwner)
            editProducerInEditCar.setText(it.producer)
            editModelInEditCar.setText(it.model)
            editPlateNumberInEditCar.setText(it.registerNumber)
        }
    }

    private fun getCarExtra(intent: Intent) {
        activityScope.launch {
            carId = intent.getIntExtra(carID, 0)
            car = carDatabaseRepository.getCar(carId)
        }
    }

    private fun updateCarObject() =
            Car(
                    nameOwner = editOwnerNameInEditCar.text.toString(),
                    producer = editProducerInEditCar.text.toString(),
                    model = editModelInEditCar.text.toString(),
                    registerNumber = editPlateNumberInEditCar.text.toString(),
                    photo = car?.photo
            )
}