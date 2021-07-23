drop database softIsdel;
create database softIsdel;
use softIsdel;

CREATE TABLE IF NOT EXISTS `softIsdel`.`users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `enabled` BIT(1) NULL DEFAULT NULL,
  `password` VARCHAR(60) NULL DEFAULT NULL,
  `password1` VARCHAR(60) NULL DEFAULT NULL,
  `username` VARCHAR(30) NULL DEFAULT NULL,
  `fecha_alta` DATE DEFAULT NULL,
  `email` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_r43af9ap4edm43mmtq01oddj6` (`username` ASC));


CREATE TABLE IF NOT EXISTS `softIsdel`.`authorities` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UKrimuuii4fm3j9qt8uupyiy8nd` (`user_id` ASC),
  CONSTRAINT `FKk91upmbueyim93v469wj7b2qh`
    FOREIGN KEY (`user_id`)
    REFERENCES `softIsdel`.`users` (`id`));

create table roles(
	id_rol int not null primary key auto_increment,
    rol varchar(255) not null
);

alter table authorities add column fk_id_rol int null;
alter table authorities add constraint fk_id_rol foreign key
(fk_id_rol) references roles(id_rol);

create table alumnos(
	id_alumno int not null primary key auto_increment,
    nombre varchar(255) not null,
    apellido varchar(255) not null,
    dni varchar(255) not null,
    direccion varchar(255) not null,
    fecha_nacimiento date not null,
    barrio varchar(255) not null,
    tel_1 varchar(255) not null,
    cel_1 varchar(255),
	tel_2 varchar(255),
    cel_2 varchar(255),
    nro_alumno varchar(255) not null,
    localidad varchar(255) not null
);

create table responsables(
	id_responsable int not null primary key auto_increment,
    nombre varchar(255) not null,
    apellido varchar(255) not null,
    dni varchar(255) not null,
    direccion varchar(255) not null,
    fecha_nacimiento date not null,
    barrio varchar(255) not null,
    tel_1 varchar(255),
    cel_1 varchar(255) not null,
	tel_2 varchar(255),
    cel_2 varchar(255),
    localidad varchar(255) not null
);

create table inscripciones(
	id_inscripcion int not null primary key auto_increment,
    fecha_inscripcion date not null,
    fecha_inicio_clases1 date not null,
    fecha_fin_clases1 date,
    horario_clases1 varchar(255) not null,
    dias_de_cursada_clases1 varchar(255) not null,
    valor_inscripcion double not null,
    abonado double,
    saldo_pendiente double,
    pago_acordado_cuota_1 date not null
);

create table periodos(
	id_periodo int not null primary key auto_increment, 
    periodo varchar(255) not null
);

insert into periodos(periodo) values
('Primer Período'),
('Segundo Período'),
('Tercer Período');

create table planes_de_inversion(
	id_plan_de_inversion int not null primary key auto_increment,
    costo_total double not null, 
    cantidad_cuotas_1 int not null,
    cantidad_cuotas_2 int not null,
    valor_cuota_1 double not null,
    valor_cuota_2 double not null
);

create table cuotas(
	id_cuota int not null primary key auto_increment,
    vencimiento date not null,
    saldo_pendiente float not null,
    grupo int not null,
    pagado boolean
);

create table importes_abonados_cuotas(
	id_importe_abonado_cuota int not null primary key auto_increment,
    fecha date not null,
    importe_abonado float not null,
    observacion varchar(255) not null
);

create table importes_abonados_inscripciones(
	id_importe_abonado_inscripcion int not null primary key auto_increment,
    fecha date not null,
    importe_abonado float not null,
    observacion varchar(255) not null
);


create table estados_cuotas(
	id_estado_cuota int not null primary key auto_increment,
    estado_cuota varchar(255) not null
);

insert into estados_cuotas(estado_cuota) values
("Al día"),
("Pagada"),
("Vencida");

/*
insert into cuotas(importe_abonado, fecha, vencimiento, demora) values
(2500, '2021-03-17', '2021-03-10', true),
(2600, '2021-03-17', '2021-04-10', true),
(2700, '2021-04-17', '2021-04-10', true),
(2800, '2021-04-17', '2021-05-10', true);
*/

create table cursos(
	id_curso int not null primary key auto_increment,
    nombre varchar(255) not null
);

create table categorias_cursos(
	id_categoria_curso int not null primary key auto_increment,
    categoria varchar(255) not null
);

insert into categorias_cursos(categoria) values
("Área Técnica"),
("Área Informática"),
("Área Estética"),
("Talleres y Perfeccionamiento");

alter table alumnos add column fk_id_responsable int null;

alter table cursos add column fk_id_categoria_curso int null;

alter table inscripciones add column fk_id_alumno int null;
alter table inscripciones add column fk_id_curso int null;
alter table inscripciones add column fk_id_usuario BIGINT(20) null;
/*
alter table inscripciones add column fk_id_asesor int null;
*/

alter table importes_abonados_cuotas add column fk_id_cuota int null;
alter table importes_abonados_inscripciones add column fk_id_insc int null;
alter table cuotas add column fk_id_plan_de_inversion int null;
alter table cuotas add column fk_id_estado_cuota int null;
alter table cuotas add column cobrar_ajuste boolean;

alter table planes_de_inversion add column fk_id_inscripcion int null;
alter table planes_de_inversion add column fk_id_periodo int null;

alter table alumnos add constraint fk_id_responsable foreign key 
(fk_id_responsable) references responsables(id_responsable);

alter table cursos add constraint fk_id_categoria_curso foreign key 
(fk_id_categoria_curso) references categorias_Cursos(id_categoria_curso);

alter table inscripciones add constraint fk_id_alumno foreign key 
(fk_id_alumno) references alumnos(id_alumno);

alter table inscripciones add constraint fk_id_curso foreign key 
(fk_id_curso) references cursos(id_curso);

alter table inscripciones add constraint fk_id_usuario foreign key 
(fk_id_usuario) references users(id);

/*
alter table inscripciones add foreign key (fk_id_asesor) references asesores(id_asesor);
*/

alter table importes_abonados_inscripciones add constraint fk_id_insc foreign key 
(fk_id_insc) references inscripciones(id_inscripcion);

alter table importes_abonados_cuotas add constraint fk_id_cuota foreign key 
(fk_id_cuota) references cuotas(id_cuota);

alter table cuotas add constraint fk_id_planes_de_inversion foreign key 
(fk_id_plan_de_inversion) references planes_de_inversion(id_plan_de_inversion);

alter table cuotas add constraint fk_id_estado_cuota foreign key 
(fk_id_estado_cuota) references estados_cuotas(id_estado_cuota);


alter table planes_de_inversion add constraint fk_id_inscripcion foreign key 
(fk_id_inscripcion) references inscripciones(id_inscripcion);

alter table planes_de_inversion add constraint fk_id_periodo foreign key 
(fk_id_periodo) references periodos(id_periodo);

INSERT INTO roles (rol) VALUES
('ROLE_USER'),
('ROLE_ADMIN'),
('ROLE_SECRETARIA'),
('ROLE_ASESOR');

INSERT INTO `users` (username, password, password1, enabled, fecha_alta, email) VALUES ('admin','$2a$10$SQBb5TjkreSbP6FHZf8z1OrGSL8AOzJlBkzqGorrYQEGzomnQcmFi','12345',1,'2021-06-28', 'admin@isdel.com.ar');

INSERT INTO `authorities` (user_id, fk_id_rol) VALUES (1,2);

select * from responsables;
select * from alumnos;
select * from categorias_cursos;
select * from cursos;
select * from inscripciones;
select * from planes_de_inversion;
select * from periodos;
select * from importes_abonados_cuotas;
select * from importes_abonados_inscripciones;
select * from cuotas;
select * from estados_cuotas;
select * from users;
select * from authorities;