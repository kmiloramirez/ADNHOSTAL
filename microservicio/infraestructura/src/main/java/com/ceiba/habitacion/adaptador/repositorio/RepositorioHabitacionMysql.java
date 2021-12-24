package com.ceiba.habitacion.adaptador.repositorio;

import com.ceiba.habitacion.modelo.entidad.Habitacion;
import com.ceiba.habitacion.puerto.repositorio.RepositorioHabitacion;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioHabitacionMysql implements RepositorioHabitacion {

    @SqlStatement(namespace = "habitacion", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "habitacion", value = "existePorNumero")
    private static String sqlExistePorNumero;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioHabitacionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Habitacion habitacion) {
        return this.customNamedParameterJdbcTemplate.crear(habitacion, sqlCrear);
    }

    @Override
    public boolean existe(String numero) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("numero", numero);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePorNumero, paramSource, Boolean.class);
    }
}
