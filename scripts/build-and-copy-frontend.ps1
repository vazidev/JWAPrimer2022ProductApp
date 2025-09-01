Param()
Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'

$root = Split-Path -Parent $PSScriptRoot
$frontend = Join-Path $root "..\Product-Jwa_us"
$dist = Join-Path $frontend "dist\product-jwa-us"
$static = Join-Path $root "src\main\resources\static"

Write-Host "Building Angular app (production)..."
Push-Location $frontend
npm ci
npm run build -- --configuration production --base-href /app/ --deploy-url /app/
Pop-Location

Write-Host "Copying dist to Spring static..."
New-Item -ItemType Directory -Force -Path $static | Out-Null
Remove-Item -Recurse -Force "$static\*" -ErrorAction SilentlyContinue
Copy-Item -Recurse "$dist\*" $static
Write-Host "Done. Static assets available under $static"

