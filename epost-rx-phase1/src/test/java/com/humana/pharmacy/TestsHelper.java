package com.humana.pharmacy;

import java.lang.reflect.Field;

/**
 * The helper class of tests.
 */
public class TestsHelper {
    /**
     * Private constructor.
     */
    private TestsHelper() {
        // empty
    }

    /**
     * Set field of the given object.
     *
     * @param obj   the given object
     * @param field the field name
     * @param value the field value
     */
    public static void setFieldValue(Object obj, String field, Object value) {
        try {
            Field privateField = null;
            try {
                // Create Field object
                privateField
                        = obj.getClass().getDeclaredField(field);
            } catch (Exception e) {
                // Ignore
            }
            if (privateField == null) {
                privateField
                        = obj.getClass().getSuperclass().getDeclaredField(field);
            }

            // Set the accessibility as true
            privateField.setAccessible(true);

            // Store the value of private field in variable
            privateField.set(obj, value);

            // Set the accessibility as false
            privateField.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
