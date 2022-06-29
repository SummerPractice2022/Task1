package com.voxeldev.summerpractice2022.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import com.voxeldev.summerpractice2022.R

class ResultActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)

        val result = intent.getStringExtra("result")

        findViewById<MaterialTextView>(R.id.textview_result).text = result
    }
}