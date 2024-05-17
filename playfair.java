import java.util.ArrayList;
import java.util.List;

public class playfair {
    public static void main(String[] args) {
        String textPlain = "instruments";
        textPlain = removeSpaces(toLowerCase(textPlain));
        List<String> plainTextList = diagraph(fillLetter(textPlain));
        if (plainTextList.get(plainTextList.size() - 1).length() != 2) {
            plainTextList.set(plainTextList.size() - 1, plainTextList.get(plainTextList.size() - 1) + 'z');
        }

        String key = "Monarchy";
        System.out.println("Key text: " + key);
        key = toLowerCase(key);
        char[][] matrix = generateKeyTable(key);

        System.out.println("Plain Text: " + textPlain);
        List<String> cipherList = encryptByPlayfairCipher(matrix, plainTextList);

        StringBuilder cipherText = new StringBuilder();
        for (String cipher : cipherList) {
            cipherText.append(cipher);
        }
        System.out.println("CipherText: " + cipherText);
    }

    public static String toLowerCase(String text) {
        return text.toLowerCase();
    }

    public static String removeSpaces(String text) {
        StringBuilder newText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c != ' ') {
                newText.append(c);
            }
        }
        return newText.toString();
    }

    public static List<String> diagraph(String text) {
        List<String> diagraph = new ArrayList<>();
        int group = 0;
        for (int i = 2; i < text.length(); i += 2) {
            diagraph.add(text.substring(group, i));
            group = i;
        }
        diagraph.add(text.substring(group));
        return diagraph;
    }

    public static String fillLetter(String text) {
        int k = text.length();
        if (k % 2 == 0) {
            for (int i = 0; i < k; i += 2) {
                if (text.charAt(i) == text.charAt(i + 1)) {
                    String newWord = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
                    return fillLetter(newWord);
                }
            }
        } else {
            for (int i = 0; i < k - 1; i += 2) {
                if (text.charAt(i) == text.charAt(i + 1)) {
                    String newWord = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
                    return fillLetter(newWord);
                }
            }
        }
        return text;
    }

    public static char[][] generateKeyTable(String word) {
        List<Character> keyLetters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            if (!keyLetters.contains(c)) {
                keyLetters.add(c);
            }
        }

        List<Character> compElements = new ArrayList<>(keyLetters);
        for (char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
            if (!compElements.contains(c)) {
                compElements.add(c);
            }
        }

        char[][] matrix = new char[5][5];
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = compElements.get(index++);
            }
        }
        return matrix;
    }

    public static List<String> encryptByPlayfairCipher(char[][] matrix, List<String> plainList) {
        List<String> cipherText = new ArrayList<>();
        for (String plain : plainList) {
            char c1, c2;
            int e1x = 0, e1y = 0, e2x = 0, e2y = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == plain.charAt(0)) {
                        e1x = i;
                        e1y = j;
                    }
                    if (matrix[i][j] == plain.charAt(1)) {
                        e2x = i;
                        e2y = j;
                    }
                }
            }

            if (e1x == e2x) {
                c1 = e1y == 4 ? matrix[e1x][0] : matrix[e1x][e1y + 1];
                c2 = e2y == 4 ? matrix[e2x][0] : matrix[e2x][e2y + 1];
            } else if (e1y == e2y) {
                c1 = e1x == 4 ? matrix[0][e1y] : matrix[e1x + 1][e1y];
                c2 = e2x == 4 ? matrix[0][e2y] : matrix[e2x + 1][e2y];
            } else {
                c1 = matrix[e1x][e2y];
                c2 = matrix[e2x][e1y];
            }

            cipherText.add("" + c1 + c2);
        }
        return cipherText;
    }
}
