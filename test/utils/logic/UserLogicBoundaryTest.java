package utils.logic;

import de.fhws.biedermann.webshop.utils.logic.UserLogic;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserLogicBoundaryTest {

    @ParameterizedTest
    @CsvSource({
            "T2.1, 38, 1, 1, Start123!, 40",
            "T2.2, 1, 38, 1, Start123!, 40",
            "T2.3, 1, 1, 38, Start123!, 40"
    })
    public void testValidPasswords( String testCaseId, int uppercaseCount, int numCount, int specialCharCount, String oldPass, int stringLength ) {
        String newPass = createPassword( uppercaseCount, numCount, specialCharCount, stringLength );
        assertTrue( UserLogic.isValidPassword( oldPass, newPass ) );
    }

    @ParameterizedTest
    @CsvSource({
            "T2.4, 39, 1, 1, Start123!, 41",
            "T2.5, 1, 39, 1, Start123!, 41",
            "T2.6, 1, 1, 39, Start123!, 41",
            "T2.7, 0, 1, 1, Start123!, 8",
            "T2.8, 1, 0, 1, Start123!, 8",
            "T2.9, 1, 1, 0, Start123!, 8",
            "T2.10, 1, 1, 1, Start123!, 7",
            "T2.11, 0, 0, 0, Start123!, 0"
    })
    public void testInvalidPasswords( String testCaseId, int uppercaseCount, int numCount, int specialCharCount, String oldPass, int stringLength ) {
        String newPass = createPassword( uppercaseCount, numCount, specialCharCount, stringLength );
        assertFalse( UserLogic.isValidPassword( oldPass, newPass ) );
    }

    public static String createPassword( int uppercaseCount, int numCount, int specialCharCount, int stringLength ) {

        // das überprüfen der Länge im Vergleich zu den Angaben, ist lediglich zum Sicherstellen von korrekten Werten dar
        int testCharLength = uppercaseCount + numCount + specialCharCount;
        if ( testCharLength > stringLength ) {
            throw new IllegalArgumentException( "Laenge der Passwortgenerierung stimmt nicht mit der Anzahl der" +
                    "uebergebenen Parameter ueberein" );
        }

        String newPass = "";
        for ( int i = 0; i < uppercaseCount; i++ ) {
            newPass += "A";
        }
        for ( int i = 0; i < numCount; i++ ) {
            newPass += "1";
        }
        for ( int i = 0; i < specialCharCount; i++ ) {
            newPass += "!";
        }

        int lowercaseCount = stringLength - testCharLength;
        for ( int i = 0; i < lowercaseCount; i++ ) {
            newPass += "b";
        }
        return newPass;
    }

}
