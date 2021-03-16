package interviewTask.PhoneValidator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestExtractCountryCodeFromPhone {
    private static final String VALID_PHONE_NUMBER = "+3717737156";
    private static final String NULL_POINTER_EXCEPTION = "0003717737156";
    private static final String NUMBER_FORMAT_EXCEPTION = "aaaa3717737156";
    private static final int COUNTRY_CODE = 371;
    ExtractCountryCodeFromPhone extractCode;


    @Test
    public void formatPhoneNumber() {
        assertEquals(extractCode.Instance.getCtryCode(VALID_PHONE_NUMBER), COUNTRY_CODE);

    }

    @Test
    public void whenException() {

        Assertions.assertThrows(NumberFormatException.class, () ->
        {
            extractCode.Instance.getCtryCode(NUMBER_FORMAT_EXCEPTION);
        });
        Assertions.assertThrows(NullPointerException.class, () ->
        {
            extractCode.Instance.getCtryCode(NULL_POINTER_EXCEPTION);
        });

    }
}
