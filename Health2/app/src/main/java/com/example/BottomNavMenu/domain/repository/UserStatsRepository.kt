package com.example.BottomNavMenu.domain.repository

import com.example.health2.data.remote.dto.UserStatsDto

/**

Contrat métier pour la gestion des statistiques
de santé d'un utilisateur.
Définit les opérations disponibles pour consulter
les statistiques personnelles. */ interface UserStatsRepository {

/**

Récupère toutes les statistiques d'un utilisateur.
@param userId Identifiant de l'utilisateur.
@return Liste des statistiques ou une erreur. */ 

suspend fun getUserStats( userId: String ): Result<List<UserStatsDto>>

/**

Récupère une statistique précise.
        @param userId Identifiant de l'utilisateur.
        @param id Identifiant de la mesure.
        @return Statistique demandée ou une erreur. */ 
        
       suspend fun getUserStatById( userId: String, id: String ): Result<UserStatsDto> } 
       EOF

echo "[1/3] UserStatsRepository.kt OK"
# ==========================================
# 2. SUGGESTION REPOSITORY
# ==========================================

cat > "$REPOSITORY/SuggestionRepository.kt" <<'EOF' 
package com.example.BottomNavMenu.domain.repository

import com.example.health2.data.remote.dto.SuggestionDto

/**

    Contrat métier pour la gestion des suggestions
    personnalisées de santé.
    Les suggestions peuvent concerner :
        L'activité physique.
        Le sommeil.
        L'alimentation.
        Le bien-être général.

*/ interface SuggestionRepository {

/**
 * Récupère les suggestions d'un utilisateur.
 *
 * @param userId Identifiant de l'utilisateur.
 * @return Liste des suggestions ou une erreur.
 */
suspend fun getSuggestions(
    userId: String
): Result<List<SuggestionDto>>

} 
