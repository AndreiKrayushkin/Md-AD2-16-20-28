package by.andrei.firstproject.homework_5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.andrei.firstproject.homework_5.R
import by.andrei.firstproject.homework_5.data.Car

typealias OnCarClickListener = (car: Car, position: Int) -> Unit

class CarAdapter(
        var carList: MutableList<Car>,
        var onCarClickListener: OnCarClickListener
        ) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cars_list, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car: Car = carList[position]

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
                onCarClickListener.invoke(car, adapterPosition)
            }
        }
    }
}