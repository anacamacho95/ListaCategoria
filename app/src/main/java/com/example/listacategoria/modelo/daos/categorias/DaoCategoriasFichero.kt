package com.example.listacategoria.modelo.daos.categorias

import android.util.Log
import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.interfaces.InterfaceDao
import com.example.listacategoria.modelo.interfaces.InterfaceDaoCategorias

class DaoCategoriasFichero: InterfaceDaoCategorias, InterfaceDao {
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

    override fun updateCategoria(caAnt: Categoria, caNue: Categoria) {
        val lista = conexion.leer()
        var categoriaEncontrada = lista.find { it.nombre == caAnt.nombre }
        if (categoriaEncontrada != null) {
            categoriaEncontrada.nombre = caNue.nombre// Actualizar el nombre de la categoría encontrada

            conexion.escribir(lista)
        } else {
            Log.d("error", "La categoría ${caAnt.nombre} no existe")
        }


    }

    override fun deleteCategoria(ca: Categoria) {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        if (categoriaEncontrada != null) {
            lista.remove(categoriaEncontrada)
            conexion.escribir(lista)

        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
        }
    }


}