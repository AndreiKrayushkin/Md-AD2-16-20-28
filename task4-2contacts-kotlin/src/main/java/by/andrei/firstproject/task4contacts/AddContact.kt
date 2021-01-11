package by.andrei.firstproject.task4contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val setTextName: EditText = findViewById(R.id.addTextForName)
        val setTextPhoneOrEmail: EditText = findViewById(R.id.addTextForPhoneNumber)
        val buttonAddContactInfo: Button = findViewById(R.id.buttonAdd)

        buttonAddContactInfo.setOnClickListener {
            val textName = setTextName.text.toString()
            val textPhoneOrEmail = setTextPhoneOrEmail.text.toString()
            val image = R.drawable.ic_baseline_contact_mail_24

            val intent = Intent(this, MainActivity::class.java)
            val contact = Contact(image, textName, textPhoneOrEmail)

            intent.putExtra("TEXT", contact)

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}