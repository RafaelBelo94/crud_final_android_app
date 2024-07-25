package com.example.listadecadastrosenai

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecadastrosenai.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = MyDatabaseHelper(this)

        binding.buttonP1.setOnClickListener {
            if (!binding.inputUser.text.toString().trim().isEmpty() &&
                !binding.inputSenha.text.toString().trim().isEmpty() &&
                !binding.inputEmail.text.toString().trim().isEmpty() &&
                !binding.inputTelefone.text.toString().trim().isEmpty()
            ) {

                val value = database.insertUser(
                    binding.inputUser.text.toString(),
                    binding.inputSenha.text.toString(),
                    binding.inputEmail.text.toString(),
                    binding.inputTelefone.text.toString()
                )

                if (value > -1) {
                    Snackbar.make(
                        binding.root,
                        "Usuário " + binding.inputUser.text.toString() + " cadastrado com sucesso!",
                        Snackbar.LENGTH_LONG
                    )
                        .setBackgroundTint(resources.getColor(R.color.black))
                        .setTextColor(resources.getColor(R.color.white))
                        .show()
                }
            } else {
                Snackbar.make(
                    binding.root, "Nenhum campo pode estar Vazio!",
                    Snackbar.LENGTH_LONG
                )
                    .setBackgroundTint(resources.getColor(R.color.black))
                    .setTextColor(resources.getColor(R.color.white))
                    .show()
            }
        }

        binding.buttonP2.setOnClickListener {
            startActivity(Intent(this, MainList::class.java))
        }
    }
}