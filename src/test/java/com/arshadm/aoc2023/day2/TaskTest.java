package com.arshadm.aoc2023.day2;

import com.arshadm.aoc2023.TestBase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest extends TestBase {
  private final int DAY = 2;

  record Trial(int red, int green, int blue) {
    public boolean validTrial(int red, int green, int blue) {
      return this.red <= red && this.green <= green && this.blue <= blue;
    }

    public int power() {
      return red * green * blue;
    }
  }

  record Game(int num, List<Trial> trials) {
    public boolean validGame(int red, int green, int blue) {
      return trials.stream().allMatch(trial -> trial.validTrial(red, green, blue));
    }

    public Trial minimumNeeded() {
      final var red = trials.stream().mapToInt(trial -> trial.red).max().getAsInt();
      final var green = trials.stream().mapToInt(trial -> trial.green).max().getAsInt();
      final var blue = trials.stream().mapToInt(trial -> trial.blue).max().getAsInt();

      return new Trial(red, green, blue);
    }
  }

  @Test
  public void task1Test() {
    final var data1 = getInputData(DAY, "1a");
    final var actual1 = doTask1(data1);
    final var expected1 = "8";
    assertEquals(expected1, actual1);

    final var data2 = getInputData(DAY, "1b");
    final var actual2 = doTask1(data2);
    final var expected2 = "2505";
    assertEquals(expected2, actual2);

  }

  @Test
  public void task2Test() {
    final var data1 = getInputData(DAY, "2a");
    final var actual1 = doTask2(data1);
    final var expected1 = "2286";
    assertEquals(expected1, actual1);

    final var data2 = getInputData(DAY, "2b");
    final var actual2 = doTask2(data2);
    final var expected2 = "70265";
    assertEquals(expected2, actual2);

  }

  private String doTask1(Collection<String> lines) {
    final int red1 = 12, green1 = 13, blue1 = 14;

    final var games = lines.stream()
        .map(line -> parseGame(line))
        .collect(Collectors.toList());

    final var possibleGames = games.stream()
        .filter(game -> game.validGame(red1, green1, blue1))
        .collect(Collectors.toList());


    return possibleGames.stream()
        .map(game -> game.num)
        .reduce(0, (a, b) -> a + b)
        .toString();
  }

  private String doTask2(Collection<String> lines) {
    final var games = lines.stream()
        .map(line -> parseGame(line))
        .collect(Collectors.toList());

    final var power =  games.stream().map(Game::minimumNeeded).mapToInt(Trial::power).sum();

    return Integer.valueOf(power).toString();
  }

  @Test
  public void testParseGame() {
    final var game = parseGame("Game 31: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");

    final var expectedGame = new Game(31, List.of(
        new Trial(20, 8, 6),
        new Trial(4, 13, 5),
        new Trial(1, 5, 0)
    ));

    assertEquals(expectedGame, game);
  }

  private final Pattern GAME_PATTERN = Pattern.compile("Game (\\d+): (.*)$");
  private final Pattern TRIAL_PATTERN = Pattern.compile("([^;]+)+(?:; )?");
  private final Pattern INSTANCE_PATTERN = Pattern.compile("(\\d+) (red|green|blue)(?:, )?");

  private Game parseGame(String game) {
    final var gameMatcher = GAME_PATTERN.matcher(game);
    assertTrue(gameMatcher.matches());
    final var gameNumber = Integer.parseInt(gameMatcher.group(1));
    final var trials = new ArrayList<Trial>();

    final var trialMatcher = TRIAL_PATTERN.matcher(gameMatcher.group(2));
    while (trialMatcher.find()) {
      final var instanceMatcher = INSTANCE_PATTERN.matcher(trialMatcher.group(0));
      int red = 0, green = 0, blue = 0;

      while (instanceMatcher.find()) {
        final var count = Integer.parseInt(instanceMatcher.group(1));
        final var colour = instanceMatcher.group(2);
        switch (colour) {
          case "red":
            red = count;
            break;
          case "green":
            green = count;
            break;
          case "blue":
            blue = count;
            break;
          default:
            new RuntimeException("Invalid colour: " + colour);
        }
      }

      trials.add(new Trial(red, green, blue));
    }

    return new Game(gameNumber, trials);
  }

}
