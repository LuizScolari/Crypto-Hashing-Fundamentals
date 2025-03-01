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
}