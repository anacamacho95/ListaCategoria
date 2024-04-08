package com.example.listacategoria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.daos.categorias.DaoCategoriasFichero
import com.example.listacategoria.modelo.daos.listas.DaoTareasFichero
import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.entidades.Item
import com.example.listacategoria.modelo.entidades.Tarea
import com.example.listacategoria.modelo.interfaces.InterfaceDaoCategorias
import com.example.listacategoria.modelo.interfaces.InterfaceDaoListas

class MainActivity : AppCompatActivity() {

    lateinit var daoCategoria: InterfaceDaoCategorias
    lateinit var daoTarea: InterfaceDaoListas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val conexion= BDFichero(this)

        daoCategoria = DaoCategoriasFichero()
        daoTarea = DaoTareasFichero()
        daoTarea.createConexion(conexion)
        daoCategoria.createConexion(conexion)

        //borro datos del fichero
        conexion.borrarArchivos()

        //añado categorias
        var hogar = Categoria("Hogar")
        daoCategoria.addCategoria(hogar)
        var viajes = Categoria("Viajes")
        daoCategoria.addCategoria(viajes)

        //creo la lista de categorias
        val listCategorias: List<Categoria> = daoCategoria.getCategorias()
        //muestro categorias
        listCategorias.forEach {
            Log.d("Categorias", it.nombre)
        }

        //añado lista a categoria Hogar
        var cocina = Tarea( "Cocina")
        daoTarea.addTarea( hogar, cocina)
        var aseo = Tarea( "Aseo")
        daoTarea.addTarea( hogar, aseo)
        //veo lista de Hogar
        val listHogar: List<Tarea> = daoTarea.getTareas(hogar)
        listHogar.forEach {
            Log.d("ListaHogar", it.nombre)
        }

        //añado items a cocina
        var coc1 = Item("Hacer canelones", false)
        daoTarea.addItem(cocina, coc1)
        //veo items cocina
        val listCocina: List<Item> = daoTarea.getItems(cocina)
        listCocina.forEach{
            Log.d("ListaCocina", it.accion)
        }

        //incluyo listas en categoria de Viajes
        var playa = Tarea( "Playas")
        daoTarea.addTarea( viajes, playa)
        var mont = Tarea( "Montañas")
        daoTarea.addTarea( viajes, mont)
        val listViaje: List<Tarea> = daoTarea.getTareas(viajes)
        listViaje.forEach {
            Log.d("ListaViajes", it.nombre)
        }

        //muesta la lista de hogar
        val listasHogar: List<Tarea> = daoTarea.getTareas(hogar)
        for (lista in listasHogar) {
            Log.d("Listas de Hogar", "Lista: ${lista.nombre}")
            val items: List<Item> = daoTarea.getItems(lista)
            for (item in items) {
                Log.d("Listas de Hogar", "Item (${lista.nombre}): ${item.accion}")
            }
        }

        // Obtengo y muestro todas las categorías y sus listas e ítems
        val muestraCategorias: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias) {
            Log.d("muestraCategorias", "Categoría: ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria)
            for (lista in tareas) {
                Log.d("muestraCategorias", "Lista: ${lista.nombre}")

                val items: List<Item> = daoTarea.getItems(lista)
                for (item in items) {
                    Log.d("muestraCategorias", "*: ${item.accion}")
                }
            }
        }

        //modifico categorias
        var casa= Categoria("Casa")
        daoCategoria.updateCategoria(hogar,casa)
        val listCat2: List<Categoria> = daoCategoria.getCategorias()
        //muestro categorias actualizado
        listCat2.forEach {
            Log.d("CatActualizado", it.nombre)
        }
    }
}