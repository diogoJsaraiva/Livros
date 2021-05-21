package com.example.livros

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Assert.*


import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestBaseDados {
    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext
    private fun GetBdLivrosOpenHelper() = BdLivrosOpenHelper(getAppContext())
    private fun gettabelaCategorias(db: SQLiteDatabase) = TabelaCategorias(db)

    private fun inserirCategoria(gettabelaCategorias: TabelaCategorias, categoria: Categoria) : Long {
        val id = gettabelaCategorias.insert(categoria.toContentValues())
        assertNotEquals(-1, id)
        return id
    }


    @Before
    fun apagaBaseDados(){
        getAppContext().deleteDatabase(BdLivrosOpenHelper.NOME_BASE_DADOS)
    }



    @Test
    fun consegueAbrirBaseDados(){
        val db = GetBdLivrosOpenHelper().readableDatabase
        assert(db.isOpen)
        db.close()


    }

    @Test
    fun consegueInserirCategorias(){
        val db = GetBdLivrosOpenHelper().writableDatabase


        val categoria = Categoria(nome="Drama")
        inserirCategoria(gettabelaCategorias(db), categoria)

        db.close()
    }

    @Test
    fun consegueAlterarCategorias{
        val db = GetBdLivrosOpenHelper().writableDatabase

        val categoria = Categoria(nome="sci")
        val gettabelaCategorias = gettabelaCategorias(db)

        inserirCategoria(gettabelaCategorias, categoria)
        categoria.id = inserirCategoria(gettabelaCategorias,categoria)
        categoria.nome = "sci-fi"

        val registosAlterados = gettabelaCategorias.update(
                categoria.toContentValues(),"${BaseColumns._ID}=?",
                arrayOf(categoria.id.toString())
        )

        assertEquals(1, registosAlterados)


        db.close()
    }

    @Test
    fun consegueApagarCategorias(){
        val db = GetBdLivrosOpenHelper().writableDatabase
        val gettabelaCategorias = gettabelaCategorias(db)

        val categoria = Categoria(nome="sci")
        categoria.id = inserirCategoria(gettabelaCategorias,categoria)

        val registosEleminatos = gettabelaCategorias.delete(
                "${BaseColumns._ID}=?",
                arrayOf(categoria.id.toString())
        )

        assertEquals(1,registosEleminatos)

    }

    @Test
    fun consegueLerCategorias(){
        val db = GetBdLivrosOpenHelper().writableDatabase
        val gettabelaCategorias = gettabelaCategorias(db)
        val categoria = Categoria(nome="sci")
        categoria.id = inserirCategoria(gettabelaCategorias,categoria)

        val cursor = gettabelaCategorias.query(
                TabelaCategorias.TODOS_CAMPOS,
                "${BaseColumns._ID}=?",
                arrayOf(categoria.id.toString()),
                null,null,null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

       
    }



}