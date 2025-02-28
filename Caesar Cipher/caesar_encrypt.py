ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
KEY = 3

def caesar_encrypt(text):
    text = text.upper()
    cipher_text = ""

    for l in text:
        if l != " ":
            index =  ALPHABET.find(l)
            index = (index+KEY) % len(ALPHABET)
            cipher_text = cipher_text + ALPHABET[index]
        else:
            cipher_text = cipher_text + " " 
    return cipher_text

print(caesar_encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ"))