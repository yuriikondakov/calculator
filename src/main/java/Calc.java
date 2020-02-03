import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Calc {

    static Deque<Double> numbers = new LinkedList<>();
    static Deque<String> operations = new LinkedList<>();

    static Double getResult(List<String> input) {

        for (String item : input) {
            if (Character.isDigit(item.charAt(0))) {
                numbers.push(Double.parseDouble(item));
            } else {
                if (operations.isEmpty()) {
                    operations.push(item);
                } else {
                    Operation previousOperation = Operation.valueOfSign(operations.peek());
                    Operation currentOperation = Operation.valueOfSign(item);
                    if (previousOperation.priority < currentOperation.priority) {
                        operations.push(item);
                    } else {
                        while (previousOperation != null && previousOperation.priority >= currentOperation.priority) {
                            doCalculate(previousOperation);
                            previousOperation = Operation.valueOfSign(operations.peek());
                        }
                        operations.push(item);
                    }
                }
            }
        }
        while (!operations.isEmpty()) {
            doCalculate(Operation.valueOfSign(operations.peek()));
        }
        return numbers.pop();
    }

    private static void doCalculate(Operation operation) {
        Double arg1 = numbers.pop();
        Double arg2 = numbers.pop();
        switch (operation) {
            case ADD:
                numbers.push(arg2 + arg1);
                break;
            case DIVISION:
                numbers.push(arg2 - arg1);
                break;
            case MULTIPLY:
                numbers.push(arg2 * arg1);
                break;
            case SUBTRACTION:
                numbers.push(arg2 / arg1);
        }
        operations.pop();
    }
}
