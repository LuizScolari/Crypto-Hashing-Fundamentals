from random import randint

ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

def random_sequence(text):
    random = []

    for l in text:
        if l != " ":
            random.append(randint(0,len(ALPHABET)-1))

    return random

def encrypt(text, random):
    text = text.upper()
    cipher_text = ""

    i = 0
    for l in text:
        if l != " ":    
            index = ALPHABET.find(l)
            index = (index + random[i]) % len(ALPHABET)
            i += 1
            cipher_text = cipher_text + ALPHABET[index]
        else:
            cipher_text = cipher_text + " "

    return(cipher_text)

def decrypt(text, random):
    text = text.upper()
    decrypt_text = ""

    i = 0
    for l in text:
        if l != " ":    
            index = ALPHABET.find(l)
            index = (index - random[i]) % len(ALPHABET)
            i += 1
            decrypt_text = decrypt_text + ALPHABET[index]
        else:
            decrypt_text = decrypt_text + " "
    
    return decrypt_text

text = "HELLO WORLD"
print("Original Text: ", text)

random = random_sequence(text)
print("Pseudo-Random sequence: ", random)

cipher_text = encrypt(text, random)
print("Cipher Text: ", cipher_text)

decrypt_text = decrypt(cipher_text, random)
print("Decrypt Text: ", decrypt_text)