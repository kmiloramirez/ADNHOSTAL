package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {

    @SqlStatement(namespace = "reserva", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "habitacion", value = "existePorNumero")
    private static String sqlExisteHabitacionPorNumero;

    @SqlStatement(namespace = "reserva", value = "disponibilidadHabitacion")
    private static String sqlExisteDisponibilidadHabitacion;

    @SqlStatement(namespace = "reserva", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "reserva", value = "existeReserva")
    private static String sqlExisteReserva;

    @SqlStatement(namespace = "reserva", value = "eliminar")
    private static String sqlEliminar;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Reserva reserva) {
        return this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
    }

    @Override
    public boolean disponibilidadHabitacion(String numeroHabitacion, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("numeroHabitacion", numeroHabitacion);
        paramSource.addValue("fechaEntrada", fechaEntrada);
        paramSource.addValue("fechaSalida", fechaSalida);

        return !(this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteDisponibilidadHabitacion, paramSource, Boolean.class));
    }

    @Override
    public void actualizar(Reserva reserva) {
        this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlActualizar);
    }

    @Override
    public boolean existeReserva(int numeroReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("numeroReserva", numeroReserva);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteReserva, paramSource, Boolean.class);
    }

    @Override
    public void eliminar(int numeroReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("numeroReserva", numeroReserva);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }


}
