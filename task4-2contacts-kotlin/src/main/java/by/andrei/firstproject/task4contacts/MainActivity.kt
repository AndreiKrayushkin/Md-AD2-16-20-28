package by.andrei.firstproject.task4contacts

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val SECOND_ACTIVITY_REQUEST_CODE = 0

    //Тут не понял, что в качестве параметра идет
    private val adapterContact: ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.listContacts)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        recyclerView.layoutManager = linearLayoutManager

        //И тут не совсем понял что нужно передать
        recyclerView.adapter = ContactAdapter( )

        val buttonGoToAddView: Button = findViewById(R.id.testButtonGoToAddView)
        buttonGoToAddView.setOnClickListener {
            val intent = Intent(this, AddContact::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {

                val contactView: Contact? = data?.getParcelableExtra("TEXT")
                if (contactView != null) {
                    //тут добавляем полученные данные в адаптер
                    adapterContact.addContact(contactView)
                }
            }
        }


    }
}