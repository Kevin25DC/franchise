CREATE TABLE franchise (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE branch (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          franchise_id BIGINT NOT NULL REFERENCES franquicia(id)
);

CREATE TABLE product (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          stock INT NOT NULL DEFAULT 0,
                          branch_id BIGINT NOT NULL REFERENCES sucursal(id)
);