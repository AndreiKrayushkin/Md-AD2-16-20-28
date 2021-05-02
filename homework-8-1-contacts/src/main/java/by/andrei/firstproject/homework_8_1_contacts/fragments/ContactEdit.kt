package by.andrei.firstproject.homework_8_1_contacts.fragments

import android.os.Bundle
import android.view.View
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.andrei.firstproject.homework_8_1_contacts.OnChangeFragmentListener
import by.andrei.firstproject.homework_8_1_contacts.R
import by.andrei.firstproject.homework_8_1_contacts.const.Constants
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.KEY_CONTACT_ID
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.KEY_CONTACT_NAME
import by.andrei.firstproject.homework_8_1_contacts.data.Contact
import by.andrei.firstproject.homework_8_1_contacts.database.ContactDB
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactEdit : Fragment(R.layout.fragment_edit) {

    private lateinit var nameEditText: EditText
    private lateinit var phoneOrEmailEditText: EditText
    private lateinit var buttonDeleteThisContact: FloatingActionButton
    private lateinit var database: ContactDB
    private var thisContact: Contact? = null
    private var name: String? = ""
    private var contactId: Int? = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val bundle: Bundle? = arguments
        contactId = bundle?.getInt(KEY_CONTACT_ID)
        name = bundle?.getString(KEY_CONTACT_NAME)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = name
        }

        database = ContactDB.getDatabase(requireContext())
        nameEditText = view.findViewById(R.id.editTextForName)
        phoneOrEmailEditText = view.findViewById(R.id.editTextForEmail)
        buttonDeleteThisContact = view.findViewById(R.id.buttonDeleteThisContact)

        thisContact = contactId?.let { database.getContactDAO().getThisContact(it) }
        thisContact?.let { setTextInThisFragment(it) }

        buttonDeleteThisContact.setOnClickListener {
            val thisContactId = thisContact?.id
            thisContactId?.let { it1 -> database.getContactDAO().deleteThisContact(it1) }
            (activity as OnChangeFragmentListener).onFragmentChange(Constants.CONTACT_LIST_FRAGMENT, null)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_edit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                (activity as OnChangeFragmentListener).onFragmentChange(Constants.CONTACT_LIST_FRAGMENT, null)
            }
            R.id.button_apply_edit -> {
                val editContact = thisContact?.let { it1 -> getEditContact(it1).also { it.id = contactId } }
                editContact?.let { it1 -> database.getContactDAO().updateContact(it1) }
                (activity as OnChangeFragmentListener).onFragmentChange(Constants.CONTACT_LIST_FRAGMENT, null)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTextInThisFragment(contact: Contact) {
        contact.apply{
            nameEditText.setText(this.nameContact)
            phoneOrEmailEditText.setText(this.phoneOrEmailContact)
        }
    }

    private fun getEditContact(contact: Contact) =
            Contact(
                    imageView = contact.imageView,
                    nameContact = nameEditText.text.toString(),
                    phoneOrEmailContact = phoneOrEmailEditText.text.toString()
            )
}