package com.example.listadecadastrosenai

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecadastrosenai.databinding.ActivityMainAtualizarBinding

class MainAtualizar : AppCompatActivity() {

    private lateinit var binding: ActivityMainAtualizarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainAtualizarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = MyDatabaseHelper(this)
        val IntentId = intent
        IntentId.getStringExtra("id").toString().toInt()
        var list = database.UserSelectById(IntentId.getStringExtra("id").toString().toInt())

        binding.inputUser.setText(list.get(0).username)
        binding.inputSenha.setText(list.get(0).password)
        binding.inputEmail.setText(list.get(0).email)
        binding.inputTelefone.setText(list.get(0).cellphone)

        binding.buttonAtualizar.setOnClickListener {

            val id = IntentId.getStringExtra("id").toString().toInt()
            val username = binding.inputUser.text.toString()
            val password = binding.inputSenha.text.toString()
            val email = binding.inputEmail.text.toString()
            val telefone = binding.inputTelefone.text.toString()

            val user = User(id, username, password, email, telefone)
            database.updateUser(user)

            finish()
        }

        binding.buttonExcluir.setOnClickListener {
            database.deleteUser(IntentId.getStringExtra("id").toString().toInt())
        }

        binding.buttonP7.setOnClickListener {
            startActivity(Intent(this, MainList::class.java))
        }
    }
}