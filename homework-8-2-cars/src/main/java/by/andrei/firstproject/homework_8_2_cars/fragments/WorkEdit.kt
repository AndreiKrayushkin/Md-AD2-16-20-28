package by.andrei.firstproject.homework_8_2_cars.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.andrei.firstproject.homework_8_2_cars.R
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_WORK_ID
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_WORK_NAME
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.KEY_WORK_STATUS
import by.andrei.firstproject.homework_8_2_cars.data.Work
import by.andrei.firstproject.homework_8_2_cars.database.CarDatabase
import by.andrei.firstproject.homework_8_2_cars.database.WorkDAO

class WorkEdit : Fragment(R.layout.fragment_work_edit) {

    private lateinit var editWorkName: EditText
    private lateinit var editCost: EditText
    private lateinit var editDescription: EditText
    private lateinit var dateWork: TextView
    private lateinit var imageStatusWorkPending: ImageView
    private lateinit var imageStatusWorkInProgress: ImageView
    private lateinit var imageStatusWorkCompleted: ImageView
    private lateinit var textStatusWorkPending: TextView
    private lateinit var textStatusWorkInProgress: TextView
    private lateinit var textStatusWorkCompleted: TextView
    private lateinit var workDAO: WorkDAO
    private var workId: Int? = 0
    private var work: Work? = null
    private var color: Int? = null
    private var progress: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editWorkName = view.findViewById(R.id.editWorkNameEditWorkActivity)
        editCost = view.findViewById(R.id.editCostEditWorkActivity)
        editDescription = view.findViewById(R.id.editDescriptionEditWorkActivity)
        dateWork = view.findViewById(R.id.dateWorkEditWorkActivity)
        imageStatusWorkPending = view.findViewById(R.id.statusWorkPendingEditWorkActivity)
        imageStatusWorkInProgress = view.findViewById(R.id.statusProgressEditWorkActivity)
        imageStatusWorkCompleted = view.findViewById(R.id.statusCompletedEditWorkActivity)
        textStatusWorkPending = view.findViewById(R.id.textPendingEditWorkActivity)
        textStatusWorkInProgress = view.findViewById(R.id.textProgressEditWorkActivity)
        textStatusWorkCompleted = view.findViewById(R.id.textCompletedEditWorkActivity)

        setHasOptionsMenu(true)
        val bundle: Bundle? = arguments
        val titleWork = bundle?.getString(KEY_WORK_NAME)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = titleWork
        }

        workDAO = CarDatabase.getDatabase(requireContext()).getWorkDAO()
        getWorkData(bundle)
        if (work != null) {
            setDataInView(bundle)
        }

        imageStatusWorkPending.setOnClickListener {
            setStatusPending()
        }
        imageStatusWorkInProgress.setOnClickListener {
            setStatusInProgress()
        }
        imageStatusWorkCompleted.setOnClickListener {
            setStatusCompleted()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_edit_work, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.supportFragmentManager?.popBackStack()
            }
            R.id.button_apply_edit_work -> {
                val updateWork = updateWorkObject().also {
                    it.id = work?.id
                    it.parentCar = work?.parentCar
                }
                workDAO.update(updateWork)
                activity?.supportFragmentManager?.popBackStack()
            }
            R.id.button_delete_work -> {
                workId?.let { workDAO.deleteThisWork(it) }
                activity?.supportFragmentManager?.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getWorkData(bundle: Bundle?){
        workId = bundle?.getInt(KEY_WORK_ID)
        work = workId?.let { workDAO.getWork(it) }
    }

    private fun setDataInView(bundle: Bundle?) {
        work?.let {
            editWorkName.setText(it.workName)
            editCost.setText(it.cost)
            editDescription.setText(it.description)
            dateWork.text = it.applicationDate
        }

        when (bundle?.getString(KEY_WORK_STATUS)) {
            getString(R.string.progress_pending) -> setStatusPending()
            getString(R.string.progress_in_progress) -> setStatusInProgress()
            getString(R.string.progress_completed) -> setStatusCompleted()
        }
    }

    private fun setStatusPending() {
        imageStatusWorkPending.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_status))
        imageStatusWorkInProgress.setColorFilter(ContextCompat.getColor(requireContext(), R.color.default_status))
        imageStatusWorkCompleted.setColorFilter(ContextCompat.getColor(requireContext(), R.color.default_status))
        color = R.color.red_status
        progress = getString(R.string.progress_pending)
    }
    private fun setStatusInProgress() {
        imageStatusWorkPending.setColorFilter(ContextCompat.getColor(requireContext(), R.color.default_status))
        imageStatusWorkInProgress.setColorFilter(ContextCompat.getColor(requireContext(), R.color.yellow_status))
        imageStatusWorkCompleted.setColorFilter(ContextCompat.getColor(requireContext(), R.color.default_status))
        color = R.color.yellow_status
        progress = getString(R.string.progress_in_progress)
    }
    private fun setStatusCompleted() {
        imageStatusWorkPending.setColorFilter(ContextCompat.getColor(requireContext(), R.color.default_status))
        imageStatusWorkInProgress.setColorFilter(ContextCompat.getColor(requireContext(), R.color.default_status))
        imageStatusWorkCompleted.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green_status))
        color = R.color.green_status
        progress = getString(R.string.progress_completed)
    }
    private fun updateWorkObject() =
            Work(
                    workName = editWorkName.text.toString(),
                    cost = editCost.text.toString(),
                    description = editDescription.text.toString(),
                    applicationDate = dateWork.text.toString(),
                    progressWork = progress,
                    colorStatus = color
            )
}