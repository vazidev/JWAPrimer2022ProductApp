# productApp (Spring Boot)

Simple Spring Boot service backing the Angular Product app.

## Run

- macOS/Linux:
  - `./mvnw spring-boot:run`
- Windows (PowerShell):
  - `.\mvnw.cmd spring-boot:run`

Optional build jar:
- macOS/Linux: `./mvnw clean package && java -jar target/productApp-*.jar`
- Windows: `.\mvnw.cmd clean package; java -jar target\productApp-*.jar`

## Server

- Port: `9090`
- Context path: `/app`
- Health check: `GET http://localhost:9090/app/home`

## Endpoints (selected)

- `GET /app/home` → welcome message
- `GET /app/pay/{amount}/{customer}` → demo payment response
- Products (base `/app/product`):
  - `GET /app/product` → list products (optional filters: `productName`, `status`)
  - `GET /app/product/{productId}` → single product
  - `POST /app/product` → create
  - `PUT /app/product` → update
  - `DELETE /app/product/{productId}` → delete

## Database

Default MySQL (from `src/main/resources/application.properties`):
- URL: `jdbc:mysql://localhost:3306/RevatureJwaPrimerDb`
- Username: `root`
- Password: `root`

Override via env vars:
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SPRING_PROFILES_ACTIVE`

H2 examples are provided in `application.properties` (commented). Uncomment to run without MySQL.

### Sample Data

On startup, if no products exist, the app seeds a few example products.
- Seeder: `src/main/java/com/vazidev/learn/jwa/config/SampleDataLoader.java`
- Disable by removing the bean or seeding your own data.

SQL-based seeding is also available:
- File: `src/main/resources/data.sql` (MySQL/H2 compatible)
- Optional H2 file: `src/main/resources/data-h2.sql`
- To enable SQL seeding on any DB, set:
  - `spring.sql.init.mode=always`
  - `spring.sql.init.platform=mysql` (or `h2`)

Dev profile seeding (enabled by default when using `dev` profile):
- `application-dev.properties` turns on SQL seeding:
  - `spring.sql.init.mode=always`
  - `spring.sql.init.platform=mysql` (change to `h2` if using H2)
- Activate dev profile:
  - PowerShell: `$env:SPRING_PROFILES_ACTIVE='dev'; .\mvnw.cmd spring-boot:run`
  - bash/zsh: `SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run`

H2 console in dev:
- Enabled by default in `application-dev.properties`.
- URL: `http://localhost:9090/app/h2-console`
- Typical console settings:
  - Driver Class: `org.h2.Driver`
  - JDBC URL (file): `jdbc:h2:file:./h2DataBase/revaturejwadb`
  - JDBC URL (in-memory): `jdbc:h2:mem:serverDb`
  - User: `sa` (or as configured)
- If you want to use H2 instead of MySQL in dev, set:
  - PowerShell: `$env:SPRING_SQL_INIT_PLATFORM='h2'`
  - bash/zsh: `SPRING_SQL_INIT_PLATFORM=h2`

### Demo/Selenium Helpers

- JSON info endpoint: `GET /app/demo/info` returns `{ status, time, productCount, sampleProducts }`.
- Static page for selectors: `/app/selenium-demo.html` includes stable IDs (`btn-login`, `btn-signup`, etc.).

## Environment Examples

- macOS/Linux (bash/zsh):
  ```sh
  export SERVER_PORT=9091 \
         SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/RevatureJwaPrimerDb \
         SPRING_DATASOURCE_USERNAME=root \
         SPRING_DATASOURCE_PASSWORD=root \
         SPRING_PROFILES_ACTIVE=dev
  ./mvnw spring-boot:run
  ```
- Windows (PowerShell):
  ```powershell
  $env:SERVER_PORT = 9091
  $env:SPRING_DATASOURCE_URL = 'jdbc:mysql://localhost:3306/RevatureJwaPrimerDb'
  $env:SPRING_DATASOURCE_USERNAME = 'root'
  $env:SPRING_DATASOURCE_PASSWORD = 'root'
  $env:SPRING_PROFILES_ACTIVE = 'dev'
  .\mvnw.cmd spring-boot:run
  ```

