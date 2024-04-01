package com.example.listacategoria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.daos.categorias.DaoCategoriasFichero
import com.example.listacategoria.modelo.daos.listas.DaoListasFichero
import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.entidades.Item
import com.example.listacategoria.modelo.entidades.Lista
import com.example.listacategoria.modelo.interfaces.InterfaceDaoCategorias
import com.example.listacategoria.modelo.interfaces.InterfaceDaoListas

class MainActivity : AppCompatActivity() {

    lateinit var daoCategoria: InterfaceDaoCategorias
    lateinit var daoLista: InterfaceDaoListas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val conexion= BDFichero(this)


        daoCategoria = DaoCategoriasFichero()
        daoLista = DaoListasFichero()
        daoCategoria.createConexion(conexion)

        //borro datos del fichero
        var bdFichero = BDFichero(this)
        bdFichero.borrarArchivos()

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
        var cocina = Lista( "Cocina")
        daoLista.addLista( hogar, cocina)
        var aseo = Lista( "Aseo")
        daoLista.addLista( hogar, aseo)

        //veo lista de hogar
        val listHogar: List<Lista> = daoLista.getListas(hogar)
        listHogar.forEach {
            Log.d("Hogar", it.nombre)
        }

        //añado items a cocina
        var coc1 = Item("Hacer canelones", false)
        daoLista.addItem(cocina, coc1)

        //veo items cocina
        val listCocina: List<Item> = daoLista.getItems(cocina)
        listCocina.forEach{
            Log.d("Cocina", it.accion)
        }

        //incluyo lista en categoria de Viajes
        var playa = Lista( "Playas")
        daoLista.addLista( viajes, playa)
        val listViaje: List<Lista> = daoLista.getListas(hogar)
        listViaje.forEach {
            Log.d("Viajes", it.nombre)
        }

    }
}