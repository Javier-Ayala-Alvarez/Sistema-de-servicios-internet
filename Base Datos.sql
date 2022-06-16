CREATE TABLE `cliente`  (
  `id_duicliente` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `apellidocliente` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `direccioncliente` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `estado` tinyint(1) NULL DEFAULT 0,
  `nombrecliente` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `telefonocliente` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_duicliente`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `contrato`  (
  `idcontrato` int NOT NULL,
  `Direccion_contrato` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fechabaja` date NULL DEFAULT NULL,
  `fechainicio` date NULL DEFAULT NULL,
  `motivobaja` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `vigente` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `idcliente` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `idempleado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `idservicio` int NULL DEFAULT NULL,
  PRIMARY KEY (`idcontrato`) USING BTREE,
  INDEX `FK_contrato_idcliente`(`idcliente`) USING BTREE,
  INDEX `FK_contrato_idservicio`(`idservicio`) USING BTREE,
  INDEX `FK_contrato_idempleado`(`idempleado`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `empleado`  (
  `id_duiempleado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `apellidoempleado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `direccionempleado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `estado` tinyint(1) NULL DEFAULT 0,
  `fecha_contrato` date NULL DEFAULT NULL,
  `nombreempleado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `telefonoempleado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_nitempresa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `puesto` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_duiempleado`) USING BTREE,
  INDEX `FK_empleado_puesto`(`puesto`) USING BTREE,
  INDEX `FK_empleado_id_nitempresa`(`id_nitempresa`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `empresa`  (
  `id_nitempresa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `correo` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `direccionempresa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nombreempresa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `telefonoempresa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_nitempresa`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `factura`  (
  `idfactura` int NOT NULL AUTO_INCREMENT,
  `estado` int NULL DEFAULT NULL,
  `fechafactura` date NULL DEFAULT NULL,
  `fechapagofactura` date NULL DEFAULT NULL,
  `idcontrato` int NULL DEFAULT NULL,
  `idempleado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idfactura`) USING BTREE,
  INDEX `FK_factura_idempleado`(`idempleado`) USING BTREE,
  INDEX `FK_factura_idcontrato`(`idcontrato`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `puestos`  (
  `idpuesto` int NOT NULL,
  `estado` tinyint(1) NULL DEFAULT 0,
  `nombrepuesto` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `salario` decimal(38, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`idpuesto`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `servicio`  (
  `idservicio` int NOT NULL,
  `estado` tinyint(1) NULL DEFAULT 0,
  `mora` decimal(38, 0) NULL DEFAULT NULL,
  `periodo_mes` int NULL DEFAULT NULL,
  `precioservicio` decimal(38, 0) NULL DEFAULT NULL,
  `sancion` decimal(38, 0) NULL DEFAULT NULL,
  `servicio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idservicio`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `usuarios`  (
  `idusuario` int NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `usuario` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `idempleado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idusuario`) USING BTREE,
  INDEX `FK_usuarios_idempleado`(`idempleado`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

ALTER TABLE `contrato` ADD CONSTRAINT `FK_contrato_idcliente` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`id_duicliente`) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE `contrato` ADD CONSTRAINT `FK_contrato_idempleado` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`id_duiempleado`) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE `contrato` ADD CONSTRAINT `FK_contrato_idservicio` FOREIGN KEY (`idservicio`) REFERENCES `servicio` (`idservicio`) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE `empleado` ADD CONSTRAINT `FK_empleado_id_nitempresa` FOREIGN KEY (`id_nitempresa`) REFERENCES `empresa` (`id_nitempresa`) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE `empleado` ADD CONSTRAINT `FK_empleado_puesto` FOREIGN KEY (`puesto`) REFERENCES `puestos` (`idpuesto`) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE `factura` ADD CONSTRAINT `FK_factura_idcontrato` FOREIGN KEY (`idcontrato`) REFERENCES `contrato` (`idcontrato`) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE `factura` ADD CONSTRAINT `FK_factura_idempleado` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`id_duiempleado`) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE `usuarios` ADD CONSTRAINT `FK_usuarios_idempleado` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`id_duiempleado`) ON DELETE RESTRICT ON UPDATE CASCADE;

