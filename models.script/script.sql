DROP DATABASE IF EXISTS proyecto_rest_cpyd_db;
DROP TABLE IF EXISTS proyecto_rest_cpyd_db.usuario CASCADE;
DROP TABLE IF EXISTS proyecto_rest_cpyd_db.sismo CASCADE;

CREATE DATABASE proyecto_rest_cpyd_db;

BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario BIGSERIAL NOT NULL,
    token varchar(255) NOT NULL,
    app varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    validacion BOOLEAN DEFAULT false,
    createdAt TIMESTAMP NOT NULL,
    UNIQUE (token),
    PRIMARY KEY(id_usuario)
);


CREATE TABLE IF NOT EXISTS sismo (
    id_sismo BIGSERIAL NOT NULL,
    token varchar(255) NOT NULL,
    fecha_local TIMESTAMP NOT NULL,
    latitud INTEGER NOT NULL,
    longitud INTEGER NOT NULL,
    profundidad INTEGER NOT NULL,
    magnitud_local FLOAT NOT NULL,
    agencia VARCHAR(255) NOT NULL,
    ref_geografica VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    PRIMARY KEY(id_sismo)
);

CREATE INDEX ON sismo(createdAt);

CREATE UNIQUE INDEX ON usuario(UPPER(TRIM(both FROM app)));

COMMIT;