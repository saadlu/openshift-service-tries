package net.saad.tries.servicetries.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import net.saad.tries.servicetries.model.GiftToStore;
import net.saad.tries.servicetries.repository.GiftsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GiftsPostControllerTest {

    @InjectMocks
    private GiftsPostController giftsPostController;

    @Mock
    private GiftsRepository giftsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        RestAssuredMockMvc.standaloneSetup(giftsPostController);
    }

    @Test
    void testPost() {
        // @formatter:off
        given().
                contentType("application/json").
                body("{\"name\": \"toy\"}").
        when().
                post("/gifts").
        then().
                statusCode(201);
        // @formatter:on

        ArgumentCaptor<GiftToStore> giftArgumentCaptor = ArgumentCaptor.forClass(GiftToStore.class);
        verify(giftsRepository).save(giftArgumentCaptor.capture());
        assertThat(giftArgumentCaptor.getValue())
                .isEqualToIgnoringGivenFields(new GiftToStore("toy"), "id");
    }
}
