package by.andrei.firstproject.homework_8_2_cars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_CAR_ADD
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_CAR_EDIT
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_CAR_LIST
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_WORK_ADD
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_WORK_EDIT
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_WORK_LIST
import by.andrei.firstproject.homework_8_2_cars.fragments.*

class MainActivity : AppCompatActivity(), OnChangeFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        showFragment()
    }

    override fun onFragmentChange(fragmentConst: Int, bundle: Bundle?) {
        when(fragmentConst) {
            FRAGMENT_CAR_LIST -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, CarList()::class.java, bundle)
                    .addToBackStack(null)
                    .commit()
            FRAGMENT_CAR_ADD -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, CarAdd()::class.java, bundle)
                    .addToBackStack(null)
                    .commit()
            FRAGMENT_CAR_EDIT -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, CarEdit()::class.java, bundle)
                    .addToBackStack(null)
                    .commit()
            FRAGMENT_WORK_LIST -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, WorkList()::class.java, bundle)
                    .addToBackStack(null)
                    .commit()
            FRAGMENT_WORK_ADD -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, WorkAdd()::class.java, bundle)
                    .addToBackStack(null)
                    .commit()
            FRAGMENT_WORK_EDIT -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, WorkEdit()::class.java, bundle)
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun showFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, CarList())
                .commit()
    }
}