package entregas.cabreraJose;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class reto001 {
  public static void main(String[] args) {
    long seed = args.length > 0 ? Long.parseLong(args[0]) : 42L;
    int totalMinutes = 120;
    int messageLength = 10;
    double pOneChange = 0.3;
    double pTwoChanges = 0.1;
    SimulationEngine engine = new SimulationEngine(totalMinutes, messageLength, seed, pOneChange, pTwoChanges);
    List<GameSummary> summaries = engine.run();
    for (int i = 0; i < summaries.size(); i++) {
      GameSummary s = summaries.get(i);
      System.out.println(
        "Juego " + (i + 1) +
        " | inicio=" + s.startMinute +
        " | participantes=" + s.participants +
        " | duracion=" + s.duration +
        " | original=" + s.initialMessage +
        " | final=" + s.finalMessage
      );
    }
  }

  static final class SimulationEngine {
    final int totalSimulationMinutes;
    final int messageLength;
    final Random random;
    final double probabilityOneChange;
    final double probabilityTwoChanges;

    int currentMinute = 0;
    int queueLength = 0;
    int waitingRoomCount = 0;
    boolean gameInProgress = false;

    int setupPhaseMinutesRemaining = 0;
    int messagePassesRemaining = 0;
    int finalWriteMinutesRemaining = 0;
    int participantsInCurrentGame = 0;

    String initialMessage = "";
    char[] messageBuffer = new char[0];

    final List<GameSummary> gameResults = new ArrayList<>();

    SimulationEngine(int totalSimulationMinutes, int messageLength, long seed, double p1, double p2) {
      this.totalSimulationMinutes = totalSimulationMinutes;
      this.messageLength = messageLength;
      this.random = new Random(seed);
      this.probabilityOneChange = p1;
      this.probabilityTwoChanges = p2;
    }

    List<GameSummary> run() {
      while (currentMinute < totalSimulationMinutes) {
        int arrivals = arrivalsForMinute(currentMinute);
        if (gameInProgress) waitingRoomCount += arrivals; else queueLength += arrivals;
        if (!gameInProgress && queueLength > 5) startGameIfFits();
        if (gameInProgress) tickGame(); else currentMinute++;
      }
      return gameResults;
    }

    int arrivalsForMinute(int minute) {
      if (minute < 10) return random.nextInt(3);
      if (minute < 30) return ((minute - 10) % 3 == 0) && random.nextBoolean() ? 1 : 0;
      return 0;
    }

    void startGameIfFits() {
      int duration = 1 + queueLength + 1;
      if (currentMinute + duration > totalSimulationMinutes) return;
      gameInProgress = true;
      setupPhaseMinutesRemaining = 1;
      messagePassesRemaining = queueLength;
      finalWriteMinutesRemaining = 1;
      participantsInCurrentGame = queueLength;
      initialMessage = randomMessage(messageLength);
      messageBuffer = initialMessage.toCharArray();
    }

    void tickGame() {
      if (setupPhaseMinutesRemaining > 0) {
        setupPhaseMinutesRemaining--;
        currentMinute++;
        return;
      }
      if (messagePassesRemaining > 0) {
        mutate(messageBuffer);
        messagePassesRemaining--;
        currentMinute++;
        return;
      }
      if (finalWriteMinutesRemaining > 0) {
        finalWriteMinutesRemaining--;
        currentMinute++;
        if (finalWriteMinutesRemaining == 0) finishGame();
      }
    }

    void finishGame() {
      int start = currentMinute - (1 + participantsInCurrentGame + 1);
      gameResults.add(new GameSummary(start, participantsInCurrentGame, initialMessage, new String(messageBuffer), 1 + participantsInCurrentGame + 1));
      gameInProgress = false;
      queueLength += waitingRoomCount;
      waitingRoomCount = 0;
    }

    String randomMessage(int len) {
      StringBuilder sb = new StringBuilder(len);
      for (int i = 0; i < len; i++) sb.append((char) ('A' + random.nextInt(26)));
      return sb.toString();
    }

    void mutate(char[] msg) {
      double r = random.nextDouble();
      int changes = r < probabilityOneChange ? 1 : (r < probabilityOneChange + probabilityTwoChanges ? 2 : 0);
      if (changes == 0) return;
      if (changes == 1) {
        int i = random.nextInt(msg.length);
        char c;
        do { c = (char) ('A' + random.nextInt(26)); } while (c == msg[i]);
        msg[i] = c;
        return;
      }
      int i = random.nextInt(msg.length);
      int j = random.nextInt(msg.length - 1);
      if (j >= i) j++;
      char c1, c2;
      do { c1 = (char) ('A' + random.nextInt(26)); } while (c1 == msg[i]);
      do { c2 = (char) ('A' + random.nextInt(26)); } while (c2 == msg[j]);
      msg[i] = c1;
      msg[j] = c2;
    }
  }

  static final class GameSummary {
    final int startMinute;
    final int participants;
    final String initialMessage;
    final String finalMessage;
    final int duration;
    GameSummary(int startMinute, int participants, String initialMessage, String finalMessage, int duration) {
      this.startMinute = startMinute;
      this.participants = participants;
      this.initialMessage = initialMessage;
      this.finalMessage = finalMessage;
      this.duration = duration;
    }
  }
}
