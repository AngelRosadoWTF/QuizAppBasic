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
import android.content.Intent


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

        var checkBoxList = arrayOf(checkBox1.isChecked, checkBox2.isChecked, checkBox3.isChecked, checkBox4.isChecked, checkBox5.isChecked)

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

        val intent = Intent(this, Activity2::class.java)
        intent.putExtra("checkBoxList", "checkBoxList")
        intent.putExtra("sliderValue", "sliderValue")
        intent.putExtra("spinerValue", "spinerValue")
        intent.putExtra("switchValue", "switchValue")

    }


}