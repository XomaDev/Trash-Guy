import java.util.Scanner;

public class Main {

  private static final String TRASH = "\uD83D\uDDD1️";
  private static final String HAPPY_RIGHT = "(> ^_^)>";
  private static final String HAPPY_LEFT = "<(^_^ <)";

  public static void main(String[] args) {
    print_(false, TRASH, HAPPY_RIGHT, "     ");
    Scanner scanner = new Scanner(System.in);

    String input = scanner.next().trim();
    if (input.isEmpty()) return;

    StringBuilder text = new StringBuilder()
            .append(input);
    char[] texts = input.toCharArray();

    print("\n");

    for (char ch : texts) {
      for (int j = 0; j < 6; j++) {
        String left = spaces(j);
        String right = spaces(5 - j);

        print(TRASH, left, HAPPY_RIGHT, right, text, '\n');
      }
      text.deleteCharAt(0);
      for (int j = 0; j < 5; j++) {
        String left = spaces(j);
        String right = spaces(5 - j);

        print(TRASH, right, ch, HAPPY_LEFT, left, text, '\n');
      }
      print(TRASH, HAPPY_LEFT, spaces(6), text, '\n');
    }
    print_(false, "\n ~mlemon");
  }

  private static String spaces(int j) {
    StringBuilder spaces = new StringBuilder(j);
    for (int k = 0; k < j; k++)
      spaces.append(" ");
    return spaces.toString();
  }

  private static void print(Object... strings) {
    print_(true, strings);
  }

  private static void print_(boolean clear, Object... objects) {
    if (clear) for (int i = 0; i < 50; i++) System.out.println();
    for (Object object : objects)
      System.out.print(object);
    try {
      // just making it faster
      Thread.sleep(700);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}