package com.example.quizappbasic
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider


class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity2_pantallaopcionesdejuego)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var checkBox1 = findViewById<CheckBox>(R.id.themeCheckbox1)
        var checkBox2 = findViewById<CheckBox>(R.id.themeCheckbox2)
        var checkBox3 = findViewById<CheckBox>(R.id.themeCheckbox3)
        var checkBox4 = findViewById<CheckBox>(R.id.themeCheckbox4)
        var checkBox5 = findViewById<CheckBox>(R.id.themeCheckbox5)

        var slider = findViewById<Slider>(R.id.slider)
        var spinner = findViewById<Spinner>(R.id.spinner)
        var switch = findViewById<Switch>(R.id.Switch)

        var checkBox1Value = checkBox1.isChecked
        var checkBox2Value = checkBox2.isChecked
        var checkBox3Value = checkBox3.isChecked
        var checkBox4Value = checkBox4.isChecked
        var checkBox5Value = checkBox5.isChecked

        var sliderValue = 5
        var spinnerValue = ""
        var switchValue = switch.isChecked

        slider.addOnChangeListener{slider, value, fromUser ->
            sliderValue += value.toInt()

            if(sliderValue < 5){
                sliderValue = 5
            } else if(sliderValue > 10){
                sliderValue = 10
            }
        }

        var opciones = arrayOf(getString(R.string.facil),getString(R.string.medio), getString(R.string.dificil))
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        //queda en duda si de hecho funciona xd
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerValue = parent.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

    }


}