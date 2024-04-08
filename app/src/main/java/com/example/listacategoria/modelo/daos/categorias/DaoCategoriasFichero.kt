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
        var categoriaEncontrada = false
        for (i in lista.indices) {
            val categoria = lista[i]
            if (categoria.nombre == caAnt.nombre) {
                lista[i] = caNue // Actualiza la categoría en la lista
                categoriaEncontrada = true
                break // Sale del bucle una vez que se actualiza la categoría
            }
        }
        if (categoriaEncontrada) {
            conexion.escribir(lista) // Escribe la lista actualizada en el archivo
        } else {
            Log.d("error", "La categoría ${caAnt.nombre} no existe")
        }
    }

    override fun deleteCategoria(ca: Categoria) {
        val lista = conexion.leer()
        val indice = lista.indexOf(ca)
        if (indice != -1) {
            lista.removeAt(indice)
            conexion.escribir(lista)
        } else {
            Log.d("error","La categoría ${ca.nombre} no existe")
        }
    }


}