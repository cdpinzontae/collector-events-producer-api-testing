package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import io.restassured.builder.RequestSpecBuilder;
import static io.restassured.RestAssured.given;


import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredExampleSteps {

    private Response response;
    private RequestSpecification request;
    private static final String BASE_PATH = "https://jsonplaceholder.typicode.com/todos";
   // private String ENDPOINT_GET_ALL_PLANETS = "https://swapi.co/api/planets";
    private String ENDPOINT_GET_ALL_PLANETS = "us/90210";

    private static RequestSpecification requestSpec;
    private ValidatableResponse json;

    @Before
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();
    }


    @Given("^The endpoint is already configured$")
    public void the_endpoint_is_already_configured() {
        System.out.println("the_endpoint_is_already_configured");
             response =    given().
                spec(requestSpec)
                .when()
                .get(ENDPOINT_GET_ALL_PLANETS);
    }

    @When("^I input a valid userId \"([^\"]*)\"$")
    public void i_input_a_valid_userId(String userId) {

        json = response
                .then()
                .statusCode(200);

    }

    @Then("^I should have the status code \"([^\"]*)\"$")
    public void i_should_have_the_status_code(String statusCode) {
        System.out.println("the_endpoint_is_already_configured");
        //response.then().statusCode(Integer.parseInt(statusCode));
        response.then().log().body();
        response.then().assertThat().body("places[0].state", equalTo("California"));
    }

    /*
    @Then("^content type should be in \"([^\"]*)\" format$")
    public void content_type_should_be_in_format(String format) {

        if(format.equals("JSON")){
            response.then().assertThat().contentType(ContentType.JSON)
                    .and()
                    .body(matchesJsonSchemaInClasspath("user-schema.json"));
        }
    }*/

    @Then("^the body response content should be matched$")
    public void the_body_response_content_should_be_matched(DataTable table) {
        System.out.println("the_endpoint_is_already_configured");
       /* List<List<String>> data = table.asLists();
        List<Map<String, String>> mapData =  table.asMaps();

        response.then().body("id", equalTo(Integer.parseInt(data.get(1).get(1))));

        for(int i = 2; i < data.size(); i++) {
            response.then().assertThat().body(data.get(i).get(0), equalTo(data.get(i).get(1)));
        }*/
    }
}
