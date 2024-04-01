package com.example.listacategoria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listacategoria.modelo.conexiones.BDFichero
import com.example.listacategoria.modelo.daos.categorias.DaoCategoriasFichero
import com.example.listacategoria.modelo.entidades.Categoria
import com.example.listacategoria.modelo.interfaces.InterfaceDaoCategorias

class MainActivity : AppCompatActivity() {

     //lateinit var daoCategoria: InterfaceDaoCategorias
     lateinit var daoCategoria: InterfaceDaoCategorias
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daoCategoria = DaoCategoriasFichero(this)
        //añado categoria
        var hogar = Categoria("Hogar")
        daoCategoria.addCategoria(hogar)
        //muestro categorias
        Log.d("Categorias", daoCategoria.getCategorias().toString())



    }
}