package com.it.uncle.lib.util;

import android.os.Looper;

import junit.framework.AssertionFailedError;
import junit.framework.ComparisonFailure;

/**
 * Android断言工具类
 *
 * @author vectorzeng
 */
public class AndroidAssert {

    private static boolean isEnable = true;

    public static void enable(boolean enable) {
        isEnable = enable;
    }


    /**
     * Fails a test with no message.
     *
     * 抛出断言异常
     */
    public static void fail() {
        fail(null);
    }

    /**
     * Fails a test with the given message.
     *
     * 抛出断言异常
     */
    public static void fail(String message) {
        if (isEnable) {
            throw new AssertionFailedError(message);
        }
    }


    /////// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ thread ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    /**
     * 检查当前是否是子线程，如果不是，抛出断言异常
     */
    public static void assertSubThread() {
        if (isEnable)
            assertTrue(Looper.getMainLooper() != Looper.myLooper());
    }

    /**
     * 检查当前是否是主线程，如果不是，抛出断言异常
     */
    public static void assertMainThread() {
        if (isEnable)
            assertTrue(Looper.getMainLooper() == Looper.myLooper());
    }

    /////// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ thread ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    /////// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ null or nunNull ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    /**
     * Asserts that an object isn't null.
     *
     * 检查object是否为为非空，如果为空，抛出断言异常
     */
    public static void assertNotNull(Object object) {
        assertNotNull(null, object);
    }

    /**
     * Asserts that an object isn't null. If it is an AssertionFailedError is
     * thrown with the given message.
     *
     * 检查参数object是否为为非空，如果为空，抛出断言异常
     */
    public static void assertNotNull(String message, Object object) {
        assertTrue(message, object != null);
    }

    /**
     * Asserts that an object is null.
     *
     * 检查object是否为null，如果不是null，抛出断言异常
     */
    public static void assertNull(Object object) {
        assertNull(null, object);
    }

    /**
     * Asserts that an object is null. If it is not an AssertionFailedError is
     * thrown with the given message.
     *
     * 检查object是否为null，如果不是null，抛出断言异常
     */
    public static void assertNull(String message, Object object) {
        assertTrue(message, object == null);
    }
    /////// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ null or nunNull ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑



    /////// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ true or false ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    /**
     * Asserts that a condition is true. If it isn't it throws an
     * AssertionFailedError.
     *
     * @param condition 不是true，将抛出断言异常
     */
    public static void assertTrue(boolean condition) {
        assertTrue(null, condition);
    }

    /**
     * Asserts that a condition is true. If it isn't it throws an
     * AssertionFailedError with the given message.
     *
     * @param condition 不是true，将抛出断言异常
     */
    public static void assertTrue(String message, boolean condition) {
        if (!condition)
            fail(message);
    }

    /**
     * Asserts that a condition is false. If it isn't it throws an
     * AssertionFailedError.
     *
     * @param condition 不是false，将抛出断言异常
     */
    public static void assertFalse(boolean condition) {
        assertFalse(null, condition);
    }

