package by.andrei.firstproject.task4contacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AddContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val textViewName: EditText = findViewById(R.id.addTextForName)
        val textViewPhoneOrEmail: EditText = findViewById(R.id.addTextForPhoneNumber)
        val buttonAddContactInfo: Button = findViewById(R.id.buttonAdd)
        val textViewTEST: EditText = findViewById(R.id.addTextTESSTT)

        buttonAddContactInfo.setOnClickListener {
            val textName = textViewName.text.toString()
            val textPhoneOrEmail = textViewPhoneOrEmail.text.toString()
            val textTEST = textViewTEST.text.toString()
            val image = R.drawable.ic_baseline_contact_mail_24
            val intent = Intent(this, MainActivity::class.java)
            val contact = Contact(image, textName, textPhoneOrEmail)

            intent.putExtra("TEXT", contact)
            intent.putExtra("TEXT2", textTEST)

            Log.v("LOG_ERR", "add view")
            //startActivityForResult(intent, RESULT_OK)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}