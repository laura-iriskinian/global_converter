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
            System.out.println("Please select the original format you want to convert: ");
            String sourceFormat = getValidFormat();

            // Get valid input to convert
            String conversionText = getValidInput(sourceFormat);

            // Get target format
            System.out.println("Please select in what format you wish to convert your input: ");
            String targetFormat = getValidFormat();

            // Create the conversion object and convert format
            Conversion converter = new Conversion(conversionText, sourceFormat, targetFormat);

            // Display the converted format
            String result = converter.performConversion();
            System.out.println("Result: " + result);

            //Ask if user wants to continue
            continueProgram = askToContinue();
        }

        displayGoodbye();
        scanner.close();
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
        String formatName = getFormatDisplayName(format);
        System.out.println("Please type what you would like to convert in format " + formatName);
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
                System.out.println("Invalid input (not expected " + formatName + " format");
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
                System.out.println("   Exemple: Hello World");
                System.out.println("   Caractères autorisés: lettres, chiffres, espaces, ponctuation de base");
                break;

            case "hexadecimal":
            case "-h":
                System.out.println("   Exemple: 48 65 6C 6C 6F (pour 'Hello')");
                System.out.println("   Caractères autorisés: 0-9, A-F (séparés par des espaces)");
                break;

            case "octal":
            case "-o":
                System.out.println("   Exemple: 110 145 154 154 157 (pour 'Hello')");
                System.out.println("   Caractères autorisés: 0-7 (séparés par des espaces)");
                break;

            case "decimal":
            case "-d":
                System.out.println("   Exemple: 72 101 108 108 111 (codes ASCII pour 'Hello')");
                System.out.println("   Caractères autorisés: 0-9 (séparés par des espaces)");
                break;

            case "binary":
            case "-b":
                System.out.println("   Exemple: 1001000 1100101 1101100 1101100 1101111 (pour 'Hello')");
                System.out.println("   Caractères autorisés: 0 et 1 (séparés par des espaces)");
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


