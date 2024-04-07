package com.example.listacategoria.modelo.interfaces

import com.example.listacategoria.modelo.entidades.Categoria

interface InterfaceDaoCategorias: InterfaceDao {

    //CRUD
    //crear
    fun addCategoria (ca: Categoria)
    //leer Todas las categorias
    fun getCategorias(): MutableList<Categoria>
    //actualizar
    fun updateCategoria(caAnt: Categoria, caNue: Categoria)
    //borrar
    fun deleteCategoria (ca: Categoria)
}