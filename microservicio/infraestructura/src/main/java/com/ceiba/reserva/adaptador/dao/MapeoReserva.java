package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {
    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        int numeroReserva = resultSet.getInt("numeroReserva");
        String nombre = resultSet.getString("nombre");
        LocalDateTime fechaEntrada = extraerLocalDateTime(resultSet,"fechaEntrada");
        String numeroHabitacion = resultSet.getString("numeroHabitacion");
        LocalDateTime fechaSalida = extraerLocalDateTime(resultSet,"fechaSalida");
        LocalDate fechaRegistro = extraerLocalDate(resultSet,"fechaRegistro");
        double costoTotal = resultSet.getDouble("costoTotal");
        String estadoReserva = resultSet.getString("estadoReserva");

        return new DtoReserva(numeroReserva,nombre,fechaEntrada,numeroHabitacion,fechaSalida,fechaRegistro,
                costoTotal,estadoReserva);
    }
}