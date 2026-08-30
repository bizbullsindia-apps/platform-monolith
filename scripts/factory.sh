#!/bin/bash
# One Platform Many Brands - Factory Script
# Usage: ./scripts/factory.sh <tenantId> <brandName> <domain>
set -e
TENANT_ID=${1:-demo}
BRAND_NAME=${2:-Demo Brand}
DOMAIN=${3:-demo.bizbulls.com}
API="http://localhost:8085"
TOKEN=$4

if [ -z "$TOKEN" ]; then
  echo "Need SUPER_ADMIN token as 4th arg"
  echo "Example: ./scripts/factory.sh bizbulls 'BizBulls India' bizbulls.com \$TOKEN"
  exit 1
fi

echo "Creating tenant: $TENANT_ID"
curl -s -X POST $API/api/tenants \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d "{\"id\":\"$TENANT_ID\",\"name\":\"$BRAND_NAME\",\"domain\":\"$DOMAIN\"}" | jq .

echo ""
echo "Creating brand config: $BRAND_NAME"
curl -s -X POST $API/api/brands \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d "{\"tenantId\":\"$TENANT_ID\",\"brandName\":\"$BRAND_NAME\",\"domain\":\"$DOMAIN\",\"primaryColor\":\"#0F172A\",\"logoUrl\":\"https://$DOMAIN/logo.png\"}" | jq .

echo ""
echo "✅ Factory done for $TENANT_ID ($BRAND_NAME)"
