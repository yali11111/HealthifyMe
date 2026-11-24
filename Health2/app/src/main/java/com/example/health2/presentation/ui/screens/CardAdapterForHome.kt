
package com.example.health2.presentation.ui.adapters

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Modèle représentant une carte d'ajout de mesure.
 */
data class AddCard(
    val id: String,
    val title: String,
    val description: String
)

/**
 * Adapter permettant d'afficher les cartes d'ajout de mesures.
 */
class CardAdapterForAdd(
    private val items: List<AddCard>,
    private val onItemClick: (AddCard) -> Unit
) : RecyclerView.Adapter<CardAdapterForAdd.AddViewHolder>() {

    /**
     * ViewHolder représentant une carte.
     */
    inner class AddViewHolder(
        private val textView: TextView
    ) : RecyclerView.ViewHolder(textView) {

        fun bind(item: AddCard) {

            textView.text = "${item.title}\n${item.description}"

            textView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    /**
     * Création visuelle des cartes.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddViewHolder {

        val textView = TextView(parent.context).apply {

            textSize = 16f

            gravity = Gravity.CENTER_VERTICAL

            setPadding(32, 32, 32, 32)

            setTextColor(Color.BLACK)

            setBackgroundColor(Color.LTGRAY)

            layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 16
            }
        }

        return AddViewHolder(textView)
    }

    /**
     * Association des données aux cartes.
     */
    override fun onBindViewHolder(
        holder: AddViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    /**
     * Nombre total de cartes.
     */
    override fun getItemCount(): Int {
        return items.size
    }
}

