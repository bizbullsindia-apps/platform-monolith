#!/bin/bash
echo "🔍 RLS Check - Protects 50 apps isolation"
# Simple check - looks for ENABLE ROW LEVEL SECURITY in init.sql
if grep -q "ENABLE ROW LEVEL SECURITY" infra/init.sql; then
  echo "✅ RLS Check PASSED - RLS found"
  exit 0
else
  echo "❌ RLS Check FAILED - No RLS found in init.sql"
  exit 1
fi
