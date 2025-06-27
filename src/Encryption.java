public class Encryption {

    // Caesar cipher encryption/decryption
    public static String caesarCipher(String text, int shift, boolean encrypt) {
        StringBuilder result = new StringBuilder();

        // If decrypting, use negative shift
        if (!encrypt) {
            shift = -shift;
        }

        // Normalize shift to be positive
        shift = ((shift % 26) + 26) % 26;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (Character.isLetter(currentChar)) {
                // Determine if uppercase or lowercase
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';

                // Apply shift
                int shiftedValue = (currentChar - base + shift) % 26;
                char encryptedChar = (char) (base + shiftedValue);

                result.append(encryptedChar);
            } else {
                // Keep non-alphabetic characters unchanged
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    // Encrypt text with given shift
    public static String encrypt(String text, int shift) {
        return caesarCipher(text, shift, true);
    }

    // Decrypt text with given shift
    public static String decrypt(String text, int shift) {
        return caesarCipher(text, shift, false);
    }
}