package com.example.listacompra.model

data class Producto (
    val id:Long = System.currentTimeMillis(),
    val nombre: String,
    var comprado: Boolean = false
)



