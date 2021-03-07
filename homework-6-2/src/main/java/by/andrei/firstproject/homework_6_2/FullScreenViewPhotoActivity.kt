package by.andrei.firstproject.homework_6_2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

private const val PATH = "path"

class FullScreenViewPhotoActivity : AppCompatActivity() {

    private lateinit var fullScreenPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiry_view_this_image)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fullScreenPhoto = findViewById(R.id.photo_view_full_screen)
        loadImage()
    }

    private fun getPathFromIntent(): Uri? {
        val path = intent.getParcelableExtra<Uri>(PATH)
        if (path != null) {
            return path
        }
        return null
    }

    private fun loadImage() {
        val path = getPathFromIntent()
        if (path != null) {
            Glide.with(this).load(path).into(fullScreenPhoto)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivityForResult(intent, 0)
        return true
    }
}