/*
 Navicat PostgreSQL Data Transfer

 Source Server         : herokuDB
 Source Server Type    : PostgreSQL
 Source Server Version : 140003
 Source Host           : ec2-34-230-153-41.compute-1.amazonaws.com:5432
 Source Catalog        : d82d8e177hv98b
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140003
 File Encoding         : 65001

 Date: 03/06/2022 21:17:42
*/


-- ----------------------------
-- Sequence structure for contrato_idcontrato_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."contrato_idcontrato_seq";
CREATE SEQUENCE "public"."contrato_idcontrato_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;
ALTER SEQUENCE "public"."contrato_idcontrato_seq" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Sequence structure for factura_idfactura_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."factura_idfactura_seq";
CREATE SEQUENCE "public"."factura_idfactura_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;
ALTER SEQUENCE "public"."factura_idfactura_seq" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Sequence structure for puestos_idpuesto_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."puestos_idpuesto_seq";
CREATE SEQUENCE "public"."puestos_idpuesto_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;
ALTER SEQUENCE "public"."puestos_idpuesto_seq" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Sequence structure for servicio_idservicio_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."servicio_idservicio_seq";
CREATE SEQUENCE "public"."servicio_idservicio_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;
ALTER SEQUENCE "public"."servicio_idservicio_seq" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Sequence structure for usuarios_idusuario_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."usuarios_idusuario_seq";
CREATE SEQUENCE "public"."usuarios_idusuario_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;
ALTER SEQUENCE "public"."usuarios_idusuario_seq" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Table structure for cliente
-- ----------------------------
DROP TABLE IF EXISTS "public"."cliente";
CREATE TABLE "public"."cliente" (
  "id_duicliente" varchar(12) COLLATE "pg_catalog"."default" NOT NULL,
  "nombrecliente" varchar(30) COLLATE "pg_catalog"."default",
  "apellidocliente" varchar(30) COLLATE "pg_catalog"."default",
  "telefonocliente" varchar(10) COLLATE "pg_catalog"."default",
  "direccioncliente" varchar(40) COLLATE "pg_catalog"."default",
  "estado" bool
)
;
ALTER TABLE "public"."cliente" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Records of cliente
-- ----------------------------
BEGIN;
INSERT INTO "public"."cliente" VALUES ('09875678-0', 'JOHARIS', 'ARIAS', '7856 6745', 'EL ROSARIO', 't');
INSERT INTO "public"."cliente" VALUES ('07435478-6', 'KRISSIA', 'ESCOBAR', '7654 3567', 'EL CENTRO', 't');
INSERT INTO "public"."cliente" VALUES ('05443565-4', 'DIANA ', 'RIVERA', '2245 7752', 'SAN RAFAEL', 't');
INSERT INTO "public"."cliente" VALUES ('04762433-2', 'RODOLFO', 'SALDAÑA', '7325 9233', 'SAN RAFAEL', 't');
INSERT INTO "public"."cliente" VALUES ('44566567-6', 'ALEXANDER', 'COLINDRES', '8767 8989', 'SAN RAFAEL', 't');
INSERT INTO "public"."cliente" VALUES ('75767876-8', 'JOSE', 'LOPEZ', '7878 8798', 'SAN VICENTE', 'f');
COMMIT;

