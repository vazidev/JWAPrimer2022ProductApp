#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
FRONTEND_DIR="${ROOT_DIR}/../Product-Jwa_us"
DIST_DIR="${FRONTEND_DIR}/dist/product-jwa-us"
STATIC_DIR="${ROOT_DIR}/src/main/resources/static"

echo "Building Angular app (production)..."
pushd "$FRONTEND_DIR" >/dev/null
npm ci
npm run build -- --configuration production --base-href /app/ --deploy-url /app/
popd >/dev/null

echo "Copying dist to Spring static..."
mkdir -p "$STATIC_DIR"
rm -rf "${STATIC_DIR:?}"/*
cp -R "$DIST_DIR"/* "$STATIC_DIR"/
echo "Done. Static assets available under $STATIC_DIR"

