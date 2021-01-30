package com.example.tipcalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // access all the layout
        val calculate_button: Button  = findViewById(R.id.calculate_button)
        // do something when the button is clicked
        calculate_button.setOnClickListener { tip_calculator() }
    }

    // function definition  for calculating the tip
    fun tip_calculator(){
        // access the id the of the UI components
        val costOfService: EditText = findViewById(R.id.cost_of_service);
        val tipResult: TextView = findViewById(R.id.tip_result);
        val tipOptions: RadioGroup = findViewById(R.id.tip_options);
        val roundUpSwitch: Switch =  findViewById(R.id.round_up_switch)
        // get the value of the UI component
        val cost = costOfService.text.toString().toDoubleOrNull()
        if (cost == null) {
            tipResult.text = ""
            return
        }
        val tipPercentage = when (tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        if (roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        tipResult.text = formattedTip // text is a short hand for getter and setter
    }
}