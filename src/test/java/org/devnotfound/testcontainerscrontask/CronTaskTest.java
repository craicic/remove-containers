package org.devnotfound.testcontainerscrontask;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CronTaskTest {

    private final CronTask ct = new CronTask();

    @Test
    @Order(1)
    void start_shouldReturn0() throws Exception {
        int value = ct.start();
        assertEquals(value, 0);
    }

}