package by.andrei.firstproject.task4contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val CONTACT_INFO = "CONTACT_INFO"
private const val ADD_KEY = 0
private const val EDIT_KEY = 1
private const val THIS_POSITION = "THIS_POSITION"
private const val THIS_CONTACT = "THIS_CONTACT"
private const val CORRECTED_CONTACT = "CORRECTED_CONTACT"

class MainActivity : AppCompatActivity() {

    private lateinit var adapterContact: ContactAdapter
    private lateinit var adapterContactListener: OnContactClickListener

    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbarMain: Toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        val recyclerView: RecyclerView = findViewById(R.id.listContacts)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val intentGoEdit = Intent(this, EditContact::class.java)

        adapterContactListener = object : OnContactClickListener {

            override fun invoke(contact: Contact, position: Int) {
                this@MainActivity.contact = adapterContact.contactList[position]
                setResult(RESULT_OK, intentGoEdit)
                intentGoEdit.putExtra(THIS_CONTACT, contact)
                intentGoEdit.putExtra(THIS_POSITION, position)
                setResult(RESULT_OK, intentGoEdit)
                startActivityForResult(intentGoEdit, EDIT_KEY)
            }
        }

        adapterContact = ContactAdapter(mutableListOf<Contact>(), adapterContactListener)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapterContact

        val buttonGoToAddView: FloatingActionButton = findViewById(R.id.testButtonGoToAddView)
        buttonGoToAddView.setOnClickListener {
            val intent = Intent(this, AddContact::class.java)
            startActivityForResult(intent, ADD_KEY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var contactView: Contact?
        if (requestCode == ADD_KEY) {
            if(resultCode == RESULT_OK) {
                val tBG = findViewById<TextView>(R.id.backgroundTextInfo)
                contactView = data?.getParcelableExtra(CONTACT_INFO)
                if (contactView != null) {
                    adapterContact.addContact(contactView)
                    tBG.visibility = View.INVISIBLE
                }
            }
        }

        if (requestCode == EDIT_KEY) {
            if(resultCode == RESULT_OK) {
                contactView = data?.getParcelableExtra(CORRECTED_CONTACT)
                val position = data?.getIntExtra(THIS_POSITION, 0)
                if (contactView != null) {
                    adapterContact.editContact(position!!, contactView)
                } else {
                    adapterContact.removeContact(position!!)
                }
            }
        }
    }
}