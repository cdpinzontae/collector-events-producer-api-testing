package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PublicApiTest {

    @Test
    public void testPostToPublicApi() {
        // Definir la URL base
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Crear el cuerpo de la solicitud
        String requestBody = """
            {
              "title": "foo",
              "body": "bar",
              "userId": 1
            }
            """;

        // Enviar la solicitud POST
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .assertThat()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .extract().response();

        System.out.println("Response: " + response.asPrettyString());
    }
}
