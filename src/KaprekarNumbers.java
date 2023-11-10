import java.util.Arrays;

public class KaprekarNumbers {
    public void run() {
        System.out.println("Números de Kaprekar en el rango de 1 a 10000:");
        for (int i = 1; i <= 10000; i++) {
            if (isKaprekarNumber(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isKaprekarNumber(int number) {
        if (hasFourDistinctDigits(number)) {
            return isKaprekarNumber(number, 7);
        }
        return false;
    }

    public static boolean isKaprekarNumber(int number, int attempt) {
        if (number == 6174) {
            return true;
        } else if (attempt > 0) {
            int result = sortNumberDescending(number) - sortNumberAscending(number);
            return isKaprekarNumber(result, --attempt);
        } else {
            return false;
        }
    }

    public static int sortNumberAscending(int num) {
        int[] digits = getDigits(num);
        Arrays.sort(digits);
        return createNumber(digits);
    }

    public static int sortNumberDescending(int num) {
        int[] digits = getDigits(num);
        Arrays.sort(digits);
        int temp;
        for (int i = 0; i < digits.length / 2; i++) {
            temp = digits[i];
            digits[i] = digits[digits.length - i - 1];
            digits[digits.length - i - 1] = temp;
        }
        return createNumber(digits);
    }

    public static int[] getDigits(int num) {
        int[] digits = new int[4];
        for (int i = 0; i < 4; i++) {
            digits[i] = num % 10;
            num /= 10;
        }
        return digits;
    }

    public static int createNumber(int[] digits) {
        int result = 0;
        for (int digit : digits) {
            result = result * 10 + digit;
        }
        return result;
    }

    public static boolean hasFourDistinctDigits(int number) {
        if (number < 1000 || number > 9999) {
            // Verifica si el número no tiene 4 cifras.
            return false;
        }

        int[] digits = new int[4];
        int temp = number;

        for (int i = 0; i < 4; i++) {
            digits[i] = temp % 10;
            temp /= 10;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (digits[i] == digits[j]) {
                    // Verifica si hay cifras repetidas.
                    return false;
                }
            }
        }

        return true;  // Si pasa todas las comprobaciones, el número tiene 4 cifras distintas.
    }
}
