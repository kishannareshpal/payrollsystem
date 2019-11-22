package com.kishannareshpal.utils;

public class Helper {

    /**
     * https://gist.github.com/gkhays/6168489
     * @param s a string
     * @return true if its either null or empty, otherwise false.
     */
    public static boolean isNullOrEmpty(String s)
    {
        if (s == null) return true;
        return s.isBlank();
    }

    /**
     * When a question is asked and the user is given the choice between YES or NO,
     * use this method to determine the user input.
     *
     * @param yesOrNo the user input.
     * @param shouldBeYesByDefault the default value for when the user input is empty.
     *                             For:
     *                                  (Y/n) – shouldBeYesByDefault = true
     *                                  (y/N) – shouldBeYesByDefault = false
     * @return true if the input was meant to be true, or false if otherwise.
     */
    public static boolean isYesOrNo(String yesOrNo, boolean shouldBeYesByDefault) {
        boolean isYes = yesOrNo.toLowerCase().startsWith("y");
        boolean isNo = yesOrNo.toLowerCase().startsWith("n");

        if (isYes || (isNullOrEmpty(yesOrNo) && shouldBeYesByDefault)){
            return true;

        } else if (isNo){
            return false;

        } else {
            return false;
        }
    }


    /**
     * Generates n number of whitespaces as String.
     *
     * @param numOfWhitespaces number of whitespaces
     * @return whitespaces
     */
    public static String generateWhitespaces(int numOfWhitespaces) {
        StringBuilder whitespaces = new StringBuilder();
        for (int i = 0; i < numOfWhitespaces; i++) {
            whitespaces.append(" ");
        }
        return whitespaces.toString();
    }


    /**
     * Abbreviate a long string by trimming it and adding elipsis (...) in the end.
     * For example:
     *      Helper.abbreviate("abcdefghij", 5) = "abcde..."
     *
     * @param str
     * @param maxWidth
     * @return
     */
    public static String abbreviate(String str, int maxWidth) {
        int length = str.length();
        if ((length > maxWidth) && maxWidth > 3) {
            return  str.substring(0, maxWidth) + "...";

        } else {
            return str;
        }
    }

    public static double calculateTax(double income) {
        double taxPercentage = 0.0;
        if (income < 12500) {
            // no tax is deduced.
            taxPercentage = 0.0;

        } else if (income >= 12501 && income <= 50000) {
            // 20% of the annual income.
            taxPercentage = 0.2;

        } else if (income >= 50001 && income <= 150000){
            // 40% of the annual income.
            taxPercentage = 0.4;
        } else if (income > 150000) {
            // 45% of the annual income.
            taxPercentage = 0.45;
        }
        return income * taxPercentage;
    }
}