package by.andrei.firstproject.task4contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

private const val THIS_POSITION = "THIS_POSITION"
private const val THIS_CONTACT = "THIS_CONTACT"
private const val CORRECTED_CONTACT = "CORRECTED_CONTACT"

class EditContact : AppCompatActivity(){
    private lateinit var editTextForName: EditText
    private lateinit var editTextForPhoneOrMail: EditText
    private lateinit var buttonEdit: Button
    private lateinit var buttonRemove: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        editTextForName = findViewById(R.id.editTextForName)
        editTextForPhoneOrMail = findViewById(R.id.editTextForEmail)
        buttonEdit = findViewById(R.id.buttonEdit)
        buttonRemove = findViewById(R.id.buttonRemove)

        val intent = intent
        var contact: Contact? = intent.getParcelableExtra(THIS_CONTACT)
        val position = intent.getIntExtra(THIS_POSITION, 0)

        editTextForName.setText(contact?.textContactName)
        editTextForPhoneOrMail.setText(contact?.textContactPhoneOrEmail)

        buttonEdit.setOnClickListener {
            val getEditTextForName = editTextForName.text.toString()
            val getEditTextForPhoneOrMail = editTextForPhoneOrMail.text.toString()
            val getImageView: Int? = contact?.imageView
            contact = Contact(getImageView!!, getEditTextForName, getEditTextForPhoneOrMail)
            intent.putExtra(CORRECTED_CONTACT, contact)
            intent.putExtra(THIS_POSITION, position)
            setResult(RESULT_OK, intent)
            finish()
        }
        buttonRemove.setOnClickListener {
            contact = null
            intent.putExtra(CORRECTED_CONTACT, contact)
            intent.putExtra(THIS_POSITION, position)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}