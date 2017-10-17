package com.github.vatbub.commandlineGames.games;

/*-
 * #%L
 * commandlineGames
 * %%
 * Copyright (C) 2016 - 2017 Frederik Kammel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


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
