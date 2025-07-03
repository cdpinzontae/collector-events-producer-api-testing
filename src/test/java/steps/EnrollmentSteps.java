package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.config.HttpClientConfig;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class EnrollmentSteps {

    private Response response;
    private Map<String, Object> payload = new HashMap<>();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://collector-events-producer.uat.api.loyalty.com:34213/collector-events-producer/enrollment";

        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 10000)
                        .setParam("http.socket.timeout", 10000));
    }

    @Given("the enrollment endpoint is available")
    public void the_enrollment_endpoint_is_available() {
        // Ya está configurado en @BeforeAll
    }

    @Given("I prepare the payload with all mandatory fields and a valid collector number")
    public void i_prepare_the_payload_with_all_mandatory_fields_and_a_valid_collector_number() {
        payload.put("emailAddress", "cdiazpinzon@loyalty.com");
        payload.put("language", "es");
        payload.put("scheme", "basic");
        payload.put("channel", "web");
        payload.put("emailOptIn", true);
        payload.put("cardNumber", "80000001155"); // Debe pasar MOD11
        payload.put("timestamp", "2025-05-12T15:30:00Z");
    }

    @Given("I prepare the payload with all mandatory fields except {string}")
    public void i_prepare_the_payload_with_all_mandatory_fields_except(String missingField) {
        i_prepare_the_payload_with_all_mandatory_fields_and_a_valid_collector_number();
        payload.remove(missingField);
    }

    @Given("I prepare the payload with an invalid collector number")
    public void i_prepare_the_payload_with_an_invalid_collector_number() {
        i_prepare_the_payload_with_all_mandatory_fields_and_a_valid_collector_number();
        payload.put("cardNumber", "12345678901"); // No válido (MOD11 falla)
    }

    @Given("I prepare the payload with issuerCode as null")
    public void i_prepare_the_payload_with_issuer_code_as_null() {
        i_prepare_the_payload_with_all_mandatory_fields_and_a_valid_collector_number();
        payload.put("issuerCode", null); // explícitamente null
    }

    @When("I send the enrollment request")
    public void i_send_the_enrollment_request() {
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given()
                .header("correlationId", "test-correlation-id")
                .header("originClient", "airmiles:web:digighost")
                .contentType("application/json")
                .body(payload);

        response = request.post("");
        System.out.println("RESPONSE BODY:\n" + response.prettyPrint());
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatus) {
        assertEquals(expectedStatus.intValue(), response.statusCode());
    }

    @Then("the response body should contain message {string}")
    public void the_response_body_should_contain_message(String expectedMessage) {
        String actualMessage = response.jsonPath().getString("message");
        assertEquals(expectedMessage, actualMessage);
    }

    @Then("the response body should contain error message {string}")
    public void the_response_body_should_contain_error_message(String expectedMessage) {
        Map<String, Object> error = response.jsonPath().getMap("error");
        String actualMessage = (String) error.get("message");
        assertEquals(expectedMessage, actualMessage);
    }
}
