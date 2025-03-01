public class CaesarCipher{

    private String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public String encrypt(String text, int KEY){
        String cipher_text = "";
        text = text.toUpperCase();

        for(int i = 0; i < text.length(); i++){
            String character = String.valueOf(text.charAt(i));
            int index = ALPHABET.indexOf(character);
            
            if (character.equals(" ")){ 
                cipher_text = cipher_text + " ";
            } else {
                index = (index + KEY) % 26;
                cipher_text = cipher_text + String.valueOf(ALPHABET.charAt(index));
            }
        };
        return cipher_text;
    }
    
    public String decrypt(String text, int KEY){
        String decrypt_text = "";
        text = text.toUpperCase();

        for(int i = 0; i < text.length(); i++){
            String character = String.valueOf(text.charAt(i));
            int index = ALPHABET.indexOf(character);
            
            if (character.equals(" ")){ 
                decrypt_text = decrypt_text + " ";
            } else {
                index = (index - KEY) % 26;
                decrypt_text = decrypt_text + String.valueOf(ALPHABET.charAt(index));
            }
        };
        return decrypt_text;
    }
}

/*
EXAMPLE OF TEST
public class main {
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();
        String encrypted = cipher.encrypt("HELLO WORLD", 3);
        System.out.println(encrypted); // "KHOOR ZRUOG"
        String decrypt = cipher.decrypt("KHOOR ZRUOG", 3);
        System.out.println(decrypt); // "HELLO WORLD"
    }
}
 */