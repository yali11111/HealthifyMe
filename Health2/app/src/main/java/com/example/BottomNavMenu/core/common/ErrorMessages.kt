package com.example.BottomNavMenu.core.common

/**
 * Messages comprehensibles pour les utilisateurs.
 *
 * Ne jamais afficher les exceptions techniques.
 */
object ErrorMessages {

    fun getMessage(error: AppError): String {

        return when (error) {

            AppError.NetworkUnavailable ->
                "Aucune connexion Internet disponible."

            AppError.Timeout ->
                "Le serveur met trop de temps a repondre."

            AppError.Unauthorized ->
                "Votre session doit etre verifiee."

            AppError.SessionExpired ->
                "Votre session a expire. Veuillez vous reconnecter."

            AppError.Forbidden ->
                "Vous ne disposez pas des autorisations necessaires."

            AppError.NotFound ->
                "Les informations demandees sont introuvables."

            AppError.ServerError ->
                "Le serveur rencontre actuellement un probleme."

            AppError.InvalidData ->
                "Les donnees transmises sont invalides."

            AppError.Validation ->
                "Veuillez verifier les informations saisies."

            AppError.Database ->
                "Impossible d'acceder aux donnees locales."

            AppError.Conflict ->
                "Cette operation entre en conflit avec une donnee existante."

            AppError.RateLimited ->
                "Trop de requetes. Veuillez patienter."

            AppError.Unknown ->
                "Une erreur inattendue est survenue."
        }
    }
}
