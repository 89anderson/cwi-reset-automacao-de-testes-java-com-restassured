package br.com.restassuredapitesting.tests.healthcheck.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetPingRequest {

    @Step("Verifica se a API esta online através de um ping")
    public Response pingReturnApi() {
        return given()
                        .header("Content-Type", "application/json")
                        .when()
                        .get("ping");
    }
}
