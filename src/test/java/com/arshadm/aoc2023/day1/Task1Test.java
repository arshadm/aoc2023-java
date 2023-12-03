package com.arshadm.aoc2023.day1;

import com.arshadm.aoc2023.TestBase;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test extends TestBase {
    private final int DAY = 1;

    @Test
    public void task1Test() {
        final var data1 = getInputData(DAY, "1a");
        final var actual1 = doTask1(data1);
        final var expected1 = "142";
        assertEquals(expected1, actual1);

        final var data2 = getInputData(DAY, "1b");
        final var actual2 = doTask1(data2);
        final var expected2 = "54634";
        assertEquals(expected2, actual2);

    }

    @Test
    public void task2Test() {
        final var data1 = getInputData(DAY, "2a");
        final var actual1 = doTask2(data1);
        final var expected1 = "281";
        assertEquals(expected1, actual1);

        final var data2 = getInputData(DAY, "2b");
        final var actual2 = doTask2(data2);
        final var expected2 = "53846";
        assertEquals(expected2, actual2);

    }

    private String doTask1(Collection<String> lines) {
        final var sum = lines.stream().map(line -> {
            final var ZERO = '0';
            final var digits = line.chars()
                    .filter(Character::isDigit)
                    .map(ch -> ch - ZERO)
                    .toArray();

            return digits[0]*10 + digits[digits.length-1];
        }).reduce(0, Integer::sum);
        return sum.toString();
    }

    private String doTask2(Collection<String> lines) {
        final var digitMap = List.of("one", "1", "two", "2", "three", "3",
                "four", "4", "five", "5", "six", "6", "seven", "7",
                "eight", "8", "nine", "9");

        return doTask1(
            lines.stream().map(line -> {
                var buf = new StringBuilder();
                OUTER: for(int pos=0; pos<line.length();) {
                    var subLine = line.substring(pos);
                    for(int i=0; i<digitMap.size(); i+=2) {
                        if (subLine.startsWith(digitMap.get(i))) {
                            buf.append(digitMap.get(i+1));
                            pos += digitMap.get(i).length();
                            continue OUTER;
                        }
                    }
                    buf.append(line.charAt(pos));
                    pos += 1;
                }
                return buf.toString();
            })
            .collect(Collectors.toList())
        );
    }

}
