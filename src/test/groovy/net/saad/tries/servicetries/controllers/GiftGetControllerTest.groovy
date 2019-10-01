package net.saad.tries.servicetries.controllers

import io.restassured.module.mockmvc.RestAssuredMockMvc
import net.saad.tries.servicetries.model.GiftToStore
import net.saad.tries.servicetries.repository.GiftsRepository
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.json.JSONException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.skyscreamer.jsonassert.JSONCompare
import org.skyscreamer.jsonassert.JSONCompareMode
import org.skyscreamer.jsonassert.JSONCompareResult

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when

class GiftGetControllerTest {
    @InjectMocks
    private GiftGetController giftGetController

    @Mock
    private GiftsRepository giftsRepository



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this)

        RestAssuredMockMvc.standaloneSetup(giftGetController)
    }

    @Test
    void testGet() {
        Mockito.when(giftsRepository.findAll())
                .thenReturn(Arrays.asList(
                        new GiftToStore("toy"),
                        new GiftToStore("shirt"),
                        new GiftToStore("caps")))

        // @formatter:off
        when().
                get("/gifts").
        then().
                statusCode(200).
                body(JsonMatcher.jsonMatcher(
                        """
                        [
                          {
                            "name": "toy"
                          },
                          {
                            "name": "shirt"
                          },
                          {
                            "name": "caps"
                          }
                        ]
                        """
                ))
        // @formatter:on

    }

    private static class JsonMatcher extends BaseMatcher<String> {

        private String expected

        private JsonMatcher(String expected) {
            this.expected = expected
        }

        @Override
        boolean matches(Object item) {
            try {
                JSONCompareResult jsonCompareResult = JSONCompare.compareJSON(expected,
                        item.toString(), JSONCompareMode.LENIENT)

                return jsonCompareResult.passed()

            } catch (JSONException e) {
                return false
            }

        }

        @Override
        void describeTo(Description description) {
            description.appendText(expected)
        }

        static JsonMatcher jsonMatcher(String expected) {
            return new JsonMatcher(expected)
        }
    }
}
