public class Calculator {
    public static double calculate(double firstOperand, double secondOperand, String operator) {
        switch (operator) {
            case "addition":
                return firstOperand + secondOperand;

            case "subtraction":
                return firstOperand - secondOperand;

            case "multiplication":
                return firstOperand * secondOperand;

            case "division":
                if (secondOperand != 0)
                    return firstOperand / secondOperand;
                else
                    throw new ArithmeticException("Can't divided by zero!");

            default:
                throw new RuntimeException("Invalid Operator");
        }
    }
}
