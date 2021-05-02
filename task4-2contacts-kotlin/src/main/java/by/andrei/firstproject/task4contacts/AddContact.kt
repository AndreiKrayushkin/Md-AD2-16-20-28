package by.andrei.firstproject.task4contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

private const val CONTACT_INFO = "CONTACT_INFO"

class AddContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val setTextName: EditText = findViewById(R.id.addTextForName)
        val setTextPhoneOrEmail: EditText = findViewById(R.id.addTextForPhoneNumber)
        val buttonAddContactInfo: Button = findViewById(R.id.buttonAdd)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroupAdd)
        val radioPhone: RadioButton = findViewById(R.id.phoneCheck)
        val radioEmail: RadioButton = findViewById(R.id.emailCheck)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            findViewById<RadioButton>(checkedId)?.apply {
                setTextPhoneOrEmail.hint = text
            }
        }

        buttonAddContactInfo.setOnClickListener {
            val textName = setTextName.text.toString()
            val textPhoneOrEmail = setTextPhoneOrEmail.text.toString()
            val image = if (radioPhone.isChecked) {
                R.drawable.ic_baseline_contact_phone_24
            } else {
                R.drawable.ic_baseline_contact_mail_24
            }

            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                putExtra(CONTACT_INFO, Contact(image, textName, textPhoneOrEmail))
            }

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}