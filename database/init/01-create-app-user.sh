#!/bin/bash

set -Eeuo pipefail

: "${BottomNavMenu_DB_USER:?Utilisateur applicatif manquant}"
: "${BottomNavMenu_DB_PASSWORD:?Mot de passe applicatif manquant}"

if [[ "$BottomNavMenu_DB_USER" == *"'"* ]] ||
   [[ "$BottomNavMenu_DB_USER" == *'"'* ]] ||
   [[ "$BottomNavMenu_DB_USER" == *'\'* ]]; then

    echo "Nom utilisateur invalide."
    exit 1
fi

psql \
    --username "$POSTGRES_USER" \
    --dbname "$POSTGRES_DB" \
    --set ON_ERROR_STOP=1 \
    --set app_user="$BottomNavMenu_DB_USER" \
    --set app_password="$BottomNavMenu_DB_PASSWORD" <<'SQL'

CREATE ROLE :"app_user"
WITH LOGIN PASSWORD :'app_password';

GRANT CONNECT ON DATABASE BottomNavMenu TO :"app_user";

GRANT USAGE, CREATE ON SCHEMA public TO :"app_user";

SQL

echo "Utilisateur applicatif PostgreSQL initialise."
