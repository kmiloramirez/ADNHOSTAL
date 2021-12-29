package com.ceiba.habitacion.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.habitacion.comando.ComandoHabitacion;
import com.ceiba.habitacion.puerto.dao.DaoHabitacion;
import com.ceiba.habitacion.testdatabuilder.ComandoHabitacionTesDataBuider;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorHabitacion.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorHabitacionTest {

    private static final String CREAR_HABITACION = "/habitacion";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DaoHabitacion daoHabitacion;

    @Test
    void crear() throws Exception {

        ComandoHabitacion comandoHabitacion = new ComandoHabitacionTesDataBuider().conNumero("102").build();

        MockHttpServletRequestBuilder request = post(CREAR_HABITACION).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoHabitacion));

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

        double precio = daoHabitacion.obtenerPrecioHabitacion("102");
        assertEquals(100000.0, precio);

    }
}