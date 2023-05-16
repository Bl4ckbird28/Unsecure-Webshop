package utils.logic;

import de.fhws.biedermann.webshop.utils.logic.UserLogic;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserLogicParameterizedTest {

    @Test
    public void empty_test() {
        // empty test for GitHub-Action
    }

    @ParameterizedTest
    @CsvSource({
            "T1.1, Start123!, Hallo123!"
    })
    public void testValidPasswords( String testCaseId, String oldPass, String newPass ) {
        assertTrue( UserLogic.isValidPassword( oldPass, newPass ) );
    }

    @ParameterizedTest
    @CsvSource({
            "T1.2, Start123!, Start123!",
            "T1.3, Start123!, Hal1!",
            "T1.4, Start123!, hallo123!",
            "T1.5, Start123!, Hallonum!",
            "T1.6, Start123!, Hallo123s"
    })
    public void testInvalidPasswords( String testCaseId, String oldPass, String newPass ) {
        assertFalse( UserLogic.isValidPassword( oldPass, newPass ) );
    }

}
