import java.util.Scanner;

public class CaesarCipher {
    public static void decrypt(String text, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                if (Character.isLowerCase(ch)) {
                    char c = (char) ((ch - 'a' - key + 26) % 26 + 'a');
                    decryptedText.append(c);
                } else if (Character.isUpperCase(ch)) {
                    char c = (char) ((ch - 'A' - key + 26) % 26 + 'A');
                    decryptedText.append(c);
                } else if (Character.isDigit(ch)) {
                    char c = (char) ((ch - '0' - key + 10) % 10 + '0');
                    decryptedText.append(c);
                }
            } else {
                System.out.println("Invalid Message");
                return;
            }
        }
        System.out.println("Decrypted message: " + decryptedText);
    }

    public static void encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                if (Character.isLowerCase(ch)) {
                    char c = (char) ((ch - 'a' + key) % 26 + 'a');
                    encryptedText.append(c);
                } else if (Character.isUpperCase(ch)) {
                    char c = (char) ((ch - 'A' + key) % 26 + 'A');
                    encryptedText.append(c);
                } else if (Character.isDigit(ch)) {
                    char c = (char) ((ch - '0' + key) % 10 + '0');
                    encryptedText.append(c);
                }
            } else {
                System.out.println("Invalid Message");
                return;
            }
        }
        System.out.println("Encrypted message: " + encryptedText);
        decrypt(encryptedText.toString(), key);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the msg : ");
        String text = scanner.nextLine();
        System.out.print("Enter the Key : ");
        int key = scanner.nextInt();
        encrypt(text, key);
    }
}
