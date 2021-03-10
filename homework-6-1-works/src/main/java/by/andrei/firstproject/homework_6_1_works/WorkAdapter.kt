package by.andrei.firstproject.homework_6_1_works

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WorkAdapter (
        var workList: List<Work>
        ) :RecyclerView.Adapter<WorkAdapter.WorkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_work_list, parent, false)
        return WorkViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        val work: Work = workList[position]
        holder.bind(work)
    }

    override fun getItemCount(): Int = workList.size

    fun updateList(list: ArrayList<Work>) {
        workList = ArrayList(list)
        notifyDataSetChanged()
    }

    class WorkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var workName: TextView = itemView.findViewById(R.id.nameWorkInWorkList)
        private var workDate: TextView = itemView.findViewById(R.id.dateWorkInWorkList)
        private var workCost: TextView = itemView.findViewById(R.id.costInWorkList)
        private var workProgress: TextView = itemView.findViewById(R.id.textStatusInWorkList)
        private var workStatusImage: ImageView = itemView.findViewById(R.id.statusWorkInWorkList)
        fun bind(work: Work){
            workName.text = work.workName
            workDate.text = work.applicationDate
            workCost.text = work.cost
            workProgress.text = work.progressWork
            workStatusImage.setColorFilter(ContextCompat.getColor(itemView.context, work.colorStatus!!))
        }
    }
}