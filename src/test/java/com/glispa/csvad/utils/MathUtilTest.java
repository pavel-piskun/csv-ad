package com.glispa.csvad.utils;

import org.junit.Test;

import static com.glispa.csvad.utils.MathUtil.getDistributedAdAmount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MathUtilTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
        assertEquals(3, getDistributedAdAmount(10, 30));
        assertEquals(5, getDistributedAdAmount(10, 45));
        assertEquals(2, getDistributedAdAmount(10, 11));
        assertEquals(2, getDistributedAdAmount(10, 19));
        assertEquals(0, getDistributedAdAmount(10, 0));
        assertEquals(10, getDistributedAdAmount(10, 100));
        assertEquals(99, getDistributedAdAmount(100, 99));
        assertEquals(91, getDistributedAdAmount(100, 91));
        assertEquals(50, getDistributedAdAmount(50, 99));
    }
}
