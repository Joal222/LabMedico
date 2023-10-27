--CATALOGO TIPO ESTADO SOLICITUD - CATALOGO CU2 - RN8
create table tipo_estado_solicitud(
	id SERIAL,
	nombre varchar(50) not null, 
	descripcion varchar (255) not null,
	fecha_creacion date not null,
	fecha_modificacion date null,
	creado_por varchar(255) not null,
	modificado_por varchar (255) null,
	primary key (id)
);

--CATALOGO TIPO SOPORTE - CATALOGO CU1 - RN8
create table tipo_soporte(
	id SERIAL,
	nombre varchar(50) not null, 
	descripcion varchar (255) not null,
	fecha_creacion date not null,
	fecha_modificacion date null,
	creado_por varchar(255) not null,
	modificado_por varchar (255) null,
	primary key (id)
);

--CATALOGO ROL/TIPO USUARIO - CATALOGO*
create table rol (
	id SERIAL,
	nombre varchar(50) not null, 
	descripcion varchar (255) not null,
	fecha_creacion date not null,
	fecha_modificacion date null,
	creado_por varchar(255) not null,
	modificado_por varchar (255) null,
	primary key (id)
);

--CATALOGO TIPO DE SOLICITUD - CATALOGO CU1 - RN4
create table tipo_solicitud (
	id SERIAL,
	nombre varchar(50) not null, 
	descripcion varchar (255) not null,
	fecha_creacion date not null,
	fecha_modificacion date null,
	creado_por varchar(255) not null,
	modificado_por varchar (255) null,
	primary key (id)
);

--CATALOGO TIPO SOLICITANTE - CATALOGO CU1 - RN1
create table tipo_solicitante(
	id SERIAL,
	nombre varchar(50) not null, 
	descripcion varchar (255) not null,
	fecha_creacion date not null,
	fecha_modificacion date null,
	creado_por varchar(255) not null,
	modificado_por varchar (255) null,
	primary key (id)
);

--TABLA USUARIO*
create table usuario (
	id SERIAL,
    --SIN TABLA CATALOGO, POR DEFAULT 1 Y ROL CORRESPONDE A USUARIO INTERNO 2.1,2.2 ETC
    id_tipo_usuario integer default 1,
	id_rol integer default null,
    --PENDIENTE DE APLICAR A MI DB LOCAL
	--id_expediente integer unique null, --ESTE CAMBO SE ELIMINO EL 23-10-23 POR LA OBIA ELIMINACION DE LA TABLA EXPEDIENTES.
    nit varchar(8) not null,
	nombres varchar(255) not null,
	apellidos varchar(255) not null,
	email varchar(255) not null,
	genero varchar(255) not null,
	telefono varchar(8) not null,
    direccion varchar(255) not null,
	password varchar(255) not null,
	primary key (id)
);

--FKEY EN TABLA USUARIO POR RELACION CON ROL*
alter table usuario
add constraint usuario_id_rol_fkey foreign key (id_rol)
references rol (id) match simple;

create table solicitud_muestra_medica (
	id SERIAL,
    id_usuario integer not null,
	id_tipo_solicitante integer default 1,
	id_tipo_solicitud integer not null,
    id_tipo_soporte integer not null, --CAMPO AGREGADO A PARTIR DE LA ELIMINACION DE TABLA DE EXPEDIENTES 23-10-23
	descripcion_solicitud_muestra_medica varchar(2000) null,
	fecha_creacion_solicitud date not null,
	dias_vencimiento_solicitud integer null,
	primary key (id)
);

--CRACION DE FKEY EN TABLA SOLICITUD_MUESTRA_MEDICA POR RELACION CON TABALA TIPO_SOPORTE
alter table solicitud_muestra_medica
add constraint solicitud_muestra_medica_id_tipo_soporte_fkey foreign key (id_tipo_soporte)
references tipo_soporte (id) match simple;

alter table solicitud_muestra_medica
add constraint solicitud_muestra_medica_id_usuario_fkey foreign key (id_usuario)
references usuario (id) match simple;

alter table solicitud_muestra_medica
add constraint solicitud_muestra_medica_id_tipo_solicitante_fkey foreign key (id_tipo_solicitante)
references tipo_solicitante (id) match simple;

alter table solicitud_muestra_medica
add constraint solicitud_muestra_medica_id_tipo_solicitud_fkey foreign key (id_tipo_solicitud)
references tipo_solicitud (id) match simple;

--TABLA ESTADO SOLICITUD / BITCORA
create table bitacora_estado (
    id SERIAL,
    id_tipo_estado_solicitud integer,
    id_solicitud_muestra_medica integer,
    id_usuario integer,
    fecha_estado timestamp default current_timestamp,
    comentario varchar (255),
    primary key (id)
);

alter table bitacora_estado
    add constraint bitacora_estado_id_tipo_estado_solicitud_fkey foreign key (id_tipo_estado_solicitud)
        references tipo_estado_solicitud (id) match simple;

