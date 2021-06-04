package com.example.livros

import android.database.Cursor
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AdapterLivros(var cursor: Cursor? = null)RecyclerView.Adapter<AdapterLivros.ViewHolderLivro>() {
    class ViewHolderLivro(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}