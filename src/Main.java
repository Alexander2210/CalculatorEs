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

        //Определяем арифметическое действие:
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if (actionIndex == -1) {
            throw new IllegalArgumentException("Недопустимое арифметическое действие");
        }

        //Делим строчку по найденному арифметическому знаку
        String[] data = exp.split(regexActions[actionIndex]);

        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if (data.length > 2) {
            throw new IllegalArgumentException("Неверный формат арифметической операции");
        }

        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                //если римские, то конвертируем их в арабские

                a = Converter.romanToArabic(data[0]);
                b = Converter.romanToArabic(data[1]);

            } else {
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            //Определяем, находятся ли числа в диапазоне от 1 до 10
            if (a < 1 || a >= 10 || b < 1 || b >= 10) {
                throw new IllegalArgumentException("Ошибка. Нужно вводить значения от 1 до 10");
            }
            //выполняем с числами арифметическое действие
            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };
            //Возврат результата после сравнения систем исчисления
            if (isRoman) {
                //если числа были римские, возвращаем результат в римском числе
                return Converter.arabicToRoman(result);
            } else {
                //если числа были арабские, возвращаем результат в арабском числе
                return String.valueOf((result));

            }
        } else {
            throw new NumberFormatException("Числа должны быть в одном формате или Неверный формат арифметической операции");
        }
    }
}