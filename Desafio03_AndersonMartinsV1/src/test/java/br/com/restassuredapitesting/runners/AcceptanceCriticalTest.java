package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.healthcheck.tests.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.AcceptanceCriticalTest.class)
@Suite.SuiteClasses({
        GetPingTest.class
})
public class AcceptanceCriticalTest {
}
