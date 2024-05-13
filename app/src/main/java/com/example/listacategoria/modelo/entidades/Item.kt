package com.example.listacategoria.modelo.entidades

import java.io.Serializable

class Item (var accion: String, var activo: Boolean): Serializable {

    override fun toString(): String {
        return "Item(accion='$accion', activo=$activo)"
    }
}