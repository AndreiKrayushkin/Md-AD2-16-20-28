package by.andrei.firstproject.task4contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter (
        var contactList: MutableList<Contact>,
        var onClickListener: OnContactClickListener
        ): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    interface OnContactClickListener {
        fun onContactClick(contact: Contact, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image_info, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

        val contact: Contact = contactList[position]

        holder.bind(contact)

        holder.itemView.setOnClickListener {
            onClickListener.onContactClick(contact, position)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imageView: ImageView = itemView.findViewById(R.id.imageContacts)
        private var textViewName: TextView = itemView.findViewById(R.id.textContactsName)
        private var textViewPhone: TextView = itemView.findViewById(R.id.textPhoneNumber)

        fun bind(contact: Contact) {
            imageView.setImageResource(contact.imageView)
            textViewName.text = contact.textContactName
            textViewPhone.text = contact.textContactPhoneOrEmail
        }
    }

    fun addContact(contact: Contact) {
        contactList.add(contact)
        notifyDataSetChanged()
    }

    fun editContact(position: Int, contact: Contact) {
        contactList[position] = contact
        notifyItemChanged(position)
    }

    fun removeContact(position: Int) {
        contactList.removeAt(position)
        notifyItemRemoved(position)
    }

}