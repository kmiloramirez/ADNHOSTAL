create table habitacion (
 id int(11) not null auto_increment,
 numero varchar(100) not null unique,
 camas int not null,
 precio double not null,
 descripcion varchar(100) not null,
 primary key (id)
);
