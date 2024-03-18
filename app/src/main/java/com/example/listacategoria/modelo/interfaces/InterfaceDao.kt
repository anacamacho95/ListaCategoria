package com.example.listacategoria.modelo.interfaces

import com.example.listacategoria.modelo.conexiones.BDFichero

interface InterfaceDao {
    fun createConexion (con: BDFichero)
}