package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoReservaMysql implements DaoReserva {

    private static final String NO_SE_ENCONTRO_EL_NUMERO_RESERVA = "No se encontró la reserva con el numero: %s";



    @SqlStatement(namespace = "reserva", value = "obtenerReserva")
    private static String sqlObtenerReserva;
    @SqlStatement(namespace = "reserva", value = "listar")
    private static String sqlListar;
    @SqlStatement(namespace = "reserva", value = "reservaEstado")
    private static String sqlReservaEstado;
    @SqlStatement(namespace = "reserva", value = "obtenerEstadoReserva")
    private static String sqlObtenerEstadoReserva;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public DtoReserva obtenerReserva(int numeroReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("numeroReserva", numeroReserva);
        List<DtoReserva> reservas =this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerReserva, paramSource, new MapeoReserva());
        return reservas.stream().findFirst().orElseThrow(() -> new ExcepcionTecnica(
                String.format(NO_SE_ENCONTRO_EL_NUMERO_RESERVA, numeroReserva)));
    }

    @Override
    public List<DtoReserva> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListar, new MapeoReserva());
    }

    @Override
    public List<DtoReserva> consultarReservaEstado(String estado) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("estadoReserva", estado);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlReservaEstado, paramSource, new MapeoReserva());
    }

    @Override
    public String obtenerEstadoReservaReserva(int numeroReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("numeroReserva", numeroReserva);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerEstadoReserva, paramSource, String.class);
    }
}
