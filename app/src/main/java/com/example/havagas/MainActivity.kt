package com.example.havagas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val formacoes = listOf("Fundamental", "Médio", "Graduação", "Especialização", "Mestrado", "Doutorado")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, formacoes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFormacao.adapter = adapter

        binding.spinnerFormacao.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val formacao = formacoes[position]
                when (formacao) {
                    "Fundamental", "Médio" -> {
                        binding.layoutFormacaoAdicional.visibility = View.VISIBLE
                        binding.editTextAnoConclusao.visibility = View.VISIBLE
                        binding.editTextInstituicao.visibility = View.GONE
                        binding.editTextTituloMonografia.visibility = View.GONE
                        binding.editTextOrientador.visibility = View.GONE
                    }
                    "Graduação", "Especialização" -> {
                        binding.layoutFormacaoAdicional.visibility = View.VISIBLE
                        binding.editTextAnoConclusao.visibility = View.VISIBLE
                        binding.editTextInstituicao.visibility = View.VISIBLE
                        binding.editTextTituloMonografia.visibility = View.GONE
                        binding.editTextOrientador.visibility = View.GONE
                    }
                    "Mestrado", "Doutorado" -> {
                        binding.layoutFormacaoAdicional.visibility = View.VISIBLE
                        binding.editTextAnoConclusao.visibility = View.VISIBLE
                        binding.editTextInstituicao.visibility = View.VISIBLE
                        binding.editTextTituloMonografia.visibility = View.VISIBLE
                        binding.editTextOrientador.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.layoutFormacaoAdicional.visibility = View.GONE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Não faz nada
            }
        }


        binding.checkBoxAddCelular.setOnCheckedChangeListener { _, isChecked ->
            binding.editTextCelular.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
    }
}
