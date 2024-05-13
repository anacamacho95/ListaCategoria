package com.example.listacategoria.modelo.daos.categorias

import android.util.Log
import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.interfaces.InterfaceDaoConexion
import com.example.listacategoria.modelo.interfaces.InterfaceDaoCategorias

class DaoCategoriasFichero: InterfaceDaoCategorias, InterfaceDaoConexion {
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


    override fun getCategoria(nombre: String): Categoria {
        val lista=conexion.leer()
        return lista.find { it.nombre == nombre }?: Categoria("Categoría no encontrada")
    }

    override fun updateCategoria(ca: Categoria) {
        val lista = conexion.leer()
        var categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        if (categoriaEncontrada != null) {
            categoriaEncontrada.nombre = ca.nombre// Actualizar el nombre de la categoría encontrada

            conexion.escribir(lista)
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
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