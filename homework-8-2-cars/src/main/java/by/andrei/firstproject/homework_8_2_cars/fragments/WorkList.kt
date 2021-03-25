package by.andrei.firstproject.homework_8_2_cars.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.andrei.firstproject.homework_8_2_cars.OnChangeFragmentListener
import by.andrei.firstproject.homework_8_2_cars.R
import by.andrei.firstproject.homework_8_2_cars.adapter.OnWorkClickListener
import by.andrei.firstproject.homework_8_2_cars.adapter.WorkAdapter
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_WORK_ADD
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_WORK_EDIT
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_CAR_ID
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_CAR_MODEL
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_CAR_NAME
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_PARENT_CAR
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_WORK_ID
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_WORK_NAME
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_WORK_STATUS
import by.andrei.firstproject.homework_8_2_cars.data.Work
import by.andrei.firstproject.homework_8_2_cars.database.CarDatabase
import by.andrei.firstproject.homework_8_2_cars.database.WorkDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WorkList : Fragment(R.layout.fragment_work_list) {
    private lateinit var workAdapter: WorkAdapter
    private lateinit var buttonAddWork: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var onWorkClickListener: OnWorkClickListener
    private lateinit var workDAO: WorkDAO

    private var parentCar: String? = null
    private var carId: Int? = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val bundle: Bundle? = arguments
        val producerCar = bundle?.getString(KEY_CAR_NAME)
        val modelCar = bundle?.getString(KEY_CAR_MODEL)

        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "$producerCar $modelCar"
        }

        recyclerView = view.findViewById(R.id.recyclerViewWorksAtTheCar)
        buttonAddWork = view.findViewById(R.id.worksFloatingButtonAddWorks)

        getIntentData(bundle)
        workDAO = CarDatabase.getDatabase(requireContext()).getWorkDAO()

        onWorkClickListener = object : OnWorkClickListener {
            override fun invoke(work: Work, position: Int) {
                work.id?.let { bundle?.putInt(KEY_WORK_ID, it) }
                bundle?.putString(KEY_WORK_STATUS, work.progressWork)
                bundle?.putString(KEY_WORK_NAME, work.workName)
                (activity as OnChangeFragmentListener).onFragmentChange(FRAGMENT_WORK_EDIT, bundle)
            }
        }

        buttonAddWork.setOnClickListener {
            bundle?.putString(KEY_PARENT_CAR, parentCar)
            (activity as OnChangeFragmentListener).onFragmentChange(FRAGMENT_WORK_ADD, bundle)
        }

        val linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        workAdapter = WorkAdapter(mutableListOf(), onWorkClickListener)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = workAdapter
        }
        checkDataBase()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_list_work, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.supportFragmentManager?.popBackStack()
//                (activity as OnChangeFragmentListener).onFragmentChange(FRAGMENT_CAR_LIST, null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getIntentData(bundle: Bundle?) {
        carId = bundle?.getInt(KEY_CAR_ID)
        parentCar = bundle?.getString(KEY_CAR_MODEL)
    }

    private fun checkDataBase() {
        val workList = workDAO.getWorkFromParentsCar(parentCar)
        workAdapter.workList = workList
        workAdapter.notifyDataSetChanged()
    }
}
