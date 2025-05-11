import random
from math import floor
from math import sqrt

# Range for generating random numbers
random_start = 100
random_end = 500

# Check if a number is prime
def is_prime(num):
    if num < 2:
        return False
    if num % 2 == 0:
        return False
    # Test divisibility up to square root of the number
    for i in range(3, floor(sqrt(num)) + 1, 2):
        if num % i == 0:
            return False
    return True

# Compute greatest common divisor (GCD) using Euclidean algorithm
def gdc(a, b):
    while b != 0:
        a, b = b, a % b
    return a

# Extended Euclidean Algorithm to find integers x, y such that ax + by = gcd(a, b)
def egcd(a, b):
    if b == 0:
        return a, 1, 0  # Base case
    gcd, x1, y1 = egcd(b, a % b)
    x = y1
    y = x1 - (a // b) * y1
    return gcd, x, y

# Generate a random large prime number within a range
def generate_large_prime(star=random_start, end=random_end):
    num = random.randint(star, end)
    while not is_prime(num):
        num = random.randint(star, end)
    return num  # Return the generated prime

'''
Primeiro passo:
    - Calulate n
    - n = p * q (p & q are primes)
Segundo passo:
    - Calculate phi (totiente de Euler)
    - Isso representa a quantidade de inteiros positivos menores que n que são coprimos com n
    - phi = (p-1)*(q-1)
    - ex: 3, 5 => phi = 2*4 = 8; Então 8 números menores que 15 (3*5) são coprimos com 15; φ(15)=8
Terceiro passo:
    - Escolhe um valor e
    - 1 < e < φ(n); gcd(e,φ(n))=1
    - O e precisa ser menor que φ(n) e maior que 1; O mds entre φ(n) e e precisa ser 1, ou seja, φ(n) e e precisam ser coprimos
Quarto passo: 
    - Calcular d, que é o inverso modular de e mod φ(n)
    - d*e ≡ 1 mod φ(n)
    - Utiliza o algoritmo de euclides extendido

m = mensagem
Private key = (d,n)
Encrypt = (m**d)%n

Public key = (e, n)
Decrypt = (m**e)%n
'''

# Generate RSA public and private key pair
def generate_rsa_keys():
    p = generate_large_prime()  # First prime
    q = generate_large_prime()  # Second prime
    n = p * q  # Modulus for both keys
    phi = (p - 1) * (q - 1)  # Euler's totient

    e = random.randrange(1, phi)  # Choose public exponent e
    while gdc(e, phi) != 1:  # Ensure e is coprime with phi
        e = random.randrange(1, phi)

    d = egcd(e, phi)[1] % phi  # Compute modular inverse of e (private key)
    return (d, n), (e, n)  # Return (private_key, public_key)


# Encrypt a plaintext message using the public key
def encrypt(public_key, plain_text):
    e, n = public_key
    cipher_text = []

    for char in plain_text:
        a = ord(char)  # Convert character to ASCII
        cipher_text.append((a ** e) % n)  # Encrypt using RSA: c = (m^e) mod n

    return cipher_text  # Return the full encrypted message list

# Decrypt a ciphertext message using the private key
def decrypt(private_key, cipher_text):
    d, n = private_key
    plain_text = ''

    for num in cipher_text:
        a = int((num ** d) % n)  # Decrypt using RSA: m = (c^d) mod n
        plain_text = plain_text + str(chr(a))  # Convert back to character and append

    return plain_text  # Return the decrypted string


if __name__ == '__main__':
    message = 'This is my secret message'
    private_key, public_key = generate_rsa_keys()

    message_encrypted = encrypt(public_key, message)
    original_message = decrypt(private_key, message_encrypted)

    print(message_encrypted)
    print(original_message)