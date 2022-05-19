package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTest;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTest;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    public void deleteBooking() {

        int idParaDeletar = getBookingRequest.bookingReturnIds()
                .then()
                .assertThat()
                    .statusCode(200)
                .extract()
                    .path("[0].bookingid");
        deleteBookingRequest.deleteBooking(idParaDeletar, postAuthRequest.getToken())
                .then()
                .assertThat()
                    .statusCode(201);
    }

    @Test
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    public void deletarReservaQueNaoExiste() {
        int idParaDeletar = 99999;

        deleteBookingRequest.deleteBooking(idParaDeletar, postAuthRequest.getToken())
                .then()
                .log().all()
                    .assertThat()
                        .statusCode(403);
    }

    @Test
    @Category({AllTests.class, SecurityTest.class})
    public void deletarReservaSemToken() {
        int idParaDeletar = getBookingRequest.bookingReturnIds()
                .then()
                    .assertThat()
                        .statusCode(200)
                    .extract()
                        .path("[0].bookingid");

        deleteBookingRequest.deleteBookingSemToken(idParaDeletar)
                .then()
                    .assertThat()
                        .statusCode(403);
    }
}
