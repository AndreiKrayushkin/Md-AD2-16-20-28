package by.andrei.firstproject.homework_8_2_cars.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.andrei.firstproject.homework_8_2_cars.OnChangeFragmentListener
import by.andrei.firstproject.homework_8_2_cars.R
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_CAR_LIST
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.FRAGMENT_WORK_LIST
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_PARENT_CAR
import by.andrei.firstproject.homework_8_2_cars.data.Work
import by.andrei.firstproject.homework_8_2_cars.database.CarDatabase
import by.andrei.firstproject.homework_8_2_cars.database.WorkDAO
import java.text.SimpleDateFormat
import java.util.*

class WorkAdd : Fragment(R.layout.fragment_work_add) {
    private lateinit var dateWork: TextView
    private lateinit var nameWork: EditText
    private lateinit var cost: EditText
    private lateinit var description: EditText
    private lateinit var imagePendingWork: ImageView
    private lateinit var imageInProgressWork: ImageView
    private lateinit var imageCompletedWork: ImageView
    private lateinit var textPendingWork: TextView
    private lateinit var textInProgressWork: TextView
    private lateinit var textCompletedWork: TextView
    private lateinit var workDAO: WorkDAO
    private var parentCarName: String? = null
    private var color: Int? = null
    private var progress: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val bundle: Bundle? = arguments
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.content_description_add_work_button)
        }

        dateWork = view.findViewById(R.id.dateWorkAddWork)
        nameWork = view.findViewById(R.id.editWorkName)
        cost = view.findViewById(R.id.editCost)
        description = view.findViewById(R.id.editDescription)
        imagePendingWork = view.findViewById(R.id.statusWorkPendingAddWorkActivity)
        imageInProgressWork = view.findViewById(R.id.statusProgressAddWorkActivity)
        imageCompletedWork = view.findViewById(R.id.statusCompletedAddWorkActivity)
        textPendingWork = view.findViewById(R.id.textPendingAddWorkActivity)
        textInProgressWork = view.findViewById(R.id.textProgressAddWorkActivity)
        textCompletedWork = view.findViewById(R.id.textCompletedAddWorkActivity)

        parentCarName = bundle?.getString(KEY_PARENT_CAR)
        dateWork.text = getTimeWork()

        workDAO = CarDatabase.getDatabase(requireContext()).getWorkDAO()
        setProgress()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_add_work, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                activity?.supportFragmentManager?.popBackStack()
            }
            R.id.button_add_new_work -> {
                createWorkObject().apply {
                    parentCar = parentCarName
                    workDAO.insertAll(this)
                    activity?.supportFragmentManager?.popBackStack()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getTimeWork() = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault()).format(Date())

    private fun createWorkObject() =
            Work(
                    workName = nameWork.text.toString(),
                    cost = cost.text.toString(),
                    description = description.text.toString(),
                    applicationDate = getTimeWork(),
                    progressWork = progress,
                    colorStatus = color
            )

    private fun setProgress() {
        imagePendingWork.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_status))
        textPendingWork.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_status))
        color = R.color.red_status
        progress = getString(R.string.pending_status)
    }
}