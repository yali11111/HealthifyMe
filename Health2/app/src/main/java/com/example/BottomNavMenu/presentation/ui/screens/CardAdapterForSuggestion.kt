
package com.example.health2.presentation.ui.adapters

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Modèle représentant une suggestion personnalisée.
 */
data class SuggestionCard(
    val id: String,
    val title: String,
    val description: String,
    val category: String
)

/**
 * Adapter permettant d'afficher les suggestions personnalisées.
 */
class CardAdapterForSuggestion(
    private val items: List<SuggestionCard>,
    private val onItemClick: (SuggestionCard) -> Unit
) : RecyclerView.Adapter<CardAdapterForSuggestion.SuggestionViewHolder>() {

    /**
     * ViewHolder représentant une suggestion.
     */
    inner class SuggestionViewHolder(
        private val textView: TextView
    ) : RecyclerView.ViewHolder(textView) {

        fun bind(item: SuggestionCard) {

            textView.text = "${item.title}\n" +
                    "${item.description}\n" +
                    "Catégorie : ${item.category}"

            textView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    /**
     * Création visuelle des cartes de suggestions.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuggestionViewHolder {

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

        return SuggestionViewHolder(textView)
    }

    /**
     * Association des données à chaque suggestion.
     */
    override fun onBindViewHolder(
        holder: SuggestionViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    /**
     * Retourne le nombre total de suggestions.
     */
    override fun getItemCount(): Int {
        return items.size
    }
}


