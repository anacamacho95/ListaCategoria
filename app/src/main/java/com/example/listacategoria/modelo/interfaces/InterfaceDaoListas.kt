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
    //actualizar
    fun updateNombreTarea(ca: Categoria, taAnt: Tarea, taNue: Tarea)
    //borrar
    fun deleteTarea (ca: Categoria, ta: Tarea)

    //CRUD ITEMS
    //crear
    fun addItem (ca: Categoria, ta: Tarea, ite: Item)
    //leer Todos los items
    fun getItems(ca: Categoria, ta: Tarea): MutableList<Item>
    //actualizar item
    fun updateItem(ca: Categoria, ta: Tarea,iteAnt: Item, iteNue: Item)
    //borrar
    fun deleteItem (ca: Categoria ,ta: Tarea, ite: Item)
}