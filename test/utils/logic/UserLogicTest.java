package utils.logic;

import de.fhws.biedermann.webshop.utils.logic.UserLogic;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserLogicTest {

    @Test
    public void test_isValidPassword_newPassword_correct () {
        assertTrue( UserLogic.isValidPassword( "Test123!", "Hallo123!" ) );
    }

    @Test
    public void test_isValidPassword_with_empty_newPassword () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "" ) );
    }

    // empty oldPassword musstn´t be checked here! Already checked from the function which calls this function.

    @Test
    public void test_isValidPassword_newPassword_same_as_oldPassword () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "Test123!" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_shorter_than_min_length () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "Test123!" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_without_upper_char () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "hallo123!" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_without_num () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "Halloasd!" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_without_special_char () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "Hallo1234" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_without_upper_char_and_num () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "halloasd!" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_without_upper_char_and_special_char () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "hallo1234" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_without_num_and_special_char () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "halloasdf" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_shorter_than_min_length_and_without_upper_char () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "h123!" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_shorter_than_min_length_and_without_num () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "Hasd!" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_shorter_than_min_length_and_without_special_char () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "H1234" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_shorter_than_min_length_and_without_upper_char_and_num () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "hasd!" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_shorter_than_min_length_and_without_upper_char_and_special_char () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "h1234" ) );
    }

    @Test
    public void test_isValidPassword_newPassword_shorter_than_min_length_and_without_num_and_special_char () {
        assertFalse( UserLogic.isValidPassword( "Test123!", "hasdf" ) );
    }

    // same as oldPassword in combination with other ranges doesn´t make sence

}
