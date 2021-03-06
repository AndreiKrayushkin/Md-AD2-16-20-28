package by.andrei.firstproject.homework_7_2_coroutines

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import by.andrei.firstproject.homework_7_2_coroutines.data.Car
import by.andrei.firstproject.homework_7_2_coroutines.data.CarDatabaseRepository
import by.andrei.firstproject.homework_7_2_coroutines.data.WorkDatabaseRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import java.util.*


private const val FORMAT_PHOTO = ".jpg"
private const val FILE_PROVIDER_AUTHORITY = "by.andrei.firstproject.homework_7_2_coroutines.fileprovider"
private const val CAR_ID = "CAR ID"

class AddCar : AppCompatActivity() {
    private lateinit var addOwnerName: EditText
    private lateinit var addProducer: EditText
    private lateinit var addModel: EditText
    private lateinit var addPlateNumber: EditText
    private lateinit var buttonAddCar: ImageButton
    private lateinit var buttonBackToCarList: ImageButton
    private lateinit var addPhoto: FloatingActionButton
    private lateinit var photoCar: ImageView
    private lateinit var carDatabaseRepository: CarDatabaseRepository
    private lateinit var workDatabaseRepository: WorkDatabaseRepository

    private val activityScope = CoroutineScope(Dispatchers.Main + Job())

    private var photoFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_car)
        addOwnerName = findViewById(R.id.editOwnerNameInAddCarLayout)
        addProducer = findViewById(R.id.editProducerInAddCarLayout)
        addModel = findViewById(R.id.editModelInAddCarLayout)
        addPlateNumber = findViewById(R.id.editNumberInAddCarLayout)
        buttonAddCar = findViewById(R.id.checkButtonAddCarListActivity)
        buttonBackToCarList = findViewById(R.id.backButtonAddCarListActivity)
        addPhoto = findViewById(R.id.photoFloatingButtonInAddCarLayout)
        photoCar = findViewById(R.id.imageViewCarInAddCarLayout)

//        database = Repository(this)
        carDatabaseRepository = CarDatabaseRepository(this)
        workDatabaseRepository = WorkDatabaseRepository(this)

        buttonAddCar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val car = createCarObject()
            activityScope.launch {
                carDatabaseRepository.insertAll(car)
                intent.putExtra(CAR_ID, car.id)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
        buttonBackToCarList.setOnClickListener {
            finish()
        }
        addPhoto.setOnClickListener {
            photoFile = File(this.filesDir, UUID.randomUUID().toString() + FORMAT_PHOTO)
            val photoUri = FileProvider.getUriForFile(this, FILE_PROVIDER_AUTHORITY, photoFile!!)
            val intentGetPhoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intentGetPhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            getFileWritingPermission(intentGetPhoto, photoUri)
            startActivityForResult(intentGetPhoto, 1)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun getFileWritingPermission(intentGetPhoto: Intent, photoUri: Uri?) {
        val cameraActivities: List<ResolveInfo> =
                packageManager.queryIntentActivities(intentGetPhoto, PackageManager.MATCH_DEFAULT_ONLY)
        for (cameraActivity in cameraActivities){
            grantUriPermission(
                    cameraActivity.activityInfo.packageName,
                    photoUri,
                    Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
            )
        }
    }

    private fun createCarObject() =
            Car(
                    nameOwner = addOwnerName.text.toString(),
                    producer = addProducer.text.toString(),
                    model = addModel.text.toString(),
                    registerNumber = addPlateNumber.text.toString(),
                    photo = photoFile?.path
            )

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        photoCar.setImageURI(photoFile?.path?.toUri())
    }
}