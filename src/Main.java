import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String result = calc(input);
        System.out.println(result);
    }

    public static String calc(String input) {
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            return "Неверный формат ввода";
        }

        String operand1 = tokens[0];
        String operator = tokens[1];
        String operand2 = tokens[2];

        int num1, num2;
        boolean isRoman = false;

        try {
            num1 = Integer.parseInt(operand1);
            num2 = Integer.parseInt(operand2);
        } catch (NumberFormatException e) {
            num1 = RomanNumeral.toDecimal(operand1);
            num2 = RomanNumeral.toDecimal(operand2);
            isRoman = true;
        }

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            return "Числа должны быть от 1 до 10";
        }
        int result;
        if (operator.equals("+")) {
            result = num1 + num2;
        } else if (operator.equals("-")) {
            result = num1 - num2;
        } else if (operator.equals("*")) {
            result = num1 * num2;
        } else if (operator.equals("/")) {
            result = num1 / num2;
        } else {
            return "Недопустимая операция: " + operator;
        }
        if (isRoman) {
            if (result <= 0) {
                return "Римские числа не могут быть меньше или равны нулю";
            }
            return RomanNumeral.toRoman(result);
        } else {
            return String.valueOf(result);
        }
    }
}
class RomanNumeral {
    private static final String[] romanSymbils = {"X", "IX", "V", "IV", "I"};
    private static final int[] romanValues = {10, 9, 5, 4, 1};

    public static int toDecimal(String roman) {
        int decimal = 0;
        int i = 0;

        for (int j = 0; j < romanSymbils.length; j++) {
            while (roman.startsWith(romanSymbils[j], i)) {
                decimal += romanValues[j];
                i += romanSymbils[j].length();
            }
        }

        return decimal;
    }

    public static String toRoman(int decimal) {
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < romanValues.length; i++) {
            while (decimal >= romanValues[i]) {
                roman.append(romanSymbils[i]);
                decimal -= romanValues[i];
            }
        }

        return roman.toString();
    }
}