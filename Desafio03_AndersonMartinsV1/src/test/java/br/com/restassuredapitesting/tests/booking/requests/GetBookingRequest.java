package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna uma lista com as ids das reservas existentes")
    public Response bookingReturnIds() {
        return given()
                            .when()
                            .get("booking");
    }

    @Step("Retorna uma reserva específica através de um id")
    public Response retornaReservaDoId(int id) {
        return given()
                .header("Accept", "application/json")
                .when()
                    .get("booking/"+id);
    }

    @Step("Retorna Id das reservas quando se utiliza parâmetro para criar um filtro")
    public Response getReturnIdFilters(String key, String value,String keyTwo, String valueTwo, String checkIn, String dateOne, String checkOut, String dateTwo) {
        return given()
                .queryParams(key, value, keyTwo, valueTwo, checkIn, dateOne, checkOut, dateTwo)
                .when()
                    .get("booking");
    }

    @Step("Visualizar erro de filtro mal formatado")
    public Response getReturnIdWithBadFilter(String key, String value,String keyTwo, String valueTwo) {
        return given()
                .queryParams(key, value)
                .queryParam(keyTwo, valueTwo)
                .when()
                .get("booking");
    }
}
