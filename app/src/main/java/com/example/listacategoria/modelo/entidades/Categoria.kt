package com.example.listacategoria.modelo.entidades

import java.io.Serializable

class Categoria (var nombre: String): Serializable {
    //Una categoria tiene muchas listas
    var listas : MutableList<Lista> = mutableListOf()
}