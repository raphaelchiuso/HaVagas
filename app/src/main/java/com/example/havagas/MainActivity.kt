package com.example.havagas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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

        binding.buttonLimpar.setOnClickListener {
            clearForm()
        }

        binding.buttonSalvar.setOnClickListener {
            saveForm()
        }
    }

    private fun clearForm() {
        binding.editTextNome.text.clear()
        binding.editTextEmail.text.clear()
        binding.checkBoxEmailUpdates.isChecked = false
        binding.radioGroupTelefoneTipo.clearCheck()
        binding.editTextTelefone.text.clear()
        binding.checkBoxAddCelular.isChecked = false
        binding.editTextCelular.text.clear()
        binding.radioGroupSexo.clearCheck()
        binding.editTextDataNascimento.text.clear()
        binding.spinnerFormacao.setSelection(0)
        binding.editTextAnoConclusao.text.clear()
        binding.editTextInstituicao.text.clear()
        binding.editTextTituloMonografia.text.clear()
        binding.editTextOrientador.text.clear()
        binding.editTextVagasInteresse.text.clear()
    }

    private fun saveForm() {
        val nome = binding.editTextNome.text.toString()
        val email = binding.editTextEmail.text.toString()
        val telefoneTipo = when (binding.radioGroupTelefoneTipo.checkedRadioButtonId) {
            R.id.radioButtonComercial -> "Comercial"
            R.id.radioButtonResidencial -> "Residencial"
            else -> ""
        }
        val telefone = binding.editTextTelefone.text.toString()
        val celular = if (binding.checkBoxAddCelular.isChecked) binding.editTextCelular.text.toString() else ""
        val sexo = when (binding.radioGroupSexo.checkedRadioButtonId) {
            R.id.radioButtonMasculino -> "Masculino"
            R.id.radioButtonFeminino -> "Feminino"
            else -> ""
        }
        val dataNascimento = binding.editTextDataNascimento.text.toString()
        val formacao = binding.spinnerFormacao.selectedItem.toString()
        val anoConclusao = binding.editTextAnoConclusao.text.toString()
        val instituicao = binding.editTextInstituicao.text.toString()
        val tituloMonografia = binding.editTextTituloMonografia.text.toString()
        val orientador = binding.editTextOrientador.text.toString()
        val vagasInteresse = binding.editTextVagasInteresse.text.toString()

        val mensagem = """
            Nome: $nome
            E-mail: $email
            Telefone: $telefone ($telefoneTipo)
            Celular: $celular
            Sexo: $sexo
            Data de Nascimento: $dataNascimento
            Formação: $formacao
            Ano de Conclusão: $anoConclusao
            Instituição: $instituicao
            Título de Monografia: $tituloMonografia
            Orientador: $orientador
            Vagas de Interesse: $vagasInteresse
        """.trimIndent()

        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }
}
