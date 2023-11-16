Script de la Base de Datos, en este caso se utilizo PostgreSQL

Configuración de la Base de datos en: IngSoftII-Parcial2/src/resources/application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/actions
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true



Para crear la tabla de 'acciones': 

----------------------------------------------------------------------------------------------------------
-- Table: public.accion

-- DROP TABLE IF EXISTS public.accion;

CREATE TABLE IF NOT EXISTS public.accion
(
    precio_actual integer NOT NULL,
    precio_anterior integer NOT NULL,
    accion_id bigint NOT NULL DEFAULT nextval('accion_accion_id_seq'::regclass),
    nombre_accion character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT accion_pkey PRIMARY KEY (accion_id),
    CONSTRAINT accion_nombre_accion_key UNIQUE (nombre_accion)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.accion
    OWNER to postgres;
----------------------------------------------------------------------------------------------------------


Para crear la tabla de 'usuarios':

----------------------------------------------------------------------------------------------------------
-- Table: public.usuario

-- DROP TABLE IF EXISTS public.usuario;

CREATE TABLE IF NOT EXISTS public.usuario
(
    cartera bigint,
    cartera_original bigint,
    usuario_id bigint NOT NULL DEFAULT nextval('usuario_usuario_id_seq'::regclass),
    contrasena character varying(255) COLLATE pg_catalog."default",
    nombre_usuario character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (usuario_id),
    CONSTRAINT usuario_nombre_usuario_key UNIQUE (nombre_usuario)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuario
    OWNER to postgres;
----------------------------------------------------------------------------------------------------------

Para crear la tabla 'usuario_acciones' (Relacion de Compra de Acciones por parte de un Usuario):

----------------------------------------------------------------------------------------------------------
-- Table: public.usuario_acciones

-- DROP TABLE IF EXISTS public.usuario_acciones;

CREATE TABLE IF NOT EXISTS public.usuario_acciones
(
    cantidad_acciones bigint,
    id_accion bigint NOT NULL,
    umbral_inferior bigint,
    umbral_superior bigint,
    usuario_usuario_id bigint NOT NULL,
    estado_accion character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT usuario_acciones_pkey PRIMARY KEY (id_accion, usuario_usuario_id),
    CONSTRAINT fk6qi4cwv0jys4hi742akpja02a FOREIGN KEY (id_accion)
        REFERENCES public.accion (accion_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkb3gajb13nhlakp0ka51rp5iov FOREIGN KEY (usuario_usuario_id)
        REFERENCES public.usuario (usuario_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuario_acciones
    OWNER to postgres;
----------------------------------------------------------------------------------------------------------
