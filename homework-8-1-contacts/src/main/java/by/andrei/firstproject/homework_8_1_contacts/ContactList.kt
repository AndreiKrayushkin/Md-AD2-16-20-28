package by.andrei.firstproject.homework_8_1_contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.andrei.firstproject.homework_8_1_contacts.adapter.ContactAdapter
import by.andrei.firstproject.homework_8_1_contacts.adapter.OnContactClickListener
import by.andrei.firstproject.homework_8_1_contacts.const.Constants
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.CONTACT_ADD_FRAGMENT
import by.andrei.firstproject.homework_8_1_contacts.data.Contact
import by.andrei.firstproject.homework_8_1_contacts.databinding.FragmentListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactList : Fragment(R.layout.fragment_list) {
    private lateinit var adapterContact: ContactAdapter
    private lateinit var contactClickListener: OnContactClickListener
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNewContact: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNewContact = view.findViewById(R.id.testButtonGoToAddView)
        recyclerView = view.findViewById(R.id.listContacts)
        contactClickListener = object : OnContactClickListener {
            override fun invoke(contact: Contact, position: Int) {
                TODO("Not yet implemented")
            }
        }
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val bundle: Bundle? = arguments
        adapterContact = ContactAdapter(contactClickListener)

        if (bundle != null) {
            val getContact: Contact? = bundle.getParcelable("KEY")
            adapterContact.addContact(getContact)
        }

        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = adapterContact
        }

        addNewContact.setOnClickListener {
            (activity as OnChangeFragmentListener).onFragmentChange(CONTACT_ADD_FRAGMENT, bundle)
        }
    }


}