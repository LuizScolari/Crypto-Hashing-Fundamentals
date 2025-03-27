from Crypto.Cipher import AES
from Crypto.Util.Padding import pad
from Crypto.Util.Padding import unpad
from Crypto.Random import get_random_bytes

def encrypt(key, plaintext):
    cipher = AES.new(key, AES.MODE_CBC)
    iv = cipher.iv

    ciphertext = cipher.encrypt(pad(plaintext, AES.block_size))
    return ciphertext, iv

def decrypt(key, ciphertext, iv):
    new_cipher = AES.new(key, AES.MODE_CBC, iv)

    decrypt = unpad(new_cipher.decrypt(ciphertext), AES.block_size)
    return decrypt

key = get_random_bytes(16)

cipher_text, iv = encrypt(key, b'this is my secret message!')
print(cipher_text)

original_text = decrypt(key, cipher_text, iv)
print(original_text)