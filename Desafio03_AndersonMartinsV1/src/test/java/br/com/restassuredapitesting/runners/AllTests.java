package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.auth.tests.PostAuthTest;
import br.com.restassuredapitesting.tests.booking.tests.GetBookingTest;
import br.com.restassuredapitesting.tests.booking.tests.PutBookingTest;
import br.com.restassuredapitesting.tests.healthcheck.tests.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.AllTests.class)
@Suite.SuiteClasses({
        GetPingTest.class,
        GetBookingTest.class,
        PostAuthTest.class,
        PutBookingTest.class
})
public class AllTests {

}
