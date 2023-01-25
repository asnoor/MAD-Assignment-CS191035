package com.example.splashscreen.model.datamodels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class RecordDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val record = intent.getSerializableExtra("record") as? Record

        val recordDetailsName: TextView = binding.recordDetailName
        recordDetailsName.text = record?.name
        recordDetailsName.textSize = 28F

        val recordDetailsDescription: TextView = binding.recordDetailDescription
        recordDetailsDescription.text = record?.description

        val recordDetailsImage: ImageView = binding.recordDetailImage

        Glide.with(recordDetailsImage.context)
            .load(record?.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_home_black_24dp)
            .into(recordDetailsImage)
    }
}