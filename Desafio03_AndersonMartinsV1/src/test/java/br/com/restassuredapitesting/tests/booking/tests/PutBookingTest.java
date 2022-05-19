package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTest;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

public class PutBookingTest extends BaseTest {
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    public void alteraReservaUtilizandoToken() {

        int primeiroId = getBookingRequest.bookingReturnIds()
                        .then()
                            .assertThat()
                                .statusCode(200)
                            .extract()
                                .path("[0].bookingid");

        putBookingRequest.updateBooking(primeiroId, postAuthRequest.getToken())
                .then()
                    .assertThat()
                        .statusCode(200)
                    .body("size()", greaterThan(0));

    }

    @Test
    @Category({AllTests.class, SecurityTest.class})
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
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    public void alteraReservaUtilizandoTokenInvalido() {

        String token ="abobrinha123";
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBooking(primeiroId, token)
                .then()
                    .assertThat()
                        .statusCode(403);
    }

    @Test
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    public void alteraReservaQueNaoExiste() {

        int primeiroId = 9999;

        putBookingRequest.updateBooking(primeiroId, postAuthRequest.getToken())
                .then()
                .assertThat()
                .statusCode(405);

    }



}
