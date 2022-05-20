package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.Utils.Utils;
import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SchemaTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

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
