package com.moviles.puntotres.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moviles.puntotres.R
import com.moviles.puntotres.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val view = binding.root
        setContentView(view)

        val observer = Observer<String> {
            binding.resultImcTextView.text = buildString {
                append(getString(R.string.IMC))
                append(it)
            }
        }
        val observer2 = Observer<String> {
            binding.RecomendatextView.text =it
        }

        mainViewModel.imc.observe(this,observer)
        mainViewModel.message.observe(this, observer2)

        binding.button.setOnClickListener {
            if (!validateEmpty()){
                mainViewModel.calculateIMC(
                    binding.persoInputtext.text.toString().toDouble(),
                    binding.alturaInputText.text.toString().toDouble(),
                    getString(R.string.bajopeso),
                    getString(R.string.buenpeso),
                    getString(R.string.sobrepeso),
                    getString(R.string.obeso),)
            } else {
                Toast.makeText(this, getString(R.string.emptyField), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateEmpty() =
        binding.alturaInputText.text.toString().isEmpty() || binding.persoInputtext.text.toString()
            .isEmpty()
}