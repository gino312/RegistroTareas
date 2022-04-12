package com.RegistroTareasAPI.RegistroTareas.controller;

import com.RegistroTareasAPI.RegistroTareas.model.Tarea;
import com.RegistroTareasAPI.RegistroTareas.service.TareaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest( value = TareaController.class)
class TareaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TareaService tareaService;

    Tarea MockTarea = new Tarea("Tarea1",true);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTareas() throws Exception{
        List<Tarea> tareaList = new ArrayList<>();
        //Generacion de identificador ya que este en la practica se genera en la Base de Datos
        MockTarea.setIdentificador(1);
        tareaList.add(MockTarea);

        when(tareaService.getAllTareas()).thenReturn(tareaList);

        RequestBuilder mockRequestBuilder = MockMvcRequestBuilders.get("/tarea/getAll");

        MvcResult resultado = mockMvc.perform(mockRequestBuilder).andReturn();
        System.out.println("Content: " + resultado.getResponse().getContentAsString());

    }

    @Test
    void add() throws Exception{

        RequestBuilder mockRequestBuilder = MockMvcRequestBuilders.post("/tarea/add")
                .param("descripcion","TareaTest1")
                .param("vigente", String.valueOf(true));
        MvcResult resultado = mockMvc.perform(mockRequestBuilder).andReturn();
        System.out.println("Status: " + resultado.getResponse().getStatus());
        System.out.println("Message: " + resultado.getResponse().getErrorMessage());

        String resultadoEsperado = "201";

        JSONAssert.assertEquals(resultadoEsperado, String.valueOf(resultado.getResponse().getStatus()), false);
    }

    @Test
    void update() throws Exception{

        when(tareaService.getTarea(1)).thenReturn(MockTarea);

        when(tareaService.guardaTarea(MockTarea)).thenReturn(MockTarea);

        RequestBuilder mockRequestBuilder = MockMvcRequestBuilders.put("/tarea/update")
                .param("identificador","1")
                .param("descripcion","TareaTest1")
                .param("vigente", String.valueOf(false));
        MvcResult resultado = mockMvc.perform(mockRequestBuilder).andReturn();
        System.out.println("Status: " + resultado.getResponse().getStatus());
        System.out.println("Message: " + resultado.getResponse().getErrorMessage());

        String resultadoEsperado = "202";

        JSONAssert.assertEquals(resultadoEsperado, String.valueOf(resultado.getResponse().getStatus()), false);
    }

    @Test
    void delete() throws Exception {

        when(tareaService.borraTarea(1)).thenReturn(true);

        RequestBuilder mockRequestBuilder = MockMvcRequestBuilders.delete("/tarea/delete")
                .param("identificador","1");
        MvcResult resultado = mockMvc.perform(mockRequestBuilder).andReturn();
        System.out.println("Status: " + resultado.getResponse().getStatus());
        System.out.println("Message: " + resultado.getResponse().getErrorMessage());

        String resultadoEsperado = "202";

        JSONAssert.assertEquals(resultadoEsperado, String.valueOf(resultado.getResponse().getStatus()), false);
    }
}