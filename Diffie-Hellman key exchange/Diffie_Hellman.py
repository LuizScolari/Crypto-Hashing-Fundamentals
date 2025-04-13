import random

def generate_private_key(n, g):
    # Alice private key
    a = random.randint(1,n)

    # Bob private key
    b = random.randint(1, n)

    # gˆa mod n, Alice's k1
    k1 = pow(g, a, n)

    # gˆb mod n, Bob's k2
    k2 = pow(g, b, n)

    # If an atacker try to get x and y (the private keys) => the discrete logarithm problem
    # It is exponentially slow process
    print("Alice's shared key: ", pow(k2, a, n))
    print("Bob's shared key: ", pow(k1, b, n))

if __name__ == '__main__':
    # Should be a huge prime number
    n = 37
    # Primitive root of n
    g = 13

    # Using Diffie-Hellman cryptosystem to generate the shared key (for DES and AES)
    generate_private_key(n, g)