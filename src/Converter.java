import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Converter {
    boolean isRoman(String datum) {

        return RomanNumeral.isRoman(datum);
    }

    enum RomanNumeral {
        I(1), II(2), III(3), IV(4), V(5),
        VI(6), VII(7), VIII(8), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        final int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }

        static List <RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }

        static boolean isRoman(String key) {
            RomanNumeral[] romanNumeralArr = values();

            boolean found = false;
            for (RomanNumeral romanNumeral : romanNumeralArr) {
                if (romanNumeral.toString().equals(key)) {
                    found = true;
                    break;
                }
            }
            return found;
        }
    }

    static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List <RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " не может быть конвертирован");
        }

        return result;
    }

    static String arabicToRoman(int number) {
        if ((number <= 1) || (number >= 3999)) {
            throw new IllegalArgumentException(number + " не входит в диапазон представления римских чисел от 1 до 3999");
        }

        List <RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}