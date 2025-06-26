import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== GLOBAL CONVERTER ===");

        // 1. Choix du mode
        System.out.print("tapez 'c' pour convertir ou 'r' pour reconvertir : ");
        String mode = scanner.nextLine().trim().toLowerCase();

        // 2. choix de la base
        String base = InputValidator.askValidBase();

        String result;

        if (mode.equals("c")) {
            //3. Saisie de texte à convertir
            String input = InputValidator.askValidInput();

            //4. Conversion
            result = Converter.convert(input, base);
            System.out.println("Résultat converti : " + result);

        } else if (mode.equals("r")) {
            //3. Saisie de la chaîne encodée
            System.out.print("Entrez la chaîne encodée à reconvertir (ex: '4F 4B) : ");
            String encoded = scanner.nextLine();

            //4. Reconversion
            result = Converter.convert(encoded, base);
            System.out.println("texte original : " + result);

        }else {
            System.out.println("Mode invalide. Veuillez relancer le programme");
        }
    }
}
