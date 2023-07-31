package com.example.mobil2final

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var Sayi1: EditText
    private lateinit var Sayi2: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var rgrbuton1: RadioButton
    private lateinit var rgrbuton2: RadioButton
    private lateinit var Sonuc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Sayi1 = findViewById(R.id.editTextText)
        Sayi2 = findViewById(R.id.editTextText2)
        radioGroup = findViewById(R.id.rgrp)
        rgrbuton1 = findViewById(R.id.radioButton)
        rgrbuton2 = findViewById(R.id.radioButton3)
        Sonuc = findViewById(R.id.editTextText3)

        val btnAdd: Button = findViewById(R.id.button)
        val btnSubtract: Button = findViewById(R.id.button6)
        val btnMultiply: Button = findViewById(R.id.button7)
        val btnDivide: Button = findViewById(R.id.button8)
        val btnClear: Button = findViewById(R.id.button9)

        btnAdd.setOnClickListener { performOperation(Operation.ADD) }
        btnSubtract.setOnClickListener { performOperation(Operation.SUBTRACT) }
        btnMultiply.setOnClickListener { performOperation(Operation.MULTIPLY) }
        btnDivide.setOnClickListener { performOperation(Operation.DIVIDE) }
        btnClear.setOnClickListener { clearFields() }
    }
    private fun performOperation(operation: Operation) {
        val number1 = Sayi1.text.toString().toDoubleOrNull()
        val number2 = Sayi2.text.toString().toDoubleOrNull()

        if (number1 == null || number2 == null) {
            showError("Lütfen Geçerli Sayı Gir.")
            return
        }

        val result = when (operation) {
            Operation.ADD -> number1 + number2
            Operation.SUBTRACT -> {
                if (rgrbuton1.isChecked) {
                    number1 - number2
                } else {
                    number2 - number1
                }
            }
            Operation.MULTIPLY -> number1 * number2
            Operation.DIVIDE -> {
                if (number2 == 0.0) {
                    showError("0 a bölme gerçekleştirilemez.")
                    return
                }
                number1 / number2
            }
        }

        Sonuc.text = result.toString()
    }

    private fun showError(message: String) {
        Sonuc.text = ""
        Sayi1.clearFocus()
        Sayi2.clearFocus()
        Sayi1.text = null
        Sayi2.text = null
        radioGroup.clearCheck()
        Sonuc.requestFocus()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun clearFields() {
        Sayi1.clearFocus()
        Sayi2.clearFocus()
        Sayi1.text = null
        Sayi2.text = null
        radioGroup.clearCheck()
        Sonuc.text = ""
    }

    private enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}