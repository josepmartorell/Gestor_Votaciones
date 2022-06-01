
DROP DATABASE IF EXISTS `votaciones`;

CREATE DATABASE IF NOT EXISTS `votaciones` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `votaciones`;

DROP TABLE IF EXISTS votos_emitidos;
DROP TABLE IF EXISTS votaciones; 
DROP TABLE IF EXISTS titulares;

CREATE TABLE titulares
( codigo                   CHAR(3),
  nombre                   VARCHAR(50), 
  cuota_participacion      DOUBLE,
  CONSTRAINT pk_titulares PRIMARY KEY (codigo)
);

CREATE TABLE votaciones
( id_votacion              CHAR(19),           
  tema_votado              VARCHAR(200),
  CONSTRAINT pk_votaciones PRIMARY KEY (id_votacion)
);

CREATE TABLE votos_emitidos
( codigo_titular           CHAR(3),
  id_votacion              CHAR(19),
  opcion_ausente           INT,
  opcion_abstencion        INT,
  opcion_afirmativo        INT,
  opcion_negativo          INT,
  CONSTRAINT pk_votosemitidos PRIMARY KEY (codigo_titular, id_votacion),
  CONSTRAINT fk_votosemitidos_titulares FOREIGN KEY (codigo_titular) REFERENCES titulares (codigo), 
  CONSTRAINT fk_votosemitidos_votaciones FOREIGN KEY (id_votacion) REFERENCES votaciones (id_votacion) 
);

-- -----------------------------------------------------------------------------------------------------------------------------
--     MEDIANTE LA SIGUIENTE TABLA, PROCEDIMIENTOS Y FUNCION SIMULAMOS EN MySQL LAS SECUENCIAS DE ORACLE
-- -----------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS secuencias;
DROP PROCEDURE IF EXISTS crea_secuencia;
DROP PROCEDURE IF EXISTS elimina_secuencia;
DROP PROCEDURE IF EXISTS secuencia_set_valor;
DROP PROCEDURE IF EXISTS secuencia_set_incremento;
DROP FUNCTION IF EXISTS secuencia_next_valor;


CREATE TABLE secuencias
(
  secuencia_nombre VARCHAR(35) NOT NULL PRIMARY KEY,
  secuencia_valor INT UNSIGNED NOT NULL,
  secuencia_incremento INT UNSIGNED NOT NULL
);


DELIMITER //

CREATE PROCEDURE crea_secuencia(secuenciaNombre VARCHAR(35), valorIncialSecuencia INT UNSIGNED, incremento INT UNSIGNED)
BEGIN
    IF (SELECT COUNT(*) FROM secuencias WHERE secuencia_nombre = secuenciaNombre) = 0 THEN
        INSERT INTO secuencias (secuencia_nombre, secuencia_valor, secuencia_incremento) VALUES (secuenciaNombre, valorIncialSecuencia, incremento);
    ELSE
        SIGNAL SQLSTATE '50000' SET MESSAGE_TEXT = "No puede crear la secuencia especificada porque ya existe";
    END IF;
END//


CREATE PROCEDURE elimina_secuencia(secuenciaNombre VARCHAR(35))
BEGIN
    IF (SELECT COUNT(*) FROM secuencias WHERE secuencia_nombre = secuenciaNombre) > 0 THEN
        DELETE FROM secuencias WHERE secuencia_nombre = secuenciaNombre;     
    ELSE
        SIGNAL SQLSTATE '50000' SET MESSAGE_TEXT = "La secuencia especificada no existe";
    END IF;
END//


CREATE PROCEDURE secuencia_set_valor(secuenciaNombre VARCHAR(35), secuenciaValor INT UNSIGNED)
BEGIN
    IF (SELECT COUNT(*) FROM secuencias WHERE secuencia_nombre = secuenciaNombre) > 0 THEN
        UPDATE secuencias SET secuencia_valor = secuenciaValor WHERE secuencia_nombre = secuenciaNombre;      
    ELSE
        SIGNAL SQLSTATE '50000' SET MESSAGE_TEXT = "No existe la secuencia";
    END IF;
END//


CREATE PROCEDURE secuencia_set_incremento(secuenciaNombre VARCHAR(35), incremento INT UNSIGNED)
BEGIN
    IF (SELECT COUNT(*) FROM secuencias WHERE secuencia_nombre = secuenciaNombre) > 0 THEN
        UPDATE secuencias SET secuencia_incremento = incremento WHERE secuencia_nombre = secuenciaNombre;      
    ELSE
        SIGNAL SQLSTATE '50000' SET MESSAGE_TEXT = "No existe la secuencia";
    END IF;
END//


CREATE FUNCTION secuencia_next_valor(secuenciaNombre VARCHAR(35)) RETURNS INT UNSIGNED
BEGIN
    DECLARE valorActual INT;

    SET valorActual = (SELECT secuencia_valor FROM secuencias WHERE secuencia_nombre = secuenciaNombre);
    IF valorActual IS NOT NULL THEN
        UPDATE secuencias SET secuencia_valor = valorActual + secuencia_incremento  WHERE secuencia_nombre = secuenciaNombre;        
    ELSE
        SIGNAL SQLSTATE '50000' SET MESSAGE_TEXT = "No existe la secuencia";
    END IF;

    RETURN valorActual;
