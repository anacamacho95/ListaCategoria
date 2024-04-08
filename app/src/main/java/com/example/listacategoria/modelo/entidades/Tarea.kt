package com.example.listacategoria.modelo.entidades

import java.io.Serializable

class Tarea (var nombre: String ): Serializable {

    //una lista tiene un conjunto de items
    var items: MutableList<Item> = mutableListOf()
    var nTareas: Int = items.size
}