BEGIN TRANSACTION;

DROP TABLE IF EXISTS credencial_z CASCADE;

CREATE TABLE IF NOT EXISTS credencial_z (
    id BIGSERIAL NOT NULL,
    token varchar(255) NOT NULL,
    app varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    validacion BOOLEAN DEFAULT false,
    created_at TIMESTAMP,
    UNIQUE (token),
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS sismo_z CASCADE;

CREATE TABLE IF NOT EXISTS sismo_z (
    id BIGSERIAL NOT NULL,
    fecha_local TIMESTAMP NOT NULL,
    fecha_utc TIMESTAMP NOT NULL,
    latitud DOUBLE PRECISION NOT NULL,
    longitud DOUBLE PRECISION NOT NULL,
    profundidad INTEGER NOT NULL,
    magnitud_local DOUBLE PRECISION NOT NULL,
    agencia VARCHAR(255) NOT NULL,
    ref_geografica VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX ON credencial_z(UPPER(TRIM(both FROM app)));

CREATE INDEX ON sismo_z(created_at);

COMMIT;

INSERT INTO credencial_z(token, app, password, validacion) VALUES('token123', 'RekoBeta', 'test123', 'false');
INSERT INTO credencial_z(token, app, password, validacion) VALUES('token420', 'ProyectoAppMobile', 'test123', 'true');
INSERT INTO credencial_z(token, app, password, validacion) VALUES('token321', 'MiUtem', 'test123', 'false');