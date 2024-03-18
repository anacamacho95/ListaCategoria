package com.example.listacategoria.modelo.entidades

class Lista (var nombre: String,
             var tipo: String,
             var nTareas: Int) {

    //una lista tiene un conjunto de items
    var items: MutableList<Item> = mutableListOf()

}