package com.example.listacategoria.modelo.daos.listas

import android.util.Log
import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.entidades.Item
import com.example.listacategoria.modelo.entidades.Tarea
import com.example.listacategoria.modelo.interfaces.InterfaceDao
import com.example.listacategoria.modelo.interfaces.InterfaceDaoListas

class DaoTareasFichero: InterfaceDaoListas, InterfaceDao {
    lateinit var conexion: BDFichero

    override fun createConexion(con: BDFichero) {
        conexion = con as BDFichero
    }

    override fun addTarea(ca: Categoria, ta: Tarea) {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        if (categoriaEncontrada != null) {
            categoriaEncontrada.tareas.add(ta)
            conexion.escribir(lista)
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
        }
    }

    override fun getTareas(ca: Categoria): MutableList<Tarea> {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        return categoriaEncontrada?.tareas ?: mutableListOf()    }

    override fun getTareas(ca: Categoria, ta: Tarea): MutableList<Tarea> {
        TODO("Not yet implemented")
    }


    override fun updateNombreTarea(ca: Categoria, ta: Tarea, nomAnt: String, nomNue: String) {
        TODO("Not yet implemented")

    }

    override fun deleteTarea(ca: Categoria, ta: Tarea) {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        if (categoriaEncontrada != null) {
            categoriaEncontrada.tareas.remove(ta)
            conexion.escribir(lista)
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
        }    }

    override fun addItem(ta: Tarea, ite: Item) {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ta.nombre }
        if (categoriaEncontrada != null) {
            val tareaEncontrada = categoriaEncontrada.tareas.find { it.nombre == ta.nombre }
            if (tareaEncontrada != null) {
                tareaEncontrada.items.add(ite)
                conexion.escribir(lista)
            } else {
                Log.d("error", "La tarea ${ta.nombre} no existe")
            }
        }
        ta.nTareas++
    }

    override fun getItems(ta: Tarea): MutableList<Item> {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ta.nombre }
        if (categoriaEncontrada != null) {
            val tareaEncontrada = categoriaEncontrada.tareas.find { it.nombre == ta.nombre }
            if (tareaEncontrada != null) {
                return tareaEncontrada.items
            } else {
                Log.d("error", "La tarea ${ta.nombre} no existe")
            }
        }
        return mutableListOf()    }

    override fun updateItem(ta: Tarea, ite: Item, ant: String, nue: String) {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ta.nombre }
        if (categoriaEncontrada != null) {
            val tareaEncontrada = categoriaEncontrada.tareas.find { it.nombre == ta.nombre }
            if (tareaEncontrada != null) {
                val itemEncontrado = tareaEncontrada.items.find { it.accion == ant }
                if (itemEncontrado != null) {
                    itemEncontrado.accion = nue
                    conexion.escribir(lista)
                } else {
                    Log.d("error", "El ítem con acción $ant no existe en la tarea ${ta.nombre}")
                }
            }
        }
    }

    override fun deleteItem(ta: Tarea, ite: Item) {
//        ta.items.remove(ite)
//        ta.nTareas--
        TODO("Not yet implemented")

    }

}