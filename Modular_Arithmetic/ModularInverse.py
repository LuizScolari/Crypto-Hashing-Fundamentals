def modular_inverse(a, m):
    # Brute-force
    # a * a^-1 mod m == 1 
    for inv in range(0, m):
        if (a*inv)%m == 1:
            return inv
    print('There is no modular inverse')

if __name__ == '__main__':
    print(modular_inverse(9, 31))