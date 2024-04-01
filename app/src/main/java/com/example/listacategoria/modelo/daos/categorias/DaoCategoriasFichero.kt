package com.example.listacategoria.modelo.daos.categorias

import android.util.Log
import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.interfaces.InterfaceDaoCategorias

class DaoCategoriasFichero: InterfaceDaoCategorias {
    lateinit var conexion: BDFichero

    override fun createConexion(con: BDFichero) {
        conexion = con as BDFichero
    }

    override fun addCategoria(ca: Categoria) {
        val lista=conexion.leer()
        lista.add(ca)
        conexion.escribir(lista)
    }

    override fun getCategorias(): MutableList<Categoria> {
        return conexion.leer()
    }

    override fun getCategorias(nombre: String): MutableList<Categoria> {
        val lista=conexion.leer()
        return lista.filter { it.nombre==nombre } as MutableList<Categoria>
    }

    override fun updateCategoria(ca: Categoria) {
        val lista = conexion.leer()
        val index = lista.indexOfFirst { it.nombre == ca.nombre }
        if (index != -1) {
            lista[index] = ca
            conexion.escribir(lista)
        } else {
            Log.d("error","La categoría ${ca.nombre} no existe")
        }
    }

    override fun deleteCategoria(ca: Categoria) {
        val lista = conexion.leer()
        val index = lista.indexOfFirst { it.nombre == ca.nombre }
        if (index != -1) {
            lista.removeAt(index)
            conexion.escribir(lista)
        } else {
            Log.d("error","La categoría ${ca.nombre} no existe")
        }    }


}