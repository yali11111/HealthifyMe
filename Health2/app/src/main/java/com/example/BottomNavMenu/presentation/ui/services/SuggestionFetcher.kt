package com.example.BottomNavMenu.presentation.ui.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Modèle simple de suggestion.
 */
data class HealthSuggestion(
    val id: String,
    val title: String,
    val description: String,
    val category: String
)

/**
 * SuggestionFetcher
 *
 * Fournit une première interface
 * de récupération des suggestions.
 *
 * Les règles de génération et les données
 * définitives seront connectées aux
 * Repository et UseCases de DOMAIN.
 */
class SuggestionFetcher {

    /**
     * Récupère les suggestions disponibles.
     *
     * Version initiale locale.
     */
    suspend fun fetchSuggestions():
        Result<List<HealthSuggestion>> {

        return withContext(Dispatchers.IO) {

            runCatching {

                listOf(
                    HealthSuggestion(
                        id = "1",
                        title = "Activité physique",
                        description =
                            "Pensez à intégrer une activité physique régulière.",
                        category = "Activité"
                    ),
                    HealthSuggestion(
                        id = "2",
                        title = "Hydratation",
                        description =
                            "Pensez à boire régulièrement de l'eau.",
                        category = "Hydratation"
                    ),
                    HealthSuggestion(
                        id = "3",
                        title = "Sommeil",
                        description =
                            "Conservez des horaires de sommeil réguliers.",
                        category = "Sommeil"
                    )
                )
            }
        }
    }
}
