package com.example.listacategoria.modelo.conexiones

import android.content.Context
import com.example.listacategoria.modelo.entidades.Categoria
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class BDFichero (private val context: Context) {
    val nombre="listaDeTareas.dat"

    fun escribir(lista:MutableList<Categoria>,nombreArchivo: String=nombre) {
        try {
            val fileOutputStream = context.openFileOutput(nombreArchivo, Context.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(lista)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    fun leer():MutableList<Categoria> {
        var lista: MutableList<Categoria>? = null
        try {
            val fileInputStream = context.openFileInput(nombre)
            val objectInputStream = ObjectInputStream(fileInputStream)
            lista = objectInputStream.readObject() as MutableList<Categoria>
            objectInputStream.close()
            fileInputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return lista ?: mutableListOf()
    }



    fun borrarArchivos() {
        val archivo = context.getFileStreamPath(nombre)
        if (archivo.exists()) {
            archivo.delete()
        }
    }
}