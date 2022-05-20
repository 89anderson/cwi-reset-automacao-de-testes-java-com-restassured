package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTest;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTest;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Verificar se é possivel deletar uma reserva específica")
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
    @Severity(SeverityLevel.MINOR)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Verificar se é possivel deletar uma reserva que não existe")
    public void deletarReservaQueNaoExiste() {
        int idParaDeletar = 99999;

        deleteBookingRequest.deleteBooking(idParaDeletar, postAuthRequest.getToken())
                .then()
                    .assertThat()
                        .statusCode(405);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SecurityTest.class})
    @DisplayName("Verificar se é possivel deletar uma reserva sem autenticação")
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
