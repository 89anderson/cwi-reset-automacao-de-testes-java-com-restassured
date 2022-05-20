package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.Utils.Utils;
import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTest;
import br.com.restassuredapitesting.suites.SchemaTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.notNullValue;

public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    public void criaReserva() {

        postBookingRequest.createBooking()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body("bookingid", notNullValue());

    }

    @Test
    @Category({AllTests.class, SchemaTest.class})
    public void validaSchemaDeCriacaoDeReserva(){
        postBookingRequest.createBooking()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "criarreservaschema"))));

    }

    @Test
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    public void criaReservaComPayloadInvalido() {

        postBookingRequest.buscaPayloadInvalido()
                .then()
                    .assertThat()
                        .statusCode(500);

    }

    @Test
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    public void criaReservasEmSequencia() {

        for(int i=0; i<5; i++) {
            postBookingRequest.createBooking()
                    .then()
                        .assertThat()
                            .statusCode(200)
                        .body("bookingid", notNullValue());
        }

    }

    @Test
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    public void criaReservaComPayloadComParametrosExtra() {

        postBookingRequest.buscaPayloadComParametrosExtra()
                .then()
                    .assertThat()
                        .statusCode(200);

    }

}
