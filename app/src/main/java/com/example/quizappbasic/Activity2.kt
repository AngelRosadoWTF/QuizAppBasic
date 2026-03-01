package com.example.quizappbasic

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider

class Activity2 : AppCompatActivity() {

    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var checkBox4: CheckBox
    private lateinit var checkBox5: CheckBox
    private lateinit var slider: Slider
    private lateinit var spinner: Spinner
    private lateinit var switchGame: Switch
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity2_pantallaopcionesdejuego)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        checkBox1 = findViewById(R.id.themeCheckbox1)
        checkBox2 = findViewById(R.id.themeCheckbox2)
        checkBox3 = findViewById(R.id.themeCheckbox3)
        checkBox4 = findViewById(R.id.themeCheckbox4)
        checkBox5 = findViewById(R.id.themeCheckbox5)

        slider = findViewById(R.id.slider)
        spinner = findViewById(R.id.spinner)
        switchGame = findViewById(R.id.Switch)
        startButton = findViewById(R.id.startButton)

        slider.valueFrom = 5f
        slider.valueTo = 10f
        slider.stepSize = 1f

        slider.addOnChangeListener { _, value, _ ->
            slider.value = value
            if (value < 5f) {
                slider.value = 5f
            } else if (value > 10f) {
                slider.value = 10f
            }
        }

        val opciones = listOf(
            getString(R.string.facil),
            getString(R.string.medio),
            getString(R.string.dificil)
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        startButton.setOnClickListener {
            sendAllData()
        }
    }

    private fun sendAllData() {

        val checkBoxList = booleanArrayOf(
            checkBox1.isChecked,
            checkBox2.isChecked,
            checkBox3.isChecked,
            checkBox4.isChecked,
            checkBox5.isChecked
        )

        var sliderValue = slider.value.toInt()

        if (sliderValue < 5) sliderValue = 5
        if (sliderValue > 10) sliderValue = 10

        val spinnerValue = spinner.selectedItem.toString()
        val switchValue = switchGame.isChecked

        val intent = Intent(this, GameActivity::class.java)

        intent.putExtra("checkBoxList", checkBoxList)
        intent.putExtra("sliderValue", sliderValue)
        intent.putExtra("spinnerValue", spinnerValue)
        intent.putExtra("switchValue", switchValue)

        startActivity(intent)
    }
}