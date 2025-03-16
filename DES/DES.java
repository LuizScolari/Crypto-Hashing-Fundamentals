package DES;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author luizscolari
 */
public class DES {
    private SecretKey secretKey;
    private SecureRandom random;
    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private IvParameterSpec ivParams;
    
    public DES(){
        try{
            secretKey = KeyGenerator.getInstance("DES").generateKey();
            random = new SecureRandom();
       
            encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            
            
            // vector (IV)
            byte[] initializationVector = new byte[encryptCipher.getBlockSize()];
            random.nextBytes(initializationVector);
            ivParams = new IvParameterSpec(initializationVector);
            
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
            
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String encrypt(String plainText){
    
        try {
            byte[] bytes = plainText.getBytes("UTF-8");
            byte[] cipherText = encryptCipher.doFinal(bytes);
            return Base64.getEncoder().encodeToString(cipherText);
            
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public String decrypt(String ciphertext){
        
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
            byte[] plaintext = decryptCipher.doFinal(decodedBytes);
            return new String(plaintext, "UTF-8");

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args){
        DES des = new DES();
        String text = "This is an example!";
        String cipherText = des.encrypt(text);
        System.out.println(cipherText);
        System.out.println(des.decrypt(cipherText));
    }
    
}