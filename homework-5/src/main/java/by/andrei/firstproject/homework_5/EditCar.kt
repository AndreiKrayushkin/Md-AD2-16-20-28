package by.andrei.firstproject.homework_5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import by.andrei.firstproject.homework_5.data.Car
import by.andrei.firstproject.homework_5.data.CarDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditCar: AppCompatActivity() {
    private lateinit var photoImageViewInEdit: ImageView
    private lateinit var editOwnerNameInEditCar: EditText
    private lateinit var editProducerInEditCar: EditText
    private lateinit var editModelInEditCar: EditText
    private lateinit var editPlateNumberInEditCar: EditText
    private lateinit var buttonApplyInEditCar: Button
    private lateinit var buttonEditImageInEditCar: FloatingActionButton
    private lateinit var buttonDeleteElementInEditCar: Button
    private lateinit var dao: CarDatabase
    private var car: Car? = null
    private var carId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_car)

        photoImageViewInEdit = findViewById(R.id.imageViewCarInEditCarLayout)
        editOwnerNameInEditCar = findViewById(R.id.editOwnerNameInEditCarLayout)
        editProducerInEditCar = findViewById(R.id.editProducerInEditCarLayout)
        editModelInEditCar = findViewById(R.id.editModelInEditCarLayout)
        editPlateNumberInEditCar = findViewById(R.id.editNumberInEditCarLayout)
        buttonApplyInEditCar = findViewById(R.id.buttonApplyInEditCarLayout)
        buttonEditImageInEditCar = findViewById(R.id.photoFloatingButtonInEditCarLayout)
        buttonDeleteElementInEditCar = findViewById(R.id.buttonDeleteInEditCarLayout)

        dao = CarDatabase.init(this)
        getCarExtra(intent)
        if (car != null) {
            setTextInView()
        }

        val position = intent.getIntExtra("POSITION", 0)
        buttonApplyInEditCar.setOnClickListener {
            val intentUpdateCar = Intent(this, MainActivity::class.java)
            val updateCar = updateCarObject()
            dao.getCarDAO().update(updateCar)
            intentUpdateCar.putExtra("positionCar", position)
            setResult(RESULT_OK, intentUpdateCar)
            finish()
        }
    }

    private fun setTextInView() {
        photoImageViewInEdit.setImageResource(R.drawable.ic_baseline_arrow_forward_24)
        editOwnerNameInEditCar.setText(car?.nameOwner)
        editProducerInEditCar.setText(car?.producer)
        editModelInEditCar.setText(car?.model)
        editPlateNumberInEditCar.setText(car?.registerNumber)
    }

    private fun getCarExtra(intent: Intent) {
        carId = intent.getIntExtra("POSITION", 0)
        car = dao.getCarDAO().getCar(carId)
    }

    private fun updateCarObject(): Car {
        return Car(
                editOwnerNameInEditCar.text.toString(),
                editProducerInEditCar.text.toString(),
                editModelInEditCar.text.toString(),
                editPlateNumberInEditCar.text.toString(),
                R.drawable.ic_baseline_photo_camera_24
        )
    }
}