END//

DELIMITER ;

-- -----------------------------------------------------------------------------------------------------------------------------

-- CALL elimina_secuencia("secuencia_codigo_titular");

CALL crea_secuencia("secuencia_codigo_titular", 1, 1);

-- CALL secuencia_set_valor("secuencia_codigo_titular", 100);

-- CALL secuencia_set_incremento("secuencia_codigo_titular", 5);

-- SELECT secuencia_next_valor("secuencia_codigo_titular");

-- -----------------------------------------------------------------------------------------------------------------------------


INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'ALARCON LAGUNA, CONCEPCION',1.143);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'ALPUENTE CARRASCO, VICENTA',1.480);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'APARISI  SERRANO, FELIX',0.937);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'BARBERA SORIANO, RAMON',1.238);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'BLANCO ALAMAN, PILAR',1.012);
INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'BOLUMAR GASCO, ESTRELLA',1.239);
INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'BUENAVENTURA SANCHEZ, ANTONIO',1.085);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'CALATRAVA CARBONELL, TOMAS',0.871);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'CALVO SERRANO, ANGELES',1.520);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'CARABELLA CALVO, BLANCA',1.135);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'CARBO NAVARRO, SANTIAGO',1.305);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'CARRETERO RUS, ANTONIO',0.869);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'CATALA PARDO, CECILIA',1.315);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'DELGADO MARTIN, FRANCISCO MIGUEL',1.873);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'ESTEBAN MARTINEZ, MANUEL',0.968);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'FERRERO BOTELLA, FEDERICO',0.732);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'FONTALES ROMEU, INMACULADA ',1.354);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'GALIANA MONSALVE, AGUSTIN',1.982);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'GALLEGO GONZALEZ, YOLANDA',2.105);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'GALLEGO LASTRA, FERNANDO',2.098);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'GARCIA JIMENEZ, DOLORES',1.129);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'GIMENEZ OLIVARES, EDUARDO',0.879);
INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'GOMEZ MARTINEZ, AURORA',1.930);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'GUTIERREZ SOTO, ALVARO',1.459);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'HERRERA BALUARTE, OSCAR',1.380);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'HUERTA CASTELLO, ARTURO',2.390);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'IBARRA PARDO, LUIS',1.814);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'ISERTE PARDO, BEATRIZ',1.590);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'JIMENEZ JIMENEZ, LUCAS',2.349);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'JULVE REVERTER, SANTIAGO',1.650);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'LIBERTO REVERTER, ARMANDO',1.895);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'LLOPIS HERNANDEZ, ENRIQUE',2.053);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'LUJAN TORREGROSA, RAMON',1.270);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'MAEZTU AMORES, JUAN',2.983);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'MARTINEZ CLIMENT, CESAR',1.579);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'MERLOS PASCUAL, ISABEL',1.809);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'MINGUEZ FONTALES, SERGIO',0.928);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'MONRRABAL ARIÑO, SANTIAGO',0.835);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'MORENO CASTILLO, MARCOS',2.558);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'MORILLAS SALVADOR, ANGEL',1.207);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'MUÑOZ SANCHIS, VICTOR',0.893);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'ORDINAS SUAREZ, PEDRO',2.008);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'ORIOLA CASTELLO, ANA MARIA',1.901);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'OSUNA PORTOLES, EUGENIO',2.254);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'PEREZ CRESPO, GONZALO',0.908);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'PEREZ RAMON, EUGENIO',0.945);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'PERIS ALBELDA, ALBERTO',1.703);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'RAMIREZ SANCHEZ, ROSARIO',1.308);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'RAMS SANTAMARIA, GLORIA',0.850);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'RAUSELL MARTIN, VIRGINIA',1.589);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'RODRIGO QUEVEDO, JULIAN',0.980);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'RODRÍGUEZ SANTOS, VALERIO',0.802);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'ROIG GUILLOT, MARIA LUISA',2.190);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'ROMERO TAMARIT, PAULA',1.578);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'SANCHEZ PUERTAS, JOSE',1.759);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'SOLIVAS VALIENTE, MIGUEL ANGEL',0.845);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'SORIANO LLOPIS,EMILIO',2.015);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'SORIANO SAINZ, JOSE MANUEL',1.798);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'TEJEDOR LAGUNA, VICENTE',1.890);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'TELLO JOVER, TERESA',2.154);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'UMBERT MORENO, FELISA',1.350);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'VELA ASENSI, SIXTO',2.057);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'VILLALBA ALVAREZ, ALFONSO',0.859);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'VILLAREJO GOMEZ, JORGE',1.250);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'VIVES FONTANA, JOAQUIN',1.803);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'ZAPATERO MARCO, SALVADOR',0.905);

INSERT INTO titulares (codigo,nombre,cuota_participacion) VALUES (LPAD(FORMAT(secuencia_next_valor("secuencia_codigo_titular"),0),3,'0'),'ZARAGOZA ASENSI, ROSA',1.458);




COMMIT;
