package com.example.listacompra.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listacompra.data.repository.ListaCompraRepository
import com.example.listacompra.model.Producto

class ListaCompraViewModel : ViewModel() {

    private  val repo = ListaCompraRepository()
    private var _listaCompra = MutableLiveData<List<Producto>>()
    val listaCompra: LiveData<List<Producto>> = _listaCompra

    private fun getListaCompra(){
        _listaCompra.value = repo.getListaCompra()

    }

    fun agregarProducto(nombreProducto:String){
        repo.addProducto(Producto(nombre = nombreProducto, comprado = false))
        getListaCompra()
    }

    fun borrarProducto(producto: Producto){
        repo.removeProducto(producto)
        getListaCompra()
    }

}