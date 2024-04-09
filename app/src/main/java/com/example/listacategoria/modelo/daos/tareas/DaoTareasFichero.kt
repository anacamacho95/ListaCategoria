package com.example.listacategoria.modelo.daos.tareas

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
        return categoriaEncontrada?.tareas ?: mutableListOf()
    }

    override fun updateNombreTarea(ca: Categoria, taAnt: Tarea, taNue: Tarea) {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        if (categoriaEncontrada != null) {
            val tareaEncontrada = categoriaEncontrada.tareas.find { it.nombre == taAnt.nombre }
            if (tareaEncontrada != null) {
                tareaEncontrada.nombre = taNue.nombre
                conexion.escribir(lista)
            } else {
                Log.d("error", "La tarea ${taAnt.nombre} no existe en la categoría ${ca.nombre}")
            }
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
        }
    }

    override fun deleteTarea(ca: Categoria, ta: Tarea) {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        if (categoriaEncontrada != null) {
            categoriaEncontrada.tareas.remove(ta)
            conexion.escribir(lista)
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
        }
    }



    override fun addItem(ca: Categoria, ta: Tarea, ite: Item) {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        if (categoriaEncontrada != null) {
            val tareaEncontrada = categoriaEncontrada.tareas.find { it.nombre == ta.nombre }
            if (tareaEncontrada != null) {
                tareaEncontrada.items.add(ite)
                conexion.escribir(lista)
                ta.nItems++ // Incrementar el contador de items
            } else {
                Log.d("error", "La tarea ${ta.nombre} no existe en la categoría ${ca.nombre}")
            }
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
        }
    }

    override fun getItems(ca: Categoria, ta: Tarea): MutableList<Item> {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        if (categoriaEncontrada != null) {
            val tareaEncontrada = categoriaEncontrada.tareas.find { it.nombre == ta.nombre }
            if (tareaEncontrada != null) {
                return tareaEncontrada.items
            } else {
                Log.d("error", "La tarea ${ta.nombre} no existe en la categoría ${ca.nombre}")
            }
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
        }
        return mutableListOf()
    }


    override fun updateItem(ca: Categoria, ta: Tarea, iteAnt: Item, iteNue: Item) {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        if (categoriaEncontrada != null) {
            val tareaEncontrada = categoriaEncontrada.tareas.find { it.nombre == ta.nombre }
            if (tareaEncontrada != null) {
                val itemIndex = tareaEncontrada.items.indexOfFirst { it.accion == iteAnt.accion }
                if (itemIndex != -1) {
                    tareaEncontrada.items[itemIndex] = iteNue
                    conexion.escribir(lista)
                } else {
                    Log.d("error", "El ítem ${iteAnt.accion} no existe en la tarea ${ta.nombre}")
                }
            } else {
                Log.d("error", "La tarea ${ta.nombre} no existe en la categoría ${ca.nombre}")
            }
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
        }
    }

    override fun deleteItem(ca: Categoria, ta: Tarea, ite: Item) {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }
        if (categoriaEncontrada != null) {
            val tareaEncontrada = categoriaEncontrada.tareas.find { it.nombre == ta.nombre }
            if (tareaEncontrada != null) {
                tareaEncontrada.items.remove(ite)
                ta.nItems--//decrementa el numero de items
                conexion.escribir(lista)
            } else {
                Log.d("error", "La tarea ${ta.nombre} no existe en la categoría ${ca.nombre}")
            }
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
        }
    }

}