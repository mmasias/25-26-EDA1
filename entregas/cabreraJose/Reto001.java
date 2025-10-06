package entregas.cabreraJose;

public class Reto001 {
  public static final int TOTAL_MINUTES = 120;
  public static final int MESSAGE_LENGTH = 10;
  public static final int MINIMUM_CHILDREN_TO_START = 6;

  public static void main(String[] args) {
    ArrivalProcess arrivalProcess = new ArrivalProcess();
    MessageMutator messageMutator = new MessageMutator();
    SimulationEngine simulationEngine = new SimulationEngine(
      TOTAL_MINUTES,
      MESSAGE_LENGTH,
      MINIMUM_CHILDREN_TO_START,
      arrivalProcess,
      messageMutator
    );
    simulationEngine.run();
  }
}

class SimulationEngine {
  private final int totalSimulationMinutes;
  private final int messageLength;
  private final int minimumChildrenToStart;
  private final ArrivalProcess arrivalProcess;
  private final MessageMutator messageMutator;

  private int currentMinute = 0;
  private int numberOfChildrenInQueue = 0;
  private int numberOfWaitingChildren = 0;

  private boolean gameInProgress = false;
  private int setupMinutesRemaining = 0;
  private int messagePassesRemaining = 0;
  private int finalWriteMinutesRemaining = 0;
  private int participantsInCurrentGame = 0;

  private char[] messageBuffer = new char[0];
  private String initialMessage = "";

  public SimulationEngine(
    int totalSimulationMinutes,
    int messageLength,
    int minimumChildrenToStart,
    ArrivalProcess arrivalProcess,
    MessageMutator messageMutator
  ) {
    this.totalSimulationMinutes = totalSimulationMinutes;
    this.messageLength = messageLength;
    this.minimumChildrenToStart = minimumChildrenToStart;
    this.arrivalProcess = arrivalProcess;
    this.messageMutator = messageMutator;
  }

  public void run() {
    while (currentMinute < totalSimulationMinutes) {
      int arrivalsThisMinute = arrivalProcess.getArrivalsAtMinute(currentMinute);
      if (gameInProgress) {
        numberOfWaitingChildren += arrivalsThisMinute;
      } else {
        numberOfChildrenInQueue += arrivalsThisMinute;
      }
      if (!gameInProgress && numberOfChildrenInQueue >= minimumChildrenToStart) {
        tryStartGame();
      }
      if (gameInProgress) {
        advanceOneMinuteInGame();
      } else {
        currentMinute++;
      }
    }
  }

  private void tryStartGame() {
    int plannedDuration = 1 + numberOfChildrenInQueue + 1;
    if (currentMinute + plannedDuration > totalSimulationMinutes) return;
    gameInProgress = true;
    setupMinutesRemaining = 1;
    messagePassesRemaining = numberOfChildrenInQueue;
    finalWriteMinutesRemaining = 1;
    participantsInCurrentGame = numberOfChildrenInQueue;
    messageBuffer = createRandomMessage(messageLength);
    initialMessage = new String(messageBuffer);
  }

  private void advanceOneMinuteInGame() {
    if (setupMinutesRemaining > 0) {
      setupMinutesRemaining--;
      currentMinute++;
      return;
    }
    if (messagePassesRemaining > 0) {
      messageMutator.mutateMessage(messageBuffer);
      messagePassesRemaining--;
      currentMinute++;
      return;
    }
    if (finalWriteMinutesRemaining > 0) {
      finalWriteMinutesRemaining--;
      currentMinute++;
      if (finalWriteMinutesRemaining == 0) {
        finishGame();
      }
    }
  }

  private void finishGame() {
    int gameDuration = 1 + participantsInCurrentGame + 1;
    int startMinute = currentMinute - gameDuration;
    GameSummary summary = new GameSummary(
      startMinute,
      participantsInCurrentGame,
      initialMessage,
      new String(messageBuffer),
      gameDuration
    );
    System.out.println(
      "Juego | inicio=" + summary.startMinute +
      " | participantes=" + summary.participants +
      " | duracion=" + summary.duration +
      " | original=" + summary.initialMessage +
      " | final=" + summary.finalMessage
    );
    gameInProgress = false;
    numberOfChildrenInQueue = numberOfWaitingChildren;
    numberOfWaitingChildren = 0;
  }

  private char[] createRandomMessage(int length) {
    char[] buffer = new char[length];
    for (int positionIndex = 0; positionIndex < length; positionIndex++) {
      buffer[positionIndex] = (char) ('A' + (int) (Math.random() * 26));
    }
    return buffer;
  }
}

class ArrivalProcess {
  public int getArrivalsAtMinute(int minuteIndex) {
    if (minuteIndex < 10) {
      return (int) (Math.random() * 3);
    }
    if (minuteIndex < 30) {
      boolean isArrivalMinute = ((minuteIndex - 10) % 3) == 0;
      boolean coinFlip = Math.random() < 0.5;
      return isArrivalMinute && coinFlip ? 1 : 0;
    }
    return 0;
  }
}

class MessageMutator {
  private static final double PROBABILITY_NO_CHANGE = 0.5;
  private static final double PROBABILITY_ONE_CHANGE_UPPER_BOUND = 0.85;

  public void mutateMessage(char[] messageBuffer) {
    double randomValue = Math.random();
    int numberOfChanges = computeNumberOfChanges(randomValue);
    if (numberOfChanges <= 0) return;
    if (numberOfChanges == 1) {
      int indexOne = (int) (Math.random() * messageBuffer.length);
      messageBuffer[indexOne] = differentRandomUppercaseLetter(messageBuffer[indexOne]);
      return;
    }
    int indexOne = (int) (Math.random() * messageBuffer.length);
    int indexTwo = (int) (Math.random() * (messageBuffer.length - 1));
    if (indexTwo >= indexOne) indexTwo++;
    messageBuffer[indexOne] = differentRandomUppercaseLetter(messageBuffer[indexOne]);
    messageBuffer[indexTwo] = differentRandomUppercaseLetter(messageBuffer[indexTwo]);
  }

  private int computeNumberOfChanges(double randomValue) {
    if (randomValue < PROBABILITY_NO_CHANGE) return 0;
    if (randomValue < PROBABILITY_ONE_CHANGE_UPPER_BOUND) return 1;
    return 2;
  }

  private char differentRandomUppercaseLetter(char currentLetter) {
    char candidate = currentLetter;
    while (candidate == currentLetter) {
      candidate = (char) ('A' + (int) (Math.random() * 26));
    }
    return candidate;
  }
}

class GameSummary {
  public final int startMinute;
  public final int participants;
  public final String initialMessage;
  public final String finalMessage;
  public final int duration;

  public GameSummary(int startMinute, int participants, String initialMessage, String finalMessage, int duration) {
    this.startMinute = startMinute;
    this.participants = participants;
    this.initialMessage = initialMessage;
    this.finalMessage = finalMessage;
    this.duration = duration;
  }
}
