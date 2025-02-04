package com.example.listacompra.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.listacompra.R
import com.example.listacompra.databinding.ProductLayoutBinding
import com.example.listacompra.model.Producto

class ProductAdapter (private var listaProductos:List<Producto>, val onDelete:(Producto)->Unit) : RecyclerView.Adapter<ProductViewHolder>(){

    fun actualizarProductos(newList:List<Producto>){
        listaProductos = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return ProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.render(listaProductos[position], onDelete)
    }

    override fun getItemCount() = listaProductos.size

}

class ProductViewHolder(v: View) : RecyclerView.ViewHolder(v){

    private val binding = ProductLayoutBinding.bind(v)
    fun render(producto: Producto, onDelete:(Producto)->Unit){
        binding.tvNombre.text = producto.nombre
        binding.ivEdit.setBackgroundColor(if (producto.comprado) Color.parseColor("#39FF14") else Color.parseColor("#ED0707"))
        binding.ivEdit.setImageResource(if (producto.comprado) R.drawable.ico_comprado else R.drawable.ico_nocomprado)
        binding.ivEdit.setOnClickListener {
           producto.comprado = !producto.comprado
            binding.ivEdit.setBackgroundColor(if (producto.comprado) Color.parseColor("#39FF14") else Color.parseColor("#ED0707"))
            binding.ivEdit.setImageResource(if (producto.comprado) R.drawable.ico_comprado else R.drawable.ico_nocomprado)
        }
        binding.ivBorrar.setOnClickListener {
            onDelete(producto)
        }
    }

    fun toggleBackgroundColor(imageView: ImageView) {
        val currentColor = (imageView.background as? android.graphics.drawable.ColorDrawable)?.color

        // Define los colores en formato entero
        val redColor = Color.parseColor("#ED0707")
        val neonGreenColor = Color.parseColor("#39FF14") // Verde fosforito

        if (currentColor == redColor) {
            imageView.setBackgroundColor(neonGreenColor)
        } else {
            imageView.setBackgroundColor(redColor)
        }
    }

}