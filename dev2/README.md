# HEALTH2 - DEV 2

Environnement Docker indépendant.

## Services

- PostgreSQL : localhost:5433
- Redis : localhost:6380
- Adminer : localhost:8082

## Commandes

Démarrage :
docker compose up -d

État :
docker compose ps

Logs :
docker compose logs -f

Arrêt :
docker compose down

Les données PostgreSQL et Redis persistent dans des volumes Docker.
