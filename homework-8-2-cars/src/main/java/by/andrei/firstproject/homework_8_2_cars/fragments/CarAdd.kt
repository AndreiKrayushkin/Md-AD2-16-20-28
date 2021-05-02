package by.andrei.firstproject.homework_8_2_cars.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.andrei.firstproject.homework_8_2_cars.OnChangeFragmentListener
import by.andrei.firstproject.homework_8_2_cars.R
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_CAR_LIST
import by.andrei.firstproject.homework_8_2_cars.data.Car
import by.andrei.firstproject.homework_8_2_cars.database.CarDatabase

class CarAdd : Fragment(R.layout.fragment_car_add) {
    private lateinit var addOwnerName: EditText
    private lateinit var addProducer: EditText
    private lateinit var addModel: EditText
    private lateinit var addPlateNumber: EditText
    private lateinit var dao: CarDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.name_title_add_car)
        }

        addOwnerName = view.findViewById(R.id.editOwnerNameInAddCarLayout)
        addProducer = view.findViewById(R.id.editProducerInAddCarLayout)
        addModel = view.findViewById(R.id.editModelInAddCarLayout)
        addPlateNumber = view.findViewById(R.id.editNumberInAddCarLayout)

        dao = CarDatabase.getDatabase(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_add_car, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                activity?.supportFragmentManager?.popBackStack()
            }
            R.id.button_add_new_car -> {
                val car = createCarObject()
                dao.getCarDAO().insertAll(car)
                activity?.supportFragmentManager?.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
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