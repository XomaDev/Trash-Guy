package xyz.kumaraswamy.trashguy;

public class TrashGuy {

  private static final String TRASH = "\uD83D\uDDD1️";
  private static final String HAPPY_RIGHT = "(> ^_^)>";
  private static final String HAPPY_LEFT = "<(^_^ <)";

  public interface OutputCallback {
    void print(String text, boolean shouldClearOutput);
  }

  public interface EndCallback {
    void onEnd();
  }

  private static final OutputCallback DEFAULT_OUTPUT_CALLBACK = (text, shouldClearOutput) -> {
    if (shouldClearOutput)
      for (int i = 0; i < 50; i++) System.out.println();
    System.out.println(text);
    try {
      // just making it faster
      Thread.sleep(700);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println();
  };

  public static void trash(String input) {
    trash(input, DEFAULT_OUTPUT_CALLBACK);
  }

  public static void trash(String input, OutputCallback outputCallback) {
    trash(input, outputCallback, null, true);
  }

  public static void trash(String input, OutputCallback outputCallback, EndCallback endCallback, boolean skipInputPrint) {
    if (!skipInputPrint) print_(outputCallback, false, TRASH, HAPPY_RIGHT, "     ");
    if (input.isEmpty()) return;

    StringBuilder text = new StringBuilder()
            .append(input);
    char[] texts = input.toCharArray();

    for (char ch : texts) {
      for (int j = 0; j < 6; j++) {
        String left = spaces(j);
        String right = spaces(5 - j);

        print(outputCallback, TRASH, left, HAPPY_RIGHT, right, text);
      }
      text.deleteCharAt(0);
      for (int j = 0; j < 5; j++) {
        String left = spaces(j);
        String right = spaces(5 - j);

        print(outputCallback, TRASH, right, ch, HAPPY_LEFT, left, text);
      }
      print(outputCallback, TRASH, HAPPY_LEFT, spaces(6), text);
    }
    if (endCallback != null) endCallback.onEnd();
  }

  private static String spaces(int j) {
    StringBuilder spaces = new StringBuilder(j);
    for (int k = 0; k < j; k++)
      spaces.append(" ");
    return spaces.toString();
  }

  private static void print(OutputCallback outputCallback, Object... strings) {
    print_(outputCallback, true, strings);
  }

  private static void print_(OutputCallback outputCallback, boolean clear, Object... objects) {
    StringBuilder text = new StringBuilder();
    for (Object object : objects)
      text.append(object);
    outputCallback.print(text.toString(), clear);
  }
}
