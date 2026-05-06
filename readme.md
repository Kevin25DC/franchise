# Franchise Web API

## Despliegue local

### Requisitos

- Java 21, Spring 3.5.14, DB postgreSQL 15, Flyway 9.16.1.
- Acceso a una base de datos PostgreSQL y a las variables de entorno.

### Variables de entorno

Establece las variables antes de iniciar la aplicacion:

```bash
 -Las variables de entorno se enviaran por correo
```

### Ejecutar en local

Desde la raiz del proyecto:

```bash
./mvnw spring-boot:run
```

La aplicacion inicia en `http://localhost:8080`.

### Migraciones

Flyway se ejecuta automaticamente al iniciar, usando `DB_JDBC_URL`, `DB_USER` y `DB_PASSWORD`.

### Ejecutar con Docker

Requisitos:

- Docker Desktop en ejecucion.

Para levantar la base de datos y la aplicacion:

```bash
docker compose up --build
```

La aplicacion inicia en `http://localhost:8080`.

Para detener los contenedores:

```bash
docker compose down
```

- Por correo estaran las variables de entorno necesarias para la conexion a la base de datos.
- se agrego swagger para documentar la API, se puede acceder a traves de `http://localhost:8080/swagger-ui/index.html` despues de iniciar la aplicacion.

### JSON de prueba por endpoint

Base URL: `http://localhost:8080`

**Crear franquicia** `POST /api/franchises`

```json
{
  "name": "Franquicia Centro"
}
```

**Actualizar nombre de franquicia** `PATCH /api/franchises/{franchiseId}/name`

```json
{
  "name": "Franquicia Norte"
}
```

**Obtener producto con mayor stock por sucursal** `GET /api/franchises/{franchiseId}/top-stock-products`

Respuesta ejemplo:

```json
[
  {
    "productId": 10,
    "productName": "Hamburguesa",
    "stock": 120,
    "branchId": 3,
    "branchName": "Sucursal Centro"
  }
]
```

**Crear sucursal en franquicia** `POST /api/franchises/{franchiseId}/branches`

```json
{
  "name": "Sucursal Centro"
}
```

**Actualizar nombre de sucursal** `PATCH /api/branches/{branchId}/name`

```json
{
  "name": "Sucursal Norte"
}
```

**Agregar producto a sucursal** `POST /api/branches/{branchId}/products`

```json
{
  "name": "Hamburguesa",
  "stock": 50
}
```

**Actualizar stock de producto** `PATCH /api/products/{productId}/stock`

```json
{
  "stock": 75
}
```

**Actualizar nombre de producto** `PATCH /api/products/{productId}/name`

```json
{
  "name": "Hamburguesa Doble"
}
```

**Eliminar producto** `DELETE /api/products/{productId}`

No lleva body. Respuesta: `204 No Content`.
