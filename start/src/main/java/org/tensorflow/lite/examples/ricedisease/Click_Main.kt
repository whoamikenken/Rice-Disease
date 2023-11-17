package org.tensorflow.lite.examples.ricedisease

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.lite.examples.ricedisease.ml.RiceACC
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.label.Category
import java.io.IOException
import java.lang.Double.min
import kotlin.math.min

class Click_Main : AppCompatActivity() {
    var img_c: ImageView? = null
    private lateinit var bitmap: Bitmap
    private lateinit var labelDisease: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_main)
        val detect_c: Button = findViewById(R.id.detect_c)

        val scan_c: Button = findViewById(R.id.scan_c)

        val gallery_c: Button = findViewById(R.id.gallery_c)

        labelDisease = findViewById(R.id.labelDisease)

        img_c = findViewById(R.id.img_c);

        //Open Scanner Camera
        detect_c.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@Click_Main,
                    Scanner::class.java
                )
            )
        })

        scan_c.setOnClickListener(View.OnClickListener {
            val open_cam = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(open_cam, 101)
        })

        gallery_c.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 150)
        })
    }

    fun classify(photo_c: Bitmap?) {
        try {
                val model = RiceACC.newInstance(this)

                // Creates inputs for reference.
                val image = TensorImage.fromBitmap(photo_c)

                // Runs model inference and gets result.
                val outputs = model.process(image).scoresAsCategoryList.apply { sortByDescending { it.score } }.take(
                 1)
                for (output in outputs) {
                    Log.d("output", output.label)
                    if(output.label == "Leaf_Blight"){
                        startActivity(
                            Intent(
                                this@Click_Main,
                                Leaf_blight::class.java
                            )
                        )
                    }else if(output.label == "Tungro"){
                        startActivity(
                            Intent(
                                this@Click_Main,
                                Tungro::class.java
                            )
                        )
                    }else if(output.label == "Leaf_Streak"){
                        startActivity(
                            Intent(
                                this@Click_Main,
                                Leaf_streak::class.java
                            )
                        )
                    }else if(output.label == "Brown_Spot"){
                        startActivity(
                            Intent(
                                this@Click_Main,
                                Brown_spot::class.java
                            )
                        )
                    }else if(output.label == "False_Smut"){
                        startActivity(
                            Intent(
                                this@Click_Main,
                                False_smut::class.java
                            )
                        )
                    }else{
                        labelDisease.text = output.label;
                    }

                }
                // Releases model resources if no longer used.
                model.close()
        } catch (e: IOException) {
            // TODO Handle the exception
        }
    }

    //Camera Result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 150) {
            img_c?.setImageURI(data?.data)

            val uri: Uri? = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            val dim = min(bitmap.width, bitmap.height)
            val thumbnail = ThumbnailUtils.extractThumbnail(bitmap, dim, dim)
            img_c?.setImageBitmap(thumbnail)

            val scaledPhoto = Bitmap.createScaledBitmap(thumbnail, 224, 224, false)
            classify(scaledPhoto)
        }else if (resultCode == Activity.RESULT_OK && requestCode == 101) {
            val photo_c = data?.extras?.get("data") as Bitmap
            val dim = min(photo_c.width, photo_c.height)
            val thumbnail = ThumbnailUtils.extractThumbnail(photo_c, dim, dim)
            img_c?.setImageBitmap(thumbnail)

            val scaledPhoto = Bitmap.createScaledBitmap(thumbnail, 224, 224, false)
            classify(scaledPhoto)
        }
    }
}