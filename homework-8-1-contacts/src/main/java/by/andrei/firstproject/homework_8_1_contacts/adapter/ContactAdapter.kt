package by.andrei.firstproject.homework_8_1_contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.andrei.firstproject.homework_8_1_contacts.R
import by.andrei.firstproject.homework_8_1_contacts.data.Contact

typealias OnContactClickListener = (contact: Contact, position: Int) -> Unit

class ContactAdapter(
        var contactList: MutableList<Contact>,
        var onClickListener: OnContactClickListener
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)

        holder.itemView.setOnClickListener {
            onClickListener.invoke(contact, position)
        }
    }

    override fun getItemCount(): Int = contactList.size

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imageView: ImageView = itemView.findViewById(R.id.imageContacts)
        private var nameContact: TextView = itemView.findViewById(R.id.textContactsName)
        private var phoneOrEmail: TextView = itemView.findViewById(R.id.textPhoneNumber)

        fun bind(contact: Contact) {
            imageView.setImageResource(contact.imageView)
            nameContact.text = contact.nameContact
            phoneOrEmail.text = contact.phoneOrEmailContact
        }
    }

    fun addContact(contact: Contact?) {
        contactList.add(contact!!)
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