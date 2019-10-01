package net.saad.tries.servicetries.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeadControllerTest {

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(new HeadController());
    }

    @Test
    void testHead() {
       when().head().then().statusCode(200);
    }

}
