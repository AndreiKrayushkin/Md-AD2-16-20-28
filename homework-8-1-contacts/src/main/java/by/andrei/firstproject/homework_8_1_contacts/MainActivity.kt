package by.andrei.firstproject.homework_8_1_contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showAddContactFragment()
    }

    private fun showAddContactFragment() {
        val bundle = bundleOf("KEY" to "VALUE FOR THE ADD CONTACT")
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ContactAdd())
                .commit()
    }
}