import java.util.Scanner;

public class railfenc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string: ");
        String s = scanner.nextLine();
        System.out.print("Enter key: ");
        int k = scanner.nextInt();

        char[][] enc = new char[k][s.length()];
        int flag = 0, row = 0;
        for (int i = 0; i < s.length(); i++) {
            enc[row][i] = s.charAt(i);
            if (row == 0) {
                flag = 0;
            } else if (row == k - 1) {
                flag = 1;
            }
            if (flag == 0) {
                row++;
            } else {
                row--;
            }
        }

        StringBuilder ct = new StringBuilder();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (enc[i][j] != ' ') {
                    ct.append(enc[i][j]);
                }
            }
        }
        String cipher = ct.toString();
        System.out.println("Cipher Text: " + cipher);

        decryptMsg(cipher, k);
    }

    public static void decryptMsg(String msg, int key) {
        int msgLen = msg.length(), i, j, k = -1, row = 0, col = 0, m = 0;
        char[][] railMatrix = new char[key][msgLen];
        for (i = 0; i < key; ++i) {
            for (j = 0; j < msgLen; ++j) {
                railMatrix[i][j] = '\n';
            }
        }
        for (i = 0; i < msgLen; ++i) {
            railMatrix[row][col++] = '*';
            if (row == 0 || row == key - 1)
                k = k * (-1);
            row = row + k;
        }
        for (i = 0; i < key; ++i) {
            for (j = 0; j < msgLen; ++j) {
                if (railMatrix[i][j] == '*') {
                    railMatrix[i][j] = msg.charAt(m++);
                }
            }
        }
        row = col = 0;
        k = -1;
        System.out.print("\nDecrypted Message: ");
        for (i = 0; i < msgLen; ++i) {
            System.out.print(railMatrix[row][col++]);
            if (row == 0 || row == key - 1)
                k = k * (-1);
            row = row + k;
        }
    }
}
