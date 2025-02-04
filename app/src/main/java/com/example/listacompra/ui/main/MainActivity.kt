package com.example.listacompra.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listacompra.R
import com.example.listacompra.databinding.ActivityMainBinding
import com.example.listacompra.model.Producto
import com.example.listacompra.ui.adapter.ProductAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ListaCompraViewModel by viewModels()
    private var nombre = ""
    private val adapter = ProductAdapter(listOf<Producto>()) {
        producto -> viewModel.borrarProducto(producto)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.listaCompra.observe(this){
            adapter.actualizarProductos(it)
        }
        setRecycler()
        binding.btAdd.setOnClickListener {
            addProducto()
        }
    }

    private fun setRecycler() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvProductos.layoutManager = layoutManager
        binding.rvProductos.adapter = adapter
    }

    private fun addProducto() {
        nombre = binding.etProducto.text.toString()
        if(nombre.isEmpty()){
            binding.etProducto.error="Escribe el nombre de un producto"
            return
        }
        viewModel.agregarProducto(nombre)
        binding.etProducto.setText("")
    }
}