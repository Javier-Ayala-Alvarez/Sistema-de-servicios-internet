use baseDatosProyecto;
DELIMITER // 
DROP PROCEDURE IF EXISTS InsertFacturas//
CREATE PROCEDURE InsertFacturas() 
BEGIN -- DECLARACION DE VARIABLES.

	DECLARE idContrato INTEGER;
	DECLARE control INTEGER DEFAULT 0;
	-- DECLARACION DEL CURSOR Y QUE QUERY SERA LA QUE SE VA A RECORRER.
	DECLARE cursorForContrato CURSOR FOR
	SELECT c.idcontrato from contrato c WHERE(EXTRACT(DAY FROM c.fechainicio)) 
    = EXTRACT(DAY from ( DATE_ADD(NOW(), INTERVAL -6 HOUR)));
    
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET control = 1;
	OPEN cursorForContrato;
	-- BUCLE
	bucle: LOOP 
		FETCH cursorForContrato INTO idContrato;
	IF control = 1 THEN LEAVE bucle;
	END IF;
	INSERT INTO
	factura  (fechafactura, idcontrato, estado)
	VALUES
	(NOW(), idContrato, 1);
	END LOOP bucle;
CLOSE cursorForContrato;
END //
DELIMITER ;

