package com.example.health2.data.repository

import com.example.health2.data.local.db.dao.UserStatsDao import com.example.health2.data.local.db.entity.UserStatsEntity import com.example.health2.data.remote.api.ApiService import kotlinx.coroutines.flow.Flow

/**

Implémentation du repository des statistiques Health2. */ class UserStatsRepositoryImpl( private val userStatsDao: UserStatsDao, private val apiService: ApiService ) {

/**

Récupère toutes les statistiques locales. */ fun getAllStats( userId: String ): Flow<List<UserStatsEntity>> {

return userStatsDao.getAllStats(userId) }

/**

Récupère les dernières statistiques. */ suspend fun getLatestStats( userId: String ): UserStatsEntity? {

return userStatsDao.getLatestStats(userId) }

/**

Enregistre une nouvelle mesure localement. */ suspend fun insertStats( stats: UserStatsEntity ): Long {

return userStatsDao.insert( stats.copy(isSynced = false) ) }

/**

Récupère les statistiques sur une période. */ fun getStatsBetweenDates( userId: String, startDate: Long, endDate: Long ): Flow<List<UserStatsEntity>> {

return userStatsDao.getStatsBetweenDates( userId, startDate, endDate ) }

/**

Synchronise les données locales avec le serveur.
Les données sont d'abord envoyées au serveur.
Elles sont marquées comme synchronisées uniquement
après confirmation de la réponse HTTP. */ suspend fun synchronizeStats( userId: String ): Result<Unit> {

return try {

 val unsyncedStats =
     userStatsDao.getUnsyncedStats(userId)

 for (stats in unsyncedStats) {

     val response = apiService.uploadUserStats(
         userId = userId,
         stats = stats
     )

     if (!response.isSuccessful) {

         return Result.failure(
             Exception(
                 "Erreur serveur : ${response.code()}"
             )
         )
     }

     if (response.body() == null) {

         return Result.failure(
             Exception("Réponse serveur vide")
         )
     }

     userStatsDao.markAsSynced(
         id = stats.id,
         syncTimestamp = System.currentTimeMillis()
     )
 }

 Result.success(Unit)


} catch (exception: Exception) {

 Result.failure(exception)


} }

/**

Récupère les données distantes et les enregistre
dans la base locale. */ suspend fun refreshStats( userId: String ): Result<Unit> {

return try {

 val response = apiService.getUserStats(userId)

 if (!response.isSuccessful) {

     return Result.failure(
         Exception(
             "Erreur serveur : ${response.code()}"
         )
     )
 }

 val remoteStats = response.body()
     ?: return Result.failure(
         Exception("Aucune donnée reçue")
     )

 userStatsDao.insertAll(
     remoteStats.map {
         it.copy(isSynced = true)
     }
 )

 Result.success(Unit)


} catch (exception: Exception) {

 Result.failure(exception)


} }

/**

Supprime les statistiques locales d'un utilisateur. */ suspend fun deleteAllStats( userId: String ) {

userStatsDao.deleteAllStats(userId) }

/**

Compte les statistiques enregistrées. */ suspend fun countStats( userId: String ): Int {

return userStatsDao.countStats(userId) } }