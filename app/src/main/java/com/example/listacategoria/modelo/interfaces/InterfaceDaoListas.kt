package com.example.listacategoria.modelo.interfaces

import com.example.listacategoria.modelo.entidades.Item
import com.example.listacategoria.modelo.entidades.Lista

interface InterfaceDaoListas: InterfaceDao {
    //CRUD LISTAS
    //crear
    fun addLista (li: Lista)
    //leer Todas las listas
    fun getListas(): MutableList<Lista>
    //leer una lista en concreto
    fun getListas(nombre: String): MutableList<Lista>
    //actualizar
    fun updateNombreLista(li: Lista)
    //borrar
    fun deleteLista (li: Lista)

    //CRUD ITEMS
    //crear
    fun addItem (ite: Item)
    //leer Todos los items
    fun getItems(): MutableList<Item>
    //actualizar item
    fun updateItem(ite: Item)
    //borrar
    fun deleteItem (ite: Item)
}