    /**
     * Asserts that a condition is false. If it isn't it throws an
     * AssertionFailedError with the given message.
     *
     * @param condition 不是false，将抛出断言异常
     */
    public static void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }

    /////// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ true or false ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    /////// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ same ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    /**
     * Asserts that two objects refer to the same object. If they are not the
     * same an AssertionFailedError is thrown.
     *
     * expected == actual为false，抛出断言异常
     */
    public static void assertSame(Object expected, Object actual) {
        assertSame(null, expected, actual);
    }

    /**
     * Asserts that two objects refer to the same object. If they are not an
     * AssertionFailedError is thrown with the given message.
     *
     * expected == actual为false，抛出断言异常
     */
    public static void assertSame(String message, Object expected, Object actual) {
        if (expected == actual)
            return;
        failNotSame(message, expected, actual);
    }

    /**
     * Asserts that two objects refer to the same object. If they are not the
     * same an AssertionFailedError is thrown.
     *
     * object != actual为false，抛出断言异常
     */
    public static void assertNotSame(Object expected, Object actual) {
        assertNotSame(null, expected, actual);
    }

    /**
     * Asserts that two objects refer to the same object. If they are not an
     * AssertionFailedError is thrown with the given message.
     *
     * object != actual为false，抛出断言异常
     */
    public static void assertNotSame(String message, Object expected,
                                     Object actual) {
        if (expected == actual)
            failSame(message);
    }


    private static void failNotSame(String message, Object expected,
                                    Object actual) {
        if (!isEnable)
            return;
        String formatted = "";
        if (message != null)
            formatted = message + " ";
        fail(formatted + "expected same:<" + expected + "> was not:<" + actual
                + ">");
    }

    private static void failSame(String message) {
        if (!isEnable)
            return;
        String formatted = "";
        if (message != null)
            formatted = message + " ";
        fail(formatted + "expected not same");
    }

    /////// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ same ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    /////// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ equals ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    /**
     * Asserts that two objects are equal. If they are not an
     * AssertionFailedError is thrown.
     *
     * 检查expected、actual两个对象equals是否为true，如果为false那么抛出断言异常
     */
    public static void assertEquals(Object expected, Object actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two objects are equal. If they are not an
     * AssertionFailedError is thrown with the given message.
     */
    public static void assertEquals(String message, Object expected,
                                    Object actual) {
        if (!isEnable)
            return;

        if (expected == null && actual == null)
            return;
        if (expected != null && expected.equals(actual))
            return;
        failNotEquals(message, expected, actual);
    }


    /**
     * Asserts that two booleans are equal.
     *
     */
    public static void assertEquals(boolean expected, boolean actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two bytes are equal.
     *
     */
    public static void assertEquals(byte expected, byte actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two chars are equal.
     */
    public static void assertEquals(char expected, char actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two doubles are equal concerning a delta. If the expected
     * value is infinity then the delta value is ignored.
     */
    public static void assertEquals(double expected, double actual, double delta) {
        assertEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that two floats are equal concerning a delta. If the expected
     * value is infinity then the delta value is ignored.
     */
    public static void assertEquals(float expected, float actual, float delta) {
        assertEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that two ints are equal.
     */
    public static void assertEquals(int expected, int actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two longs are equal.
     */
    public static void assertEquals(long expected, long actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two shorts are equal.
     */
    public static void assertEquals(short expected, short actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two booleans are equal. If they are not an
     * AssertionFailedError is thrown with the given message.
     */
    public static void assertEquals(String message, boolean expected,
                                    boolean actual) {
        if (!isEnable)
            return;
        assertEquals(message, Boolean.valueOf(expected), Boolean.valueOf(actual));
    }

    /**
     * Asserts that two bytes are equal. If they are not an AssertionFailedError
     * is thrown with the given message.
     */
    public static void assertEquals(String message, byte expected, byte actual) {
        if (!isEnable)
            return;
        assertEquals(message, Byte.valueOf(expected), Byte.valueOf(actual));
    }

    /**
     * Asserts that two chars are equal. If they are not an AssertionFailedError
     * is thrown with the given message.
     */
    public static void assertEquals(String message, char expected, char actual) {
        if (!isEnable)
            return;
        assertEquals(message, Character.valueOf(expected), Character.valueOf(actual));
    }

    /**
     * Asserts that two doubles are equal concerning a delta. If they are not an
     * AssertionFailedError is thrown with the given message. If the expected
     * value is infinity then the delta value is ignored.
     */
    public static void assertEquals(String message, double expected,
                                    double actual, double delta) {
        if (!isEnable)
            return;

        // handle infinity specially since subtracting to infinite values gives
        // NaN and the
        // the following test fails
        if (Double.isInfinite(expected)) {
            if (!(expected == actual))
                failNotEquals(message, Double.valueOf(expected), Double.valueOf(actual));
        } else if (!(Math.abs(expected - actual) <= delta)) // Because
            // comparison
            // with NaN always
            // returns false
            failNotEquals(message, Double.valueOf(expected), Double.valueOf(actual));
    }

    /**
     * Asserts that two floats are equal concerning a delta. If they are not an
     * AssertionFailedError is thrown with the given message. If the expected
     * value is infinity then the delta value is ignored.
     */
    public static void assertEquals(String message, float expected,
                                    float actual, float delta) {
        if (!isEnable)
            return;

        // handle infinity specially since subtracting to infinite values gives
        // NaN and the
        // the following test fails
        if (Float.isInfinite(expected)) {
            if (!(expected == actual))
                failNotEquals(message, Float.valueOf(expected), Float.valueOf(actual));
        } else if (!(Math.abs(expected - actual) <= delta))
            failNotEquals(message, Float.valueOf(expected), Float.valueOf(actual));
    }

    /**
     * Asserts that two ints are equal. If they are not an AssertionFailedError
     * is thrown with the given message.
     */
    public static void assertEquals(String message, int expected, int actual) {
        if (!isEnable)
            return;
        assertEquals(message, Integer.valueOf(expected), Integer.valueOf(actual));
    }

    /**
     * Asserts that two longs are equal. If they are not an AssertionFailedError
     * is thrown with the given message.
     */
    public static void assertEquals(String message, long expected, long actual) {
        if (!isEnable)
            return;
        assertEquals(message, Long.valueOf(expected), Long.valueOf(actual));
    }

    /**
     * Asserts that two shorts are equal. If they are not an
     * AssertionFailedError is thrown with the given message.
     */
    public static void assertEquals(String message, short expected, short actual) {
        if (!isEnable)
            return;
        assertEquals(message, new Short(expected), new Short(actual));
    }

    /**
     * Asserts that two Strings are equal.
     */
    public static void assertEquals(String expected, String actual) {
        assertEquals(null, expected, actual);
    }


    /**
     * Asserts that two Strings are equal.
     */
    public static void assertEquals(String message, String expected,
                                    String actual) {
        if (!isEnable)
            return;

        if (expected == null && actual == null)
            return;
        if (expected != null && expected.equals(actual))
            return;

        throw new ComparisonFailure(message, expected, actual);
    }



    private static void failNotEquals(String message, Object expected,
                                      Object actual) {
        if (!isEnable)
            return;
        fail(format(message, expected, actual));
    }

    /////// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ equals ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑






    private static String format(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null)
            formatted = message + " ";
        return formatted + "expected:<" + expected + "> but was:<" + actual
                + ">";
    }

    protected AndroidAssert() {
    }
}
