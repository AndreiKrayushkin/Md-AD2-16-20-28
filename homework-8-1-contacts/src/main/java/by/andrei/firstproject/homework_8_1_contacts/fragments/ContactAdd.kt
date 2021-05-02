package by.andrei.firstproject.homework_8_1_contacts.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.andrei.firstproject.homework_8_1_contacts.OnChangeFragmentListener
import by.andrei.firstproject.homework_8_1_contacts.R
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.CONTACT_LIST_FRAGMENT
import by.andrei.firstproject.homework_8_1_contacts.data.Contact
import by.andrei.firstproject.homework_8_1_contacts.database.ContactDB

class ContactAdd : Fragment(R.layout.fragment_add) {
    private lateinit var setTextName: EditText
    private lateinit var setTextPhoneOrEmail: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioPhone: RadioButton
    private lateinit var radioEmail: RadioButton
    private lateinit var database: ContactDB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.fragment_add_name_title)
        }
        setTextName = view.findViewById(R.id.addTextForName)
        setTextPhoneOrEmail = view.findViewById(R.id.addTextForPhoneNumber)
        radioGroup = view.findViewById(R.id.radioGroupAdd)
        radioPhone = view.findViewById(R.id.phoneCheck)
        radioEmail = view.findViewById(R.id.emailCheck)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            view.findViewById<RadioButton>(checkedId)?.apply {
                setTextPhoneOrEmail.hint = text
            }
        }
        database = ContactDB.getDatabase(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                (activity as OnChangeFragmentListener).onFragmentChange(CONTACT_LIST_FRAGMENT, null)
            }
            R.id.icon_add_new_contact -> {
                val contact = createContactObject()
                database.getContactDAO().addContact(contact)
                (activity as OnChangeFragmentListener).onFragmentChange(CONTACT_LIST_FRAGMENT, null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createContactObject() =
            Contact(
                    imageView = if(radioPhone.isChecked) {
                        R.drawable.ic_baseline_local_phone_24
                    } else {
                        R.drawable.ic_baseline_local_post_office_24
                    },
                    nameContact = setTextName.text.toString(),
                    phoneOrEmailContact = setTextPhoneOrEmail.text.toString()
            )
}