package com.example.listacategoria.modelo.interfaces

import com.example.listacategoria.modelo.conexiones.BDFichero

interface InterfaceDaoConexion {
    fun createConexion (con: BDFichero)
}