package com.arshadm.aoc2023.day3;

import com.arshadm.aoc2023.TestBase;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest extends TestBase {
  private final int DAY = 3;

  private record Part(int x, int y, int num) {
  }

  @FunctionalInterface
  interface PartsTransformer {
    Set<Part> transform(int x, int y, char symbol, Set<Part> parts);
  }

  @Test
  public void task1Test() {
    final var data1 = getInputData(DAY, "1a").toArray(new String[]{});
    final var actual1 = doTask1(data1);
    final var expected1 = "4361";
    assertEquals(expected1, actual1);

    final var data2 = getInputData(DAY, "1b").toArray(new String[]{});
    final var actual2 = doTask1(data2);
    final var expected2 = "507214";
    assertEquals(expected2, actual2);

  }

  @Test
  public void task2Test() {
    final var data1 = getInputData(DAY, "2a").toArray(new String[]{});
    final var actual1 = doTask2(data1);
    final var expected1 = "467835";
    assertEquals(expected1, actual1);

    final var data2 = getInputData(DAY, "2b").toArray(new String[]{});
    final var actual2 = doTask2(data2);
    final var expected2 = "72553319";
    assertEquals(expected2, actual2);

  }

  private String doTask1(String[] lines) {
    final Set<Part> parts = findParts(lines, (x, y, s, p) -> p);
    final var value = parts.stream().mapToInt(Part::num).sum();
    return String.valueOf(value);
  }

  private String doTask2(String lines[]) {
    final Set<Part> parts = findParts(lines, (x, y, s, p) -> {
      if (s == '*' && p.size() == 2) {
        final var ratio = p.stream().mapToInt(Part::num).reduce(1, (a,b) -> a*b);
        return Sets.newHashSet(new Part(x, y, ratio));
      } else {
        return Sets.newHashSet();
      }
    });
    final var value = parts.stream().mapToInt(Part::num).sum();
    return String.valueOf(value);
  }

  private Set<Part> findParts(String[] lines, PartsTransformer tramsformer) {
    final Set<Part> parts = Sets.newHashSet();

    for (int y = 0; y < lines.length; y++) {
      final var line = lines[y];
      for (int x = 0; x < line.length(); x++) {
        final var ch = line.charAt(x);
        final var isSymbol = !(ch == '.' || Character.isDigit(ch));

        if (isSymbol) {
          final Set<Part> subParts = Sets.newHashSet();

          findPart(x - 1, y - 1, lines, subParts);
          findPart(x, y - 1, lines, subParts);
          findPart(x + 1, y - 1, lines, subParts);

          findPart(x - 1, y, lines, subParts);
          findPart(x + 1, y, lines, subParts);

          findPart(x - 1, y + 1, lines, subParts);
          findPart(x, y + 1, lines, subParts);
          findPart(x + 1, y + 1, lines, subParts);

          parts.addAll(tramsformer.transform(x, y, ch, subParts));
        }
      }
    }

    return parts;
  }

  private void findPart(int x, int y, String[] lines, Set<Part> parts) {
    if (x < 0 || y < 0 || y >= lines.length) {
      return;
    }

    final var line = lines[y];
    if (x >= line.length()) {
      return;
    }

    final var ch = line.charAt(x);
    if (Character.isDigit(ch)) {
      int z1, z2;
      for(z1=x-1; z1>=0 && Character.isDigit(line.charAt(z1)); z1--) {}
      if (z1 < 0) {
        z1 = 0;
      }
      if (!Character.isDigit(line.charAt(z1))) {
        z1++;
      }

      for (z2 = x+1; z2 < line.length() && Character.isDigit(line.charAt(z2)); z2++) {}
      if (z2 == line.length()) {
        z2--;
      }
      if (!Character.isDigit(line.charAt(z2))) {
        z2--;
      }
      parts.add(new Part(z1, y, Integer.parseInt(line.substring(z1, z2+1))));
    }
  }
}
