package com.example.livros

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaLivro (db: SQLiteDatabase) : BaseColumns  {
        private val db: SQLiteDatabase = db
        fun cria() {
            db.execSQL("CREATE TABLE $NOME_TABELA  (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_TITULO TEXT NOT NULL, $CAMPO_AUTOR TEXT NOT NULL), $CAMPO_ID_CATEGORIA INTEGER NOT NULL,FOREIGN KEY $CAMPO_ID_CATEGORIA , REFERENCES ${(TabelaCategorias.NOME_TABELA})"

            //Create table livros(
            // _id INTEGER PRIMARY KEY AUTOINCREMENT,
            // titulo TEXT NOT NULL,
            // autor TEXT NOT NULL,
            // id_categoria INTEGER NOT NULL,
            // FOREIGN KEY (id_categorias) REFERENCES categorias
            // )
            )


        }

        fun insert(values: ContentValues): Long {
            return db.insert(NOME_TABELA,null,values)
        }

        fun update(values: ContentValues, whereArgs: Array<String>, whereClause: String): Int {
            return db.update(NOME_TABELA,values,whereClause,whereArgs)


        }

        fun delete(whereClause: String, WhereArgs: Array<String>): Int {
            return db.delete(NOME_TABELA,whereClause,WhereArgs)
        }


        fun query(
                columns: Array<String>,
                selection: String,
                selectionArgs: Array<String>,
                groupBy: String,
                having: String,
                orderBy: String
        ): Cursor? {
            return db.query(NOME_TABELA,columns,selection,selectionArgs,groupBy,having,orderBy)
        }

        //CRUD




        companion object{
            const val NOME_TABELA = "livros"
            const val CAMPO_TITULO = "titulo"
            const val CAMPO_AUTOR = "autor"
            const val CAMPO_ID_CATEGORIA = "id_categoria"

        }






}