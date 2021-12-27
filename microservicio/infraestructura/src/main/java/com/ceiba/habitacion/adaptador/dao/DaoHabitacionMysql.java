package com.ceiba.habitacion.adaptador.dao;

import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import com.ceiba.habitacion.puerto.dao.DaoHabitacion;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoHabitacionMysql implements DaoHabitacion {

    @SqlStatement(namespace = "habitacion", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "habitacion", value = "precioHabitacion")
    private static String sqlPrecioHabitacion;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoHabitacionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoHabitacion> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListar, new MapeoHabitacion());
    }

    @Override
    public double obtenerPrecioHabitacion(String numero) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("numero", numero);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlPrecioHabitacion, paramSource, double.class);
    }
}
