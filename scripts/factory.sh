#!/bin/bash
set -e
TENANT_ID=$1
BRAND_NAME=$2
DOMAIN=$3
TOKEN_INPUT=$4

if [ $# -lt 3 ]; then
  echo "Usage: ./scripts/factory.sh <id> <Brand> <domain>"
  exit 1
fi

if [ -z "$TOKEN_INPUT" ]; then
  echo "→ Auto-login as super@bizbulls.in..."
  TOKEN=$(curl -s -X POST http://localhost:8085/api/auth/login -H "Content-Type: application/json" -H "X-Tenant-ID: default" -d '{"email":"super@bizbulls.in","password":"SuperAdmin123!"}' | python3 -c "import sys,json; print(json.load(sys.stdin).get('token',''))")
else
  TOKEN=$TOKEN_INPUT
fi

echo "Creating tenant $TENANT_ID..."
curl -s -X POST http://localhost:8085/api/tenants -H "Authorization: Bearer $TOKEN" -H "X-Tenant-ID: default" -H "Content-Type: application/json" -d "{\"id\":\"$TENANT_ID\",\"name\":\"$BRAND_NAME\",\"domain\":\"$DOMAIN\"}"

echo ""
echo "Creating brand..."
curl -s -X POST http://localhost:8085/api/brands -H "Authorization: Bearer $TOKEN" -H "X-Tenant-ID: $TENANT_ID" -H "Content-Type: application/json" -d "{\"tenantId\":\"$TENANT_ID\",\"brandName\":\"$BRAND_NAME\",\"primaryColor\":\"#0F172A\",\"domain\":\"$DOMAIN\"}"

echo ""
echo "✅ $TENANT_ID done"