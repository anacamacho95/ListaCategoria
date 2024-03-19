package com.example.listacategoria.modelo.interfaces

import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.entidades.Item
import com.example.listacategoria.modelo.entidades.Lista

interface InterfaceDaoListas: InterfaceDao {
    //CRUD LISTAS
    //crear
    fun addLista (ca: Categoria, li: Lista)
    //leer Todas las listas
    fun getListas(ca: Categoria): MutableList<Lista>
    //leer una lista en concreto
    fun getListas(ca: Categoria, nombre: String): MutableList<Lista>
    //actualizar
    fun updateNombreLista(ca: Categoria, li: Lista, nomAnt: String, nomNue: String)
    //borrar
    fun deleteLista (ca: Categoria, li: Lista)

    //CRUD ITEMS
    //crear
    fun addItem (li: Lista, ite: Item)
    //leer Todos los items
    fun getItems(li: Lista): MutableList<Item>
    //actualizar item
    fun updateItem(li: Lista,ite: Item,  ant: String, nue: String)
    //borrar
    fun deleteItem (li: Lista, ite: Item)
}