insert into reserva
    (nombre,
     fechaEntrada,
     numeroHabitacion,
     fechaSalida,
     fechaRegistro,
     costoTotal,
     estadoReserva)
values (:nombre,
        :fechaEntrada,
        :numeroHabitacion,
        :fechaSalida,
        :fechaRegistro,
        :costoTotal,
        :estadoReserva)