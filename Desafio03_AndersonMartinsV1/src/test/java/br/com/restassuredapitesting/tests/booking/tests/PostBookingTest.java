package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.Utils.Utils;
import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTest;
import br.com.restassuredapitesting.suites.SchemaTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Feature de criação de reservas")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel criar uma reserva")
    public void criaReserva() {

        postBookingRequest.createBooking()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body("bookingid", notNullValue());

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTest.class})
    @DisplayName("Verificar se o schema de criação de reserva está assegurado")
    public void validaSchemaDeCriacaoDeReserva(){
        postBookingRequest.createBooking()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "criarreservaschema"))));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Verificar se é possivel criar uma reserva com um payload inválido")
    public void criaReservaComPayloadInvalido() {

        postBookingRequest.criaReservaComPayloadInvalido()
                .then()
                    .assertThat()
                        .statusCode(500);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Verificar se é possivel criar várias reservas em sequencia")
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
    @Severity(SeverityLevel.TRIVIAL)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Verificar se é possivel criar uma reserva com um payload com parametros extras")
    public void criaReservaComPayloadComParametrosExtra() {

        postBookingRequest.criaReservaComPayloadDeParametrosExtras()
                .then()
                    .assertThat()
                        .statusCode(200);

    }

}
