package com.example.marokkanischbernessen.utile;

import androidx.databinding.InverseMethod;

public class Converter {
    private static boolean CONVERT_OK = false;

    @InverseMethod("stringToInt")
    public static String intToString(int value) {

        if (value == 0) return "Numéro";
        else return String.valueOf(value);
    }

    public static int stringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            if (value == "0") return 0;
            else return -1;
        }
    }

    @InverseMethod("stringToShort")
    public static String shortToString(short value) {
        return String.valueOf(value);
    }

    public static short stringToShort(String value) {
        return Short.parseShort(value);
    }
}
