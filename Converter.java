public class Converter {
    public static String convert(String input, String base){
        // Transforme chaque caractère du texte en code ASCII
        // Convertit chaque code dans la base choisie
        // Concatène tous les résultats

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int ascii = (int) c;
            String converted ="";

            switch (base) {
                case "-b":
                case "binaire":
                    converted = BaseConverter.toBinary(ascii);
                    break;
                case "-o":
                case "octal":
                    converted = BaseConverter.toOctal(ascii);
                    break;
                case "-d":
                case "decimal":
                case "décimal":
                    converted = BaseConverter.toDecimal(ascii);
                    break;
                case "-h":
                case "hexadecimal":
                case "hexadécimal":
                    converted = BaseConverter.toHex(ascii);
                    break;
                default:
                    throw new IllegalArgumentException("Base non prise en charge : " + base);
            }
            result.append(converted).append(" ");// espace = séparateur
        }
        return result.toString().trim(); // supprime le dernier espace
    }

    public static String reverse (String encoded, String base){
        // Coupe la chaîne encodée en morceaux (selon la base)
        // Convertit chaque morceau en ACII
        // Reconstruit la chaîne originale

        String[] blocks = encoded.trim().split("\\s+"); // découpe par espace
        StringBuilder result = new StringBuilder();

        for (String block : blocks) {
            int ascii = 0;

            switch (base) {
                case "-b":
                case "binaire":
                    ascii = BaseConverter.fromBinary(block);
                    break;
                case "-o":
                case "octal":
                    ascii = BaseConverter.fromOctal(block);
                    break;
                case "-d":
                case "decimal":
                case "décimal":
                    ascii = BaseConverter.fromDecimal(block);
                    break;
                case "-h":
                case "hexadecimal":
                case "hexadécimal":
                    ascii = BaseConverter.fromHex(block);
                    break;
                default:
                    throw new IllegalArgumentException("Base non prise en charge : " + base);
            }
            result.append((char) ascii);
        }
        return result.toString();
    }
}
