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

--CATALOGO ROL/TIPO USUARIO - CATALOGO
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

--CATALOGO PRIVILEGIOS
--PENDIENTE INTERRELACION
create table privilegios(
	id SERIAL,
	nombre varchar(50) not null, 
	descripcion varchar (255) not null,
	fecha_creacion date not null,
	fecha_modificacion date null,
	creado_por varchar(255) not null,
	modificado_por varchar (255) null,
	primary key (id)
);

--TABLA ESTADO SOLICITUD
create table estado_solicitud (
	id SERIAL,
	id_tipo_estado_solicitud integer not null,
	primary key (id)
);

--drop table estado_solicitud;

alter table estado_solicitud 
add constraint estado_solicitud_tipo_estado_solicitud_fkey foreign key (id_tipo_estado_solicitud)
references tipo_estado_solicitud (id) match simple;

--TABLA EXPEDIENTE MEDICO
create table expediente (
	id SERIAL,	
	nit_cliente varchar(8) not null,
	direccion varchar(255) not null,
	id_tipo_soporte integer null,
	numero_soporte integer null,
	primary key (id)
);

--TABLA USUARIO
create table usuario (
	id SERIAL,
    --SIN TABLA CATALOGO, POR DEFAULT 1 Y ROL CORRESPONDE A USUARIO INTERNO 2.1,2.2 ETC
    id_tipo_usuario integer default 1,
	id_rol integer null,
	id_expediente integer unique null,
	nombres varchar(255) not null,
	apellidos varchar(255) not null,
	email varchar(255) not null,
	genero varchar(255) not null,
	telefono varchar(8) not null,
	password varchar(255) not null,
	primary key (id)
);


--FKEY ENTRE USUARIO Y EXPEDIENTE
alter table usuario 
add constraint usuario_id_expediente_fkey foreign key (id_expediente)
references expediente (id) match simple;

--FKEY EN TABLA USUARIO POR RELACION CON ROL
alter table usuario
add constraint usuario_id_rol_fkey foreign key (id_rol)
references rol (id) match simple;

create table solicitud_muestra_medica (
	id SERIAL,
    id_usuario integer,
	id_tipo_solicitante integer default 1,
	id_tipo_solicitud integer not null,
	id_estado_solicitud integer null,
	descripcion_solicitud_muestra_medica varchar(2000) null,
	fecha_creacion_solicitud date not null,
	dias_vencimiento_solicitud integer null,
	primary key (id)
);

alter table solicitud_muestra_medica
add constraint solicitud_muestra_medica_id_usuario_fkey foreign key (id_usuario)
references usuario (id) match simple;

alter table solicitud_muestra_medica
add constraint solicitud_muestra_medica_id_tipo_solicitante_fkey foreign key (id_tipo_solicitante)
references tipo_solicitante (id) match simple;

alter table solicitud_muestra_medica
add constraint solicitud_muestra_medica_id_tipo_solicitud_fkey foreign key (id_tipo_solicitud)
references tipo_solicitud (id) match simple;

alter table solicitud_muestra_medica
add constraint solicitud_muestra_medica_id_estado_solicitud_fkey foreign key (id_estado_solicitud)
references estado_solicitud (id) match simple;

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

alter table items 
add constraint items_tipo_items_fkey foreign key (id_tipo_items)
references tipo_items (id) match simple;

alter table items 
add constraint items_solicitud_muestra_medica_fkey foreign key (id_solicitud_muestra_medica)
references solicitud_muestra_medica (id) match simple;

--TABLA DE DOCUMENTOS ADJUNTOS
create table documento_muestra_adjunto (
	id SERIAL,
	formato_documento varchar(4),
	tamaño_documento varchar (4),
	primary key (id)
);

create table muestra_medica (
	id SERIAL,
	id_solicitud_muestra_medica integer not null,
	id_presentacion_muestra integer not null,
	id_tipo_muestra integer not null,
	id_documento_muestra_adjunto integer null,
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

alter table muestra_medica
add constraint muestra_medica_id_documento_muestra_adjunto_fkey foreign key (id_documento_muestra_adjunto)
references documento_muestra_adjunto (id) match simple;

alter table muestra_medica
add constraint muestra_medica_id_unidad_medida_fkey foreign key (id_unidad_medida)
references unidad_medida (id) match simple;

create table documento_analisis(
	id SERIAL,
	id_muestra_medica integer,
	primary key (id)
);

alter table documento_analisis 
add constraint documento_analisis_id_muestra_medica_fkey foreign key (id_muestra_medica)
references muestra_medica (id) match simple;

--create table bitacora

--INGRESO DE DATOS A CATOLOGO DE TIPO DE SOPORTE
insert into tipo_soporte(nombre, descripcion,fecha_creacion,creado_por) 
values
('SM','Solicitud Médica','2023-10-15 22:30:00','Jonathan'),
('ET','Examen Externo','2023-10-15 22:30:00','Jonathan'),
('FP','Factura','2023-10-15 22:30:00','Jonathan'),
('HO','Hoja Oficio','2023-10-15 22:30:00','Jonathan"');

--CREACION DE RELACION DE EXPEDIENTE A TIPO DE SOPORTE
alter table expediente 
add constraint expediente_tipo_soporte_fkey foreign key (id_tipo_soporte)
references tipo_soporte (id) match simple;

--CREACION DE EXPEDIENTE DE PRUEBA
insert into expediente (nit_cliente,direccion,id_tipo_soporte)
values
('99914190','Ciudad Quetazal, San Juan Sac.',4);

--CREACION DE USUARIO DE PRUEBA
insert into usuario (id_expediente,nombres,apellidos,email,genero,telefono,password)
values
(1,'Jonathan','Guamuch','joelmorales0598@gmail.com','Masculino','46740797','prueba123');

--INGRESO DE DATOS A CATOLOGO DE ROL
insert into rol(nombre, descripcion,fecha_creacion,creado_por)
values
    ('REV','Revisor','2023-10-18 22:30:00','Jonathan'),
    ('TEC','Tecnico','2023-10-18 22:30:00','Jonathan'),
    ('CEN','Centralizador','2023-10-18 22:30:00','Jonathan'),
    ('ALT','Analista','2023-10-18 22:30:00','Jonathan"');

