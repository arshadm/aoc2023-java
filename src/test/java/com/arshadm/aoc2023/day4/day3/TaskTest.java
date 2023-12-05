package com.arshadm.aoc2023.day4.day3;

import com.arshadm.aoc2023.TestBase;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest extends TestBase {
  private final int DAY = 4;

  private record Card(Set<Integer> winning, Set<Integer> numbers) {
    public long score() {
      return (long)Math.pow(2,(Sets.intersection(winning, numbers).stream().count() - 1));
    }
  }

  @Test
  public void task1Test() {
    final var data1 = getInputData(DAY, "1a");
    final var actual1 = doTask1(data1);
    final var expected1 = "13";
    assertEquals(expected1, actual1);

    final var data2 = getInputData(DAY, "1b");
    final var actual2 = doTask1(data2);
    final var expected2 = "507214";
    assertEquals(expected2, actual2);

  }

  @Test
  public void task2Test() {
    final var data1 = getInputData(DAY, "2a");
    final var actual1 = doTask2(data1);
    final var expected1 = "467835";
    assertEquals(expected1, actual1);

    final var data2 = getInputData(DAY, "2b");
    final var actual2 = doTask2(data2);
    final var expected2 = "72553319";
    assertEquals(expected2, actual2);

  }

  private String doTask1(Collection<String> lines) {
    final var score = parseCards(lines).stream().mapToLong(card -> card.score()).sum();
    return String.valueOf(score);
  }

  private String doTask2(Collection<String> lines) {
    return "";
  }

  private List<Card> parseCards(Collection<String> lines) {
    return lines.stream().map(line -> {
      final var tokens = (line.split(":")[1]).trim().split(" \\| ");
      final Set<Integer> winning = parseNumbers(tokens[0]);
      final Set<Integer> numbers = parseNumbers(tokens[1]);
      return new Card(winning, numbers);
    }).collect(Collectors.toList());
  }

  private Set<Integer> parseNumbers(String str) {
    return Sets.newHashSet(Arrays.stream(str.trim().split(" "))
        .filter(Predicate.not(String::isBlank))
        .mapToInt(Integer::parseInt)
        .iterator());
  }
}
