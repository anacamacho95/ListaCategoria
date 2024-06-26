package com.example.listacategoria.modelo.entidades

import java.io.Serializable

class Tarea (var nombre: String ): Serializable {

    //una tarea tiene un conjunto de items
    var items: MutableList<Item> = mutableListOf()

    override fun toString(): String {
        return "Tarea(nombre='$nombre')"
    }
}