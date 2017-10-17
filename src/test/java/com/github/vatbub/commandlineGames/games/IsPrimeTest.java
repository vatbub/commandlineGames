package com.github.vatbub.commandlineGames.games;

import org.junit.Assert;
import org.junit.Test;

public class IsPrimeTest {
    @Test
    public void listTest() {
        IsPrime isPrimeInstance = new IsPrime();
        for (int i = 1; i <= 100000; i++) {
            StringBuilder assertMessage = new StringBuilder(Integer.toString(i)).append(" is ");
            boolean expected = isPrimeTest(i);
            if (!expected) {
                assertMessage.append("not ");
            }
            assertMessage.append("a prime number");

            Assert.assertEquals(assertMessage.toString(), expected, isPrimeInstance.isPrime(i));
        }
    }

    private boolean isPrimeTest(int integer) {
        if (integer == 1) {
            return false;
        }
        for (int i = 2; i < integer; i++) {
            if (integer / ((double) i) == integer / i) {
                return false;
            }
        }
        return true;
    }
}
