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