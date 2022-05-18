package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SmokeTests;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.hamcrest.Matchers.notNullValue;

public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Category({AllTests.class, SmokeTests.class})
    public void createNewBooking() {

        postBookingRequest.createBooking()
                .then()
                .log().all()
                .statusCode(200)
                .body("bookingid", notNullValue());

    }

}
