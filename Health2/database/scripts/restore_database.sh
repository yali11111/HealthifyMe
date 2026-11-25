#!/bin/bash

set -Eeuo pipefail

ROOT="$(cd "$(dirname "$0")/../.." && pwd)"

if [[ $# -ne 1 ]]; then
    echo "Usage : $0 chemin/backup.dump"
    exit 1
fi

BACKUP="$(realpath "$1")"

if [[ ! -s "$BACKUP" ]]; then
    echo "ERREUR : fichier de sauvegarde absent ou vide."
    exit 1
fi

if [[ ! -f "$ROOT/.env" ]]; then
    echo "ERREUR : fichier .env absent."
    exit 1
fi

echo "ATTENTION : cette operation peut remplacer les donnees."
echo "Sauvegarde : $BACKUP"

read -r -p "Confirmer la restauration ? (oui/non) : " ANSWER

if [[ "$ANSWER" != "oui" ]]; then
    echo "Restauration annulee."
    exit 0
fi

docker compose \
    --env-file "$ROOT/.env" \
    -f "$ROOT/docker-compose.database.yml" \
    exec -T postgres \
    pg_restore \
    -U "$POSTGRES_ADMIN_USER" \
    -d "$POSTGRES_DB" \
    --clean \
    --if-exists \
    --no-owner \
    --no-privileges \
    < "$BACKUP"

echo "Restauration terminee."
