package com.ceiba.habitacion.adaptador.dao;

import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoHabitacion implements RowMapper<DtoHabitacion>, MapperResult {

    @Override
    public DtoHabitacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        String numero = resultSet.getString("numero");
        int camas = resultSet.getInt("camas");
        double precio = resultSet.getDouble("precio");
        String descripcion = resultSet.getString("descripcion");

        return new DtoHabitacion(id,numero,camas,precio,descripcion);
    }
}


