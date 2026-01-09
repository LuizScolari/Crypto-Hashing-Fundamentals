package ECDSA;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class CryptographyHelper {
    public static KeyPair generateKeys(){
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");

            ECGenParameterSpec params = new ECGenParameterSpec("secp256r1");

            SecureRandom random = new SecureRandom();

            keyPairGenerator.initialize(params, random);

            return keyPairGenerator.generateKeyPair();

        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] sign(PrivateKey privateKey, String message){
        Signature signature;

        try {
            signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(privateKey);
            signature.update(message.getBytes());
            return signature.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean verify(PublicKey publicKey, String message, byte[] signature){
        try {
            Signature algorithm = Signature.getInstance("SHA256withECDSA");
            algorithm.initVerify(publicKey);
            algorithm.update(message.getBytes());
            return algorithm.verify(signature);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
