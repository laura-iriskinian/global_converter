public class BaseConverter {
    //méthode ver binaire
    //méthode vers octal
    //méthode vers hexadecimal
    //méthode vers décimal
    //méthode inverse de chaque

    public static String toBinary(int value) {
        if (value == 0) return "0";

        StringBuilder binary = new StringBuilder();

        while (value > 0) {
            int remainder = value % 2;          //reste = 0 ou 1
            binary.insert(0, remainder); //on ajoute au début
            value = value / 2;                 //division entière
        }
        return binary.toString();
    }

    public static String toOctal(int value) {
        if (value == 0) return "0";

        StringBuilder octal = new StringBuilder();

        while (value > 0) {
            octal.insert(0, value % 8);
            value = value / 8;
        }

        return octal.toString();
    }

    public static String toDecimal(int value) {
        return String.valueOf(value);
    }

    public static String toHex(int value) {
        if (value == 0) return "0";

        StringBuilder hex = new StringBuilder();
        char[] hexDigits = "0123456789ABCDEF".toCharArray();

        while (value > 0) {
            int remainder = value % 16;
            hex.insert(0, hexDigits[remainder]);
            value = value / 16;
        }
        return hex.toString();
    }

    // CONVERSIONS : String -> int

    public static int fromBinary(String binary) {
        int result = 0;
        for (int i = 0; i < binary.length(); i++) {
            char c = binary.charAt(i);
            if (c != '0' && c != '1') throw new IllegalArgumentException("Ivalid binary digit");
            result = result * 2 + (c - '0');
        }
        return result;
    }
    public static int fromOctal(String octal) {
        int result = 0;
        for (int i = 0; i < octal.length(); i++) {
            char c = octal.charAt(i);
            if (c < '0' || c > '7') throw new IllegalArgumentException("Invalid octal digit");
            result = result * 8 + (c - '0');
        }
        return result;
    }

    public static int fromDecimal(String decimal) {
        return Integer.parseInt(decimal);
    }

    public static int fromHex(String hex) {
        int result = 0;
        hex = hex.toUpperCase();
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            int value;
            if (c >= '0' && c <= '9') {
                value = c - '0';
            } else if (c >= 'A' && c <= 'F') {
                value = 10 + (c - 'A');
            } else {
                throw new IllegalArgumentException("Invalid hex digit");
            }
            result = result * 16 + value;
        }
        return result;
    }
}
