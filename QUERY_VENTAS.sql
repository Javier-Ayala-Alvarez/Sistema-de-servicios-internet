use baseDatosProyecto;
-- evalua si hay mas de una factura pendiente.
select
  count(f.idfactura) as facturasPendientes
from
  factura f
where
  f.idcontrato = 1
  and f.estado = 1
  and (
    select
      contrato.vigente
    from
      contrato
    where
      contrato.idcontrato = 1
  ) = 1;
-- si tiene mas de 1 factura
select
  count(idfactura) * (
    Select
      (
        s.precioservicio + (if (count(idfactura) > 1, s.mora, 0))
      )
    from
      contrato c
      inner join servicio s on s.idservicio = c.idservicio
    where
      c.idcontrato = 1
  ) as total
from
  factura
where
  idcontrato = 1
  and estado = 1;
select
  *
from
  factura
order by
  idfactura desc
limit
  1;
select
  c.idcontrato,
  cli.nombrecliente,
  cli.id_duicliente as DUI,
  (
    select
      count(f.idfactura) as facturasPendientes
    from
      factura f
    where
      f.idcontrato = c.idcontrato
      and f.estado = 1
  ) -1 as facturasPendientes,
  (
    select
      s.mora * (facturasPendientes)
    from
      servicio s
    where
      s.idservicio = c.idservicio
  ) as mora,
  (
    select
      s.precioservicio * (facturasPendientes + 1)
    from
      servicio s
    where
      s.idservicio = c.idservicio
  ) as deuda,
  (
    select
      deuda + mora
  ) as total
from
  contrato c
  inner join cliente cli on cli.id_duicliente = c.idcliente
where
  (
    (
      select
        count(f.idfactura) as facturasPendientes
      from
        factura f
      where
        f.idcontrato = c.idcontrato
        and f.estado = 1
    ) -1
  ) >= 1

-- PRUEBAS
/*SELECT
  DATE_ADD(NOW(), INTERVAL -6 HOUR);
select
  now();
select
  *
from
  contrato c
where
  (
    EXTRACT(
      DAY
      FROM
        c.fechainicio
    )
  ) = EXTRACT(
    DAY
    from
      NOW()
  ) -1;
  */
  -- CREACION DEL PROCEDIMIENTO ALMACENADO.

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



SET GLOBAL event_scheduler = ON;

-- CREACION DEL EVENTO MYSQL
CREATE EVENT generarFactura ON SCHEDULE EVERY 1 DAY STARTS DATE_ADD(NOW(), INTERVAL -6 HOUR) DO
call InsertFacturas;

-- MOSTRAR EVENTOS EN BD
SHOW events ;