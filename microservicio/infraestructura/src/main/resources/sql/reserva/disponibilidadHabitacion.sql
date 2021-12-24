SELECT count(1)
FROM reserva
WHERE numeroHabitacion = :numeroHabitacion
  and (:fechaEntrada BETWEEN fechaEntrada and fechaSalida or :fechaSalida BETWEEN fechaEntrada and fechaSalida)

