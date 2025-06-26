
public class Conversion {
    public String conversionText;
    public String sourceFormat;
    public String targetFormat;


    public Conversion(String conversionText, String sourceFormat, String targetFormat) {
        this.conversionText = conversionText;
        this.sourceFormat = sourceFormat;
        this.targetFormat = targetFormat;
    }

    // Conversion from source format to ASCII

    public String textToASCII() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.conversionText.length(); i++) {
            char currentChar = this.conversionText.charAt(i);

            // Converts direct into ASCII
            int asciiCode = (int) currentChar;
            result.append(asciiCode);

            // Add a space between the codes except the last one
            if (i < this.conversionText.length() - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public String hexadecimalToASCII(String hexInput) {
        StringBuilder result = new StringBuilder();
        String[] hexValues = hexInput.trim().split("\\s+");

        for (int i = 0; i < hexValues.length; i++) {
            try {
                int asciiValue = hexToDecimal(hexValues[i]);
                result.append(asciiValue);

                if (i < hexValues.length - 1) {
                    result.append(" ");
                }
            } catch (Exception e) {
                result.append("?");
                if (i < hexValues.length - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    public String octalToASCII(String octalInput) {
        StringBuilder result = new StringBuilder();
        String[] octalValues = octalInput.trim().split("\\s+");

        for (int i = 0; i < octalValues.length; i++) {
            try {
                int asciiValue = octalToDecimal(octalValues[i]);
                result.append(asciiValue);

                if (i < octalValues.length - 1) {
                    result.append(" ");
                }
            } catch (Exception e) {
                result.append("?");
                if (i < octalValues.length - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    public String decimalToASCII(String decimalInput) {
        // ASCII is already decimal
        return decimalInput.trim();
    }

    public String binaryToASCII(String binaryInput) {
        StringBuilder result = new StringBuilder();
        String[] binaryValues = binaryInput.trim().split("\\s+");

        for (int i = 0; i < binaryValues.length; i++) {
            try {
                int asciiValue = binaryToDecimal(binaryValues[i]);
                result.append(asciiValue);

                if (i < binaryValues.length - 1) {
                    result.append(" ");
                }
            } catch (Exception e) {
                result.append("?");
                if (i < binaryValues.length - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    // Conversion from ASCII to target

    public String ASCIIToText(String asciiCodes) {
        StringBuilder result = new StringBuilder();
        String[] codes = asciiCodes.trim().split("\\s+");

        for (String code : codes) {
            try {
                int asciiValue = stringToInt(code);
                if (asciiValue >= 0 && asciiValue <= 127) { // valid ASCII
                    result.append((char) asciiValue);
                }
            } catch (NumberFormatException e) {
                result.append("?"); // result if unknown character
            }
        }

        return result.toString();
    }

    public String ASCIIToHexadecimal(String asciiCodes) {
        StringBuilder result = new StringBuilder();
        String[] codes = asciiCodes.trim().split("\\s+");

        for (int i = 0; i < codes.length; i++) {
            try {
                int asciiValue = stringToInt(codes[i]);
                String hex = decimalToHex(asciiValue);
                result.append(hex.toUpperCase());

                if (i < codes.length - 1) {
                    result.append(" ");
                }
            } catch (NumberFormatException e) {
                result.append("?");
                if (i < codes.length - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    public String ASCIIToOctal(String asciiCodes) {
        StringBuilder result = new StringBuilder();
        String[] codes = asciiCodes.trim().split("\\s+");

        for (int i = 0; i < codes.length; i++) {
            try {
                int asciiValue = stringToInt(codes[i]);
                String octal = decimalToOctal(asciiValue);
                result.append(octal);

                if (i < codes.length - 1) {
                    result.append(" ");
                }
            } catch (NumberFormatException e) {
                result.append("?");
                if (i < codes.length - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    public String ASCIIToDecimal(String asciiCodes) {
        // ASCII is decimal
        return asciiCodes;
    }

    public String ASCIIToBinary(String asciiCodes) {
        StringBuilder result = new StringBuilder();
        String[] codes = asciiCodes.trim().split("\\s+");

        for (int i = 0; i < codes.length; i++) {
            try {
                int asciiValue = stringToInt(codes[i]);
                String binary = decimalToBinary(asciiValue);
                result.append(binary);

                if (i < codes.length - 1) {
                    result.append(" ");
                }
            } catch (NumberFormatException e) {
                result.append("?");
                if (i < codes.length - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    // Perform conversion

    public String performConversion() {
        // SourceFormat -> ASCII
        String asciiCodes = convertSourceToASCII();

        // ASCII → targetFormat
        return convertASCIIToTarget(asciiCodes);
    }

    private String convertSourceToASCII() {
        switch (this.sourceFormat.toLowerCase()) {
            case "text":
            case "-t":
                return textToASCII();
            case "hexadecimal":
            case "-h":
                return hexadecimalToASCII(this.conversionText);
            case "octal":
            case "-o":
                return octalToASCII(this.conversionText);
            case "decimal":
            case "-d":
                return decimalToASCII(this.conversionText);
            case "binary":
            case "-b":
                return binaryToASCII(this.conversionText);
            default:
                return "Erreur: Format source inconnu";
        }
    }

    private String convertASCIIToTarget(String asciiCodes) {
        switch (this.targetFormat.toLowerCase()) {
            case "text":
            case "-t":
                return ASCIIToText(asciiCodes);
            case "hexadecimal":
            case "-h":
                return ASCIIToHexadecimal(asciiCodes);
            case "octal":
            case "-o":
                return ASCIIToOctal(asciiCodes);
            case "decimal":
            case "-d":
                return ASCIIToDecimal(asciiCodes);
            case "binary":
            case "-b":
                return ASCIIToBinary(asciiCodes);
            default:
                return "Erreur: Format cible inconnu";
        }
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

    private String getFormatDisplayName(String format) {
        switch (format.toLowerCase()) {
            case "text":
            case "-t":
                return "text";
            case "hexadecimal":
            case "-h":
                return "hexadecimal";
            case "octal":
            case "-o":
                return "octal";
            case "decimal":
            case "-d":
                return "decimal";
            case "binary":
            case "-b":
                return "binary";
            default:
                return format;
        }
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