## Troubleshooting

- Port in use: change with `SERVER_PORT` or stop the conflicting process.
- MySQL driver: if using MySQL 8+, prefer `com.mysql.cj.jdbc.Driver` for `spring.datasource.driver-class-name`.
- DB connectivity: ensure DB/schema exists and credentials are correct. Switch to H2 (file/memory) by using the commented H2 settings.
- Java version: uses Java 8 settings. Ensure `JAVA_HOME` points to a compatible JDK (check `java -version`).

## Related

- Angular frontend located at `../Product-Jwa_us` (runs on `http://localhost:4200` and proxies `/api/*` → `http://localhost:9090/app/*`).

## CORS

This service enables CORS via `WebCorsConfig`.
- File: `src/main/java/com/vazidev/learn/jwa/config/WebCorsConfig.java`
- Default allowed origins (dev): `http://localhost:4200`, `http://127.0.0.1:4200`.
- Configure for production using a comma‑separated list:
  - Property: `app.cors.allowed-origins`
  - Env var (PowerShell): `$env:APP_CORS_ALLOWED_ORIGINS = 'https://your-frontend.example.com'`
  - Env var (bash/zsh): `export APP_CORS_ALLOWED_ORIGINS=https://your-frontend.example.com`

## Postman

Import the collection and environment from `postman/`:
- Collection: `postman/ProductApp.postman_collection.json`
- Environment: `postman/ProductApp.local.postman_environment.json`

Usage:
1) Start this backend (port 9090).
2) In Postman, select the "ProductApp Local" environment.
3) Use variables like `{{baseUrl}}`, `{{productId}}`, `{{amount}}`, `{{customer}}` to exercise endpoints.

## CLI Examples (curl)

- bash/zsh:
  ```sh
  # Health
  curl -s http://localhost:9090/app/home

  # Pay demo
  curl -s "http://localhost:9090/app/pay/100/jane"

  # List products
  curl -s http://localhost:9090/app/product | jq .

  # Get product by ID
  curl -s http://localhost:9090/app/product/1 | jq .

  # Create product
  curl -s -X POST -H 'Content-Type: application/json' \
    -d '{"productId":101,"productName":"Sample","quantityOnHand":10,"price":99.99,"status":"Active"}' \
    http://localhost:9090/app/product | jq .

  # Update product
  curl -s -X PUT -H 'Content-Type: application/json' \
    -d '{"productId":101,"productName":"Updated","quantityOnHand":20,"price":79.99,"status":"Active"}' \
    http://localhost:9090/app/product | jq .

  # Delete product
  curl -s -X DELETE http://localhost:9090/app/product/101
  ```

- Windows PowerShell:
  ```powershell
  # Health
  iwr 'http://localhost:9090/app/home'

  # Pay demo
  iwr 'http://localhost:9090/app/pay/100/jane'

  # List products
  iwr 'http://localhost:9090/app/product'

  # Get product by ID
  iwr 'http://localhost:9090/app/product/1'

  # Create product
  $body = '{"productId":101,"productName":"Sample","quantityOnHand":10,"price":99.99,"status":"Active"}'
  iwr -Method Post -ContentType 'application/json' -Body $body 'http://localhost:9090/app/product'

  # Update product
  $body = '{"productId":101,"productName":"Updated","quantityOnHand":20,"price":79.99,"status":"Active"}'
  iwr -Method Put -ContentType 'application/json' -Body $body 'http://localhost:9090/app/product'

  # Delete product
  iwr -Method Delete 'http://localhost:9090/app/product/101'
  ```

## Serve Angular Build with Spring Boot

You can deploy the Angular dist inside this Spring Boot app so the UI and API are served together.

