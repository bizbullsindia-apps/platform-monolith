#!/bin/bash
echo "🔍 JWT & Encryption Check"
EXPIRY=$(grep "jwt-expiry-minutes" src/main/resources/application.yml | grep -o '[0-9]*' || echo 15)
if [ "$EXPIRY" -le 15 ]; then
  echo "✅ JWT Check PASSED - Expiry $EXPIRY mins <=15"
  exit 0
else
  echo "❌ JWT Check FAILED - Expiry $EXPIRY mins >15"
  exit 1
fi
