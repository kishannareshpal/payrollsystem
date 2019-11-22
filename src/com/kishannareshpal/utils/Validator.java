package com.kishannareshpal.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validate(String answer, String fieldName, boolean specialCharsIsAllowed) {
        if (Helper.isNullOrEmpty(answer)) {
            // if the string to be validated is empty or contains whitespaces;
            System.err.println(!Helper.isNullOrEmpty(fieldName) ? fieldName + " cannot be empty." : "This cannot be empty.");
            return false;

        } else if (!specialCharsIsAllowed) {
            // checks if the string to be validated contains any special characters;
            Pattern pattern = Pattern.compile("^[a-zA-Z\\s]*$");
            Matcher matcher = pattern.matcher(answer); // string with only letters and spaces (nothing else).
            if (matcher.matches()) {
                // does not contain any special chars.
                return true;
            } else {
                // contains specials chars.
                System.err.println(!Helper.isNullOrEmpty(fieldName) ? fieldName + " cannot contain any special characters or numbers. It must only contain Letters." : "This cannot contain any special characters or numbers. It must only contain Letters.");
                return false;
            }

        } else {
            // is valid.
            return true;
        }
    }

    public static String validateDate(String date, String fieldName) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        sdf.setLenient(false);
        try {
            Date d = sdf.parse(date);
            cal.setTime(d);
        } catch (ParseException e) {
            System.err.println(!Helper.isNullOrEmpty(fieldName) ? fieldName + " is invalid. Please enter the Date Of Birth in the correct format." : "Invalid date. Please enter the date of birth in the correct format.");
            return null;
        }

        int rawDay = cal.get(Calendar.DAY_OF_MONTH);
        String day = (rawDay < 10) ? "0" + rawDay : String.valueOf(rawDay);
        int rawMonth = cal.get(Calendar.MONTH) + 1;
        String month = (rawMonth < 10) ? "0" + rawMonth : String.valueOf(rawMonth);
        String year = String.valueOf(cal.get(Calendar.YEAR));


        return day + "/" + month + "/" + year;
    }

    public static String validateNINO(String nino, String fieldName) {
        nino = nino.toUpperCase();
        Pattern exp1 = Pattern.compile("^[A-CEGHJ-NOPR-TW-Z]{1}[A-CEGHJ-NPR-TW-Z]{1}[0-9]{6}[A-D\\s]{1}");
        Pattern exp2 = Pattern.compile("(^GB)|(^BG)|(^NK)|(^KN)|(^TN)|(^NT)|(^ZZ).+");

        if (exp1.matcher(nino).matches() && !exp2.matcher(nino).matches()) {
            return nino.toUpperCase();
        } else {
            System.err.println(!Helper.isNullOrEmpty(fieldName) ? fieldName + " is invalid. Please enter the NINO in the correct format." : "Invalid NINO. Please enter the number in the correct format.");
            return null;
        }
    }

    public static boolean validate(String selectedOption, int[] optionsRange) {
        int minimumOption = optionsRange[0];
        int maximumOption = optionsRange[1];

        if (Helper.isNullOrEmpty(selectedOption)) {
            System.err.println("Please choose one number from the options shown above, ranging from " + minimumOption + " to " + maximumOption + ".");
            return false;
        }

        boolean isValid = false;
        try {
            int selected = Integer.parseInt(selectedOption);
            if (selected >= minimumOption && selected <= maximumOption) {
                // if the selected option is in the range of the minimum to maximum allowed option.
                isValid = true;

            } else {
                System.err.println("`"+ Helper.abbreviate(selectedOption, 10) + "` is an invalid option. Only the aforementioned options are available.");
                isValid = false;
            }


        } catch (NumberFormatException nfe) {
            // invalid option.
            System.err.println("`"+ Helper.abbreviate(selectedOption, 10) + "` is an invalid option. Choose one number from the options shown above, ranging from " + minimumOption + " to " + maximumOption + ".");
            // skip-catch
        }

        return isValid;
    }

}
