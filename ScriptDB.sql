create database policlinico;
use policlinico;

CREATE TABLE paciente (
    numero INT(11) NOT NULL,
    nombre VARCHAR(50) DEFAULT NULL,
    apellido VARCHAR(50) DEFAULT NULL,
    fechaN DATE DEFAULT NULL,
    cedula VARCHAR(11) DEFAULT NULL,
    direccion VARCHAR(150) DEFAULT NULL,
    edad INT(11) DEFAULT NULL,
    secuencia VARCHAR(1) DEFAULT NULL,
    status VARCHAR(1) DEFAULT 'A',
    user_modi VARCHAR(45) DEFAULT NULL,
    fecha_modi DATE DEFAULT NULL,
    PRIMARY KEY (numero)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE Especialidad (
    codigo INT(11) NOT NULL,
    especialidad_N VARCHAR(50) DEFAULT NULL,
    status VARCHAR(1) DEFAULT 'A',
    user_modi VARCHAR(45) DEFAULT NULL,
    fecha_modi DATE DEFAULT NULL,
    PRIMARY KEY (codigo)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE medico (
    numero INT(11) NOT NULL,
    nombre VARCHAR(100) DEFAULT NULL,
    especialidad INT(11) DEFAULT NULL,
    piso INT(11) DEFAULT NULL,
    extension INT(11) DEFAULT NULL,
    status VARCHAR(1) DEFAULT 'A',
    user_modi VARCHAR(45) DEFAULT NULL,
    fecha_modi DATE DEFAULT NULL,
    PRIMARY KEY (numero)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE estudios (
    numero VARCHAR(10) NOT NULL,
    tipo VARCHAR(2) NOT NULL,
    numero_p INT(11) DEFAULT NULL,
    maestroe INT(11) DEFAULT NULL,
    medico INT(11) DEFAULT NULL,
    estudio MEDIUMTEXT,
    fecha DATE DEFAULT NULL,
    status VARCHAR(1) DEFAULT 'A',
    user_modi VARCHAR(45) DEFAULT NULL,
    fecha_modi DATE DEFAULT NULL,
    PRIMARY KEY (numero,tipo)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE tipoEstudio (
    codigo VARCHAR(2) NOT NULL,
    descripcion VARCHAR(30) DEFAULT NULL,
    status VARCHAR(1) DEFAULT 'A',
    user_modi VARCHAR(45) DEFAULT NULL,
    fecha_modi DATE DEFAULT NULL,
    PRIMARY KEY (codigo)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;


CREATE TABLE maestro (
    numero int(11) NOT NULL,
    nombre VARCHAR(100) DEFAULT NULL,
    descripcion MEDIUMTEXT,
    status VARCHAR(1) DEFAULT 'A',
    user_modi VARCHAR(45) DEFAULT NULL,
    fecha_modi DATE DEFAULT NULL,
    PRIMARY KEY (numero)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE usuario (
    idUsuario INT(11) NOT NULL AUTO_INCREMENT,
    cedula VARCHAR(11) DEFAULT NULL,
    Nombre VARCHAR(45) DEFAULT NULL,
    Apellidos VARCHAR(45) DEFAULT NULL,
    Direccion VARCHAR(200) DEFAULT NULL,
    username VARCHAR(45) DEFAULT NULL,
    userpassw VARCHAR(40) DEFAULT NULL,
    nivel VARCHAR(1) DEFAULT NULL,
    sexo VARCHAR(1) DEFAULT NULL,
    correo VARCHAR(45) DEFAULT NULL,
    logo BLOB,
    rutaimagen VARCHAR(200) DEFAULT NULL,
    status VARCHAR(1) DEFAULT 'A',
    user_modi VARCHAR(45) DEFAULT NULL,
    fecha_modi DATE DEFAULT NULL,
    PRIMARY KEY (idUsuario)
)  ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=UTF8;

CREATE TABLE deta_localizador (
    idDeta_localizador INT(11) NOT NULL AUTO_INCREMENT,
    tipo_entidad VARCHAR(1) DEFAULT NULL,
    identidad INT(11) DEFAULT NULL,
    tipo VARCHAR(7) DEFAULT NULL,
    numero VARCHAR(10) DEFAULT NULL,
    fecha_modi DATE DEFAULT NULL,
    user_modi VARCHAR(45) DEFAULT NULL,
    status VARCHAR(1) DEFAULT 'A',
    PRIMARY KEY (idDeta_localizador),
    UNIQUE KEY idDeta_localizador_UNIQUE (idDeta_localizador),
    UNIQUE KEY ID_Tipoe_Entidad_TIPO_NUM (idDeta_localizador , tipo_entidad , identidad , tipo , numero) COMMENT 'campo unico para las diferentes entidades que llevan localizador como cliente=C,empleado=E,Suplidor=S y Empresa=B'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;