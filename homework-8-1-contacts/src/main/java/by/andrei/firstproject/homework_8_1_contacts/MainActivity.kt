package by.andrei.firstproject.homework_8_1_contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.CONTACT_ADD_FRAGMENT
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.CONTACT_EDIT_FRAGMENT
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.CONTACT_LIST_FRAGMENT
import by.andrei.firstproject.homework_8_1_contacts.fragments.ContactAdd
import by.andrei.firstproject.homework_8_1_contacts.fragments.ContactEdit
import by.andrei.firstproject.homework_8_1_contacts.fragments.ContactList

class MainActivity : AppCompatActivity(), OnChangeFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        showAddContactFragment()
    }

    private fun showAddContactFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ContactList())
                .commit()
    }

    override fun onFragmentChange(fragmentConst: Int, bundle: Bundle?) {
        when(fragmentConst) {
            CONTACT_ADD_FRAGMENT -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ContactAdd()::class.java, bundle)
                    .commit()
            CONTACT_LIST_FRAGMENT -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ContactList()::class.java, bundle)
                    .commit()
            CONTACT_EDIT_FRAGMENT -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ContactEdit()::class.java, bundle)
                    .commit()
        }
    }
}