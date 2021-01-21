package by.andrei.firstproject.task4contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditContact : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val intent = intent
        val editTextForName: EditText = findViewById(R.id.editTextForName)
        val editTextForPhoneOrMail: EditText = findViewById(R.id.editTextForEmail)
        val buttonEdit: Button = findViewById(R.id.buttonEdit)
        val buttonRemove: Button = findViewById(R.id.buttonRemove)
        var contact: Contact? = intent?.getParcelableExtra("contact")
        val position = intent.getIntExtra("position", 0)

        editTextForName.setText(contact?.textContactName)
        editTextForPhoneOrMail.setText(contact?.textContactPhoneOrEmail)

        buttonEdit.setOnClickListener {
            val getEditTextForName = editTextForName.text.toString()
            val getEditTextForPhoneOrMail = editTextForPhoneOrMail.text.toString()
            val getImageView: Int? = contact?.imageView
            contact = Contact(getImageView!!, getEditTextForName, getEditTextForPhoneOrMail)
            intent.putExtra("edit text", contact)
            intent.putExtra("position", position)
            setResult(RESULT_OK, intent)
            finish()
        }
        buttonRemove.setOnClickListener {
            contact = null
            intent.putExtra("edit text", contact)
            intent.putExtra("position", position)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}