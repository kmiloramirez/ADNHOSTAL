package com.ceiba.habitacion.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorHabitacion.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorHabitacionTest {

    private static final String LISTA_HABITACIONES = "/habitacion";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void listar() throws Exception {

        MockHttpServletRequestBuilder request = get(LISTA_HABITACIONES);

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        List<DtoHabitacion> listaHabitaciones = objectMapper.readValue(result.getResponse().getContentAsByteArray(),
                new TypeReference<List<DtoHabitacion>>() {
                });
        DtoHabitacion dtoHabitacion = listaHabitaciones.get(0);
        assertEquals(1, dtoHabitacion.getId());
        assertEquals("101", dtoHabitacion.getNumero());
        assertEquals(1, dtoHabitacion.getCamas());
        assertEquals(100000, dtoHabitacion.getPrecio());
        assertEquals("prueba", dtoHabitacion.getDescripcion());


    }
}