package net.saad.tries.servicetries.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import net.saad.tries.servicetries.model.GiftToStore;
import net.saad.tries.servicetries.repository.GiftsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GiftGetController.class)
class GiftGetControllerAnotherTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GiftsRepository giftsRepository;

    @Test
    void testGet() throws Exception {

        Mockito.when(giftsRepository.findAll()).thenReturn(Arrays.asList(new GiftToStore("toy")));

        mockMvc.perform(get("/gifts"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"toy\"}]"));
    }
}