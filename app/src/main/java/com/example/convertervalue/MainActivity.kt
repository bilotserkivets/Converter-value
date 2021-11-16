package com.example.convertervalue

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.convertervalue.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener { calculate() }
        binding.valueEnteredEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)}
    }

    private fun calculate() {
        val stringInputValue = binding.valueEnteredEditText.text.toString()
        if (stringInputValue.isEmpty()) {
            binding.textResult.text = getString(R.string.alert_answer)
        } else {
            val values = stringInputValue.toDouble()

            val selectedId = binding.checkedValue.checkedRadioButtonId
            val unit = when (selectedId) {
                R.id.check_mile -> getString(R.string.mil)
                R.id.check_inch -> getString(R.string.inc)
                R.id.check_sea_mile-> getString(R.string.sea_mil)
                R.id.check_ft -> getString(R.string.ft)
                R.id.check_yard -> getString(R.string.yd)
                R.id.check_kilometer -> getString(R.string.km)
                R.id.check_centimeter -> getString(R.string.cen)
                else -> getString(R.string.mm)
            }

            val converterValue = when(selectedId) {
                R.id.check_mile -> 0.00062137
                R.id.check_inch -> 39.370
                R.id.check_sea_mile-> 0.000185
                R.id.check_ft -> 3.2808
                R.id.check_yard -> 1.0936
                R.id.check_kilometer -> 0.001
                R.id.check_centimeter -> 100.0
                else -> 1000.0
            }
            val result = (converterValue * values)


            val formatResult = DecimalFormat("#.###").format(result).toString()
            binding.textResult.text = getString(R.string.text_result, stringInputValue, formatResult,  unit)
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}