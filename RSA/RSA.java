package RSA;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    // Parameter 'e' (encryption)
    private BigInteger publicKey;
    // Parameter 'd' (decryption)
    private BigInteger privateKey;
    // n = p*q
    private BigInteger n;

    private SecureRandom random;

    public RSA(){
        this.random = new SecureRandom();
    }

    public void generateKeys(int keyDigits){
        BigInteger p = BigInteger.probablePrime(keyDigits, random);
        BigInteger q = BigInteger.probablePrime(keyDigits, random);
        
        BigInteger n = p.multiply(q);    

        BigInteger phi = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));

        BigInteger e = generatePublicFactor(phi);

        BigInteger d = e.modInverse(phi);

        this.privateKey = d;
        this.publicKey = e;
        this.n = n;
    }

    private BigInteger generatePublicFactor(BigInteger phi) {
        BigInteger e = new BigInteger(phi.bitLength(), random);

        while(!e.gcd(phi).equals(BigInteger.ONE)){
            e = new BigInteger(phi.bitLength(), random);
        }

        return e;
    }

    public BigInteger encryptMessage(String message){
        return encrypt(publicKey, n, message);
    }
        
    public String decryptMessage(BigInteger message){
        return decrypt(privateKey, n, message);
    }
        
    private BigInteger encrypt(BigInteger e, BigInteger n, String message) {
        byte[] messageByte = message.getBytes();
        BigInteger messageInt = new BigInteger(messageByte);

        // cipher text = message ˆ e mod n
        return messageInt.modPow(e, n);
    }

    private String decrypt(BigInteger d, BigInteger n, BigInteger cipherText) {
        // cipher ^ d mod n = plain text
        BigInteger messageInt = cipherText.modPow(d, n);

        return new String(messageInt.toByteArray());
    }

    public static void main(String[] args){
        String message = "this is my message";

        RSA rsa = new RSA();
        rsa.generateKeys(1024);

        BigInteger cipherText = rsa.encryptMessage(message);
        System.out.println(cipherText);

        String  originalMessage = rsa.decryptMessage(cipherText);
        System.out.println(originalMessage);
    }
}