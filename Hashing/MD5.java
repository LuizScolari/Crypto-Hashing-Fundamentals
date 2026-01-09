package Hashing;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
    public String Digest(String message){
        MessageDigest messageDigest = null;
        byte[] messageDigestByte = null;
        String hexaHash = "";

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigestByte = messageDigest.digest(message.getBytes());

            hexaHash = new BigInteger(1, messageDigestByte).toString(16);

            while (hexaHash.length()<32) {
                hexaHash = "0" + hexaHash;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hexaHash;
    }

    public static void main(String[] args){
        MD5 md5 = new MD5();

        String message = "this is my message";
        String hash = md5.Digest(message);
        System.out.println(hash);
    }
}
