package ECDSA;

import java.security.KeyPair;

public class App {
    public static void main(String[] args){
        KeyPair keys = CryptographyHelper.generateKeys();

        byte[] signature = CryptographyHelper.sign(keys.getPrivate(), "this is my message");
        System.out.println(signature);
        System.out.println(CryptographyHelper.verify(keys.getPublic(), "this is my message", signature));
    }
}