-- ----------------------------
-- Table structure for contrato
-- ----------------------------
DROP TABLE IF EXISTS "public"."contrato";
CREATE TABLE "public"."contrato" (
  "idcontrato" int4 NOT NULL DEFAULT nextval('contrato_idcontrato_seq'::regclass),
  "idservicio" int4 NOT NULL,
  "idcliente" varchar(12) COLLATE "pg_catalog"."default" NOT NULL,
  "idempleado" varchar(12) COLLATE "pg_catalog"."default" NOT NULL,
  "fechainicio" date NOT NULL,
  "vigente" bool NOT NULL,
  "motivobaja" varchar(100) COLLATE "pg_catalog"."default",
  "fechabaja" date
)
;
ALTER TABLE "public"."contrato" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Records of contrato
-- ----------------------------
BEGIN;
INSERT INTO "public"."contrato" VALUES (4, 3, '04762433-2', '00453452-3', '2021-12-11', 't', NULL, NULL);
INSERT INTO "public"."contrato" VALUES (5, 3, '75767876-8', '00453452-3', '2021-12-13', 't', NULL, NULL);
INSERT INTO "public"."contrato" VALUES (6, 1, '44566567-6', '02882337-1', '2021-12-09', 'f', '', '2022-02-23');
INSERT INTO "public"."contrato" VALUES (1, 2, '05443565-4', '00453452-3', '2021-09-30', 't', NULL, NULL);
INSERT INTO "public"."contrato" VALUES (2, 3, '07435478-6', '02882337-1', '2021-10-17', 't', NULL, NULL);
INSERT INTO "public"."contrato" VALUES (3, 1, '09875678-0', '00453452-3', '2021-10-22', 't', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for empleado
-- ----------------------------
DROP TABLE IF EXISTS "public"."empleado";
CREATE TABLE "public"."empleado" (
  "id_duiempleado" varchar(12) COLLATE "pg_catalog"."default" NOT NULL,
  "nombreempleado" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "apellidoempleado" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "telefonoempleado" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "direccionempleado" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "puesto" int4 NOT NULL,
  "id_nitempresa" varchar(25) COLLATE "pg_catalog"."default" NOT NULL,
  "estado" bool,
  "fecha_contrato" date,
  "salario" numeric(10,2)
)
;
ALTER TABLE "public"."empleado" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Records of empleado
-- ----------------------------
BEGIN;
INSERT INTO "public"."empleado" VALUES ('05453467-2', 'SUSANA', 'TORRES', '7986 1766', 'EL ROSARIO', 4, '0711 - 210278 - 101 - 3', 't', NULL, NULL);
INSERT INTO "public"."empleado" VALUES ('09224678-4', 'JOSUE', 'ROSALES', '7645 8543', 'LOS OLIVOS', 1, '0711 - 210278 - 101 - 3', 't', NULL, NULL);
INSERT INTO "public"."empleado" VALUES ('67876897-8', 'MIGUEL', 'MALDONADO', '7890 8790', 'SAN RAFAEL', 3, '0711 - 210278 - 101 - 3', 't', NULL, NULL);
INSERT INTO "public"."empleado" VALUES ('02882337-1', 'JAIME', 'GARCIA', '7894 4264', 'SAN RAFAEL', 1, '0711 - 210278 - 101 - 3', 't', NULL, NULL);
INSERT INTO "public"."empleado" VALUES ('06100905-9', 'LORENA', 'ORELLANA', '7234-2503', 'LOS OLIVOS', 5, '0711 - 210278 - 101 - 3', 't', NULL, NULL);
INSERT INTO "public"."empleado" VALUES ('00453452-3', 'PATRICIA', 'MARTINEZ', '7233 3837', 'SAN RAFAEL', 2, '0711 - 210278 - 101 - 3', 't', NULL, NULL);
INSERT INTO "public"."empleado" VALUES ('06548253-7', 'DANIEL', 'HERNANDEZ', '7544 5322', 'SAN VICENTE', 3, '0711 - 210278 - 101 - 3', 't', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for empresa
-- ----------------------------
DROP TABLE IF EXISTS "public"."empresa";
CREATE TABLE "public"."empresa" (
  "id_nitempresa" varchar(25) COLLATE "pg_catalog"."default" NOT NULL,
  "nombreempresa" varchar(45) COLLATE "pg_catalog"."default",
  "telefonoempresa" varchar(15) COLLATE "pg_catalog"."default",
  "direccionempresa" varchar(50) COLLATE "pg_catalog"."default",
  "correo" varchar COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."empresa" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Records of empresa
-- ----------------------------
BEGIN;
INSERT INTO "public"."empresa" VALUES ('0711 - 210278 - 101 - 3', 'WIFI.NET', '7182 0509', 'SAN RAFAEL', NULL);
COMMIT;

-- ----------------------------
-- Table structure for factura
-- ----------------------------
DROP TABLE IF EXISTS "public"."factura";
CREATE TABLE "public"."factura" (
  "idfactura" int4 NOT NULL DEFAULT nextval('factura_idfactura_seq'::regclass),
  "fechafactura" date,
  "idcontrato" int4 NOT NULL,
  "idempleado" varchar(12) COLLATE "pg_catalog"."default",
  "estado" int4
)
;
ALTER TABLE "public"."factura" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Records of factura
-- ----------------------------
BEGIN;
INSERT INTO "public"."factura" VALUES (3, '2021-12-22', 2, '05453467-2', NULL);
INSERT INTO "public"."factura" VALUES (12, '2021-12-03', 3, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for puestos
-- ----------------------------
DROP TABLE IF EXISTS "public"."puestos";
CREATE TABLE "public"."puestos" (
  "idpuesto" int4 NOT NULL DEFAULT nextval('puestos_idpuesto_seq'::regclass),
  "nombrepuesto" varchar(40) COLLATE "pg_catalog"."default",
  "sueldo" float8,
  "estado" bool
)
;
ALTER TABLE "public"."puestos" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Records of puestos
-- ----------------------------
BEGIN;
INSERT INTO "public"."puestos" VALUES (3, 'SOPORTE TÉCNICO', 325, 't');
INSERT INTO "public"."puestos" VALUES (2, 'VENDEDOR AL DETALLE', 300, 't');
INSERT INTO "public"."puestos" VALUES (5, 'SERVICIO AL CLIENTE', 300, 't');
INSERT INTO "public"."puestos" VALUES (4, 'SECRETARIA', 300, 't');
INSERT INTO "public"."puestos" VALUES (1, 'VENDEDOR RUTERO', 300, 't');
INSERT INTO "public"."puestos" VALUES (6, 'PUESTO 6', 1, 't');
INSERT INTO "public"."puestos" VALUES (7, 'PUESTO 7', 2000, 't');
COMMIT;

-- ----------------------------
-- Table structure for servicio
-- ----------------------------
DROP TABLE IF EXISTS "public"."servicio";
CREATE TABLE "public"."servicio" (
  "idservicio" int4 NOT NULL DEFAULT nextval('servicio_idservicio_seq'::regclass),
  "servicio" varchar(30) COLLATE "pg_catalog"."default",
  "precioservicio" numeric(14,2),
  "estado" bool,
  "periodo-mes" int4,
  "mora" money,
  "sancion" money
)
;
ALTER TABLE "public"."servicio" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Records of servicio
-- ----------------------------
BEGIN;
INSERT INTO "public"."servicio" VALUES (1, '25 MEGAS', 24.00, 't', NULL, NULL, NULL);
INSERT INTO "public"."servicio" VALUES (3, '20 MEGAS', 30.00, 't', NULL, NULL, NULL);
INSERT INTO "public"."servicio" VALUES (2, '15 MEGAS', 28.00, 'f', NULL, NULL, NULL);
INSERT INTO "public"."servicio" VALUES (4, '30 MEGAS', 25.00, 't', NULL, NULL, NULL);
INSERT INTO "public"."servicio" VALUES (5, '100 MEGAS', 75.00, 'f', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for usuarios
-- ----------------------------
DROP TABLE IF EXISTS "public"."usuarios";
CREATE TABLE "public"."usuarios" (
  "idusuario" int4 NOT NULL DEFAULT nextval('usuarios_idusuario_seq'::regclass),
  "usuario" varchar(30) COLLATE "pg_catalog"."default",
  "password" varchar(90) COLLATE "pg_catalog"."default",
  "idempleado" varchar(10) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."usuarios" OWNER TO "dajdkctwgypeyy";

-- ----------------------------
-- Records of usuarios
-- ----------------------------
BEGIN;
INSERT INTO "public"."usuarios" VALUES (1, 'torres', 'ccb3219f68c412b5c7e94e6a4aadb42f4da7b996', '05453467-2');
INSERT INTO "public"."usuarios" VALUES (4, 'martinez', '18317725eb47fe6dfbc309f50de09abab0a69b06', '00453452-3');
INSERT INTO "public"."usuarios" VALUES (3, 'hernandez', '7c1feff10c8ac38db63edadcb76be7f10f230bdb', '06548253-7');
COMMIT;

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."contrato_idcontrato_seq"
OWNED BY "public"."contrato"."idcontrato";
SELECT setval('"public"."contrato_idcontrato_seq"', 31, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."factura_idfactura_seq"
OWNED BY "public"."factura"."idfactura";
SELECT setval('"public"."factura_idfactura_seq"', 17, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."puestos_idpuesto_seq"
OWNED BY "public"."puestos"."idpuesto";
SELECT setval('"public"."puestos_idpuesto_seq"', 9, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."servicio_idservicio_seq"
OWNED BY "public"."servicio"."idservicio";
SELECT setval('"public"."servicio_idservicio_seq"', 8, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."usuarios_idusuario_seq"
OWNED BY "public"."usuarios"."idusuario";
SELECT setval('"public"."usuarios_idusuario_seq"', 3, true);

-- ----------------------------
-- Primary Key structure for table cliente
-- ----------------------------
ALTER TABLE "public"."cliente" ADD CONSTRAINT "pk_idcliente" PRIMARY KEY ("id_duicliente");

-- ----------------------------
-- Indexes structure for table contrato
-- ----------------------------
CREATE INDEX "fki_fk_id_cliente" ON "public"."contrato" USING btree (
  "idcliente" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "fki_fk_id_empleado" ON "public"."contrato" USING btree (
  "idempleado" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "fki_fk_id_servicio" ON "public"."contrato" USING btree (
  "idservicio" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table contrato
-- ----------------------------
ALTER TABLE "public"."contrato" ADD CONSTRAINT "fk_idcontrato" PRIMARY KEY ("idcontrato");

-- ----------------------------
-- Indexes structure for table empleado
-- ----------------------------
CREATE INDEX "fki_fk_idempresa" ON "public"."empleado" USING btree (
  "id_nitempresa" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "fki_fk_puesto" ON "public"."empleado" USING btree (
  "puesto" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table empleado
-- ----------------------------
ALTER TABLE "public"."empleado" ADD CONSTRAINT "pk_idempleado" PRIMARY KEY ("id_duiempleado");

-- ----------------------------
-- Uniques structure for table empresa
-- ----------------------------
ALTER TABLE "public"."empresa" ADD CONSTRAINT "nombre" UNIQUE ("nombreempresa");

-- ----------------------------
-- Primary Key structure for table empresa
-- ----------------------------
ALTER TABLE "public"."empresa" ADD CONSTRAINT "pk_idempresa" PRIMARY KEY ("id_nitempresa");

-- ----------------------------
-- Indexes structure for table factura
-- ----------------------------
CREATE INDEX "fki_fk_idcontrato" ON "public"."factura" USING btree (
  "idcontrato" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "fki_fk_idempleado" ON "public"."factura" USING btree (
  "idempleado" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table factura
-- ----------------------------
ALTER TABLE "public"."factura" ADD CONSTRAINT "pk_idfactura" PRIMARY KEY ("idfactura");

-- ----------------------------
-- Uniques structure for table puestos
-- ----------------------------
ALTER TABLE "public"."puestos" ADD CONSTRAINT "uk_nombrepuesto" UNIQUE ("nombrepuesto");

-- ----------------------------
-- Primary Key structure for table puestos
-- ----------------------------
ALTER TABLE "public"."puestos" ADD CONSTRAINT "pk_idpuesto" PRIMARY KEY ("idpuesto");

-- ----------------------------
-- Uniques structure for table servicio
-- ----------------------------
ALTER TABLE "public"."servicio" ADD CONSTRAINT "uk_servicio" UNIQUE ("servicio");

-- ----------------------------
-- Primary Key structure for table servicio
-- ----------------------------
ALTER TABLE "public"."servicio" ADD CONSTRAINT "pk_idservicio" PRIMARY KEY ("idservicio");

-- ----------------------------
-- Indexes structure for table usuarios
-- ----------------------------
CREATE INDEX "fki_fk_idem" ON "public"."usuarios" USING btree (
  "idempleado" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table usuarios
-- ----------------------------
ALTER TABLE "public"."usuarios" ADD CONSTRAINT "usuario" UNIQUE ("usuario");

-- ----------------------------
-- Primary Key structure for table usuarios
-- ----------------------------
ALTER TABLE "public"."usuarios" ADD CONSTRAINT "pk_idusuario" PRIMARY KEY ("idusuario");

-- ----------------------------
-- Foreign Keys structure for table contrato
-- ----------------------------
ALTER TABLE "public"."contrato" ADD CONSTRAINT "fk_id_empleado" FOREIGN KEY ("idempleado") REFERENCES "public"."empleado" ("id_duiempleado") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."contrato" ADD CONSTRAINT "fk_id_servicio" FOREIGN KEY ("idservicio") REFERENCES "public"."servicio" ("idservicio") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."contrato" ADD CONSTRAINT "fk_idcliente" FOREIGN KEY ("idcliente") REFERENCES "public"."cliente" ("id_duicliente") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table empleado
-- ----------------------------
ALTER TABLE "public"."empleado" ADD CONSTRAINT "fk_idempresa" FOREIGN KEY ("id_nitempresa") REFERENCES "public"."empresa" ("id_nitempresa") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."empleado" ADD CONSTRAINT "fk_idpuesto" FOREIGN KEY ("puesto") REFERENCES "public"."puestos" ("idpuesto") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table factura
-- ----------------------------
ALTER TABLE "public"."factura" ADD CONSTRAINT "fk_idcontrato" FOREIGN KEY ("idcontrato") REFERENCES "public"."contrato" ("idcontrato") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."factura" ADD CONSTRAINT "fk_idempleado" FOREIGN KEY ("idempleado") REFERENCES "public"."empleado" ("id_duiempleado") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table usuarios
-- ----------------------------
ALTER TABLE "public"."usuarios" ADD CONSTRAINT "fk_idem" FOREIGN KEY ("idempleado") REFERENCES "public"."empleado" ("id_duiempleado") ON DELETE NO ACTION ON UPDATE NO ACTION;
