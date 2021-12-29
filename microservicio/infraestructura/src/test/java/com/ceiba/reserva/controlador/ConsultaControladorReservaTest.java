package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorReserva.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorReservaTest {

    private static final String CONSULTA_CONTROLADOR_RESERVA = "/reserva";
    private static final String CONSULTA_CONTROLADOR_RESERVA_LISTAR_RESERVAS = "/reserva/lista-reservas";
    private static final String CONSULTA_CONTROLADOR_RESERVA_ESTADOS = "/reserva/estados";
    private static final String CONSULTA_CONTROLADOR_RESERVA_RESERVAS_ESTADO = "/reserva/reserva-estado";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void obtenerReserva() throws Exception {
        int numeroReserva = 1;
        MockHttpServletRequestBuilder request = get(CONSULTA_CONTROLADOR_RESERVA).contentType(MediaType.APPLICATION_JSON)
                .param("numeroReserva", String.valueOf(numeroReserva));

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        DtoReserva reservaEncontrada = objectMapper.readValue(result.getResponse().getContentAsByteArray(),
                DtoReserva.class);
        LocalDateTime fechaEntradaEsperada = LocalDateTime.of(2021, 12, 24, 15, 00, 00, 0);
        LocalDateTime fechaSalidaEsperada = LocalDateTime.of(2021, 12, 25, 12, 00, 00, 0);
        LocalDate fechaRegistroEsperada = LocalDate.of(2021, 12, 23);
        assertEquals(1, reservaEncontrada.getNumeroReserva());
        assertEquals("Juan", reservaEncontrada.getNombre());
        assertEquals(fechaEntradaEsperada, reservaEncontrada.getFechaEntrada());
        assertEquals("101", reservaEncontrada.getNumeroHabitacion());
        assertEquals(fechaSalidaEsperada, reservaEncontrada.getFechaSalida());
        assertEquals(fechaRegistroEsperada, reservaEncontrada.getFechaRegistro());
        assertEquals(100000, reservaEncontrada.getCostoTotal());
        assertEquals("reservado", reservaEncontrada.getEstadoReserva());

    }

    @Test
    void obtenerReservaCuandoNoExisteReserva() throws Exception {
        int numeroReserva = 2;
        MockHttpServletRequestBuilder request = get(CONSULTA_CONTROLADOR_RESERVA).contentType(MediaType.APPLICATION_JSON)
                .param("numeroReserva", String.valueOf(numeroReserva));

        MvcResult result = mockMvc.perform(request).andExpect(status().isInternalServerError()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(500);

    }

    @Test
    void listar() throws Exception {

        MockHttpServletRequestBuilder request = get(CONSULTA_CONTROLADOR_RESERVA_LISTAR_RESERVAS);

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

        List<DtoReserva> listaReservas = objectMapper.readValue(result.getResponse().getContentAsByteArray(),
                new TypeReference<List<DtoReserva>>() {
                });

        assertEquals(1, listaReservas.size());

    }

    @Test
    void listarEstados() throws Exception {

        MockHttpServletRequestBuilder request = get(CONSULTA_CONTROLADOR_RESERVA_ESTADOS);

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

        List<String> listaEstados = objectMapper.readValue(result.getResponse().getContentAsByteArray(),
                new TypeReference<List<String>>() {
                });

        assertEquals(3, listaEstados.size());
    }

    @Test
    void listarReservasEstado() throws Exception {
        String estado = EstadoReserva.RESEVADO.getEstado();
        MockHttpServletRequestBuilder request = get(CONSULTA_CONTROLADOR_RESERVA_RESERVAS_ESTADO)
                .contentType(MediaType.APPLICATION_JSON).param("estado", estado);

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

        List<DtoReserva> listaReservas = objectMapper.readValue(result.getResponse().getContentAsByteArray(),
                new TypeReference<List<DtoReserva>>() {
                });

        assertEquals(1, listaReservas.size());
    }
}