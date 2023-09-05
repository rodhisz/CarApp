package com.example.carapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity


class DetailActivity : AppCompatActivity() {

    companion object {
        const val Key_Car = "Key_Car"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // calling the action bar
        val actionBar: ActionBar? = supportActionBar

        // showing the back button in action bar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val dataCar = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Car>(Key_Car, Car::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Car>(Key_Car)
        }

        val tvDetailName : TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription : TextView= findViewById(R.id.tv_detail_description)
        val ivDetailPhoto : ImageView = findViewById(R.id.iv_detail_photo)

        tvDetailName.text = dataCar?.name
        tvDetailDescription.text = dataCar?.description
        if (dataCar != null) {
            ivDetailPhoto.setImageResource(dataCar.photo)
        }

        val btnShare: Button = findViewById(R.id.btn_share)
        btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,
                    "${dataCar?.name.toString()}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}