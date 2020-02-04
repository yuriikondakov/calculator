import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final List<Character> operations = Arrays.asList('+', '-', '*', '/', '(', ')');

    public static void main(String[] args) {
        String line = getStringFromConsole();
        Deque<String> numbersAndOperations = parseStringToNumbersAndOperations(line);
        System.out.println(numbersAndOperations);
       // System.out.println(Calc.getResult(numbersAndOperations));
    }

    private static String getStringFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String line;
        do {
            System.out.println("Enter string that consist of digits, signs + - * / and parentheses");
            line = scanner.nextLine();
        } while (!isValid(line));
        return line;
    }

    private static boolean isValid(String line) {
        String regex = "^[\\d+/*.\\- ()]*$";
        boolean isValid = line.matches(regex);
        if (!isValid) {
            logger.debug("incorrect input string : " + line);
        }
        return isValid;
    }

    private static Deque<String> parseStringToNumbersAndOperations(String line) {
        Deque<String> numbersAndOperations = new LinkedList<>();

        for (char c : line.toCharArray()) {
            if (numbersAndOperations.isEmpty()) {
                numbersAndOperations.addLast(String.valueOf(c));
            } else if (isOperationSign(c)) {
                numbersAndOperations.addLast(String.valueOf(c));
            } else if (isDigitSign(c)) {
                boolean isPreviousDigit = isDigitSign(numbersAndOperations.peekLast().charAt(0));
                if (isPreviousDigit) {
                    numbersAndOperations.addLast(numbersAndOperations.pollLast() + c);
                } else numbersAndOperations.addLast(String.valueOf(c));
            }
        }
        return numbersAndOperations;
    }

    private static boolean isOperationSign(char c) {
        return operations.contains(c);
    }

    private static boolean isDigitSign(char c) {
        return !operations.contains(c);
    }

}
