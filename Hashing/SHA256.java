package Hashing;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA256 {
    public String Digest(String message){
        MessageDigest messageDigest = null;
        byte[] messageDigestByte = null;
        String hexaHash = "";

        try {
            messageDigest = MessageDigest.getInstance("SHA256");
            messageDigestByte = messageDigest.digest(message.getBytes());

            hexaHash = new BigInteger(1, messageDigestByte).toString(16);

            while (hexaHash.length()<64) {
                hexaHash = "0" + hexaHash;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hexaHash;
    }

    public static void main(String[] args){
        SHA256 sha256 = new SHA256();

        String message = "this is my message";
        String hash = sha256.Digest(message);
        System.out.println(hash);
    }
}
