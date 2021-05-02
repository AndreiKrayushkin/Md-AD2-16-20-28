package by.andrei.firstproject.homework_8_2_cars.fragments

import android.os.Bundle
import android.view.View
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.andrei.firstproject.homework_8_2_cars.OnChangeFragmentListener
import by.andrei.firstproject.homework_8_2_cars.R
import by.andrei.firstproject.homework_8_2_cars.constants.Constants
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_CAR_LIST
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_CAR_ID
import by.andrei.firstproject.homework_8_2_cars.data.Car
import by.andrei.firstproject.homework_8_2_cars.database.CarDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarEdit : Fragment (R.layout.fragment_car_edit) {
    private lateinit var photoImageViewInEdit: ImageView
    private lateinit var editOwnerNameInEditCar: EditText
    private lateinit var editProducerInEditCar: EditText
    private lateinit var editModelInEditCar: EditText
    private lateinit var editPlateNumberInEditCar: EditText
    private lateinit var buttonEditImageInEditCar: FloatingActionButton
    private lateinit var dao: CarDatabase
    private var car: Car? = null
    private var carId: Int? = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: Bundle? = arguments
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.title_edit_new_car_activity)
        }

        photoImageViewInEdit = view.findViewById(R.id.imageViewCarInEditCarLayout)
        editOwnerNameInEditCar = view.findViewById(R.id.editOwnerNameInEditCarLayout)
        editProducerInEditCar = view.findViewById(R.id.editProducerInEditCarLayout)
        editModelInEditCar = view.findViewById(R.id.editModelInEditCarLayout)
        editPlateNumberInEditCar = view.findViewById(R.id.editNumberInEditCarLayout)
        buttonEditImageInEditCar = view.findViewById(R.id.photoFloatingButtonInEditCarLayout)

        carId = bundle?.getInt(KEY_CAR_ID)
        dao = CarDatabase.getDatabase(requireContext())
        car = carId?.let { dao.getCarDAO().getCar(it) }
        car?.let { setTextInView(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_edit_car, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                activity?.supportFragmentManager?.popBackStack()
            }
            R.id.button_apply_edit_car -> {
                val newCar = updateCarObject().also { it.id = carId }
                dao.getCarDAO().update(newCar)
                activity?.supportFragmentManager?.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTextInView(car: Car) {
        photoImageViewInEdit.setImageResource(R.drawable.ic_baseline_arrow_forward_24)
        car.apply {
            editOwnerNameInEditCar.setText(this.nameOwner)
            editProducerInEditCar.setText(this.producer)
            editModelInEditCar.setText(this.model)
            editPlateNumberInEditCar.setText(this.registerNumber)
        }
    }

    private fun updateCarObject() =
            Car(
                    nameOwner = editOwnerNameInEditCar.text.toString(),
                    producer = editProducerInEditCar.text.toString(),
                    model = editModelInEditCar.text.toString(),
                    registerNumber = editPlateNumberInEditCar.text.toString(),
                    photo = R.drawable.ic_baseline_photo_camera_24
            )
}