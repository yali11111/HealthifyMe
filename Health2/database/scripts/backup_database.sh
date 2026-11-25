#!/bin/bash

set -Eeuo pipefail

ROOT="$(cd "$(dirname "$0")/../.." && pwd)"

BACKUP_DIR="$ROOT/database/backups"

mkdir -p "$BACKUP_DIR"

if [[ ! -f "$ROOT/.env" ]]; then
    echo "ERREUR : fichier .env absent."
    exit 1
fi

set -a
source "$ROOT/.env"
set +a

: "${POSTGRES_ADMIN_USER:?Variable manquante}"
: "${POSTGRES_DB:?Variable manquante}"

TIMESTAMP="$(date -u +%Y%m%d_%H%M%S)"

BACKUP="$BACKUP_DIR/BottomNavMenu_$TIMESTAMP.dump"

echo "Creation de la sauvegarde PostgreSQL..."

docker compose \
    --env-file "$ROOT/.env" \
    -f "$ROOT/docker-compose.database.yml" \
    exec -T postgres \
    sh -c 'PGPASSWORD="$POSTGRES_PASSWORD" pg_dump \
        -U "$POSTGRES_USER" \
        -d "$POSTGRES_DB" \
        -Fc' > "$BACKUP"

if [[ ! -s "$BACKUP" ]]; then
    rm -f "$BACKUP"
    echo "ERREUR : sauvegarde vide."
    exit 1
fi

chmod 600 "$BACKUP"

echo ""
echo "Sauvegarde creee : $BACKUP"
echo "Taille : $(du -h "$BACKUP" | cut -f1)"

echo ""
echo "IMPORTANT : copier egalement cette sauvegarde"
echo "vers un stockage externe chiffre."
