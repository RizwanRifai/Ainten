package com.example.fragmen_kotlin

import android.R.attr.adjustViewBounds
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class chapter : AppCompatActivity() {
    private val supabaseBaseUrl = "https://ynrxgkbvrpnnzczeaakl.supabase.co/storage/v1/object/public/"
        private val maxImages = 51

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val imageContainer = findViewById<LinearLayout>(R.id.image_container)
        val namaKomik = intent.getStringExtra("namaKomik") ?: return

        saveHistory(namaKomik)
        loadImages(imageContainer, namaKomik)
    }

    private fun loadImages(container: LinearLayout, namaKomik: String) {
        for (i in 1..maxImages) {
            val imageUrl = "$supabaseBaseUrl$namaKomik/$i.jpeg"

            val imageView = ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).also {
                    it.setMargins(0, 20, 0, 20)
                }
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.FIT_CENTER
            }

            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.image_placeholder)
                .into(imageView)

            container.addView(imageView)
        }
    }

    private fun saveHistory(namaKomik: String) {
        val prefs = getSharedPreferences("komik_history", MODE_PRIVATE)
        val editor = prefs.edit()

        val history = prefs.getStringSet("history_set", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        // Format waktu sekarang
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val currentTime = sdf.format(Date())

        // Tambahkan entry ke history
        history.add("$namaKomik - $currentTime")

        editor.putStringSet("history_set", history)
        editor.apply()
    }



}