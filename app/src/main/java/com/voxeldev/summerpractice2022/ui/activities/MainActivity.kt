package com.voxeldev.summerpractice2022.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.voxeldev.summerpractice2022.R

const val LOG_TAG = "CaloriesCalc"

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val textInputLayoutName = findViewById<TextInputLayout>(R.id.textinputlayout_name)
        val textInputLayoutHeight = findViewById<TextInputLayout>(R.id.textinputlayout_height)
        val textInputLayoutWeight = findViewById<TextInputLayout>(R.id.textinputlayout_weight)
        val textInputLayoutAge = findViewById<TextInputLayout>(R.id.textinputlayout_age)

        val textInputEditTextName = findViewById<TextInputEditText>(R.id.textinputedittext_name)
        val textInputEditTextHeight = findViewById<TextInputEditText>(R.id.textinputedittext_height)
        val textInputEditTextWeight = findViewById<TextInputEditText>(R.id.textinputedittext_weight)
        val textInputEditTextAge = findViewById<TextInputEditText>(R.id.textinputedittext_age)

        textInputEditTextName.doOnTextChanged { _, _, _, _ ->
            run {
                textInputLayoutName.isErrorEnabled = false
            }
        }
        textInputEditTextHeight.doOnTextChanged { _, _, _, _ ->
            run {
                textInputLayoutHeight.isErrorEnabled = false
            }
        }
        textInputEditTextWeight.doOnTextChanged { _, _, _, _ ->
            run {
                textInputLayoutWeight.isErrorEnabled = false
            }
        }
        textInputEditTextAge.doOnTextChanged { _, _, _, _ ->
            run {
                textInputLayoutAge.isErrorEnabled = false
            }
        }


        findViewById<MaterialButton>(R.id.button_calculate).setOnClickListener {
            calculateButtonClick(
                textInputLayoutName,
                textInputLayoutHeight,
                textInputLayoutWeight,
                textInputLayoutAge,
                textInputEditTextName,
                textInputEditTextHeight,
                textInputEditTextWeight,
                textInputEditTextAge
            )
        }
    }

    private fun calculateButtonClick(
        textInputLayoutName: TextInputLayout,
        textInputLayoutHeight: TextInputLayout,
        textInputLayoutWeight: TextInputLayout,
        textInputLayoutAge: TextInputLayout,
        textInputEditTextName: TextInputEditText,
        textInputEditTextHeight: TextInputEditText,
        textInputEditTextWeight: TextInputEditText,
        textInputEditTextAge: TextInputEditText
    ) {
        val name = textInputEditTextName.text?.toString()
        val height: Int
        val weight: Float
        val age: Int

        if (name == null) {
            showError(textInputLayoutName)
            return
        }

        try {
            height = textInputEditTextHeight.text?.toString()?.toInt()!!
        }
        catch (e: Exception) {
            Log.d(LOG_TAG, e.stackTraceToString())
            showError(textInputLayoutHeight)
            return
        }

        if (height <= 0 || height >= 250) {
            showError(textInputLayoutHeight)
            return
        }

        try {
            weight = textInputEditTextWeight.text?.toString()?.toFloat()!!
        }
        catch (e: Exception) {
            Log.d(LOG_TAG, e.stackTraceToString())
            showError(textInputLayoutWeight)
            return
        }

        if (weight <= 0 || weight >= 250) {
            showError(textInputLayoutWeight)
            return
        }

        try {
            age = textInputEditTextAge.text?.toString()?.toInt()!!
        }
        catch (e: Exception) {
            Log.d(LOG_TAG, e.stackTraceToString())
            showError(textInputLayoutAge)
            return
        }

        if (age <= 0 || age >= 250) {
            showError(textInputLayoutAge)
            return
        }

        val intent = Intent(applicationContext, ResultActivity::class.java)
        intent.putExtra("result", calculateCalories(name, height, weight, age))
        startActivity(intent)
    }

    private fun calculateCalories(name: String, height: Int, weight: Float, age: Int): String {
        return "$name,\nВаш результат: ${10 * weight + 6.25 * height - 5 * age + 5} калорий"
    }

    private fun showError(textInputLayout: TextInputLayout, error: String = "Неверное значение") {
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = error
    }
}