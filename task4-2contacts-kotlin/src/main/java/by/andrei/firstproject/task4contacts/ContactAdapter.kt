package by.andrei.firstproject.task4contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

//    private lateinit var contList: List
    private var contactList = mutableListOf<Contact>()

    fun ContactAdapter (contactList: MutableList<Contact>) {
        this.contactList = contactList
        notifyDataSetChanged()
    }
    fun getContactList() = contactList

    //private val contactList: MutableList<Contact> = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image_info, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageContacts)
        private val textViewName: TextView = itemView.findViewById(R.id.textContactsName)
        private val textViewPhone: TextView = itemView.findViewById(R.id.textPhoneNumber)

        fun bind (contact: Contact) {
            imageView.setImageResource(contact.imageView)
            textViewName.text = contact.textContactName
            textViewPhone.text = contact.textContactPhoneOrEmail
        }
    }

    fun addContact(contact: Contact) {
//        contactList.add(contact)
//        notifyItemChanged(contactList.indexOf(contact))
        notifyDataSetChanged()
    }

}