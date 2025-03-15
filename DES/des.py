from Crypto.Cipher import DES
from Crypto.Util.Padding import pad
from Crypto.Util.Padding import unpad
import binascii

key = b'mysecret'

cipher = DES.new(key, DES.MODE_CBC)

plaintext = b'this is the original message'
cipher_text = cipher.encrypt(pad(plaintext, DES.block_size))
iv = cipher.iv

decrypt_cipher = DES.new(key, DES.MODE_CBC, iv)
original = decrypt_cipher.decrypt(cipher_text)
original = unpad(original, DES.block_size)

print(binascii.hexlify(cipher_text).decode())
print(original.decode())