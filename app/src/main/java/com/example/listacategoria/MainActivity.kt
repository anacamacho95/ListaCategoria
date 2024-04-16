package com.example.listacategoria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.daos.categorias.DaoCategoriasFichero
import com.example.listacategoria.modelo.daos.tareas.DaoTareasFichero
import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.entidades.Item
import com.example.listacategoria.modelo.entidades.Tarea
import com.example.listacategoria.modelo.interfaces.InterfaceDaoCategorias
import com.example.listacategoria.modelo.interfaces.InterfaceDaoTareas

class MainActivity : AppCompatActivity() {

    lateinit var daoCategoria: InterfaceDaoCategorias
    lateinit var daoTarea: InterfaceDaoTareas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val conexion= BDFichero(this)

        daoCategoria = DaoCategoriasFichero()
        daoTarea = DaoTareasFichero()
        daoTarea.createConexion(conexion)
        daoCategoria.createConexion(conexion)

        conexion.borrarArchivos()
        Log.d("pruebas", " -- Datos previos eliminados --")
        pruebas()

    }

    fun pruebas(){

        Log.d("pruebas", " *** Añado categorias: hogar y viajes ***")
        var hogar = Categoria("Hogar")
        daoCategoria.addCategoria(hogar)
        var viajes = Categoria("Viajes")
        daoCategoria.addCategoria(viajes)

        Log.d("pruebas", " *** Veo categorias añadidas ***")
        val listCategorias: List<Categoria> = daoCategoria.getCategorias()
        listCategorias.forEach {
            Log.d("pruebas", it.nombre)
        }

        Log.d("pruebas", " *** Añado tareas a la categoria Hogar *** ")
        var cocina = Tarea( "Cocina")
        daoTarea.addTarea( hogar, cocina)
        var aseo = Tarea( "Aseo")
        daoTarea.addTarea( hogar, aseo)
        val listHogar: List<Tarea> = daoTarea.getTareas(hogar)
        listHogar.forEach {
            Log.d("pruebas", it.nombre)
        }

        Log.d("pruebas", " *** Añado items a la categoria Cocina *** ")
        var coc1 = Item("Hacer canelones", false)
        daoTarea.addItem(hogar, cocina, coc1)
        val listCocina: List<Item> = daoTarea.getItems(hogar, cocina)
        listCocina.forEach{
            Log.d("pruebas", it.accion)
        }

        Log.d("pruebas", " *** Añado tareas a la categoria Viajes *** ")
        var playa = Tarea( "Playas")
        daoTarea.addTarea( viajes, playa)
        var mont = Tarea( "Montañas")
        daoTarea.addTarea( viajes, mont)
        val listViaje: List<Tarea> = daoTarea.getTareas(viajes)
        listViaje.forEach {
            Log.d("pruebas", it.nombre)
        }

        Log.d("pruebas", " *** Muestro todas las tareas de la categoria Hogar *** ")
        val listasHogar: List<Tarea> = daoTarea.getTareas(hogar)
        for (lista in listasHogar) {
            Log.d("pruebas", "Tarea: ${lista.nombre}")
            val items: List<Item> = daoTarea.getItems(hogar,lista)
            for (item in items) {
                Log.d("pruebas", "- ${item.accion}")
            }
        }

        Log.d("pruebas", " *** Muestro todas las categorias con sus tareas e items *** ")
        val muestraCategorias: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria)
            for (lista in tareas) {
                Log.d("pruebas", "Tarea: ${lista.nombre}")

                val items: List<Item> = daoTarea.getItems(categoria,lista)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
            }
        }

        Log.d("pruebas", " *** Actualizo item de cocina (Canelones por lavavajillas) *** ")
        var coc2 = Item("Poner lavavajillas", false)
        daoTarea.updateItem(hogar,cocina,coc1,coc2)
        val listasHogar1: List<Tarea> = daoTarea.getTareas(hogar)
        for (lista3 in listasHogar1) {
            Log.d("pruebas", "Tarea: ${lista3.nombre}")
            val items: List<Item> = daoTarea.getItems(hogar,lista3)
            for (item in items) {
                Log.d("pruebas", "- ${item.accion}")
            }
        }

        Log.d("pruebas", " *** Actualizo nombre de tarea Aseo por Habitación *** ")
        var habitacion = Tarea("Habitación")
        daoTarea.updateNombreTarea(hogar,aseo,habitacion)
        val listasHogar2: List<Tarea> = daoTarea.getTareas(hogar)
        for (lista in listasHogar2) {
            Log.d("pruebas", "Tarea: ${lista.nombre}")
            val items: List<Item> = daoTarea.getItems(hogar,lista)
            for (item in items) {
                Log.d("pruebas", "- ${item.accion}")
            }
        }

        Log.d("pruebas", " *** Actualizo nombre de categoria Hogar por Casa *** ")
        var casa= Categoria("Casa")
        daoCategoria.updateCategoria(hogar,casa)
        val listCat3: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in listCat3) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas1: List<Tarea> = daoTarea.getTareas(categoria)
            for (lista in tareas1) {
                Log.d("pruebas", "Tarea: ${lista.nombre}")

                val items1: List<Item> = daoTarea.getItems(categoria,lista)
                for (item in items1) {
                    Log.d("pruebas", "- ${item.accion}")
                }
            }
        }


        Log.d("pruebas", " *** Elimino item (lavavajillas) de la tarea Casa *** ")
        daoTarea.deleteItem(casa,cocina,coc2)
        val muestraCategorias2: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias2) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas2: List<Tarea> = daoTarea.getTareas(categoria)
            for (lista in tareas2) {
                Log.d("pruebas", "Tarea: ${lista.nombre}")

                val items2: List<Item> = daoTarea.getItems(categoria,lista)
                for (item in items2) {
                    Log.d("pruebas", "- ${item.accion}")
                }
            }
        }

        Log.d("pruebas", " *** Elimino tarea (Cocina) de la Categoria Casa *** ")
        daoTarea.deleteTarea(casa,cocina)
        val muestraCategorias3: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias3) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas3: List<Tarea> = daoTarea.getTareas(categoria)
            for (lista in tareas3) {
                Log.d("pruebas", "Tarea: ${lista.nombre}")

                val items3: List<Item> = daoTarea.getItems(categoria,lista)
                for (item in items3) {
                    Log.d("pruebas", "- ${item.accion}")
                }
            }
        }

        Log.d("pruebas", " *** Elimino categoria Casa *** ")
        daoCategoria.deleteCategoria(casa)
        val muestraCategorias4: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias4) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas4: List<Tarea> = daoTarea.getTareas(categoria)
            for (lista in tareas4) {
                Log.d("pruebas", "Tarea: ${lista.nombre}")

                val items4: List<Item> = daoTarea.getItems(categoria,lista)
                for (item in items4) {
                    Log.d("pruebas", "- ${item.accion}")
                }
            }
        }



    }
}