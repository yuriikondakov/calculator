import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        do {
            line = scanner.nextLine();
        } while (!isValid(line));

        System.out.println(Calc.getResult(parseDigitsToNumbers(line)));
    }

    static boolean isValid(String input) {
        String regex = "^[\\d\\+\\/\\*\\.\\- \\(\\)]*$";
        boolean isValid = input.matches(regex);
        if (!isValid) {
            logger.debug("incorrect input line :" + input);
        }
        return isValid;
    }

    static List<String> parseDigitsToNumbers(String input) {
        List<String> numbersAndOperations = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (Character.isDigit(current) && (i < input.length() - 1)) {
                int beginIndex = i;
                int countDigits = 1;
                while (Character.isDigit(input.charAt(i + 1))) {
                    i++;
                    countDigits++;
                }
                numbersAndOperations.add(input.substring(beginIndex, beginIndex + countDigits));
                continue;
            }
            numbersAndOperations.add(String.valueOf(current));
        }
        return numbersAndOperations;
    }
}
