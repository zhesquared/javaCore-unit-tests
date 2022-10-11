import org.hamcrest.Matchers;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TaxAdviserTests {
    private static int testCount = 1;

    @BeforeAll
    public static void startTesting() {
        System.out.println("Start testing the program");
    }

    @AfterAll
    public static void finishTesting() {
        System.out.println("Testing completed");
    }

    @BeforeEach
    public void startTest() {
        System.out.println("Test #" + testCount);
    }

    @AfterEach
    public void finishTest() {
        System.out.println("Test #" + testCount + " is completed");
        testCount++;
    }

    @Test
    public void testEarnings() {
        int earnings = 1000, expected = 60;

        int result = TaxAdviser.taxEarnings(earnings);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testNegativeResult() {
        int earnings = 1200, spendings = 2600, expected = 0;
        int result = TaxAdviser.taxEarningsMinusSpendings(earnings,
                spendings);
        Assertions.assertEquals(expected, result);
    }

    @MethodSource("source")

    @ParameterizedTest
    public void testTaxEarningsMinusSpendings(int earnings,
                                              int spendings,
                                              int expected) {
        int result = TaxAdviser.taxEarningsMinusSpendings(earnings, spendings);
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(100, 200, 0),
                Arguments.of(600, 500, 15),
                Arguments.of(15200, 9500, 855));
    }

    @Test
    public void testAction() {
        int earnings = 1000, spendings = 200;
        String result = TaxAdviser.taxAdviser(earnings, spendings).toString();
        String expected = "УСН доходы";
        assertThat(result, Matchers.containsString(expected));
    }
}
