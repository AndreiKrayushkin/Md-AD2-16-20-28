package by.andrei.firstproject.homework_8_1_contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.CONTACT_ADD_FRAGMENT
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.CONTACT_LIST_FRAGMENT

class MainActivity : AppCompatActivity(), OnChangeFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                    .replace<ContactAdd>(R.id.fragment_container, "", bundle)
                    .commit()
            CONTACT_LIST_FRAGMENT -> supportFragmentManager.beginTransaction()
                    .replace<ContactList>(R.id.fragment_container, "", bundle)
                    .commit()
        }
    }
}