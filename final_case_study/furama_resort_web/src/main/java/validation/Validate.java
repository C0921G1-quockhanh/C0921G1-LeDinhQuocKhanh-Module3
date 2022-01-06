package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static Pattern pattern;
    private static Matcher matcher;

    private static final String REGEX_CUSTOMER_ID = "^CUS-(\\d){4}$";
    private static final String REGEX_SERVICE_ID = "^SER-(\\d){4}$";
    private static final String REGEX_PHONE_NUMBER = "^(\\(84\\)\\+|0)(90|91)(\\d){7}$";
    private static final String REGEX_IDENTITY_NUMBER = "^((\\d){9}|(\\d){12})$";
    private static final String REGEX_EMAIL = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static final String REGEX_TIME = "^(\\d){4}-(\\d){2}-(\\d){2}$";
    private static final String REGEX_POSITIVE_INTEGER = "^[1-9](\\d)*$";

    public static boolean regexCustomerID(String customerID) {
        pattern = Pattern.compile(REGEX_CUSTOMER_ID);
        matcher = pattern.matcher(customerID);

        return matcher.matches();
    }

    public static boolean regexServiceID(String serviceID) {
        pattern = Pattern.compile(REGEX_SERVICE_ID);
        matcher = pattern.matcher(serviceID);

        return matcher.matches();
    }

    public static boolean regexPhoneNumber(String phoneNumber) {
        pattern = Pattern.compile(REGEX_PHONE_NUMBER);
        matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public static boolean regexIdentityNumber(String identityNumber) {
        pattern = Pattern.compile(REGEX_IDENTITY_NUMBER);
        matcher = pattern.matcher(identityNumber);

        return matcher.matches();
    }

    public static boolean regexEmail(String email) {
        pattern = Pattern.compile(REGEX_EMAIL);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean regexTime(String time) {
        pattern = Pattern.compile(REGEX_TIME);
        matcher = pattern.matcher(time);

        return matcher.matches();
    }

    public static boolean regexDateOfBirth(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        pattern = Pattern.compile(REGEX_TIME);
        matcher = pattern.matcher(dateOfBirth);
        boolean isRetry = false;

        if (!matcher.matches())
            isRetry = true;
        else {
            LocalDate birthday = LocalDate.parse(dateOfBirth,formatter);
            LocalDate after18Years = birthday.plusYears(18);
            LocalDate after100Years = birthday.plusYears(100);

            if (after18Years.isAfter(now) || after100Years.isBefore(now))
                isRetry = true;
        }

        return isRetry;
    }

    public static boolean regexPositiveInteger(String positiveInteger) {
        pattern = Pattern.compile(REGEX_POSITIVE_INTEGER);
        matcher = pattern.matcher(positiveInteger);

        return matcher.matches();
    }

    public static boolean isPositive(double number) {
        return number > 0;
    }
}
