package by.andrei.firstproject.homework_8_1_contacts.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.andrei.firstproject.homework_8_1_contacts.OnChangeFragmentListener
import by.andrei.firstproject.homework_8_1_contacts.R
import by.andrei.firstproject.homework_8_1_contacts.adapter.ContactAdapter
import by.andrei.firstproject.homework_8_1_contacts.adapter.OnContactClickListener
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.CONTACT_ADD_FRAGMENT
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.CONTACT_EDIT_FRAGMENT
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.KEY_CONTACT_ID
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.KEY_CONTACT_NAME
import by.andrei.firstproject.homework_8_1_contacts.data.Contact
import by.andrei.firstproject.homework_8_1_contacts.database.ContactDB
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactList : Fragment(R.layout.fragment_list) {
    private lateinit var adapterContact: ContactAdapter
    private lateinit var contactClickListener: OnContactClickListener
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNewContact: FloatingActionButton
    private lateinit var database: ContactDB
    private lateinit var backgroundTextInfo: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setTitle(R.string.app_name)
        }
        addNewContact = view.findViewById(R.id.testButtonGoToAddView)
        recyclerView = view.findViewById(R.id.listContacts)
        backgroundTextInfo = view.findViewById(R.id.backgroundTextInfo)

        database = ContactDB.getDatabase(requireContext())
        contactClickListener = object : OnContactClickListener {
            override fun invoke(contact: Contact, position: Int) {
                val bundle = Bundle()
                contact.id?.let { bundle.putInt(KEY_CONTACT_ID, it) }
                bundle.putString(KEY_CONTACT_NAME, contact.nameContact)
                (activity as OnChangeFragmentListener).onFragmentChange(CONTACT_EDIT_FRAGMENT, bundle)
            }
        }

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapterContact = ContactAdapter(arrayListOf(), contactClickListener)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = adapterContact
        }

        addNewContact.setOnClickListener {
            (activity as OnChangeFragmentListener).onFragmentChange(CONTACT_ADD_FRAGMENT, null)
        }

        if (adapterContact != null) {
            backgroundTextInfo.visibility = View.INVISIBLE
        }
        checkDB()
    }

    private fun checkDB() {
        val contactList = database.getContactDAO().getContactList()
        adapterContact.contactList = contactList as ArrayList<Contact>
        adapterContact.notifyDataSetChanged()
    }
}