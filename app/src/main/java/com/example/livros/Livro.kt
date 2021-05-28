package com.example.livros

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import androidx.core.content.contentValuesOf

data class Livro(var id: Long = -1, var titulo: String,var autor: String, var idCategoria:Long)  {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaLivro.CAMPO_AUTOR, autor)
        valores.put(TabelaLivro.CAMPO_TITULO, titulo)
        valores.put(TabelaLivro.CAMPO_ID_CATEGORIA, idCategoria)

        return valores
    }


    companion object {
        fun fromCursor(cursor: Cursor): Livro {
            val colId = cursor.getColumnIndex(BaseColumns._ID)

            val colTitulo = cursor.getColumnIndex(TabelaLivro.CAMPO_TITULO)
            val colAutor= cursor.getColumnIndex(TabelaLivro.CAMPO_AUTOR)
            val colCategoria = cursor.getColumnIndex(TabelaLivro.CAMPO_ID_CATEGORIA)


            val id = cursor.getLong(colId)
            val titulo = cursor.getString(colTitulo)
            val autor = cursor.getString(colAutor)
            val idCategoria= cursor.getLong(colCategoria)

            return Livro(id,titulo,autor,idCategoria)
        }
    }


}