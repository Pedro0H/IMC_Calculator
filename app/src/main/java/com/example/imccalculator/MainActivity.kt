package com.example.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var alturaEditText: EditText
    private lateinit var pesoEditText: EditText
    private lateinit var calcularButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alturaEditText = findViewById(R.id.editaltura)
        pesoEditText = findViewById(R.id.editpeso)
        calcularButton = findViewById(R.id.btCalcular)

        calcularButton.setOnClickListener {
            calcularIMC()
        }
    }

    private fun calcularIMC() {
        val alturaStr = alturaEditText.text.toString()
        val pesoStr = pesoEditText.text.toString()

        if (alturaStr.isEmpty() || pesoStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val altura = alturaStr.toFloatOrNull()
        val peso = pesoStr.toFloatOrNull()

        if (altura == null || peso == null || altura <= 0f || peso <= 0f) {
            Toast.makeText(this, "Valores inválidos", Toast.LENGTH_SHORT).show()
            return
        }

        val alturaMetros = altura / 100
        val imc = peso / (alturaMetros * alturaMetros)

        val classificacao = when {
            imc < 18.5 -> "Abaixo do peso"
            imc in 18.5..24.9 -> "Peso normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            imc in 30.0..34.9 -> "Obesidade grau 1"
            imc in 35.0..39.9 -> "Obesidade grau 2"
            else -> "Obesidade grau 3"
        }

        val resultado = "IMC: %.2f\nClassificação: %s".format(imc, classificacao)

        Toast.makeText(this, resultado, Toast.LENGTH_LONG).show()
    }
}
