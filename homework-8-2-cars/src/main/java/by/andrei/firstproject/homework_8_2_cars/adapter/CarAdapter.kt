package by.andrei.firstproject.homework_8_2_cars.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.andrei.firstproject.homework_8_2_cars.R
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.OPERATION_EDIT_CAR
import by.andrei.firstproject.homework_8_2_cars.constants.Constants.OPERATION_WORK_ADD
import by.andrei.firstproject.homework_8_2_cars.data.Car

typealias OnCarClickListener = (car: Car, position: Int, operation: Int) -> Unit

class CarAdapter(
        var carList: MutableList<Car>,
        var onCarClickListener: OnCarClickListener
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.bind(car, onCarClickListener)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var registerNumber: TextView = itemView.findViewById(R.id.numberCar)
        private var producerAuto: TextView = itemView.findViewById(R.id.producerAuto)
        private var modelAuto: TextView = itemView.findViewById(R.id.modelAuto)
        private var owner:TextView = itemView.findViewById(R.id.nameMaster)
        private var photoCar: ImageView = itemView.findViewById(R.id.imageViewCar)
        private var buttonEditCarMainActivity: ImageButton = itemView.findViewById(R.id.buttonClickEditCar)

        fun bind(car: Car, onCarClickListener: OnCarClickListener) {
            registerNumber.text = car.registerNumber
            producerAuto.text = car.producer
            modelAuto.text = car.model
            owner.text = car.nameOwner
            photoCar.setImageResource(car.photo)

            buttonEditCarMainActivity.setOnClickListener {
                onCarClickListener.invoke(car, adapterPosition, OPERATION_EDIT_CAR)
            }
            itemView.setOnClickListener {
                onCarClickListener.invoke(car, adapterPosition, OPERATION_WORK_ADD)
            }
        }
    }
}