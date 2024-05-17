import java.util.Scanner;

public class dsa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p, q, h, g, r, s, t, x, y, z, k, inv, hash;

        System.out.print("\nEnter prime number p and enter q prime divisor of (p-1): ");
        p = sc.nextInt();
        q = sc.nextInt();
        System.out.print("\nEnter h such that it greater than 1 and less than (p-1): ");
        h = sc.nextInt();

        // Compute g
        t = (p - 1) / q;
        g = power(h, t, p);

        System.out.print("\nEnter user's private key such that it is greater than 0 and less than q: ");
        x = sc.nextInt();

        // Compute user's public key
        y = power(g, x, p);

        System.out.print("\nEnter user's per-message secret key k such that it is greater than 0 and less than q: ");
        k = sc.nextInt();
        System.out.print("\nEnter the hash(M) value: ");
        hash = sc.nextInt();

        // Signing. Compute r and s pair
        z = power(g, k, p);
        r = z % q;
        inv = multiplicativeInverse(k, q, p);
        s = (inv * (hash + x * r)) % q;

        // Display
        System.out.println("\n****Computed Values****");
        System.out.println("g = " + g);
        System.out.println("y = " + y);
        System.out.println("Generated Signature Sender = (" + r + ", " + s + ")");
        
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