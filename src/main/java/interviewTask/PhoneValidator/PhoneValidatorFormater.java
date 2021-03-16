package interviewTask.PhoneValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidatorFormater {
    public static final String PHONE_NUMBER_REGEX = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    public static final String LEADING_ZEROS_REGEX = "^[^1-9A-Z]+|\\s";

    public boolean isPhoneNumberValid(String id) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    public String formatPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll(" ", "").replaceAll(LEADING_ZEROS_REGEX, "");
        if (!phoneNumber.startsWith("+")) {
            phoneNumber = phoneNumber.substring(0, 0) + "+" + phoneNumber.substring(0);
        }
        return phoneNumber;

    }
}