1) Build Angular for production with the context path in URLs:
   - From `../Product-Jwa_us`:
     - macOS/Linux:
       - `npm run build` (or) `ng build --configuration production --base-href /app/ --deploy-url /app/`
     - Windows PowerShell:
       - `npm run build` (or) `ng build --configuration production --base-href /app/ --deploy-url /app/`

2) Copy the build output into Spring static resources:
   - Source: `../Product-Jwa_us/dist/product-jwa-us/`
   - Destination: `src/main/resources/static/`
   - Example (macOS/Linux):
     ```sh
     rm -rf src/main/resources/static/*
     cp -R ../Product-Jwa_us/dist/product-jwa-us/* src/main/resources/static/
     ```
   - Example (Windows PowerShell):
     ```powershell
     Remove-Item -Recurse -Force src/main/resources/static/*
     Copy-Item -Recurse ..\Product-Jwa_us\dist\product-jwa-us\* src/main/resources/static/
     ```

3) Run the Spring app as usual (`mvnw spring-boot:run`). The UI is available at `http://localhost:9090/app/`.

4) SPA routing: A fallback controller is included to forward non-asset routes to `index.html` so deep links work.
   - File: `src/main/java/com/vazidev/learn/jwa/config/SpaForwardController.java`
   - With context-path `/app`, forwarding to `/index.html` resolves correctly under `/app`.

Alternative (automated):
- Maven will auto-copy the Angular dist into `target/classes/static` during build if the dist exists (see `maven-resources-plugin` in `pom.xml`).
- Scripts to build+copy in one step:
  - macOS/Linux: `bash scripts/build-and-copy-frontend.sh`
  - Windows PowerShell: `./scripts/build-and-copy-frontend.ps1`

Maven profile (build frontend automatically):
- Run `mvnw -Pwith-frontend clean package` (Windows: `.\mvnw.cmd -Pwith-frontend clean package`).
- This profile uses `frontend-maven-plugin` to:
  - Install Node/npm locally (no system install required)
  - Run `npm ci` and `npm run build -- --configuration production --base-href /app/ --deploy-url /app/`
  - Then the resources plugin copies the built files into `target/classes/static`.

## Continuous Integration

GitHub Actions workflow builds and tests the Angular app and then builds the Spring Boot JAR.
- Workflow: `.github/workflows/ci.yml`
- Steps: checkout → Node setup → Angular tests/build → Java setup → Maven package (copies Angular dist).

## Docker

Option A — Single container (Spring Boot bundles Angular):
- Build (from repo root):
  - `docker build -f revature/JWAPrimer/Junit1/productApp/Dockerfile -t product-app .`
- Run:
  - `docker run -p 9090:9090 --name product-app product-app`
- Env overrides:
  - `-e SPRING_PROFILES_ACTIVE=dev -e APP_CORS_ALLOWED_ORIGINS=http://localhost:4200`
 - Healthcheck: container reports healthy when `GET /app/actuator/health` returns 200 (status UP).

Option B — Two containers (Angular via Nginx + Spring Boot):
- Compose (from `revature/JWAPrimer/Junit1`):
  - `docker compose up --build`
- Services:
  - Backend: http://localhost:9090/app
  - Frontend: http://localhost:4200 (Nginx proxies `/app/*` to backend)
  - Healthchecks: frontend `/health` returns 200; backend `/app/actuator/health` used for health.

## Kubernetes

Manifests are under `revature/JWAPrimer/Junit1/k8s`:

- Backend: `backend-deployment.yaml` creates a Deployment + Service named `backend` (port 9090).
  - Probes hit `/app/actuator/health/liveness` and `/app/actuator/health/readiness`.
  - Ensure image points to your pushed backend image (built from `productApp/Dockerfile`).
- Frontend: `frontend-deployment.yaml` creates a Deployment + Service named `frontend` (port 80).
  - Probes hit `/health`.
  - Ensure image points to your pushed frontend image (built from `Product-Jwa_us/Dockerfile`).
