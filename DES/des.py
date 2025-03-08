from Crypto.Cipher import DES
from Crypto.Util.Padding import pad
from Crypto.Util.Padding import unpad
from Crypto.Random import get_random_bytes

key = b'mysecret'

cipher = DES.new(key, DES.MODE_CBC)

plaintext = b'this is the original message'
cipher_text = cipher.encrypt(pad(plaintext, DES.block_size))
print(cipher_text)