package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTest;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

public class PutBookingTest extends BaseTest {
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel alterar uma reserva específica com autenticação de token")
    public void alteraReservaUtilizandoToken() {

        int primeiroId = getBookingRequest.bookingReturnIds()
                        .then()
                            .assertThat()
                                .statusCode(200)
                            .extract()
                                .path("[0].bookingid");

        putBookingRequest.updateBookingComToken(primeiroId, postAuthRequest.getToken())
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel alterar uma reserva específica com autenticação Basic Auth")
    public void alteraReservaUtilizandoBasicAuth() {

        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .extract()
                .       path("[0].bookingid");

        putBookingRequest.updateBookingComBasicAuth(primeiroId)
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, SecurityTest.class})
    @DisplayName("Verificar se é possivel alterar uma reserva específica sem autenticação")
    public void alteraReservaQuandoTokenNaoForEnviado() {

        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .extract()
                        .path("[0].bookingid");

        putBookingRequest.updateBookingSemToken(primeiroId)
                .then()
                    .assertThat()
                        .statusCode(403);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel alterar uma reserva específica com token inválido")
    public void alteraReservaUtilizandoTokenInvalido() {

        String token ="abobrinha123";
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .extract()
                        .path("[0].bookingid");

        putBookingRequest.updateBookingComToken(primeiroId, token)
                .then()
                    .assertThat()
                        .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel alterar uma reserva que não existe")
    public void alteraReservaQueNaoExiste() {

        int primeiroId = 9999;

        putBookingRequest.updateBookingComToken(primeiroId, postAuthRequest.getToken())
                .then()
                    .assertThat()
                        .statusCode(405);

    }

}
