#!/bin/bash

set -Eeuo pipefail

echo "=========================================="
echo " BottomNavMenu - DATABASE STATUS"
echo "=========================================="

docker compose \
    --env-file .env \
    -f docker-compose.database.yml \
    ps

echo ""
echo "Verification PostgreSQL..."

docker compose \
    --env-file .env \
    -f docker-compose.database.yml \
    exec -T postgres \
    sh -c 'pg_isready -U "$POSTGRES_USER" -d "$POSTGRES_DB"'

echo ""
echo "Verification terminee."
