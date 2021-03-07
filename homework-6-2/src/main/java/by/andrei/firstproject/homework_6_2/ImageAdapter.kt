package by.andrei.firstproject.homework_6_2

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private var listURI = arrayListOf<Uri>()
    lateinit var showImageListener: (Uri) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view, showImageListener)
    }

    override fun onBindViewHolder(holder: ImageAdapter.ImageViewHolder, position: Int) {
        holder.bind(listURI[position])
    }

    override fun getItemCount(): Int {
        return listURI.size
    }

    fun updateList(list: ArrayList<Uri>) {
        listURI = ArrayList(list)
        notifyDataSetChanged()
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        constructor(itemView: View, showFullImageListener: (Uri) -> Unit) :this(itemView) {
            this.showImageListener = showFullImageListener
        }
        private lateinit var showImageListener: (Uri) -> Unit
        private val image = itemView.findViewById<ImageView>(R.id.itemPhoto)
        fun bind(path: Uri) {
            Glide.with(itemView.context).load(path).into(image)
            itemView.setOnClickListener {
                showImageListener.invoke(path)
            }
        }
    }
}