package com.example.listacategoria.modelo.interfaces

import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.entidades.Item
import com.example.listacategoria.modelo.entidades.Tarea

interface InterfaceDaoListas: InterfaceDao {
    //CRUD LISTAS
    //crear
    fun addTarea (ca: Categoria, ta: Tarea)
    //leer Todas las listas
    fun getTareas(ca: Categoria): MutableList<Tarea>
    //leer una lista en concreto
    fun getTareas(ca: Categoria, ta: Tarea): MutableList<Tarea>
    //actualizar
    fun updateNombreTarea(ca: Categoria, ta: Tarea, nomAnt: String, nomNue: String)
    //borrar
    fun deleteTarea (ca: Categoria, ta: Tarea)

    //CRUD ITEMS
    //crear
    fun addItem (ta: Tarea, ite: Item)
    //leer Todos los items
    fun getItems(ta: Tarea): MutableList<Item>
    //actualizar item
    fun updateItem(ta: Tarea, ite: Item, ant: String, nue: String)
    //borrar
    fun deleteItem (ta: Tarea, ite: Item)
}