package interviewTask.PhoneValidator;


import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestPhoneValidatorFormater {
    private static final String VALID_PHONE_NUMBER = "+3717737156";
    private static final String INVALID_PHONE_NUMBER = "+3717737156a";
    private static final String NUMBER_WITHOUT_PLUS = "3717737156";
    private static final String NUMBER_WITHOUT_PLUS_LEADING_ZEROS = "0003717737156";
    private static final String NUMBER_WITH_PLUS_LEADING_ZEROS = "+003717737156";
    private static final String NUMBER_WITHOUT_PLUS_LEADING_ZEROS_AND_SPACES = "+003 71 7737  156";
    PhoneValidatorFormater phoneValidatorFormater = new PhoneValidatorFormater();

    @Test
    public void validatePhoneNumber() throws IOException {

        assertTrue(phoneValidatorFormater.isPhoneNumberValid(VALID_PHONE_NUMBER));
        assertFalse(phoneValidatorFormater.isPhoneNumberValid(INVALID_PHONE_NUMBER));
    }

    @Test
    public void formatPhoneNumber() throws IOException {
        assertEquals(phoneValidatorFormater.formatPhoneNumber(NUMBER_WITHOUT_PLUS), VALID_PHONE_NUMBER);
        assertEquals(phoneValidatorFormater.formatPhoneNumber(NUMBER_WITHOUT_PLUS_LEADING_ZEROS), VALID_PHONE_NUMBER);
        assertEquals(phoneValidatorFormater.formatPhoneNumber(NUMBER_WITH_PLUS_LEADING_ZEROS), VALID_PHONE_NUMBER);
        assertEquals(phoneValidatorFormater.formatPhoneNumber(NUMBER_WITHOUT_PLUS_LEADING_ZEROS_AND_SPACES), VALID_PHONE_NUMBER);
    }
}
