package com.moviles.puntotres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.moviles.puntotres.databinding.ActivityMainBinding
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val df = DecimalFormat("#.#")

        binding.button.setOnClickListener {
            if (!validateEmpty()){
                val peso = binding.persoInputtext.text.toString().toDouble()
                val altura = binding.alturaInputText.text.toString().toDouble()
                val imc = peso/ altura.pow(2)
                binding.resultImcTextView.text = buildString {
                    append(getString(R.string.IMC))
                    append(df.format(imc))
                }
                if (imc< 18.5) binding.RecomendatextView.text = getString(R.string.bajopeso)
                if (imc>=18.5 && imc<24.9) binding.RecomendatextView.text = getString(R.string.buenpeso)
                if (imc>=24.9 && imc<29.9) binding.RecomendatextView.text = getString(R.string.sobrepeso)
                if (imc>=29.9) binding.RecomendatextView.text = getString(R.string.obeso)
            } else {
                Toast.makeText(this, getString(R.string.emptyField), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateEmpty() =
        binding.alturaInputText.text.toString().isEmpty() || binding.persoInputtext.text.toString()
            .isEmpty()
}