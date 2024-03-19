package com.example.listacategoria.modelo.daos.listas

import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.entidades.Item
import com.example.listacategoria.modelo.entidades.Lista
import com.example.listacategoria.modelo.interfaces.InterfaceDaoListas

class DaoListasFichero: InterfaceDaoListas {
    lateinit var conexion: BDFichero

    override fun addLista(ca: Categoria, li: Lista) {
        ca.listas.add(li)
    }

    override fun getListas(ca: Categoria): MutableList<Lista> {
        return ca.listas
    }

    override fun getListas(ca: Categoria, nombre: String): MutableList<Lista> {
        return ca.listas.filter { it.nombre == nombre }.toMutableList()
    }

    override fun updateNombreLista(ca: Categoria, li: Lista, nomAnt: String, nomNue: String) {
        val lista = ca.listas.find { it.nombre == nomAnt }
        lista?.nombre = nomNue
    }

    override fun deleteLista(ca: Categoria, li: Lista) {
        ca.listas.remove(li)
    }

    override fun addItem(li: Lista, ite: Item) {
        li.items.add(ite)
    }

    override fun getItems(li: Lista): MutableList<Item> {
        return li.items
    }

    override fun updateItem(li: Lista, ite: Item, ant: String, nue: String) {
        val item = li.items.find { it.accion == ant }
        item?.accion = nue
    }

    override fun deleteItem(li: Lista, ite: Item) {
        li.items.remove(ite)
    }


    override fun createConexion(con: BDFichero) {
        conexion = con as BDFichero
    }


}