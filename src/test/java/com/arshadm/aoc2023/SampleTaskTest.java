package com.arshadm.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTaskTest extends TestBase {
    private final int DAY = 99;

    @Test
    public void task1Test() {
        final var data1 = getInputData(DAY, "1a");
        final var actual1 = doTask1(data1);
        final var expected1 = "x";
        assertEquals(expected1, actual1);

        final var data2 = getInputData(DAY, "1b");
        final var actual2 = doTask1(data2);
        final var expected2 = "x";
        assertEquals(expected2, actual2);

    }

    @Test
    public void task2Test() {
        final var data1 = getInputData(DAY, "2a");
        final var actual1 = doTask2(data1);
        final var expected1 = "x";
        assertEquals(expected1, actual1);

        final var data2 = getInputData(DAY, "2b");
        final var actual2 = doTask2(data2);
        final var expected2 = "x";
        assertEquals(expected2, actual2);

    }

    private String doTask1(Collection<String> lines) {
        return "To Do";
    }

    private String doTask2(Collection<String> lines) {
        return "To Do";
    }

}