- Ingress: `ingress.yaml` routes `product.local`:
  - `/` → frontend service (Angular app)
  - `/app` → backend service (Spring API)

Apply (example):
```sh
kubectl apply -f revature/JWAPrimer/Junit1/k8s/backend-deployment.yaml
kubectl apply -f revature/JWAPrimer/Junit1/k8s/frontend-deployment.yaml
kubectl apply -f revature/JWAPrimer/Junit1/k8s/ingress.yaml
```

Using Kustomize:
```sh
kubectl apply -k revature/JWAPrimer/Junit1/k8s
```
To override images/tags, edit `k8s/kustomization.yaml` `images:` section.

Overlays:
- Dev: `kubectl apply -k revature/JWAPrimer/Junit1/k8s/overlays/dev`
  - Sets `SPRING_PROFILES_ACTIVE=dev` and uses host `product.local`.
- Prod: `kubectl apply -k revature/JWAPrimer/Junit1/k8s/overlays/prod`
  - Sets replicas/resources and host `product.example.com`. Update images and host to your domains before applying.
  - Includes cert-manager annotation on ingress and TLS (secret `product-tls`). Ensure a `ClusterIssuer` named `letsencrypt-prod` exists.

GitHub Actions image build and Kustomize render:
- Secrets required (example for Docker Hub):
  - `REGISTRY` (e.g., docker.io)
  - `IMAGE_NAMESPACE` (e.g., your-dockerhub-username)
  - `REGISTRY_USERNAME`, `REGISTRY_PASSWORD`
- The workflow builds and pushes images and renders prod manifests with the commit SHA tag as `k8s-manifests-prod` artifact.

### Cert-Manager

Install cert-manager in your cluster, then apply an issuer:
- Prod issuer (Let’s Encrypt):
  - Edit email in `k8s/cert-manager/cluster-issuer-letsencrypt-prod.yaml`.
  - `kubectl apply -f revature/JWAPrimer/Junit1/k8s/cert-manager/cluster-issuer-letsencrypt-prod.yaml`
- Dev self-signed issuer:
  - `kubectl apply -f revature/JWAPrimer/Junit1/k8s/cert-manager/cluster-issuer-selfsigned.yaml`

Ingress class:
- Overlays set `ingressClassName: nginx`; ensure NGINX Ingress controller is installed (or adjust to your controller class).

### Helper Scripts

From `revature/JWAPrimer/Junit1`:

- Build and push images (bash):
  ```sh
  REGISTRY=docker.io IMAGE_NAMESPACE=youruser TAG=$(git rev-parse --short HEAD) \
    bash scripts/build-push-images.sh
  ```
- Build and push images (PowerShell):
  ```powershell
  ./scripts/build-push-images.ps1 -Registry docker.io -ImageNamespace youruser -Tag (git rev-parse --short HEAD)
  ```
- Deploy prod overlay with specific images (bash):
  ```sh
  REGISTRY=docker.io IMAGE_NAMESPACE=youruser TAG=$(git rev-parse --short HEAD) \
    bash scripts/deploy-k8s-prod.sh
  ```
- Deploy prod overlay with specific images (PowerShell):
  ```powershell
  ./scripts/deploy-k8s-prod.ps1 -Registry docker.io -ImageNamespace youruser -Tag (git rev-parse --short HEAD)
  ```

Configure DNS (for local clusters):
- Add `127.0.0.1 product.local` to `/etc/hosts` (or Windows hosts file) when using a local ingress controller (e.g., NGINX Ingress).

Notes:
- Backend actuator probes enabled via `management.endpoint.health.probes.enabled=true`.
- Backend context-path is `/app`; ingress forwards `/app` traffic directly to the backend service.

With MySQL database (override):
- Start with DB: `docker compose -f docker-compose.yml -f docker-compose.db.yml up --build`
- DB: MySQL 8 exposed on `localhost:3306` with `root/root`, schema `RevatureJwaPrimerDb`.
- Backend is wired to `db:3306` via env in the override file.
