package org.tensorflow.lite.examples.ricedisease

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.ThumbnailUtils
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_main)
        val detect_c: Button = findViewById(R.id.detect_c)

        val scan_c: Button = findViewById(R.id.scan_c)

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

        if (resultCode == Activity.RESULT_OK && requestCode == 101) {
            val photo_c = data?.extras?.get("data") as Bitmap
            val dim = min(photo_c.width, photo_c.height)
            val thumbnail = ThumbnailUtils.extractThumbnail(photo_c, dim, dim)
            img_c?.setImageBitmap(thumbnail)

            val scaledPhoto = Bitmap.createScaledBitmap(thumbnail, 224, 224, false)
            classify(scaledPhoto)
        }
    }
}