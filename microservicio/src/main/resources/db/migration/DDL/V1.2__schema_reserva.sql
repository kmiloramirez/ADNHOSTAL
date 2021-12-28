create table reserva (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 fechaEntrada datetime not null,
 numeroHabitacion varchar(100) not null,
 fechaSalida datetime not null,
 fechaRegistro datetime not null,
 costoTotal double not null,
 estadoReserva varchar(100) not null,
 primary key (id)
);


