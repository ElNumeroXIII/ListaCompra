package com.example.listacompra.data.repository

import com.example.listacompra.model.Producto

class ListaCompraRepository {

    private val listaCompra = mutableListOf<Producto>()

    fun getListaCompra() = listaCompra

    fun addProducto(producto: Producto) =  listaCompra.add(producto)

    fun removeProducto(producto: Producto)= listaCompra.remove(producto)



}