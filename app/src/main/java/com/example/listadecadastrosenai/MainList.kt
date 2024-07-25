package com.example.listadecadastrosenai

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecadastrosenai.databinding.ActivityMainBinding
import com.example.listadecadastrosenai.databinding.ActivityMainListBinding

class MainList : AppCompatActivity() {

    private lateinit var binding: ActivityMainListBinding
    private lateinit var adapter: ArrayAdapter<User>
    private var position = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = MyDatabaseHelper(this)
        val list = database.readUsers()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        binding.listViewLista.adapter = adapter
        adapter.notifyDataSetChanged()


        binding.listViewLista.setOnItemClickListener { parent, view, position, id ->
            binding.id.text = list.get(position).id.toString()
            this.position = position
        }



        binding.buttonP3.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.buttonP4.setOnClickListener {
            var i = Intent(this, MainAtualizar::class.java).putExtra("id",binding.id.text)
            startActivity(i)
        }


    }
}