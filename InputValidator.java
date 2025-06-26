import java.util.Scanner;

public class InputValidator {

    public static boolean isValid(String input){
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        return input.matches("^[a-zA-Z0-9]+$");
    }
    public static String askValidInput() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while(true) {
            System.out.print("Entrez votre chaine de caractère sans caractères spéciaux : ");
            input = scanner.nextLine();

            if (isValid(input)) {
                return input;
            } else {
                System.out.println("Erreur : la chaîne doit contenir uniquement des lettres et des chiffres");
            }
            scanner.close();
        }
    }

    public static String askValidBase() {
        Scanner scanner = new Scanner(System.in);
        String[] validBases = {"-b", "-h", "-d", "-o", "-t"};
        String input;

        while(true) {
            System.out.print("Choisissez une base (-b=binaire, -h=hexadécimal, -d=décimal, -o=octal, -t=texte) : ");
            input = scanner.nextLine().trim().toLowerCase();

            for (String base:  validBases) {
                if (input.equals(base)){
                    return input;
                }
            }

            System.out.println("Erreur : base invalide. Essayez avec -b, -h, -d, -o ou -t.");
        }
    }
}
