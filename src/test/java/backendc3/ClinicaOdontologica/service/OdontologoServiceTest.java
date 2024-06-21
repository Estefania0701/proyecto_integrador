package backendc3.ClinicaOdontologica.service;

import backendc3.ClinicaOdontologica.entity.Odontologo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void guardar() throws Exception {
        Odontologo odontologo = new Odontologo("Andrea", "Rúa", "4534jsdf");
        String odontologoJson = new ObjectMapper().writeValueAsString(odontologo);

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(odontologoJson)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

}