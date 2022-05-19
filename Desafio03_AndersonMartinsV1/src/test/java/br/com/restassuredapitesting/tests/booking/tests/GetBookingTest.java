package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.Utils.Utils;
import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SchemaTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    public void listagemDeIdsDasReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                //.log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    public void listarUmaReservaEspecifica() {
        int idParaBuscar = getBookingRequest.bookingReturnIds()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .extract()
                        .path("[0].bookingid");
        getBookingRequest.retornaReservaDoId(idParaBuscar)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }


    @Test
    @Category({AllTests.class, SchemaTest.class})
    public void validaSchemaDaListagemDeReservas() {
        getBookingRequest.bookingReturnIds()
                .then()
                //.log().all()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Category({AllTests.class, SchemaTest.class})
    public void testValidateSchemaDeIdEspecifico() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        getBookingRequest.retornaReservaDoId(primeiroId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "validaid"))));
    }
}
