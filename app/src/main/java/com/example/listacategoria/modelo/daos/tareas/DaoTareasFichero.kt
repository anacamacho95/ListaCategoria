package com.example.listacategoria.modelo.daos.tareas

import android.util.Log
import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.entidades.Item
import com.example.listacategoria.modelo.entidades.Tarea
import com.example.listacategoria.modelo.interfaces.InterfaceDaoConexion
import com.example.listacategoria.modelo.interfaces.InterfaceDaoTareas

class DaoTareasFichero: InterfaceDaoTareas, InterfaceDaoConexion {
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

    override fun getTarea(ta: Tarea): Tarea? {
        val lista = conexion.leer()
        for (categoria in lista) {
            val tareaEncontrada = categoria.tareas.find { it.nombre == ta.nombre }
            if (tareaEncontrada != null) {
                return tareaEncontrada
            }
        }
        return Tarea("Tarea no encontrada")
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
            val tareaEncontrada = categoriaEncontrada.tareas.find { it.nombre == ta.nombre }
            if (tareaEncontrada != null) {
                categoriaEncontrada.tareas.remove(tareaEncontrada)
                conexion.escribir(lista)
            } else {
                Log.d("error", "La tarea ${ta.nombre} no existe en la categoría ${ca.nombre}")
            }
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

    override fun getItem(it: Item): Item? {
        val lista = conexion.leer()
        for (categoria in lista) {
            for (tarea in categoria.tareas) {
                val itemEncontrado = tarea.items.find { it.accion == it.accion }
                if (itemEncontrado != null) {
                    return itemEncontrado
                }
            }
        }
        return Item("Item no encontrado",false)
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
                val itemEncontrado = tareaEncontrada.items.find { it.accion == ite.accion }
                if (itemEncontrado != null) {
                    tareaEncontrada.items.remove(itemEncontrado)
                    conexion.escribir(lista)
                } else
                    Log.d("error", "El Item con accion ${ite.accion} no existe en la tarea ${ta.nombre}")
            } else {
                Log.d("error", "La tarea ${ta.nombre} no existe en la categoría ${ca.nombre}")
            }
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
        }
    }

    override fun getNItems(ca: Categoria, ta: Tarea): Int {
        val lista = conexion.leer()
        val categoriaEncontrada = lista.find { it.nombre == ca.nombre }

        if (categoriaEncontrada != null) {
            val tareaEncontrada = categoriaEncontrada.tareas.find { it.nombre == ta.nombre }
            return tareaEncontrada?.items?.size ?: 0 //si tareaEncontrada o items es null devuelve 0
        } else {
            Log.d("error", "La categoría ${ca.nombre} no existe")
            return 0
        }
    }

}