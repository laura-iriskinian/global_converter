import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    // Main programme
    public void start() {
        displayWelcome();
        boolean continueProgram = true;

        while (continueProgram) {
            // Ask source format
            System.out.println("\nPlease select the format of your input: ");
            String sourceFormat = getValidFormat();

            // Get valid input to convert
            String conversionText = getValidInput(sourceFormat);

            // Ask if user wants to encrypt/decrypt their input
            String processedText = askForEncryption(conversionText);

            // Get target format
            System.out.println("\nPlease select in what format you wish to convert your input: ");
            String targetFormat = getValidFormat();

            // Create the conversion object and convert format
            Conversion converter = new Conversion(processedText, sourceFormat, targetFormat);

            // Display the converted format
            String result = converter.performConversion();
            System.out.println("Result: " + result);

            //Ask if user wants to continue
            continueProgram = askToContinue();
        }

        displayGoodbye();
        scanner.close();
    }

    // NEW METHOD: Ask if user wants to encrypt/decrypt
    private String askForEncryption(String text) {
        System.out.println("\nDo you want to encrypt or decrypt this input before conversion?");
        System.out.println("1. No encryption (proceed as normal)");
        System.out.println("2. Encrypt text");
        System.out.println("3. Decrypt text");

        int choice;
        boolean validChoice = false;

        do {
            System.out.print("Your choice (1, 2, or 3): ");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= 3) {
                    validChoice = true;
                } else {
                    System.out.println("Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (1, 2, or 3).");
                choice = 1; // Default to no encryption
            }
        } while (!validChoice);

        if (choice == 1) {
            return text; // No encryption
        } else {
            int shift = getValidShift();

            if (choice == 2) {
                // Encrypt
                String encrypted = Encryption.encrypt(text, shift);
                System.out.println("Encrypted text: " + encrypted);
                return encrypted;
            } else {
                // Decrypt
                String decrypted = Encryption.decrypt(text, shift);
                System.out.println("Decrypted text: " + decrypted);
                return decrypted;
            }
        }
    }

    // Get valid shift value
    private int getValidShift() {
        int shift;
        boolean validShift = false;

        do {
            System.out.print("\nEnter the shift value (1-25): ");
            try {
                shift = Integer.parseInt(scanner.nextLine().trim());
                if (shift >= 1 && shift <= 25) {
                    validShift = true;
                } else {
                    System.out.println("Please enter a number between 1 and 25.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                shift = 1; // Default value
            }
        } while (!validShift);

        return shift;
    }

    private void displayWelcome() {
        // Welcome menu
        System.out.println();
        System.out.println("Welcome to Global Converter!");
        System.out.println("____________________________");
        System.out.println();
    }

    private String getValidFormat() {
        System.out.println("""
                Hexadecimal (-h)
                Octal (-o)
                Decimal (-d)
                Binary (-b)
                Text (-t)""");
        String format;
        boolean validFormat = false;

        // Input validation loop
        do {
            System.out.print("Your choice: ");
            // Take user input, trim from spaces and remove case sensitivity
            format = scanner.nextLine().trim().toLowerCase();

            // Check the selection is appropriate
            if (format.equals("-h") || format.equals("hexadecimal") ||
                    format.equals("-o") || format.equals("octal") ||
                    format.equals("-d") || format.equals("decimal") ||
                    format.equals("-b") || format.equals("binary") ||
                    format.equals("-t") || format.equals("text")) {

                validFormat = true;
                String displayName = getFormatDisplayName(format);
                System.out.println("\nValid selection: " + format);

            } else {
                System.out.println("INVALID SELECTION! Please choose one of the following:");
                System.out.println("-h (Hexadecimal), -o (Octal), -d (Decimal), -b (Binary), -t (Text)");
                System.out.println("Or write the full name: hexadecimal, octal, decimal, binary, text");
            }
        } while (!validFormat);

        return format;
    }

    private String getValidInput(String format) {
        System.out.println("\nPlease type what you would like to convert");
        System.out.println("(No special characters, only alphanumeric characters): ");

        String conversionText;
        boolean validInput = false;
        do {
            conversionText = scanner.nextLine().trim();

            // Check if input is valid (letters, numbers and spaces only)
            if (conversionText.matches("[a-zA-Z0-9 ]+") && !conversionText.isEmpty()) {
                validInput = true;
                System.out.println("Valid input: \n" + conversionText);
            } else {
                System.out.println("Invalid input (unexpected format");
                System.out.println("Please enter only alphanumeric characters and spaces.");
                System.out.print("New input: ");
            }
        } while (!validInput);

        return conversionText;
    }

    private boolean isValidInputForFormat(String input, String format) {
        if (input.isEmpty()) return false;

        switch (format.toLowerCase()) {
            case "text":
            case "-t":
                // Letters, numbers and space
                return input.matches("[a-zA-Z0-9 ]+");

            case "hexadecimal":
            case "-h":
                // Check that all the elements are hex valid
                String[] hexValues = input.split("\\s+");
                for (String hex : hexValues) {
                    if (!hex.matches("[0-9A-Fa-f]+")) return false;
                }
                return true;

            case "octal":
            case "-o":
                // Check that all the elements are octal valid
                String[] octalValues = input.split("\\s+");
                for (String octal : octalValues) {
                    if (!octal.matches("[0-7]+")) return false;
                }
                return true;

            case "decimal":
            case "-d":
                // Check that all the elements are decimal valid
                String[] decimalValues = input.split("\\s+");
                for (String decimal : decimalValues) {
                    if (!decimal.matches("\\d+")) return false;
                }
                return true;

            case "binary":
            case "-b":
                // Check that all the elements are binary valid
                String[] binaryValues = input.split("\\s+");
                for (String binary : binaryValues) {
                    if (!binary.matches("[01]+")) return false;
                }
                return true;

            default:
                return false;
        }
    }

    private void displayFormatHelpMessage(String format) {
        String formatName = getFormatDisplayName(format);

        switch (format.toLowerCase()) {
            case "text":
            case "-t":
                System.out.println("   Example: Hello World");
                System.out.println("   Authorised characters: letters, numbers, spaces, NO PUNCTUATION");
                break;

            case "hexadecimal":
            case "-h":
                System.out.println("   Example: 48 65 6C 6C 6F (for 'Hello')");
                System.out.println("   Authorised characters: 0-9, A-F (separated by spaces)");
                break;

            case "octal":
            case "-o":
                System.out.println("   Example: 110 145 154 154 157 (for 'Hello')");
                System.out.println("   Authorised characters: 0-7 (separated by spaces)");
                break;

            case "decimal":
            case "-d":
                System.out.println("   Example: 72 101 108 108 111 (ASCII codes for 'Hello')");
                System.out.println("   Authorised characters: 0-9 (separated by spaces)");
                break;

            case "binary":
            case "-b":
                System.out.println("   Example: 1001000 1100101 1101100 1101100 1101111 (for 'Hello')");
                System.out.println("   Authorised characters: 0 et 1 (separated by spaces)");
                break;
        }
    }

    private String performConversion(String inputText, String sourceFormat, String targetFormat) {
        // Create Conversion object with source and target format
        Conversion converter = new Conversion(inputText, sourceFormat, targetFormat);

        // Perform conversion
        String result = converter.performConversion();

        return converter.performConversion();
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

    private boolean askToContinue() {
        System.out.print("\nWould you like to do another conversion? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }

    private void displayGoodbye() {
        // Goodbye sentence
        System.out.println();
        System.out.println("\nThanks for using Global Converter, HAVE A NICE DAY!");
    }
}