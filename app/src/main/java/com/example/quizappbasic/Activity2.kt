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

    companion object {
        private const val KEY_CHECKBOX1 = "key_checkbox1"
        private const val KEY_CHECKBOX2 = "key_checkbox2"
        private const val KEY_CHECKBOX3 = "key_checkbox3"
        private const val KEY_CHECKBOX4 = "key_checkbox4"
        private const val KEY_CHECKBOX5 = "key_checkbox5"
        private const val KEY_SLIDER_VALUE = "key_slider_value"
        private const val KEY_SPINNER_POSITION = "key_spinner_position"
        private const val KEY_SWITCH = "key_switch"
    }

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

        if (savedInstanceState != null) {
            checkBox1.isChecked = savedInstanceState.getBoolean(KEY_CHECKBOX1, false)
            checkBox2.isChecked = savedInstanceState.getBoolean(KEY_CHECKBOX2, false)
            checkBox3.isChecked = savedInstanceState.getBoolean(KEY_CHECKBOX3, false)
            checkBox4.isChecked = savedInstanceState.getBoolean(KEY_CHECKBOX4, false)
            checkBox5.isChecked = savedInstanceState.getBoolean(KEY_CHECKBOX5, false)
            slider.value = savedInstanceState.getFloat(KEY_SLIDER_VALUE, 5f)
            spinner.setSelection(savedInstanceState.getInt(KEY_SPINNER_POSITION, 0))
            switchGame.isChecked = savedInstanceState.getBoolean(KEY_SWITCH, false)
        }

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

        val selectedThemeNames = arrayListOf<String>()
        if (checkBoxList[0]) selectedThemeNames.add(Theme.PELICULAS.name)
        if (checkBoxList[1]) selectedThemeNames.add(Theme.VIDEOJUEGOS.name)
        if (checkBoxList[2]) selectedThemeNames.add(Theme.GEOGRAFIA.name)
        if (checkBoxList[3]) selectedThemeNames.add(Theme.PROGRAMACION.name)
        if (checkBoxList[4]) selectedThemeNames.add(Theme.ALGEBRA.name)

        if (selectedThemeNames.isEmpty()) {
            Toast.makeText(this, "Selecciona al menos un tema", Toast.LENGTH_SHORT).show()
            return
        }

        var sliderValue = slider.value.toInt()

        if (sliderValue < 5) sliderValue = 5
        if (sliderValue > 10) sliderValue = 10

        val spinnerValue = spinner.selectedItem.toString()
        val difficultyValue = when (spinner.selectedItemPosition) {
            0 -> Difficulty.FACIL.name
            1 -> Difficulty.NORMAL.name
            2 -> Difficulty.DIFICIL.name
            else -> Difficulty.NORMAL.name
        }
        val switchValue = switchGame.isChecked

        val gameIntent = Intent(this, GameActivity::class.java).apply {
            putExtra("NUM_QUESTIONS", sliderValue)
            putExtra("DIFFICULTY", difficultyValue)
            putExtra("HINTS_ENABLED", switchValue)
            putExtra("checkBoxList", checkBoxList)
            putStringArrayListExtra("selectedThemes", selectedThemeNames)
            putExtra("sliderValue", sliderValue)
            putExtra("spinnerValue", spinnerValue)
            putExtra("switchValue", switchValue)
        }

        startActivity(gameIntent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_CHECKBOX1, checkBox1.isChecked)
        outState.putBoolean(KEY_CHECKBOX2, checkBox2.isChecked)
        outState.putBoolean(KEY_CHECKBOX3, checkBox3.isChecked)
        outState.putBoolean(KEY_CHECKBOX4, checkBox4.isChecked)
        outState.putBoolean(KEY_CHECKBOX5, checkBox5.isChecked)
        outState.putFloat(KEY_SLIDER_VALUE, slider.value)
        outState.putInt(KEY_SPINNER_POSITION, spinner.selectedItemPosition)
        outState.putBoolean(KEY_SWITCH, switchGame.isChecked)
    }
}