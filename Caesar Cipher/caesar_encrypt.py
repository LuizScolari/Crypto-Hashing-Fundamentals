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

def caesar_decrypt(text):
    text = text.upper()
    decrypt_text = ""

    for l in text:
        if l != " ":
            index =  ALPHABET.find(l)
            index = (index-KEY) % len(ALPHABET)
            decrypt_text = decrypt_text + ALPHABET[index]
        else:
            decrypt_text = decrypt_text + " " 
    return decrypt_text


ciphered = caesar_encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
print(f"Encrypted: {ciphered}")

decrypt = caesar_decrypt(ciphered)
print(f"Decrypted: {decrypt}")