import java.util.Scanner;

public class rsa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p, q, n, e, d, phi, M, C;

        System.out.print("\nEnter two prime numbers p and q that are not equal: ");
        p = sc.nextInt();
        q = sc.nextInt();
        n = p * q;
        phi = (p - 1) * (q - 1);
        System.out.println("Phi(" + n + ") = " + phi);

        System.out.print("\nEnter the integer e: ");
        e = sc.nextInt();

        if (e >= 1 && e < phi) {
            if (gcd(phi, e) != 1) {
                System.out.println("\nChoose proper value for e !!!");
                return;
            }
        }

        // Key Generation
        d = multiplicativeInverse(e, phi, n);
        System.out.println("\nPublic Key PU = {" + e + "," + n + "}");
        System.out.println("\nPrivate Key PR = {" + d + "," + n + "}");

        // Encryption
        System.out.print("\nMessage M = ");
        M = sc.nextInt();
        C = power(M, e, n);
        System.out.println("\nCiphertext C = " + C);

        // Decryption
        M = power(C, d, n);
        System.out.println("\nDecrypted Message M = " + M);

        sc.close();
    }

    public static int power(int x, int y, int p) {
        int res = 1;
        x = x % p;
        while (y > 0) {
            if ((y & 1) == 1)
                res = (res * x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    public static int gcd(int a, int b) {
        int c;
        while (a != 0) {
            c = a;
            a = b % a;
            b = c;
        }
        return b;
    }

    public static int multiplicativeInverse(int a, int b, int n) {
        int sum, x, y;

        for (y = 0; y < n; y++) {
            for (x = 0; x < n; x++) {
                sum = a * x + b * (-y);
                if (sum == 1)
                    return x;
            }
        }
        return -1; // Return -1 if no multiplicative inverse is found
    }
}