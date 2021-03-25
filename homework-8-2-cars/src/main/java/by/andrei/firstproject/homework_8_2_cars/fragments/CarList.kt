package by.andrei.firstproject.homework_8_2_cars.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.andrei.firstproject.homework_8_2_cars.OnChangeFragmentListener
import by.andrei.firstproject.homework_8_2_cars.R
import by.andrei.firstproject.homework_8_2_cars.adapter.CarAdapter
import by.andrei.firstproject.homework_8_2_cars.adapter.OnCarClickListener
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_CAR_ADD
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_CAR_EDIT
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_WORK_LIST
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_CAR_ID
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_CAR_MODEL
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_CAR_NAME
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.OPERATION_EDIT_CAR
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.OPERATION_WORK_ADD
import by.andrei.firstproject.homework_8_2_cars.data.Car
import by.andrei.firstproject.homework_8_2_cars.database.CarDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarList : Fragment(R.layout.fragment_car_list) {
    private lateinit var carAdapter: CarAdapter
    private lateinit var buttonGoToAddCar: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var onCarClickListener: OnCarClickListener
    private lateinit var dao: CarDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setTitle(R.string.app_name)
        }

        recyclerView = view.findViewById(R.id.recyclerViewCars)
        buttonGoToAddCar = view.findViewById(R.id.mainFloatingButtonAddCars)

        dao = CarDatabase.getDatabase(requireContext())

        val linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        //клик по кнопке добавления автомобилей
        buttonGoToAddCar.setOnClickListener{
            (activity as OnChangeFragmentListener).onFragmentChange(FRAGMENT_CAR_ADD, null)
        }

        //клик по кнопке "редактировать автомобиль", либо по объекту автомобиля
        onCarClickListener = object : OnCarClickListener {
            override fun invoke(car: Car, position: Int, operation: Int) {
                when(operation){
                    OPERATION_EDIT_CAR -> {
                        val bundle = Bundle()
                        car.id?.let { bundle.putInt(KEY_CAR_ID, it) }
                        (activity as OnChangeFragmentListener).onFragmentChange(FRAGMENT_CAR_EDIT, bundle)
                    }
                    OPERATION_WORK_ADD -> {
                        val bundle = Bundle()
                        car.id?.let { bundle.putInt(KEY_CAR_ID, it) }
                        bundle.putString(KEY_CAR_NAME, car.producer)
                        bundle.putString(KEY_CAR_MODEL, car.model)
                        (activity as OnChangeFragmentListener).onFragmentChange(FRAGMENT_WORK_LIST, bundle)
                    }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_list_car, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun checkDataBase() {
        val carLists = dao.getCarDAO().getCarsList()
        carAdapter.carList = carLists
        carAdapter.notifyDataSetChanged()
    }
}