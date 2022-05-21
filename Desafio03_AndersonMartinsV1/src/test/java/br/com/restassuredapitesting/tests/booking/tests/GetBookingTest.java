package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.Utils.Utils;
import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SchemaTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature de retorno de reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se o endpoint está retornando todos os ids das reservas")
    public void listagemDeIdsDasReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel buscar uma reserva")
    public void listarUmaReservaEspecifica() {
        int idParaBuscar = getBookingRequest.bookingReturnIds()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .extract()
                        .path("[0].bookingid");
        getBookingRequest.retornaReservaDoId(idParaBuscar)
                .then()
                    .assertThat()
                        .statusCode(200);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel buscar uma reserva com filtro firstname")
    public void listarUmaReservaPeloFiltroFirstname() {

        PostBookingRequest postBookingRequest = new PostBookingRequest();
        Response response = postBookingRequest.createEspecificBooking();
            given()
                .contentType("application/json")
                .get("booking");

        JsonPath extractor = response.jsonPath();
        String firstName = extractor.get("booking.firstname");

        getBookingRequest.getReturnIdFilters("firstname", firstName,"","", "", "", "", "")
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body("bookingid", notNullValue())
                    .body("firstname",notNullValue());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel buscar uma reserva com filtro lastname")
    public void listarUmaReservaPeloFiltroLastname() {

        PostBookingRequest postBookingRequest = new PostBookingRequest();
        Response response = postBookingRequest.createEspecificBooking();
            given()
                .contentType("application/json")
                .get("booking");

        JsonPath extractor = response.jsonPath();
        String lastName = extractor.get("booking.lastname");

        getBookingRequest.getReturnIdFilters("", "","lastname",lastName, "", "", "", "")
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body("bookingid", notNullValue())
                    .body("lastname",notNullValue());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel buscar uma reserva com filtro checkin")
    public void listarUmaReservaPeloFiltroCheckin() {

        PostBookingRequest postBookingRequest = new PostBookingRequest();
        Response response = postBookingRequest.createEspecificBooking();
            given()
                .contentType("application/json")
                .get("booking");

        JsonPath extractor = response.jsonPath();
        String checkIn = extractor.get("booking.bookingdates.checkin");

        getBookingRequest.getReturnIdFilters("", "","","", "checkin", checkIn, "", "")
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body("bookingid", notNullValue());


    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel buscar uma reserva com filtro checkin")
    public void listarUmaReservaPeloFiltroCheckOut() {

        PostBookingRequest postBookingRequest = new PostBookingRequest();
        Response response = postBookingRequest.createEspecificBooking();
            given()
                .contentType("application/json")
                .get("booking");

        JsonPath extractor = response.jsonPath();
        String checkOut = extractor.get("booking.bookingdates.checkout");

        getBookingRequest.getReturnIdFilters("", "","","", "", "", "checkout", checkOut)
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body("bookingid", notNullValue());


    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar busca com filtro de dois valores checkout")
    public void listarUmaReservaPeloFiltroDoubleCheckOut() {

        getBookingRequest.getReturnIdWithBadFilter("checkout","2019-01-01","checkout","2021-08-18")
                .then()
                    .assertThat()
                        .statusCode(500);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Listar um Id utilizando o filtro name, checkin e checkout")
    public void ListaReservaPorFiltrosNomeCheckinCheckout() {

        PostBookingRequest postBookingRequest = new PostBookingRequest();
        Response response = postBookingRequest.createEspecificBooking();
        given()
                .contentType("application/json")
                .get("booking");

        JsonPath extractor = response.jsonPath();
        String firstName = extractor.get("booking.firstname");
        String checkIn = extractor.get("booking.bookingdates.checkin");
        String checkOut = extractor.get("booking.bookingdates.checkout");
        getBookingRequest.getReturnIdFilters("firstname", firstName, "", "",
                        "checkin", checkIn, "checkout", checkOut)
                .then()
                .log().all()
                    .assertThat()
                        .statusCode(200)
                    .body("bookingid", notNullValue());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Passar um filtro checkin mal formatado e verificar se a Api retorna o erro 500(Internal Server Error) conforme o esperado")
    public void testReturnErrorWithBadFilter(){
        getBookingRequest.getReturnIdWithBadFilter("checkin","2017-31-12","","")
                .then()
                    .assertThat()
                        .statusCode(500);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTest.class})
    @DisplayName("Valida o schema da listagem das reservas")
    public void validaSchemaDaListagemDeReservas() {
        getBookingRequest.bookingReturnIds()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTest.class})
    @DisplayName("Valida o schema da busca de uma reserva específica")
    public void testValidateSchemaDeIdEspecifico() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .extract()
                    .path("[0].bookingid");
        getBookingRequest.retornaReservaDoId(primeiroId)
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "buscaporidespecificoschema"))));
    }
}
