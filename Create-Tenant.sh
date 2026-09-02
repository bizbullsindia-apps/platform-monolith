#!/bin/bash
# RAJ BHAI EMPIRE - TENANT FACTORY - 15 MINS PER BRAND
# Usage: ./create-tenant.sh <slug> <name> <domain> [plan] [cell_id]
set -e
SLUG=$1
NAME=$2
DOMAIN=$3
PLAN=${4:-free}
CELL_ID=${5:-cell-1}
if [ -z "$SLUG" ]; then echo "Usage: $0 <slug> <name> <domain>"; exit 1; fi
SCHEMA="tenant_$(echo $SLUG | tr '-' '_')"
echo "Creating tenant $SLUG $NAME $DOMAIN Plan $PLAN Cell $CELL_ID Schema $SCHEMA"
psql $DATABASE_URL -c "INSERT INTO tenants (slug, name, domain, plan, cell_id) VALUES ('$SLUG', '$NAME', '$DOMAIN', '$PLAN', '$CELL_ID') ON CONFLICT (slug) DO UPDATE SET name=EXCLUDED.name RETURNING id;"
psql $DATABASE_URL -c "SELECT create_tenant_schema('$SLUG');"
echo "DB done Keycloak realm tenant_$SLUG MinIO bucket $SLUG-media Kong route $DOMAIN"
mkdir -p ../../android-apps/app/src/$SLUG/res/values
cat > ../../android-apps/app/src/$SLUG/res/values/strings.xml <<EOL
<resources><string name="app_name">$NAME</string><string name="tenant_slug">$SLUG</string></resources>
EOL
mkdir -p ../../web-apps/app/[$SLUG]
echo "Done tenant $SLUG"
