package com.finartz.airlinesystem.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finartz.airlinesystem.exception.ExceptionConstants;
import com.finartz.airlinesystem.exception.InvalidCreditCardException;

/**
 * @author : Yekta Anil AKSOY
 * @since : 4.02.2021
 **/
public final class Utils {

    private Utils() {

    }


    public static final String DEFAULT_CC_FORMAT = "######******####";
    private static final int CC_LENGTH = 16;

    /**
     * Applies the specified mask to the card number.
     *
     * @param cardNumber The card number in plain format
     * @param mask       The number mask pattern. Use # to include a digit from the card number at
     *                   that position, use x to skip the digit at that position
     * @return The masked card number
     */
    public static String maskCardNumber(String cardNumber, String mask) {
        cardNumber = removeNonIntegerChars(cardNumber);
        if (cardNumber.length() != CC_LENGTH) {
            throw new InvalidCreditCardException(ExceptionConstants.CC_LENGTH_INVALID);
        }
        // format the number
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == '*') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }

        // return the masked number
        return maskedNumber.toString();
    }

    public static String removeNonIntegerChars(String cardNumber) {
        return cardNumber.replaceAll("[^0-9]", "");
    }

    /**
     * @param obj is any object that can be stringfied
     * @return a JSON format of given object
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
