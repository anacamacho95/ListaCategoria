package com.example.listacategoria.modelo.entidades

class Categoria (var nombre: String) {
    //Una categoria tiene muchas listas
    var listas : MutableList<Lista> = mutableListOf()
}