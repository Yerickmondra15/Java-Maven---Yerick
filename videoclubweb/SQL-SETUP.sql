-- Script SQL para crear la estructura de CINTAS en Oracle
-- Ejecutar en SQLPlus o SQL Developer como usuario ADMIN

-- Crear tabla CINTAS
CREATE TABLE CINTAS (
    CODIGO_CINTA    NUMBER(10) PRIMARY KEY,
    ESTANTERIA      VARCHAR2(50),
    ESTANTE         VARCHAR2(50),
    FILA            VARCHAR2(50),
    TITULO          VARCHAR2(200),
    DIRECTOR        VARCHAR2(200),
    AÑO             NUMBER(4),
    GENERO          VARCHAR2(100)
);

-- Crear secuencia para auto-increment
CREATE SEQUENCE SEQ_CODIGO_CINTA
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-- Trigger para auto-increment (opcional)
CREATE OR REPLACE TRIGGER TRGG_CODIGO_CINTA
BEFORE INSERT ON CINTAS
FOR EACH ROW
BEGIN
    IF :NEW.CODIGO_CINTA IS NULL THEN
        SELECT SEQ_CODIGO_CINTA.NEXTVAL INTO :NEW.CODIGO_CINTA FROM DUAL;
    END IF;
END;
/

-- Insertar datos de prueba
INSERT INTO CINTAS VALUES (1, 'A', '1', '1', 'Matrix', 'Lana Wachowski', 1999, 'Ciencia Ficción');
INSERT INTO CINTAS VALUES (2, 'A', '1', '2', 'Inception', 'Christopher Nolan', 2010, 'Thriller');
INSERT INTO CINTAS VALUES (3, 'B', '2', '1', 'Titanic', 'James Cameron', 1997, 'Drama');
INSERT INTO CINTAS VALUES (4, 'B', '2', '2', 'Avatar', 'James Cameron', 2009, 'Aventura');
INSERT INTO CINTAS VALUES (5, 'C', '3', '1', 'Parasite', 'Bong Joon-ho', 2019, 'Drama');

-- Commit
COMMIT;

-- Ver los datos
SELECT * FROM CINTAS;

-- Contar registros
SELECT COUNT(*) AS TOTAL_CINTAS FROM CINTAS;
