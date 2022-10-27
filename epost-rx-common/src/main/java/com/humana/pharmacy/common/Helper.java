package com.humana.pharmacy.common;

import java.util.List;

/**
 * The helper class.
 */
public class Helper {
    /**
     * The string '!'.
     */
    public static final String KEY_SEP = "!";

    /**
     * The string '#'.
     */
    public static final String KEY_SEP2 = "#";

    /**
     * The string '='.
     */
    public static final String KEY_EQUAL = "=";

    /**
     * Private constructor.
     */
    private Helper() {
        // Empty
    }

    /**
     * Get a substring of this string begins at 0.
     *
     * @param str      the string
     * @param endIndex the ending index, exclusive.
     * @return the substring
     */
    public static String substring(String str, int endIndex) {
        return str.substring(0, Math.min(str.length(), endIndex));
    }

    /**
     * Check if the list is null/empty.
     *
     * @param <T>  the element type
     * @param list the list
     * @return <code>true</code> if the list is null/empty; <code>false</code> otherwise.
     */
    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    /**
     * Check if the object is null.
     *
     * @param obj  the object
     * @param name the name
     * @throws RuntimeException if the object is null
     */
    public static void checkNull(Object obj, String name) {
        if (obj == null) {
            throw new RuntimeException("'" + name + "' can't be null");
        }
    }

    /**
     * Check if the string is null/empty.
     *
     * @param obj  the string
     * @param name the name
     * @throws RuntimeException if the string is null/empty
     */
    public static void checkString(String obj, String name) {
        checkNull(obj, name);
        if (obj.trim().length() == 0) {
            throw new RuntimeException("'" + name + "' can't be empty");
        }
    }

    /**
     * Check if the list is null/empty.
     *
     * @param list the list
     * @param name the name
     * @throws RuntimeException if the list is null/empty
     */
    public static <T> void checkList(List<T> list, String name) {
        checkNull(list, name);
        if (list.isEmpty()) {
            throw new RuntimeException("'" + name + "' can't be empty");
        }
    }
}
