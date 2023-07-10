import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Input: ");
        String input = scn.nextLine();
        String resultOut = calc(input);
        System.out.println("Output: ");
        System.out.println(resultOut);
    }

    public static String calc(String input) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        String exp;
        exp = input;

        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }

        if (actionIndex == -1) {
            throw new IllegalArgumentException("Недопустимое арифметическое действие");
        }

        String[] data = exp.split(regexActions[actionIndex]);

        if (data.length > 2) {
            throw new IllegalArgumentException("Неверный формат арифметической операции");
        }

        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;

            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                a = Converter.romanToArabic(data[0]);
                b = Converter.romanToArabic(data[1]);

            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new IllegalArgumentException("Ошибка. Нужно вводить значения от 1 до 10");
            }

            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };

            if (isRoman) {

                return Converter.arabicToRoman(result);
            } else {

                return String.valueOf((result));

            }
        } else {
            throw new NumberFormatException("Числа должны быть в одном формате или Неверный формат арифметической операции");
        }
    }
}