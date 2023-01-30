package com.example.marokkanischbernessen.utile;

import java.util.regex.Pattern;

import kotlin.ranges.IntRange;

public class ExpressionValidateur {
    //FIELD STATIC FOR PATTERN
    final static String NOM_PATTERN = "^[a-zA-Z_0-9_äöüÄÖÜùûüÿàâæéèêëïîôœÙÛÜŸÀÂÆÉÈÊËÏÎÔŒ' ]*$";
    final static String RUE_PATTERN = "^[a-zA-Z_0-9_äöüÄÖÜùûüÿàâæéèêëïîôœÙÛÜŸÀÂÆÉÈÊËÏÎÔŒ' ]*$";
    final static String NUMERO_RUE_PATTERN = "^\\d{1,6}$";
    final static String CODE_POSTAL_PATTERN = "^\\d{4}$";
    final static String VILLE_PATTERN = "^[a-zA-Z_0-9_äöüÄÖÜùûüÿàâæéèêëïîôœÙÛÜŸÀÂÆÉÈÊËÏÎÔŒ' ]*$";
    final static String DATE_PATTERN = "^(0?[1-9]|[12]\\d|3[01])[\\/](0?[1-9]|1[0-2])[\\/](19|20)\\d{2}$";
    final static String HEURE_PATTERN = "^([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$";
    final static String REMARQUE_PATTERN = "^[a-zA-Z_0-9_äöüÄÖÜùûüÿàâæéèêëïîôœÙÛÜŸÀÂÆÉÈÊËÏÎÔŒ' ]*$";
    final static String NOMBRE_PATTERN = "^\\d{1,3}$";
    final static String PHONE_PATTERN = "^([0][1-9][0-9](\\s|)[0-9][0-9][0-9](\\s|)[0-9][0-9](\\s|)[0-9][0-9])$|^(([0][0]|\\+)[1-9][0-9](\\s|)[0-9][0-9](\\s|)[0-9][0-9][0-9](\\s|)[0-9][0-9](\\s|)[0-9][0-9])$";
    final static String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\n" +
            "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\n" +
            "\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:\n" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    //METHODE TO COMPILE AND MATCH STRING WITH REGULAR EXPRESSION
    public static boolean validNom(String value) {
        return Pattern.matches(NOM_PATTERN, value) && new IntRange(1, 35).contains(value.length());
    }

    public static boolean validPrenom(String value) {
        return Pattern.matches(NOM_PATTERN, value) && new IntRange(1, 35).contains(value.length());
    }

    public static boolean validRue(String value) {
        return Pattern.matches(RUE_PATTERN, value) && new IntRange(1, 69).contains(value.length());
    }

    public static boolean validNumRue(String value) {
        return Pattern.matches(NUMERO_RUE_PATTERN, value);
    }

    public static boolean validCodePostal(String value) {
        return Pattern.matches(CODE_POSTAL_PATTERN, value);
    }

    public static boolean validVille(String value) {
        return Pattern.matches(VILLE_PATTERN, value) && new IntRange(1, 69).contains(value.length());
    }

    public static boolean validEmail(String value) {
        return Pattern.matches(EMAIL_PATTERN, value) && new IntRange(1, 250).contains(value.length());
    }

    public static boolean validPhone(String value) {
        return Pattern.matches(PHONE_PATTERN, value);
    }

    public static boolean validDate(String value) {
        return Pattern.matches(DATE_PATTERN, value);
    }

    public static boolean validHeure(String value) {
        return Pattern.matches(HEURE_PATTERN, value);
    }

    public static boolean validRemarque(String value) {
        return Pattern.matches(REMARQUE_PATTERN, value) && new IntRange(1, 501).contains(value.length());
    }

    public static boolean validNombre(String value) {
        if (Pattern.matches(NOMBRE_PATTERN, value)) {
            if (Short.parseShort(value) < 1000 && Short.parseShort(value) > 29) {
                return true;
            } else return false;
        } else return false;
    }


}
