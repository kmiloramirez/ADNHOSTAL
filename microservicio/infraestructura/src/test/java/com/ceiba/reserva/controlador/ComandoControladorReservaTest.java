package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.habitacion.controlador.ComandoControladorHabitacion;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import com.ceiba.reserva.testdatabuilder.ComandoReservaTestDataBuilder;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorHabitacion.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorReservaTest {


    private static final String COMANDO_CONTROLADOR_RESERVA = "/reserva";


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crear() throws Exception {

        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder().build();
        MockHttpServletRequestBuilder request = post(COMANDO_CONTROLADOR_RESERVA).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva));

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void crearCuandoLaHabitacionNoEstaDisponible() throws Exception {

        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder().build();
        MockHttpServletRequestBuilder request1 = post(COMANDO_CONTROLADOR_RESERVA).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva));
        MockHttpServletRequestBuilder request2 = post(COMANDO_CONTROLADOR_RESERVA).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva));

        MvcResult result1 = mockMvc.perform(request1).andExpect(status().isOk()).andReturn();
        MvcResult result2 = mockMvc.perform(request2).andExpect(status().isBadRequest()).andReturn();

        assertThat(result1.getResponse().getStatus()).isEqualTo(200);
        assertThat(result2.getResponse().getStatus()).isEqualTo(400);
    }

    @Test
    void actualizarEstado() throws Exception {

        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder()
                .conEstadoReserva(EstadoReserva.ACTIVA.getEstado()).build();
        MockHttpServletRequestBuilder request = patch(COMANDO_CONTROLADOR_RESERVA).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva));

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void eliminarReserva() throws Exception {

        int numeroReserva = 1;
        MockHttpServletRequestBuilder request = delete(COMANDO_CONTROLADOR_RESERVA).contentType(MediaType.APPLICATION_JSON)
                .param("numeroReserva", String.valueOf(numeroReserva));

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void cobrarReserva() {
    }
}