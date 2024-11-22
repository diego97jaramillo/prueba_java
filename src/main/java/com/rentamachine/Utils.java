package com.rentamachine;

public class Utils {
    public static String countsql(String entity) {
        return "SELECT COUNT(*) AS total_records FROM " + entity + ";" ;
    }
}
