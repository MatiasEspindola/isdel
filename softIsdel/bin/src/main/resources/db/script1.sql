drop database softIsdel;
create database softIsdel;
use softIsdel;

CREATE TABLE IF NOT EXISTS `softIsdel`.`users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `enabled` BIT(1) NULL DEFAULT NULL,
  `password` VARCHAR(60) NULL DEFAULT NULL,
  `username` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_r43af9ap4edm43mmtq01oddj6` (`username` ASC));


CREATE TABLE IF NOT EXISTS `softIsdel`.`authorities` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UKrimuuii4fm3j9qt8uupyiy8nd` (`user_id` ASC, `authority` ASC),
  CONSTRAINT `FKk91upmbueyim93v469wj7b2qh`
    FOREIGN KEY (`user_id`)
    REFERENCES `softIsdel`.`users` (`id`));



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
    fecha_inicio_clases2 date,
    horario_clases1 varchar(255) not null,
    horario_clases2 varchar(255),
    dias_de_cursada_clases1 varchar(255) not null,
    dias_de_cursada_clases2 varchar(255),
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
('Primer Per??odo'),
('Segundo Per??odo'),
('Tercer Per??odo');

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
("Al d??a"),
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
("??rea T??cnica"),
("??rea Inform??tica"),
("??rea Est??tica"),
("Talleres y Perfeccionamiento");

alter table alumnos add column fk_id_responsable int null;

alter table cursos add column fk_id_categoria_curso int null;

alter table inscripciones add column fk_id_alumno int null;
alter table inscripciones add column fk_id_curso int null;
/*
alter table inscripciones add column fk_id_asesor int null;
*/

alter table importes_abonados_cuotas add column fk_id_cuota int null;
alter table importes_abonados_inscripciones add column fk_id_insc int null;
alter table cuotas add column fk_id_plan_de_inversion int null;
alter table cuotas add column fk_id_estado_cuota int null;

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

INSERT INTO `users` (username, password, enabled) VALUES ('admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);

INSERT INTO `authorities` (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO `authorities` (user_id, authority) VALUES (3,'ROLE_SECRETARIA');
INSERT INTO `authorities` (user_id, authority) VALUES (4,'ROLE_ASESOR');

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

update inscripciones set inscripciones.saldo_pendiente = 1000 where id_inscripcion = 1;

delete from importes_abonados_inscripciones where id_importe_abonado_inscripcion > 0;