alter table bitacora_estado
    add constraint bitacora_estado_id_solicitud_muestra_medica_fkey foreign key (id_solicitud_muestra_medica)
        references solicitud_muestra_medica (id) match simple;

alter table bitacora_estado
    add constraint bitacora_estado_id_usuario_fkey foreign key (id_usuario)
        references usuario (id) match simple;

--CATALOGO UNIDAD MEDIDA
create table unidad_medida (
	id SERIAL,
	nombre varchar(50) not null, 
	descripcion varchar (255) not null,
	fecha_creacion date not null,
	fecha_modificacion date null,
	creado_por varchar(255) not null,
	modificado_por varchar (255) null,
	primary key (id)
);

--CATALOGO TIPO MUESTRA
create table tipo_muestra (
	id SERIAL,
	nombre varchar(50) not null, 
	descripcion varchar (255) not null,
	fecha_creacion date not null,
	fecha_modificacion date null,
	creado_por varchar(255) not null,
	modificado_por varchar (255) null,
	primary key (id)
);

---CATALOGO PRESENTACION MUESTRA
create table presentacion_muestra (
	id SERIAL,
	nombre varchar(50) not null, 
	descripcion varchar (255) not null,
	fecha_creacion date not null,
	fecha_modificacion date null,
	creado_por varchar(255) not null,
	modificado_por varchar (255) null,
	primary key (id)
);

--CATALOGO TIPO DE ITEMS
create table tipo_items (
	id SERIAL,
    id_tipo_examen integer not null ,
	nombre varchar(50) not null, 
	descripcion varchar (255) not null,
	fecha_creacion date not null,
	fecha_modificacion date null,
	creado_por varchar(255) not null,
	modificado_por varchar (255) null,
	primary key (id)
);

--CATALOGO TIPO EXAMEN
create table tipo_examen (
    id SERIAL,
    nombre varchar(50) not null,
    descripcion varchar (255) not null,
    fecha_creacion date not null,
    fecha_modificacion date null,
    creado_por varchar(255) not null,
    modificado_por varchar (255) null,
    primary key (id)
);

--TABLA ITEMS
create table items (
	id SERIAL,
	id_tipo_items integer not null,
	id_solicitud_muestra_medica integer not null,
	primary key (id)
);

--RELACION ENTRE TABLA TIPO_EXAMEN A TIPO_ITEMS
alter table tipo_items
add constraint tipo_items_id_tipo_examen_fkey foreign key (id_tipo_examen)
references tipo_examen (id) match simple;

alter table items 
add constraint items_tipo_items_fkey foreign key (id_tipo_items)
references tipo_items (id) match simple;

alter table items 
add constraint items_solicitud_muestra_medica_fkey foreign key (id_solicitud_muestra_medica)
references solicitud_muestra_medica (id) match simple;

--TABLA DE DOCUMENTOS ADJUNTOS
--POR EL MOMENTO QUEDA EN PENDIENTE, DEBIDO A QUE VAMOS EN PRIMERA FASE DE DESARROLLO
--create table documento_muestra_adjunto (
	--id SERIAL,
	--formato_documento varchar(4),
	--tamaño_documento varchar (4),
	--primary key (id)
--);

create table muestra_medica (
	id SERIAL,
	id_solicitud_muestra_medica integer not null,
	id_presentacion_muestra integer not null,
	id_tipo_muestra integer not null,
	--id_documento_muestra_adjunto integer null, --POR EL MOMENTO QUEDA EN PENDIENTE, DEBIDO A QUE VAMOS EN PRIMERA FASE DE DESARROLLO
	id_unidad_medida integer not null,
	fecha_recepcion_muestra date not null,
	fecha_creacion_muestra date not null,
	--cantidad_items integer,
	--cantidad_documento_muestra_adjunto integer,
	observacion_expediente varchar(2000),
	primary key (id)
);


create table muestra_items (
	id SERIAL,
	id_muestra_medica integer not null,
	id_items integer not null,
	primary key (id)
);

alter table muestra_items
add constraint muestra_items_id_muestra_medica_fkey foreign key (id_muestra_medica)
references muestra_medica (id) match simple;

alter table muestra_items
add constraint muestra_items_id_items_fkey foreign key (id_items)
references items (id) match simple;

alter table muestra_medica
add constraint muestra_medica_id_solicitud_muestra_medica_fkey foreign key (id_solicitud_muestra_medica)
references solicitud_muestra_medica (id)match simple;

alter table muestra_medica
add constraint muestra_medica_id_presentacion_muestra_fkey foreign key (id_presentacion_muestra)
references presentacion_muestra (id) match simple;

alter table muestra_medica
add constraint muestra_medica_id_tipo_muestra_fkey foreign key (id_tipo_muestra)
references tipo_muestra (id) match simple;

