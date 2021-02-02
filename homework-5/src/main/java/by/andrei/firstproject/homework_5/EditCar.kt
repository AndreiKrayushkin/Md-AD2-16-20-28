package by.andrei.firstproject.homework_5

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var dao: CarDatabase

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

        dao = CarDatabase.init(this)

    }
}