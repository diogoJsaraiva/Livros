package com.example.livros

import android.annotation.SuppressLint
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterLivros( val fragment: ListaLivrosFragment):RecyclerView.Adapter<AdapterLivros.ViewHolderLivro>() {
    public var cursor: Cursor? = null
    get() = field
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    class ViewHolderLivro(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val textViewTitulo = itemView.findViewById<TextView>(R.id.textViewTitulo)
        private val textViewAutor= itemView.findViewById<TextView>(R.id.textViewAutor)
        private val textViewCategoria = itemView.findViewById<TextView>(R.id.textViewCategoria)

        internal var livro : Livro? = null




        init {
            itemView.setOnClickListener(this)
        }

        fun atualizaLivro(livro: Livro){
            this.livro = livro

            textViewTitulo.text = livro.titulo
            textViewAutor.text = livro.autor
            textViewCategoria.text = livro.nomeCategoria
        }




        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        override fun onClick(v: View?) {
            selecionado?.desseleciona()
            seleciona()
        }


        private fun seleciona() {
            selecionado = this
            itemView.setBackgroundResource(R.color.cor_selecao)
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }

        companion object{
            var selecionado: ViewHolderLivro? = null
        }
    }
        companion object{
            fun getLivroSelecionado() = ViewHolderLivro.selecionado?.livro
        }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderLivro {
        val itemLivro = fragment.layoutInflater.inflate(R.layout.item_livro,parent,false)
        return ViewHolderLivro(itemLivro)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolderLivro, position: Int) {
        cursor!!.moveToPosition(position)
        holder.atualizaLivro(Livro.fromCursor(cursor!!))
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
       return cursor?.count ?: 0
    }
}