--POR EL MOMENTO QUEDA EN PENDIENTE, DEBIDO A QUE VAMOS EN PRIMERA FASE DE DESARROLLO
--alter table muestra_medica
--add constraint muestra_medica_id_documento_muestra_adjunto_fkey foreign key (id_documento_muestra_adjunto)
--references documento_muestra_adjunto (id) match simple;

alter table muestra_medica
add constraint muestra_medica_id_unidad_medida_fkey foreign key (id_unidad_medida)
references unidad_medida (id) match simple;

--POR EL MOMENTO QUEDA EN PENDIENTE, DEBIDO A QUE VAMOS EN PRIMERA FASE DE DESARROLLO
--create table documento_analisis(
--	id SERIAL,
--	id_muestra_medica integer,
--	primary key (id)
--);

--POR EL MOMENTO QUEDA EN PENDIENTE, DEBIDO A QUE VAMOS EN PRIMERA FASE DE DESARROLLO
--alter table documento_analisis
--add constraint documento_analisis_id_muestra_medica_fkey foreign key (id_muestra_medica)
--references muestra_medica (id) match simple;

--create table bitacora

--INGRESO DE DATOS A CATOLOGO DE TIPO DE SOPORTE
insert into tipo_soporte(nombre, descripcion,fecha_creacion,creado_por) 
values
('SM','Solicitud Médica','2023-10-15 22:30:00','Jonathan'),
('ET','Examen Externo','2023-10-15 22:30:00','Jonathan'),
('FP','Factura','2023-10-15 22:30:00','Jonathan'),
('HO','Hoja Oficio','2023-10-15 22:30:00','Jonathan"');

--CREACION DE USUARIO DE PRUEBA
insert into usuario (nit,nombres,apellidos,email,genero,telefono,direccion,password)
values
    ('89914190','Jonathan','Guamuch','morales0598@gmail.com','Masculino','46797979','Ciudad Quetazal','prueba123'),
    ('78984512','José','Ávila','chepe@gmail.com','Masculino','45654565','Ciudad Quetazal','umg');

--INGRESO DE DATOS A CATOLOGO DE ROL
insert into rol(nombre, descripcion,fecha_creacion,creado_por)
values
    ('REV','Revisor','2023-10-18 22:30:00','Jonathan'),
    ('TEC','Tecnico','2023-10-18 22:30:00','Jonathan'),
    ('CEN','Centralizador','2023-10-18 22:30:00','Jonathan'),
    ('ALT','Analista','2023-10-18 22:30:00','Jonathan"');

--INGRESO DE DATOS A CATOLO DE TIPO SOLICITANTE
INSERT INTO tipo_solicitante (nombre, descripcion, fecha_creacion, creado_por)
VALUES
    ('INT', 'Interno', CURRENT_DATE, 'Jonathan'),
    ('EXT', 'Externo', CURRENT_DATE, 'Jonathan');

-- Insertar registros en la tabla tipo_estado_solicitud
INSERT INTO tipo_estado_solicitud (nombre, descripcion, fecha_creacion, creado_por)
VALUES
    ('CRD', 'Creado', CURRENT_DATE, 'Jonathan'),
    ('ENV', 'Enviado', CURRENT_DATE, 'Jonathan'),
    ('ASG', 'Asignado', CURRENT_DATE, 'Jonathan'),
    ('ESP', 'Espera', CURRENT_DATE, 'Jonathan'),
    ('REV', 'Revisión', CURRENT_DATE, 'Jonathan'),
    ('FIN', 'Finalizado', CURRENT_DATE, 'Jonathan'),
    ('REH', 'Rechazado', CURRENT_DATE, 'Jonathan');

-- Para tipo_solicitud
INSERT INTO tipo_solicitud (nombre, descripcion, fecha_creacion, creado_por)
VALUES
    ('MM', 'Muestra Médica', CURRENT_DATE, 'Jonathan'),
    ('LQ', 'Laboratorio', CURRENT_DATE, 'Jonathan');

-- Para tipo_soporte
INSERT INTO tipo_soporte (nombre, descripcion, fecha_creacion, creado_por)
VALUES
    ('FP', 'Factura', CURRENT_DATE, 'Jonathan'),
    ('HO', 'Hoja Oficio', CURRENT_DATE, 'Jonathan');

-- DATOS DE PRUEBA SOLICITUD_MUESTRA_MEDICA
INSERT INTO solicitud_muestra_medica (id_usuario, id_tipo_solicitante, id_tipo_solicitud, id_tipo_soporte, descripcion_solicitud_muestra_medica, fecha_creacion_solicitud, dias_vencimiento_solicitud)
VALUES
    (1, 1, 1, 1, 'Solicitud de muestra médica para análisis', CURRENT_DATE, 10),
    (1, 2, 2, 2, 'Solicitud de laboratorio para examen externo', CURRENT_DATE, 7),
    (2, 1, 1, 1, 'Solicitud de muestra médica para análisis', CURRENT_DATE, 10),
    (2, 2, 2, 2, 'Solicitud de laboratorio para examen externo', CURRENT_DATE, 7);











