# Franchise Web API

## Despliegue local

### Requisitos

- Java 21 (segun `pom.xml`).
- Acceso a una base de datos PostgreSQL y a las variables de entorno.

### Variables de entorno

Establece las variables antes de iniciar la aplicacion:

```bash
export DB_JDBC_URL="jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?prepareThreshold=0"
export DB_PASSWORD="zYvgyc-werqir-gejju6"
export DB_R2DBC_URL="r2dbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?preparedStatementCacheQueries=0"
export DB_USER="postgres.zfjlexlpyjnhqietuvyw"
```

### Ejecutar en local

Desde la raiz del proyecto:

```bash
./mvnw spring-boot:run
```

La aplicacion inicia en `http://localhost:8080`.

### Migraciones

Flyway se ejecuta automaticamente al iniciar, usando `DB_JDBC_URL`, `DB_USER` y `DB_PASSWORD`.

