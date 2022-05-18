package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.Utils.Utils;
import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Category({AllTests.class})
    public void validaListagemDeIdsDasReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                //.log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Category({AllTests.class, ContractTests.class})
    public void validaSchemaDaListagemDeReservas() {
        getBookingRequest.bookingReturnIds()
                .then()
                //.log().all()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }
}
