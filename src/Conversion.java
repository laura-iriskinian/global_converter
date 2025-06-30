public class Conversion {
    public String conversionText;
    public String sourceFormat;
    public String targetFormat;

    public Conversion(String conversionText, String sourceFormat, String targetFormat) {
        this.conversionText = conversionText;
        this.sourceFormat = sourceFormat;
        this.targetFormat = targetFormat;
    }

    // Main conversion method
    public String performConversion() {
        // Direct conversion : source → target
        String text = convertToText();
        return convertFromText(text);
    }

    // Converts source format to text
    private String convertToText() {
        switch (this.sourceFormat.toLowerCase()) {
            case "text":
            case "-t":
                return this.conversionText;
            case "hexadecimal":
            case "-h":
                return hexToText(this.conversionText);
            case "octal":
            case "-o":
                return octalToText(this.conversionText);
            case "decimal":
            case "-d":
                return decimalToText(this.conversionText);
            case "binary":
            case "-b":
                return binaryToText(this.conversionText);
            default:
                return "Erreur: Format source inconnu";
        }
    }

    // Converts text to target format
    private String convertFromText(String text) {
        switch (this.targetFormat.toLowerCase()) {
            case "text":
            case "-t":
                return text;
            case "hexadecimal":
            case "-h":
                return textToHex(text);
            case "octal":
            case "-o":
                return textToOctal(text);
            case "decimal":
            case "-d":
                return textToDecimal(text);
            case "binary":
            case "-b":
                return textToBinary(text);
            default:
                return "Erreur: Format cible inconnu";
        }
    }

    // ========== CONVERSION TO TEXT ==========

    private String hexToText(String hexInput) {
        StringBuilder result = new StringBuilder();
        String[] hexValues = hexInput.trim().split("\\s+");

        for (String hex : hexValues) {
            try {
                int asciiValue = hexToDecimal(hex);
                if (asciiValue >= 0 && asciiValue <= 127) {
                    result.append((char) asciiValue);
                }
            } catch (Exception e) {
                result.append("?");
            }
        }
        return result.toString();
    }

    private String octalToText(String octalInput) {
        StringBuilder result = new StringBuilder();
        String[] octalValues = octalInput.trim().split("\\s+");

        for (String octal : octalValues) {
            try {
                int asciiValue = octalToDecimal(octal);
                if (asciiValue >= 0 && asciiValue <= 127) {
                    result.append((char) asciiValue);
                }
            } catch (Exception e) {
                result.append("?");
            }
        }
        return result.toString();
    }

    private String decimalToText(String decimalInput) {
        StringBuilder result = new StringBuilder();
        String[] decimalValues = decimalInput.trim().split("\\s+");

        for (String decimal : decimalValues) {
            try {
                int asciiValue = stringToInt(decimal);
                if (asciiValue >= 0 && asciiValue <= 127) {
                    result.append((char) asciiValue);
                }
            } catch (Exception e) {
                result.append("?");
            }
        }
        return result.toString();
    }

    private String binaryToText(String binaryInput) {
        StringBuilder result = new StringBuilder();
        String[] binaryValues = binaryInput.trim().split("\\s+");

        for (String binary : binaryValues) {
            try {
                int asciiValue = binaryToDecimal(binary);
                if (asciiValue >= 0 && asciiValue <= 127) {
                    result.append((char) asciiValue);
                }
            } catch (Exception e) {
                result.append("?");
            }
        }
        return result.toString();
    }

    // ========== CONVERSION FROM TEXT ==========

    private String textToHex(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int asciiValue = (int) text.charAt(i);
            result.append(decimalToHex(asciiValue).toUpperCase());
            if (i < text.length() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    private String textToOctal(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int asciiValue = (int) text.charAt(i);
            result.append(decimalToOctal(asciiValue));
            if (i < text.length() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    private String textToDecimal(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int asciiValue = (int) text.charAt(i);
            result.append(asciiValue);
            if (i < text.length() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    private String textToBinary(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int asciiValue = (int) text.charAt(i);
            result.append(decimalToBinary(asciiValue));
            if (i < text.length() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    // ========== UTILITARY METHODS ==========

    private String decimalToHex(int decimal) {
        if (decimal == 0) return "0";

        StringBuilder hex = new StringBuilder();
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        while (decimal > 0) {
            hex.insert(0, hexChars[decimal % 16]);
            decimal /= 16;
        }
        return hex.toString();
    }

    private String decimalToOctal(int decimal) {
        if (decimal == 0) return "0";

        StringBuilder octal = new StringBuilder();
        while (decimal > 0) {
            octal.insert(0, decimal % 8);
            decimal /= 8;
        }
        return octal.toString();
    }

    private String decimalToBinary(int decimal) {
        if (decimal == 0) return "0";

        StringBuilder binary = new StringBuilder();
        while (decimal > 0) {
            binary.insert(0, decimal % 2);
            decimal /= 2;
        }
        return binary.toString();
    }

    private int hexToDecimal(String hex) {
        int decimal = 0;
        int power = 0;

        for (int i = hex.length() - 1; i >= 0; i--) {
            char c = Character.toUpperCase(hex.charAt(i));
            int digit;

            if (c >= '0' && c <= '9') {
                digit = c - '0';
            } else if (c >= 'A' && c <= 'F') {
                digit = c - 'A' + 10;
            } else {
                throw new IllegalArgumentException("Invalid Hex character: " + c);
            }

            decimal += digit * power(16, power);
            power++;
        }
        return decimal;
    }

    private int octalToDecimal(String octal) {
        int decimal = 0;
        int power = 0;

        for (int i = octal.length() - 1; i >= 0; i--) {
            int digit = octal.charAt(i) - '0';
            if (digit < 0 || digit > 7) {
                throw new IllegalArgumentException("Invalid octal code: " + digit);
            }
            decimal += digit * power(8, power);
            power++;
        }
        return decimal;
    }

    private int binaryToDecimal(String binary) {
        int decimal = 0;
        int power = 0;

        for (int i = binary.length() - 1; i >= 0; i--) {
            char bit = binary.charAt(i);
            if (bit == '1') {
                decimal += power(2, power);
            } else if (bit != '0') {
                throw new IllegalArgumentException("Invalid bit: " + bit);
            }
            power++;
        }
        return decimal;
    }

    private int power(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }

    private int stringToInt(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }

        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
            result = result * 10 + (c - '0');
        }
        return result;
    }
}