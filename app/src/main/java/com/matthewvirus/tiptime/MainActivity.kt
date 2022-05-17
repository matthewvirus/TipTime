package com.matthewvirus.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matthewvirus.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateTip.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringTextField = binding.serviceCost.text.toString()
        val roundUp = binding.roundTip.isChecked
        val cost = stringTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = " "
            return
        }
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty_percent -> 0.20
            R.id.eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
