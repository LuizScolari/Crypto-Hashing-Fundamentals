import java.security.SecureRandom;
import java.math.BigInteger;

public class Diffie_Hellman{
    private SecureRandom a, b;
    private BigInteger k1, k2, sharedKeyAlice, sharedKeyBob;

    public Diffie_Hellman(int n, int g){
        BigInteger G = BigInteger.valueOf(g);
        BigInteger N = BigInteger.valueOf(n);

        // Alice private key
        a = new SecureRandom();

        // Bob private key
        b = new SecureRandom();

        int numBits = 128;
        BigInteger exponential_a = new BigInteger(numBits, a);
        BigInteger exponential_b = new BigInteger(numBits, b);


        // gˆa mod n, Alice's k1
        k1 = G.modPow(exponential_a, N);

        // gˆb mod n, Alice's k1
        k2 = G.modPow(exponential_b, N);

        sharedKeyAlice = k2.modPow(exponential_a, N); // (g^b)^a mod n
        sharedKeyBob = k1.modPow(exponential_b, N);   // (g^a)^b mod n
    }

    public BigInteger getSharedKeyAlice() {
        return sharedKeyAlice;
    }

    public BigInteger getSharedKeyBob() {
        return sharedKeyBob;
    }


    public static void main(String[] args) {
        int n = 37; // prime number
        int g = 13;   // primitive root of n

        Diffie_Hellman dh = new Diffie_Hellman(n, g);

        System.out.printf("Shared key (Alice): %s\n", dh.getSharedKeyAlice());
        System.out.printf("Shared key (Bob)  : %s\n", dh.getSharedKeyBob());
    }
}