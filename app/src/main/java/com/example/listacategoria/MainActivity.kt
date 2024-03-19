package com.example.listacategoria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        var daoCategoria: InterfaceDaoCategorias =DaoCategoriasFichero()
        //añado categoria
        var hogar = Categoria("Hogar")
        daoCategoria.addCategoria(hogar)
        //muestro categorias
        daoCategoria.getCategorias()



    }
}