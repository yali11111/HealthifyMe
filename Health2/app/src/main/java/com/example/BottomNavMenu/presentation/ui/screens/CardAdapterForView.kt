
package com.example.health2.presentation.ui.adapters

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Modèle représentant une carte de statistiques de santé.
 */
data class ViewCard(
    val title: String,
    val value: String,
    val description: String
)

/**
 * Adapter permettant d'afficher les statistiques de santé.
 */
class CardAdapterForView(
    private val items: List<ViewCard>,
    private val onItemClick: (ViewCard) -> Unit
) : RecyclerView.Adapter<CardAdapterForView.ViewViewHolder>() {

    /**
     * ViewHolder représentant une carte de statistiques.
     */
    inner class ViewViewHolder(
        private val textView: TextView
    ) : RecyclerView.ViewHolder(textView) {

        fun bind(item: ViewCard) {

            textView.text = "${item.title}\n" +
                    "${item.value}\n" +
                    "${item.description}"

            textView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    /**
     * Création visuelle des cartes de statistiques.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewViewHolder {

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

        return ViewViewHolder(textView)
    }

    /**
     * Association des données aux cartes.
     */
    override fun onBindViewHolder(
        holder: ViewViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    /**
     * Retourne le nombre total de cartes.
     */
    override fun getItemCount(): Int {
        return items.size
    }
}

