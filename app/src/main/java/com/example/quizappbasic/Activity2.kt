package com.example.quizappbasic

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider
<<<<<<< HEAD
import android.content.Intent
import android.widget.Button


class Activity2 : AppCompatActivity() {
    var checkBox1 = findViewById<CheckBox>(R.id.themeCheckbox1)
    var checkBox2 = findViewById<CheckBox>(R.id.themeCheckbox2)
    var checkBox3 = findViewById<CheckBox>(R.id.themeCheckbox3)
    var checkBox4 = findViewById<CheckBox>(R.id.themeCheckbox4)
    var checkBox5 = findViewById<CheckBox>(R.id.themeCheckbox5)

    var slider = findViewById<Slider>(R.id.slider)
    var spinner = findViewById<Spinner>(R.id.spinner)
    var switch = findViewById<Switch>(R.id.Switch)
    var startButton = findViewById<Button>(R.id.startButton)
    var sliderValue = 5
    var spinnerValue = ""
    var switchValue = switch.isChecked
=======

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
>>>>>>> parent of 3f9ed21 (Revert "correcciones y acomodo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity2_pantallaopcionesdejuego)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
<<<<<<< HEAD

        slider.addOnChangeListener{slider, value, fromUser ->
            sliderValue = value.toInt()

            if(sliderValue < 5){
                sliderValue = 5
            } else if(sliderValue > 10){
                sliderValue = 10
            }
        }

        val opciones = listOf(
            getString(R.string.facil),
            getString(R.string.medio),
            getString(R.string.dificil)
        )

=======

        checkBox1 = findViewById(R.id.themeCheckbox1)
        checkBox2 = findViewById(R.id.themeCheckbox2)
        checkBox3 = findViewById(R.id.themeCheckbox3)
        checkBox4 = findViewById(R.id.themeCheckbox4)
        checkBox5 = findViewById(R.id.themeCheckbox5)

        slider = findViewById(R.id.slider)
        spinner = findViewById(R.id.spinner)
        switchGame = findViewById(R.id.Switch)
        startButton = findViewById(R.id.startButton) // Asegúrate de tener este botón en tu XML

        val opciones = listOf(
            getString(R.string.facil),
            getString(R.string.medio),
            getString(R.string.dificil)
        )

>>>>>>> parent of 3f9ed21 (Revert "correcciones y acomodo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        startButton.setOnClickListener {
<<<<<<< HEAD
            SendAllData()
        }
    }

    fun SendAllData() {
        checkBox1.isChecked
        checkBox2.isChecked
        checkBox3.isChecked
        checkBox4.isChecked
        checkBox5.isChecked

         sliderValue = slider.value.toInt()
         spinnerValue = spinner.selectedItem.toString()
         switchValue = switch.isChecked
        
//        val intent = Intent(this, Activity3::class.java)

        intent.putExtra("checkBoxList", "checkBoxList")
        intent.putExtra("sliderValue", "sliderValue")
        intent.putExtra("spinerValue", "spinerValue")
        intent.putExtra("switchValue", "switchValue")
=======
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

        val sliderValue = slider.value.toInt()
        val spinnerValue = spinner.selectedItem.toString()
        val switchValue = switchGame.isChecked


        intent.putExtra("checkBoxList", checkBoxList)
        intent.putExtra("sliderValue", sliderValue)
        intent.putExtra("spinnerValue", spinnerValue)
        intent.putExtra("switchValue", switchValue)

        startActivity(intent)
>>>>>>> parent of 3f9ed21 (Revert "correcciones y acomodo")
    }
}