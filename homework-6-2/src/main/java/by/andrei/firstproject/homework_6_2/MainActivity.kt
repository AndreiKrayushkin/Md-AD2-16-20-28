package by.andrei.firstproject.homework_6_2

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Manifest
private const val PERMISSION_CODE = 1
private const val PATH = "path"
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: ImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.photo_group)
        photoAdapter = ImageAdapter()
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = photoAdapter
        }
        checkPermissions()
    }

    private fun loadAllPhotos() {
        photoAdapter.updateList(ImageLoader.loadAddPhotos(this))
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_CODE)
        } else {
            loadAllPhotos()
            setAdapterListener()
        }
    }

    private fun setAdapterListener() {
        photoAdapter.showImageListener = {
            val intent = Intent(this, FullScreenViewPhotoActivity::class.java)
            intent.putExtra(PATH, it)
            startActivityForResult(intent, 1)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(recyclerView, "Разрешение предоставлено", Snackbar.LENGTH_SHORT).show()
                loadAllPhotos()
            }
        } else {
            Snackbar.make(recyclerView, "Разрешение не предоставлено", Snackbar.LENGTH_SHORT).show()
        }
    }
}