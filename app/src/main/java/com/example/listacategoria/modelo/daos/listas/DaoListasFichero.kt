package com.example.listacategoria.modelo.daos.listas

import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.entidades.Item
import com.example.listacategoria.modelo.entidades.Lista
import com.example.listacategoria.modelo.interfaces.InterfaceDaoListas

class DaoListasFichero: InterfaceDaoListas {
    lateinit var conexion: BDFichero

    override fun addLista(li: Lista) {

    }

    override fun getListas(): MutableList<Lista> {
        TODO("Not yet implemented")
    }

    override fun getListas(nombre: String): MutableList<Lista> {
        TODO("Not yet implemented")
    }

    override fun updateNombreLista(li: Lista) {
        TODO("Not yet implemented")
    }

    override fun deleteLista(li: Lista) {
        TODO("Not yet implemented")
    }

    // USOS ITEMS
    override fun addItem(ite: Item) {
        TODO("Not yet implemented")
    }

    override fun getItems(): MutableList<Item> {
        TODO("Not yet implemented")
    }

    override fun updateItem(ite: Item) {
        TODO("Not yet implemented")
    }

    override fun deleteItem(ite: Item) {
        TODO("Not yet implemented")
    }

    override fun createConexion(con: BDFichero) {
        conexion = con as BDFichero
    }


}